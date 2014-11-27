package www.christuniversity.in;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import com.data.generator.file.Writer;
import com.data.generator.util.Precondition;

public class ListWriter implements Writer<List<User>, String> {

	private ICsvListWriter iCsvListWriter;
	private String outputFileName;

	public ListWriter(String outputFileName) {
		super();
		this.outputFileName = outputFileName;
		writerInitialization();
	}

	public void writerInitialization() {
		File outputFile = new File(this.outputFileName);
		try {
			this.iCsvListWriter = new org.supercsv.io.CsvListWriter(
					new OutputStreamWriter(new FileOutputStream(outputFile),
							Charset.forName("UTF-8")),
					CsvPreference.EXCEL_PREFERENCE);
		} catch (IOException e) {
			throw new RuntimeException(
					"Exception while initializing the writer", e);
		}
	}

	@Override
	public void write(List<User> argUsersList) {
		User u = argUsersList.get(0);
		List<String> keysList = u.getKeysList();
		try {
			this.iCsvListWriter.writeHeader(keysList
					.toArray(new String[keysList.size()]));
			for (User user : argUsersList) {
				List<List<String>> tokensList = user.getTotalTokensList();
				for (List<String> list : tokensList) {
					this.iCsvListWriter.write(list);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(
					"Exception while writing the content into a file: ", e);
		} finally {
			if (Precondition.checkNotNull(this.iCsvListWriter)) {
				try {
					this.iCsvListWriter.close();
				} catch (Exception ignore) {
					// ignore
				}
			}
		}
	}

	@Override
	public Properties loadProperties(String argName) {
		return null;
	}
}
