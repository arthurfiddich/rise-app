package ssrn;

import java.io.File;
import java.io.IOException;

import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftConditionalCommentTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Source;

public class HtmlExtractor {

	public HtmlExtractor() {
		registerTagTypes();
	}

	private void registerTagTypes() {
		MicrosoftConditionalCommentTagTypes.register();
		PHPTagTypes.register();
		// remove PHP short tags otherwise they override processing instructions
		PHPTagTypes.PHP_SHORT.deregister();
		MasonTagTypes.register();
	}

	public Source getSource(String argFileName) {
		String inputFileName = Precondition.ensureNotEmpty(argFileName,
				"Input File Name");
		return getSource(new File(inputFileName));
	}

	public Source getSource(File argInputFile) {
		final File inputFile = Precondition.ensureNotNull(argInputFile,
				"Input File");
		Source source = null;
		try {
			source = new Source(inputFile);
		} catch (IOException e) {
			throw new RuntimeException(
					"Exception while creating a source instance with the give input file",
					e);
		}
		source.fullSequentialParse();
		return source;
	}

	public Source getSourceBasedOnHtmlString(String argHtmlSring) {
		if (Precondition.checkNotEmpty(argHtmlSring)) {
			String htmlToken = Precondition.ensureNotEmpty(argHtmlSring,
					"Html Token");
			Source source = new Source(htmlToken);
			source.fullSequentialParse();
			return source;
		} else {
			System.out.println("Empty.............");
			return null;
		}
	}

}
