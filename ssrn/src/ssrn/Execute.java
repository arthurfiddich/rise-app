package ssrn;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.FormFields;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.Source;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Execute {

	public static List<NameValuePair> buildNameValuePairs(InputStream is,
			String strUsername, String strPassword) throws Exception {
		// Initialize the list
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		// string arrays containing the column labels
		// and values
		String[] columnLabels = null, columnValues = null;

		// Register the tag types related to the Mason
		// server platform.
		MasonTagTypes.register();

		// Construct a new Source object by loading the
		// content from the specified InputStream.
		Source source = new Source(is);

		// Set the Logger that handles log messages to null.
		// We are a windowed application and we don't
		// need to handle verbose outputs.
		source.setLogger(null);

		// Parses all of the tags in this source document
		// sequentially from beginning to end.
		source.fullSequentialParse();

		// Return a list of all forms in the
		// source document.
		List<Element> formElements = source
				.getAllElements(HTMLElementName.FORM);

		// Start the loop
		for (Element formElement : formElements) {
			String loginTag = formElement.getStartTag().toString();
			// stop the execution of the current iteration
			// and go back to the beginning of the loop to
			// begin a new iteration
			if (loginTag == null)
				continue;
			// Let's find the login form.
			else if (loginTag.toLowerCase().indexOf("formmain") > -1) {
				// Create a segment of the Source document
				// containing the login form as CharSequence.
				CharSequence cs = formElement.getContent();
				// Constructs a new Source object from
				// the specified segment.
				source = new Source(cs);

				// Return a collection of FormField objects.
				// Each form field consists of a group of
				// form controls having the same name.
				FormFields formFields = source.getFormFields();

				// Return a string array containing
				// the column labels corresponding to the
				// values from the getColumnValues(Map)
				// method.
				columnLabels = formFields.getColumnLabels();
				// Convert all the form submission values
				// of the constituent form fields into a
				// simple string array.
				columnValues = formFields.getColumnValues();

				break;
			}
		}

		/**
		 * Now we can construct the List of name/value pairs to login to a
		 * website.
		 */
		for (int i = 0; i < columnValues.length; i++) {
			String key = columnLabels[i];
			String value = columnValues[i];
			if (key.equals("txtLogin")) {
				value = strUsername;
			} else if (key.equals("txtPassword")) {
				value = strPassword;
			}
			if (value != null) {
				nvps.add(new BasicNameValuePair(key, value));
			}
		}

		// if (columnLabels[i].equalsIgnoreCase("username")){
		// nvps.add(new BasicNameValuePair(columnLabels[i],
		// strUsername));

		// return statement
		return nvps;
	}
}
