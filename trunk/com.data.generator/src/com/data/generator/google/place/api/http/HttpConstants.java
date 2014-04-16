package com.data.generator.google.place.api.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Class HttpConstants.
 */
public class HttpConstants {

	/** The Constant ESCAPE_ESCAPE. */
	public static final String ESCAPE_ESCAPE = "\\\\";

	/** The Constant ESCAPE. */
	public static final String ESCAPE = "\\";

	/** The Constant ESCAPED_DOLLAR. */
	public static final String ESCAPED_DOLLAR = "\\$";

	/** The Constant DOLLAR. */
	public static final String DOLLAR = "$";

	public static final String ORIGIN = "Origin";

	public static final String X_AUTO_LOGIN = "X-Auto-Login";

	/** The Constant IF_NONE_MATCH. */
	private static final String IF_NONE_MATCH = "If-None-Match";

	/** The Constant IF_MODIFIED_SINCE. */
	private static final String IF_MODIFIED_SINCE = "If-Modified-Since";

	/** The Constant SEMI_COLON. */
	public static final String SEMI_COLON = ";";

	/** The Constant QMARK. */
	public static final String QMARK = "?";

	/** The Constant HTTP. */
	public static final String HTTP = "http";

	/** The Constant CONTENT_TRANSFER_ENCODING. */
	public static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";

	/** The Constant HTTPS. */
	public static final String HTTPS = "https";

	/** The Constant HTTP_PROTOCOL_PREFIX. */
	public static final String HTTP_PROTOCOL_PREFIX = "http://";

	/** The Constant HTTPS_PROTOCOL_PREFIX. */
	public static final String HTTPS_PROTOCOL_PREFIX = "https://";

	/** The Constant POST. */
	public static final String POST = "POST";

	/** The Constant GET. */
	public static final String GET = "GET";

	/** The Constant HEAD. */
	public static final String HEAD = "HEAD";

	/** The Constant CONNECT. */
	public static final String CONNECT = "connect";

	/** The Constant PUT. */
	public static final String PUT = "put";

	/** The Constant DELETE. */
	public static final String DELETE = "delete";

	/** The Constant OPTIONS. */
	public static final String OPTIONS = "options";

	/** The Constant PATCH. */
	public static final String PATCH = "patch";

	/** The Constant TRACE. */
	public static final String TRACE = "trace";

	/** The Constant ACCEPT. */
	public static final String ACCEPT = "Accept";

	/** The Constant ACCEPT_CHARSET. */
	public static final String ACCEPT_CHARSET = "Accept-Charset";

	/** The Constant ACCEPT_LANGUAGE. */
	public static final String ACCEPT_LANGUAGE = "Accept-Language";

	/** The Constant ACCEPT_ENCODING. */
	public static final String ACCEPT_ENCODING = "Accept-Encoding";

	/** The Constant GZIP. */
	public static final String GZIP = "gzip";

	/** The Constant DEFLATE. */
	public static final String DEFLATE = "deflate";

	/** The Constant USER_AGENT. */
	public static final String USER_AGENT = "User-Agent";

	/** The Constant USER_AGENT_CHROME. */
	public static final String USER_AGENT_CHROME = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.7 (KHTML, like Gecko) Chrome/7.0.517.44 Safari/534.7";

