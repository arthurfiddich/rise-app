package com.data.generator.geo.names.api.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.constants.GeoNameConstants;
import com.data.generator.constants.KeyBoardConstants;
import com.data.generator.exceptions.BaseUncheckedException;
import com.data.generator.file.Reader;
import com.data.generator.file.impl.UserNameFileReader;
import com.data.generator.geo.names.api.Parse;
import com.data.generator.helper.TenantConfigHelper;
import com.data.generator.util.Precondition;
import com.data.generator.util.PropertiesHelper;
import com.generator.data.xmlns.geo.names.api.v1.GeoName;
import com.generator.data.xmlns.geo.names.api.v1.GeoNames;
import com.generator.data.xmlns.geo.names.api.v1.Status;

import edu.uci.ics.crawler4j.parser.HtmlParseData;

public class GeoLocationBuilder {

	private static Logger logger = LoggerFactory
			.getLogger(GeoLocationBuilder.class);

	private WebCrawlerGeoLocation webCrawlerGeoLocation;
	private Parse<GeoNames> parse = new GeoNamesParser();
	private PropertiesHelper propertiesHelper;
	private Reader<String, Map<String, Boolean>> reader = new UserNameFileReader();
	private Map<String, Boolean> userNamesVsUsedMap;

	public GeoLocationBuilder() {
		this.webCrawlerGeoLocation = new WebCrawlerGeoLocation();
		this.propertiesHelper = TenantConfigHelper.getInstance()
				.getPropertiesHelper();
		this.userNamesVsUsedMap = this.reader.read("usernames.txt");
	}

	public GeoNames buildGeoNamesList(String argUrl) {
		Precondition.ensureNotEmpty(argUrl, "Geo URL");
		HtmlParseData htmlParseData = this.webCrawlerGeoLocation
				.processUrl(argUrl);
		Precondition.ensureNotNull(htmlParseData, "HtmlParseData");
		String xmlContent = htmlParseData.getHtml();
		Precondition.ensureNotEmpty(xmlContent, "Content");
		String packageName = com.generator.data.xmlns.geo.names.api.v1.GeoNames.class
				.getPackage().getName();
		GeoNames geoNames = null;
		InputStream inputStream = null;
		try {
			inputStream = new ByteArrayInputStream(xmlContent.getBytes(Charset
					.forName("UTF-8")));
			geoNames = this.parse.unmarshal(new GeoNames(), inputStream,
					GeoNameConstants.GEO_NAME_NAMESPACE, packageName);
		} catch (Exception e) {
			throw new BaseUncheckedException(
					"Exception while unmarshalling the token: ", e);
		} finally {
			if (Precondition.checkNull(inputStream)) {
				try {
					inputStream.close();
				} catch (Exception ignroe) {
					// ignore
				}
			}
		}
		if (Precondition.checkNotNull(geoNames)) {
			return geoNames;
		}
		return null;
	}

	private String constructFileName(String argGeonameId,
			String argToponymName, String argParentDirectory) {
		Precondition.ensureNotEmpty(argGeonameId, "GeoNameID");
		Precondition.ensureNotEmpty(argToponymName, "Toponym Name");
		String toponymName = argToponymName;
		toponymName = StringUtils.replace(toponymName, KeyBoardConstants.SPACE,
				KeyBoardConstants.HYPHEN);
		StringBuilder fileNameBuilder = new StringBuilder();
		fileNameBuilder.append(argParentDirectory);
		fileNameBuilder.append(KeyBoardConstants.FORWARD_SLASH);
		fileNameBuilder.append(toponymName);
		return fileNameBuilder.toString();
	}

	public static void main(String[] args) {
		TenantConfigHelper.getInstance();
		GeoLocationBuilder geoLocationBuilder = new GeoLocationBuilder();
		String userName = geoLocationBuilder
				.getUserName(geoLocationBuilder.userNamesVsUsedMap);
		String continentPath = "./output/continent";
		geoLocationBuilder.execute(GeoNameConstants.EARTH_GEO_NAME_ID,
				continentPath, userName);
	}

	private String getUserName(Map<String, Boolean> argUserNamesVsUsedMap) {
		Precondition.ensureNotNull(argUserNamesVsUsedMap, "UserNamesVsUsedMap");
		Iterator<Entry<String, Boolean>> iterator = argUserNamesVsUsedMap
				.entrySet().iterator();
		boolean foundUserName = false;
		while (iterator.hasNext()) {
			Entry<String, Boolean> entry = iterator.next();
			if (!entry.getValue().booleanValue()) {
				entry.setValue(new Boolean(true));
				foundUserName = true;
				return entry.getKey();
			}
		}

		if (!foundUserName && argUserNamesVsUsedMap.size() > 0) {
			Iterator<Entry<String, Boolean>> iteratorRepeat = argUserNamesVsUsedMap
					.entrySet().iterator();
			while (iteratorRepeat.hasNext()) {
				Entry<String, Boolean> entry = iteratorRepeat.next();
				entry.setValue(new Boolean(false));
			}
			return getUserName(argUserNamesVsUsedMap);
		}
		return null;
	}

	public void execute(String argGeoNameId, String argPath, String argUserName) {
		String userName = argUserName;
		String url = this.propertiesHelper.constructGeoNameUrl(argGeoNameId,
				userName);
		GeoNames geoNames = buildGeoNamesList(url);
		List<GeoName> geoNamesList = geoNames.getGeoname();
		Status status = geoNames.getStatus();
		if (checkValid(status)) {
			userName = getUserName(userNamesVsUsedMap);
			System.out
					.println("##############################################################");
			System.out.println(userName);
			System.out
					.println("##############################################################");
			url = this.propertiesHelper.constructGeoNameUrl(argGeoNameId,
					userName);
			GeoNames geoNamesWithNewUser = buildGeoNamesList(url);
			geoNamesList = geoNamesWithNewUser.getGeoname();
		}
		if (Precondition.checkNotEmpty(geoNamesList)) {
			for (GeoName geoName : geoNamesList) {
				createFolder(geoName, argPath);
				String path = constructFileName(geoName.getGeonameId(),
						geoName.getToponymName(), argPath);
				execute(geoName.getGeonameId(), path, userName);
			}
		}
	}

	protected boolean checkValid(Status status) {
		return (Precondition.checkNotNull(status) && (status.getValue()
				.intValue() == 18 || status.getValue().intValue() == 19 || status
				.getValue().intValue() == 20));
	}

	protected void createFolders(GeoLocationBuilder argGeoLocationBuilder,
			List<GeoName> argContinentGeoNamesList, String argContinentPath) {
		for (GeoName geoName : argContinentGeoNamesList) {
			createFolder(geoName, argContinentPath);
		}
	}

	protected void createFolder(GeoName argGeoName, String argContinentPath) {
		String geonameId = argGeoName.getGeonameId();
		String toponymName = argGeoName.getToponymName();
		String fileName = constructFileName(geonameId, toponymName,
				argContinentPath);
		boolean found = isFileExists(fileName);
		if (found) {
			String uuid = UUID.randomUUID().toString();
			int index = uuid.indexOf(KeyBoardConstants.HYPHEN);
			String uuidPart = StringUtils.substring(uuid, 0, index);
			fileName = new StringBuilder().append(fileName)
					.append(KeyBoardConstants.DOUBLE_HYPHEN).append(uuidPart)
					.toString();
		}
		File file = new File(fileName);
		if (file.mkdir()) {
			try {
				System.out.println(file.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isFileExists(String argFileName) {
		if (Precondition.checkNotEmpty(argFileName)) {
			File file = new File(argFileName);
			return file.exists();
		}
		return false;
	}
}