	/** The Constant USER_AGENT_FIREFOX. */
	public static final String USER_AGENT_FIREFOX = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-GB; rv:1.9.1.11) Gecko/20100701 Firefox/3.5.11 GTBDFff GTB7.0 (.NET CLR 3.5.30729) GTBA";

	/** The Constant USER_AGENT_DEFAULT. */
	public static final String USER_AGENT_DEFAULT = USER_AGENT_FIREFOX;

	/** The SalesforceProxy-Endpoint. */
	public static final String SALESFORCEPROXY_ENDPOINT = "SalesforceProxy-Endpoint";

	/** The Constant COOKIE. */
	public static final String COOKIE = "Cookie";

	/** The Constant SET_COOKIE2. */
	public static final String SET_COOKIE2 = "Set-Cookie2";

	/** The Constant SET_COOKIE. */
	public static final String SET_COOKIE = "Set-Cookie";

	/** The Constant SET_COOKIE1. */
	public static final String SET_COOKIE1 = "Set-cookie";

	/** The Constant SET_COOKIE1. */
	public static final String SET_COOKIE3 = "set-cookie";

	/** The Constant COOKIE_HEADERS. */
	private static final List<String> COOKIE_HEADERS = Arrays
			.asList(new String[] { SET_COOKIE, SET_COOKIE2, COOKIE, SET_COOKIE1, SET_COOKIE3 });

	/** The Constant CONTENT_LENGTH. */
	public static final String CONTENT_LENGTH = "Content-Length";

	/** The Constant CONTENT_DISPOSITION. */
	public static final String CONTENT_DISPOSITION = "Content-Disposition";

	/** The Constant CONTENT_ENCODING. */
	public static final String CONTENT_ENCODING = "Content-Encoding";

	/** The Constant CONTENT_TYPE. */
	public static final String CONTENT_TYPE = "Content-Type";
	/**
	 * The constant type text/javascript for gmail content type.
	 */
	public static final String TEXT_JAVASCRIPT = "text/javascript";
	/** The Constant TEXT_HTML. */
	public static final String TEXT_HTML = "text/html";

	/** The Constant TEXT_PLAIN. */
	public static final String TEXT_PLAIN = "text/plain";

	/** The Constant APPLICATION_TXT. */
	public static final String APPLICATION_TXT = "application/txt";

	/** The Constant APPLICATION__CSV. */
	public static final String APPLICATION_CSV = "application/csv";

	/** The Constant TEXT_CSV. */
	public static final String TEXT_CSV = "text/csv";

	/** The Constant TEXT_XML. */
	public static final String TEXT_XML = "text/xml";

	/** The Constant APPLICATION_XML. */
	public static final String APPLICATION_XML = "application/xml";

	/** The Constant TEXT_CSS. */
	public static final String TEXT_XML_AJAX_RESPONSE = "text/xml_Ajax-Response";

	/** The Constant TEXT_CSS. */
	public static final String TEXT_CSS = "text/css";

	/** The Constant AJAX_RESPONSE. */
	protected static final String AJAX_RESPONSE = "Ajax-Response";

	/** The Constant APPLICAION_JSON. */
	public static final String APPLICATION_JSON = "application/json";

	/** The Constant APPLICATION_X_JAVASCRIPT. */
	public static final String APPLICATION_X_JAVASCRIPT = "application/x-javascript";

	/** The Constant MULTIPART_FORM_DATA. */
	public static final String MULTIPART_FORM_DATA = "multipart/form-data";

	/** The Constant FORM_URL_ENCODED. */
	public static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
	// Added Constant For Ms_Excel
	/** The Constant APPLICAION_MS_EXCEL. */
	public static final String APPLICATION_MS_EXCEL = "application/vnd.ms-excel";

	/** The Constant APPLICATION_PDF. */
	public static final String APPLICATION_PDF = "application/pdf";

	/** The Constant XLSX_MIME_TYPE. */
	public static final String XLSX_MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/** The Constant DOCX_MIME_TYPE. */
	public static final String DOCX_MIME_TYPE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

	/** The Constant MBOX_MIME_TYPE. */
	public static final String MBOX_MIME_TYPE = "application/mbox";

	/** The Constant IMAGE_GIF. */
	public static final String IMAGE_GIF = "image/gif";

	/** The Constant IMAGE_JPEG. */
	public static final String IMAGE_JPEG = "image/jpeg";

	/** The Constant IMAGE_PNG. */
	public static final String IMAGE_PNG = "image/png";

	/** The Constant HOST. */
	public static final String HOST = "Host";

	/** The Constant LOCATION. */
	public static final String LOCATION = "Location";

	/** The Constant LOCATION_LOWER_CASE. */
	public static final String LOCATION_LOWER_CASE = LOCATION.toLowerCase();

	/** The Constant REFERER. */
	public static final String REFERER = "Referer";

	/** The Constant CACHE_CONTROL. */
	public static final String CACHE_CONTROL = "Cache-Control";

	/** The Constant CONNECTION. */
	public static final String CONNECTION = "Connection";

	/** The Constant KEEP_ALIVE. */
	public static final String KEEP_ALIVE = "Keep-Alive";

	/** The Constant HTTPS_VALUE Default value */
	public static final Integer HTTPS_VALUE = 443;

	/** The Constant HTTP_VALUE Default value */
	public static final Integer HTTP_VALUE = 80;

	/** The Constant ALLOWED_REQUEST_HEADERS. */
	public static final List<String> ALLOWED_REQUEST_HEADERS = Arrays.asList(new String[] { HOST, USER_AGENT, ACCEPT, ACCEPT_LANGUAGE,
			ACCEPT_ENCODING, ACCEPT_CHARSET, KEEP_ALIVE, CONNECTION, CACHE_CONTROL, REFERER, CONTENT_TYPE, CONTENT_LENGTH });

	/** The Constant ALLOWED_RESPONSE_HEADERS. */
	public static final List<String> ALLOWED_RESPONSE_HEADERS = Arrays.asList(new String[] { LOCATION, HOST, USER_AGENT, ACCEPT,
			ACCEPT_LANGUAGE, ACCEPT_ENCODING, ACCEPT_CHARSET, KEEP_ALIVE, CONNECTION, CACHE_CONTROL, REFERER, CONTENT_TYPE, CONTENT_LENGTH,
			CONTENT_DISPOSITION });

	/** The Constant ALLOWED_REQUEST_COOKIES. */
	public static final List<String> ALLOWED_REQUEST_COOKIES = COOKIE_HEADERS;

	/** The Constant ALLOWED_RESPONSE_COOKIES. */
	public static final List<String> ALLOWED_RESPONSE_COOKIES = COOKIE_HEADERS;

	/** The Constant DISALLOWED_REQUEST_HEADERS. */
	public static final List<String> DISALLOWED_REQUEST_HEADERS = createDisallowedRequestHeaders();

	/** The Constant DISALLOWED_RESPONSE_HEADERS. */
	public static final List<String> DISALLOWED_RESPONSE_HEADERS = createDisallowedResponseHeaders();

	/** The Constant UTF_8. */
	public static final String UTF_8 = "UTF-8";

	/** The Constant APPLICATION_OCTET_STREAM. */
	public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

	/** The Constant APPLICATION_ZIP represents zip content type. */

	public static final String APPLICATION_ZIP = "application/zip";
	public static final String APPLICATION_X_ZIP_COMPRESSED = "application/x-zip-compressed";

	public static final String GWT_RPC_CONTENT = "text/x-gwt-rpc";
	public static final String APPLICATION_ENVOY = "application/envoy";
	public static final String APPLICATION_FRACTALS = "application/fractals";
	public static final String APPLICATION_FUTURESPLASH = "application/futuresplash";
	public static final String APPLICATION_HTA = "application/hta";
	public static final String APPLICATION_INTERNET_PROPERTY_STREAM = "application/internet-property-stream";
	public static final String APPLICATION_MAC_BINHEX40 = "application/mac-binhex40";
	public static final String APPLICATION_MSWORD = "application/msword";
	// public static final String APPLICATION_MSWORD_TIKA = "application/x-tika-msoffice";

	public static final String APPLICATION_ODA = "application/oda";
	public static final String APPLICATION_OLESCRIPT = "application/olescript";
	public static final String APPLICATION_PICS_RULES = "application/pics-rules";
	public static final String APPLICATION_PKCS10 = "application/pkcs10";
	public static final String APPLICATION_PKIX_CRL = "application/pkix-crl";
	public static final String APPLICATION_POSTSCRIPT = "application/postscript";
	public static final String APPLICATION_RTF = "application/rtf";
	public static final String APPLICATION_SET_PAYMENT_INITIATION = "application/set-payment-initiation";
	public static final String APPLICATION_SET_REGISTRATION_INITIATION = "application/set-registration-initiation";
	public static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";
	public static final String APPLICATION_VND_MS_OUTLOOK = "application/vnd.ms-outlook";
	public static final String APPLICATION_VND_MS_PKICERTSTORE = "application/vnd.ms-pkicertstore";
	public static final String APPLICATION_VND_MS_PKISECCAT = "application/vnd.ms-pkiseccat";
	public static final String APPLICATION_VND_MS_PKISTL = "application/vnd.ms-pkistl";
	public static final String APPLICATION_VND_MS_POWERPOINT = "application/vnd.ms-powerpoint";
	public static final String APPLICATION_VND_MS_PROJECT = "application/vnd.ms-project";
	public static final String APPLICATION_VND_MS_WORKS = "application/vnd.ms-works";
	public static final String APPLICATION_WINHLP = "application/winhlp";
	public static final String APPLICATION_X_BCPIO = "application/x-bcpio";
	public static final String APPLICATION_X_CDF = "application/x-cdf";
	public static final String APPLICATION_X_COMPRESS = "application/x-compress";
	public static final String APPLICATION_X_COMPRESSED = "application/x-compressed";
	public static final String APPLICATION_X_CPIO = "application/x-cpio";
	public static final String APPLICATION_X_CSH = "application/x-csh";
	public static final String APPLICATION_X_DIRECTOR = "application/x-director";
	public static final String APPLICATION_X_DVI = "application/x-dvi";
	public static final String APPLICATION_X_GTAR = "application/x-gtar";
	public static final String APPLICATION_X_GZIP = "application/x-gzip";
	public static final String APPLICATION_X_HDF = "application/x-hdf";
	public static final String APPLICATION_X_INTERNET_SIGNUP = "application/x-internet-signup";
	public static final String APPLICATION_X_IPHONE = "application/x-iphone";
	public static final String APPLICATION_X_LATEX = "application/x-latex";
	public static final String APPLICATION_X_MSACCESS = "application/x-msaccess";
	public static final String APPLICATION_X_MSCARDFILE = "application/x-mscardfile";
	public static final String APPLICATION_X_MSCLIP = "application/x-msclip";
	public static final String APPLICATION_X_MSDOWNLOAD = "application/x-msdownload";
	public static final String APPLICATION_X_MSMEDIAVIEW = "application/x-msmediaview";
	public static final String APPLICATION_X_MSMETAFILE = "application/x-msmetafile";
	public static final String APPLICATION_X_MSMONEY = "application/x-msmoney";
	public static final String APPLICATION_X_MSPUBLISHER = "application/x-mspublisher";
	public static final String APPLICATION_X_MSSCHEDULE = "application/x-msschedule";
	public static final String APPLICATION_X_MSTERMINAL = "application/x-msterminal";
	public static final String APPLICATION_X_MSWRITE = "application/x-mswrite";
	public static final String APPLICATION_X_NETCDF = "application/x-netcdf";
	public static final String APPLICATION_X_PERFMON = "application/x-perfmon";
	public static final String APPLICATION_X_PKCS12 = "application/x-pkcs12";
	public static final String APPLICATION_X_PKCS7_CERTIFICATES = "application/x-pkcs7-certificates";
	public static final String APPLICATION_X_PKCS7_CERTREQRESP = "application/x-pkcs7-certreqresp";
	public static final String APPLICATION_X_PKCS7_MIME = "application/x-pkcs7-mime";
	public static final String APPLICATION_X_PKCS7_SIGNATURE = "application/x-pkcs7-signature";
	public static final String APPLICATION_X_SH = "application/x-sh";
	public static final String APPLICATION_X_SHAR = "application/x-shar";
	public static final String APPLICATION_X_SHOCKWAVE_FLASH = "application/x-shockwave-flash";
	public static final String APPLICATION_X_STUFFIT = "application/x-stuffit";
	public static final String APPLICATION_X_SV4CPIO = "application/x-sv4cpio";
	public static final String APPLICATION_X_SV4CRC = "application/x-sv4crc";
	public static final String APPLICATION_X_TAR = "application/x-tar";
	public static final String APPLICATION_X_TCL = "application/x-tcl";
	public static final String APPLICATION_X_TEX = "application/x-tex";
	public static final String APPLICATION_X_TEXINFO = "application/x-texinfo";
	public static final String APPLICATION_X_TROFF = "application/x-troff";
	public static final String APPLICATION_X_TROFF_MAN = "application/x-troff-man";
	public static final String APPLICATION_X_TROFF_ME = "application/x-troff-me";
	public static final String APPLICATION_X_TROFF_MS = "application/x-troff-ms";
	public static final String APPLICATION_X_USTAR = "application/x-ustar";
	public static final String APPLICATION_X_WAIS_SOURCE = "application/x-wais-source";
	public static final String APPLICATION_X_X509_CA_CERT = "application/x-x509-ca-cert";
	public static final String APPLICATION_YND_MS_PKIPKO = "application/ynd.ms-pkipko";
	public static final String AUDIO_BASIC = "audio/basic";
	public static final String AUDIO_MID = "audio/mid";
	public static final String AUDIO_MPEG = "audio/mpeg";
	public static final String AUDIO_X_AIFF = "audio/x-aiff";
	public static final String AUDIO_X_MPEGURL = "audio/x-mpegurl";
	public static final String AUDIO_X_PN_REALAUDIO = "audio/x-pn-realaudio";
	public static final String AUDIO_X_WAV = "audio/x-wav";
	public static final String IMAGE_BMP = "image/bmp";
	public static final String IMAGE_CIS_COD = "image/cis-cod";
	public static final String IMAGE_IEF = "image/ief";
	public static final String IMAGE_PIPEG = "image/pipeg";
	public static final String IMAGE_SVG_XML = "image/svg+xml";
	public static final String IMAGE_TIFF = "image/tiff";
	public static final String IMAGE_X_CMU_RASTER = "image/x-cmu-raster";
	public static final String IMAGE_X_CMX = "image/x-cmx";
	public static final String IMAGE_X_ICON = "image/x-icon";
	public static final String IMAGE_X_PORTABLE_ANYMAP = "image/x-portable-anymap";
	public static final String IMAGE_X_PORTABLE_BITMAP = "image/x-portable-bitmap";
	public static final String IMAGE_X_PORTABLE_GRAYMAP = "image/x-portable-graymap";
	public static final String IMAGE_X_PORTABLE_PIXMAP = "image/x-portable-pixmap";
	public static final String IMAGE_X_RGB = "image/x-rgb";
	public static final String IMAGE_X_XBITMAP = "image/x-xbitmap";
	public static final String IMAGE_X_XPIXMAP = "image/x-xpixmap";
	public static final String IMAGE_X_XWINDOWDUMP = "image/x-xwindowdump";
	public static final String MESSAGE_RFC822 = "message/rfc822";
	public static final String TEXT_H323 = "text/h323";
	public static final String TEXT_IULS = "text/iuls";
	public static final String TEXT_RICHTEXT = "text/richtext";
	public static final String TEXT_SCRIPTLET = "text/scriptlet";
	public static final String TEXT_TAB_SEPARATED_VALUES = "text/tab-separated-values";
	public static final String TEXT_WEBVIEWHTML = "text/webviewhtml";
	public static final String TEXT_X_COMPONENT = "text/x-component";
	public static final String TEXT_X_SETEXT = "text/x-setext";
	public static final String TEXT_X_VCARD = "text/x-vcard";
	public static final String VIDEO_MPEG = "video/mpeg";
	public static final String VIDEO_QUICKTIME = "video/quicktime";
	public static final String VIDEO_X_LA_ASF = "video/x-la-asf";
	public static final String VIDEO_X_MS_ASF = "video/x-ms-asf";
	public static final String VIDEO_X_MSVIDEO = "video/x-msvideo";
	public static final String VIDEO_X_SGI_MOVIE = "video/x-sgi-movie";
	public static final String X_WORLD_X_VRML = "x-world/x-vrml";
	public static final String APPLICATION_VND_MS_SYNC_WBXML = "application/vnd.ms-sync.wbxml";

	// public static final String APPLICATION_MSWORD_DOCX_TIKA = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	// public static final String APPLICATION_MSEXCEL_XLSX_TIKA = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/**
	 * Instantiates a new http constants.
	 */
	public HttpConstants() {
		// Default Constructor
	}

	/**
	 * Creates the disallowed response headers.
	 * 
	 * @return the list
	 */
	private static List<String> createDisallowedResponseHeaders() {
		List<String> result = new ArrayList<String>();
		result.addAll(ALLOWED_RESPONSE_COOKIES);
		result.add(HttpConstants.CONTENT_LENGTH);
		return result;
	}

	/**
	 * Creates the disallowed request headers.
	 * 
	 * @return the list
	 */
	private static List<String> createDisallowedRequestHeaders() {
		List<String> result = new ArrayList<String>();
		result.addAll(ALLOWED_REQUEST_COOKIES);
		result.add(HttpConstants.CONTENT_LENGTH);
		result.add(IF_MODIFIED_SINCE);
		result.add(IF_NONE_MATCH);
		result.add("Transfer-Encoding");
		return result;
	}

	/* Image content based MIME types
	 * 
	 * Keep the frequently used one on top. */
	private static final String[] imageContentTypes = { IMAGE_GIF, IMAGE_IEF, IMAGE_JPEG, IMAGE_PIPEG, IMAGE_PNG, IMAGE_BMP, IMAGE_X_ICON,
			IMAGE_TIFF, IMAGE_X_CMU_RASTER, IMAGE_X_CMX, IMAGE_CIS_COD, IMAGE_SVG_XML, IMAGE_X_PORTABLE_ANYMAP, IMAGE_X_PORTABLE_BITMAP,
			IMAGE_X_PORTABLE_GRAYMAP, IMAGE_X_PORTABLE_PIXMAP, IMAGE_X_RGB, IMAGE_X_XBITMAP, IMAGE_X_XPIXMAP, IMAGE_X_XWINDOWDUMP };

	/* Audio content based MIME types
	 * 
	 * Keep the frequently used one on top. */
	private static final String[] audioContentTypes = { AUDIO_MPEG, AUDIO_BASIC, AUDIO_MID, AUDIO_X_AIFF, AUDIO_X_MPEGURL,
			AUDIO_X_PN_REALAUDIO, AUDIO_X_WAV

	};

	/* Video content based MIME types
	 * 
	 * Keep the frequently used one on top. */
	private static final String[] videoContentTypes = { VIDEO_MPEG, VIDEO_QUICKTIME, VIDEO_X_LA_ASF, VIDEO_X_MS_ASF, VIDEO_X_MSVIDEO,
			VIDEO_X_SGI_MOVIE };

	/* Text content based MIME types under associated to an application
	 * 
	 * Keep the frequently used one on top. */
	private static final String[] applicationTextContentTypes = { FORM_URL_ENCODED, APPLICATION_X_JAVASCRIPT, APPLICATION_POSTSCRIPT,
			APPLICATION_XML, APPLICATION_JSON, APPLICATION_RTF };

	/* Binary content based MIME types under associated to an application
	 * 
	 * Keep the frequently used one on top. */
	private static final String[] applicationBinaryContentTypes = { APPLICATION_ENVOY, APPLICATION_FRACTALS, APPLICATION_FUTURESPLASH,
			APPLICATION_HTA, APPLICATION_INTERNET_PROPERTY_STREAM, APPLICATION_MAC_BINHEX40, APPLICATION_MSWORD, APPLICATION_OCTET_STREAM,
			APPLICATION_ODA, APPLICATION_OLESCRIPT, APPLICATION_PDF, APPLICATION_PICS_RULES, APPLICATION_PKCS10, APPLICATION_PKIX_CRL,
			APPLICATION_SET_PAYMENT_INITIATION, APPLICATION_SET_REGISTRATION_INITIATION, APPLICATION_VND_MS_EXCEL, APPLICATION_VND_MS_OUTLOOK,
			APPLICATION_VND_MS_PKICERTSTORE, APPLICATION_VND_MS_PKISECCAT, APPLICATION_VND_MS_PKISTL, APPLICATION_VND_MS_POWERPOINT,
			APPLICATION_VND_MS_PROJECT, APPLICATION_VND_MS_WORKS, APPLICATION_WINHLP, APPLICATION_X_BCPIO, APPLICATION_X_CDF,
			APPLICATION_X_COMPRESS, APPLICATION_X_COMPRESSED, APPLICATION_X_CPIO, APPLICATION_X_CSH, APPLICATION_X_DIRECTOR, APPLICATION_X_DVI,
			APPLICATION_X_GTAR, APPLICATION_X_GZIP, APPLICATION_X_HDF, APPLICATION_X_INTERNET_SIGNUP, APPLICATION_X_IPHONE, APPLICATION_X_LATEX,
			APPLICATION_X_MSACCESS, APPLICATION_X_MSCARDFILE, APPLICATION_X_MSCLIP, APPLICATION_X_MSDOWNLOAD, APPLICATION_X_MSMEDIAVIEW,
			APPLICATION_X_MSMETAFILE, APPLICATION_X_MSMONEY, APPLICATION_X_MSPUBLISHER, APPLICATION_X_MSSCHEDULE, APPLICATION_X_MSTERMINAL,
			APPLICATION_X_MSWRITE, APPLICATION_X_NETCDF, APPLICATION_X_PERFMON, APPLICATION_X_PKCS12, APPLICATION_X_PKCS7_CERTIFICATES,
			APPLICATION_X_PKCS7_CERTREQRESP, APPLICATION_X_PKCS7_MIME, APPLICATION_X_PKCS7_SIGNATURE, APPLICATION_X_SH, APPLICATION_X_SHAR,
			APPLICATION_X_SHOCKWAVE_FLASH, APPLICATION_X_STUFFIT, APPLICATION_X_SV4CPIO, APPLICATION_X_SV4CRC, APPLICATION_X_TAR,
			APPLICATION_X_TCL, APPLICATION_X_TEX, APPLICATION_X_TEXINFO, APPLICATION_X_TROFF, APPLICATION_X_TROFF_MAN, APPLICATION_X_TROFF_ME,
			APPLICATION_X_TROFF_MS, APPLICATION_X_USTAR, APPLICATION_X_WAIS_SOURCE, APPLICATION_X_X509_CA_CERT, APPLICATION_YND_MS_PKIPKO,
			APPLICATION_ZIP, APPLICATION_X_ZIP_COMPRESSED };

	/* Other/miscellaneous MIME types
	 * 
	 * Keep the frequently used one on top. */

	private static final String[] miscellaneousContentTypes = { MULTIPART_FORM_DATA, X_WORLD_X_VRML, MESSAGE_RFC822 };

	/* Text based MIME types
	 * 
	 * Keep the frequently used one on top. */
	private static final String[] textContentTypes = { TEXT_PLAIN, TEXT_HTML, TEXT_CSS, TEXT_XML_AJAX_RESPONSE, TEXT_XML, TEXT_CSV,
			TEXT_JAVASCRIPT, TEXT_H323, TEXT_RICHTEXT, TEXT_SCRIPTLET, TEXT_IULS, TEXT_TAB_SEPARATED_VALUES, TEXT_WEBVIEWHTML, TEXT_X_COMPONENT,
			TEXT_X_SETEXT, TEXT_X_VCARD };

	public static boolean isTextType(String contentType) {
		return checkContentTypeMatches(textContentTypes, contentType) || checkContentTypeMatches(applicationTextContentTypes, contentType);
	}

	public static boolean isBinaryType(String contentType) {
		/* For now using !isTextType()
		 * 
		 * But in case of any problem use this logic since junk content types for text content are possible
		 * 
		 * return isApplicationBinaryType(contentType) || isImageType(contentType) || isAudioType(contentType) || isVideoType(contentType)||checkContentTypeMatches(miscellaneousContentTypes, contentType); */
		return !isTextType(contentType);
	}

	public static boolean isApplicationBinaryType(String contentType) {
		return checkContentTypeMatches(applicationBinaryContentTypes, contentType);
	}

	public static boolean isImageType(String contentType) {
		return checkContentTypeMatches(imageContentTypes, contentType);
	}

	public static boolean isAudioType(String contentType) {
		return checkContentTypeMatches(audioContentTypes, contentType);
	}

	public static boolean isVideoType(String contentType) {
		return checkContentTypeMatches(videoContentTypes, contentType);
	}

	private static boolean checkContentTypeMatches(String[] typeArray, String contentType) {
		for (int i = 0; i < typeArray.length; i++) {
			if (typeArray[i].equalsIgnoreCase(contentType) || (contentType != null && contentType.contains(typeArray[i]))) {
				return true;
			}
		}
		return false;
	}
}