package com.data.generator.extractor;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.htmlparser.jericho.Source;

import com.data.generato.econpapers.ContentType;
import com.data.generato.econpapers.Email;
import com.data.generato.econpapers.HtmlExtractorConstants;
import com.data.generato.econpapers.HtmlExtractorUtil;
import com.data.generato.econpapers.HtmlParserException;
import com.data.generator.executor.DataGeneratorThreadPoolExecutor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class EconPaperDaemon {

	/** The Constant DEFAULT_THREAD_POOL_SIZE. */
	private static final int DEFAULT_THREAD_POOL_SIZE = 40;

	/** The Constant DEFAULT_QUEUE_SIZE. */
	private static final int DEFAULT_QUEUE_SIZE = 200;

	/** The Constant DEFAULT_KEEP_ALIVE. */
	private static final long DEFAULT_KEEP_ALIVE = 40;

	/** The Constant DEFAULT_WAIT_TIME_TO_QUEUE. */
	private static final long DEFAULT_WAIT_TIME_TO_QUEUE = 1 * 1000L;

	private static String excludedEmail = "econpapers@oru.se";
	private HtmlExtractor htmlExtractor;
	private EconPaperEmailExtractor econPaperEmailExtractor;
	private EconPaperDetailedExtractor econPaperDetailedExtractor;
	private EconPaperListViewExtractor econPaperListViewExtractor;
	private WebContentDownloader webContentDownloader;

	private ExecutorService executorService;
	private long timeout;
	private int threads;
	private int queueSize;
	private final ContentType contentType;

	public EconPaperDaemon() {
		this(ContentType.EMAIL);
	}

	private EconPaperDaemon(ContentType argContentType) {
		this.contentType = argContentType;
		this.htmlExtractor = new HtmlExtractor();
		this.econPaperEmailExtractor = new EconPaperEmailExtractor(
				this.htmlExtractor);
		this.econPaperDetailedExtractor = new EconPaperDetailedExtractor(
				this.htmlExtractor);
		this.econPaperListViewExtractor = new EconPaperListViewExtractor(
				this.htmlExtractor);
		this.webContentDownloader = new WebContentDownloader();
	}

	public void initialize() {
		if (Precondition.checkZero(this.getThreads())) {
			this.setThreads(DEFAULT_THREAD_POOL_SIZE);
		}
		if (Precondition.checkZero(this.getQueueSize())) {
			this.setQueueSize(DEFAULT_QUEUE_SIZE);
		}
		this.setExecutorService(createExecutorService());
	}

	private class EconPaperExecutor implements Runnable {

		private String outputDirectoryName;
		private String outputFileName;
		private String fileExtension;
		private String value;
		private ContentType contentType;

		public EconPaperExecutor(String argOutputDirectoryName,
				String argOutputFileName, String argFileExtension,
				String argValue) {
			super();
			this.outputDirectoryName = argOutputDirectoryName;
			this.outputFileName = argOutputFileName;
			this.fileExtension = argFileExtension;
			this.setValue(argValue);
		}

		public String getValue() {
			return this.value;
		}

		public void setValue(String argValue) {
			this.value = argValue;
		}

		public ContentType getContentType() {
			return this.contentType;
		}

		public void setContentType(ContentType argContentType) {
			this.contentType = argContentType;
		}

		@Override
		public void run() {
			String outputFileName = prepareOutputFileName();
			BufferedWriter bufferedWriter = null;
			try {
				try {
					bufferedWriter = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(outputFileName),
							Charset.forName("UTF-8")));
				} catch (IOException e) {
					throw new HtmlParserException(
							"Exception while creating a file writer object: ",
							e);
				}
				String html = webContentDownloader.getHtmlContent(this
						.getValue());
				List<String> tokensList = getTokensList(html,
						this.getContentType());
				if (Precondition.checkNotEmpty(tokensList)) {
					for (String token : tokensList) {
						bufferedWriter.write(token);
						bufferedWriter.write("\r\n");
					}
					bufferedWriter.flush();
				}
			} catch (IOException e) {
				throw new HtmlParserException(
						"Exception while writing the content into a file: ", e);
			} finally {
				if (Precondition.checkNotNull(bufferedWriter)) {
					try {
						bufferedWriter.close();
					} catch (Exception ignore) {
						// ignore
					}
				}
			}
		}

		public List<String> getTokensList(String argHtml,
				ContentType argContentType) {
			switch (argContentType) {
			case EMAIL:
				return extractEmail(argHtml);
			case NAME:
				return extractName(argHtml);
			default:
				break;
			}
			return null;
		}

		private List<String> extractName(String argHtml) {
			if (Precondition.checkNotEmpty(argHtml)) {
				Source source = htmlExtractor
						.getSourceBasedOnHtmlString(argHtml);
				if (Precondition.checkNotNull(source)) {
					Map<String, String> labelVsDetailedLinksMap = econPaperDetailedExtractor
							.extractNamesInPageBasedOnHtml(argHtml);
					Iterator<Entry<String, String>> detailedIterator = labelVsDetailedLinksMap
							.entrySet().iterator();
					List<String> titlesList = new ArrayList<String>();
					while (detailedIterator.hasNext()) {
						Entry<String, String> detailedEntry = detailedIterator
								.next();
						String emailHtml = webContentDownloader
								.getHtmlContent(detailedEntry.getKey());
						Source s = htmlExtractor
								.getSourceBasedOnHtmlString(emailHtml);
						String title = HtmlExtractorUtil.getTitle(s);
						title = getTitle(title);
						titlesList.add(title);
					}
					return titlesList;
				}
			}
			return null;
		}

		protected String getTitle(String title) {
			if (Precondition.checkNotEmpty(title)) {
				int index = title.indexOf(HtmlExtractorConstants.ECON_PAPERS);
				if (Precondition.checkNonNegative(index)) {
					return title.substring(index
							+ HtmlExtractorConstants.ECON_PAPERS.length());
				}
			}
			return title;
		}

		protected List<String> extractEmail(String argHtml) {
			if (Precondition.checkNotEmpty(argHtml)) {
				Map<String, String> labelVsDetailedLinksMap = econPaperDetailedExtractor
						.extractNamesInPageBasedOnHtml(argHtml);
				Iterator<Entry<String, String>> detailedIterator = labelVsDetailedLinksMap
						.entrySet().iterator();
				List<String> resultedEmailsList = new ArrayList<String>();
				while (detailedIterator.hasNext()) {
					Entry<String, String> detailedEntry = detailedIterator
							.next();
					String emailHtml = webContentDownloader
							.getHtmlContent(detailedEntry.getKey());
					Source source = htmlExtractor
							.getSourceBasedOnHtmlString(emailHtml);
					String title = HtmlExtractorUtil.getTitle(source);
					title = getTitle(title);
					List<Email> emailsList = econPaperEmailExtractor
							.getAllEmails(source);
					// .getAllEmailsBasedOnHtml(emailHtml);
					List<String> emailTokensList = econPaperEmailExtractor
							.getEmailList(emailsList);
					if (Precondition.checkNotEmpty(emailTokensList)) {
						for (String email : emailTokensList) {
							if (!excludedEmail.equals(email)) {
								resultedEmailsList.add(title + "=" + email);
							}
						}
					}
				}
				return resultedEmailsList;
			}
			return null;
		}

		private String prepareOutputFileName() {
			StringBuilder fileNameBuilder = new StringBuilder();
			fileNameBuilder.append(this.outputDirectoryName);
			fileNameBuilder.append(this.outputFileName);
			fileNameBuilder.append(this.fileExtension);
			return fileNameBuilder.toString();
		}

	}

	// public static void main(String[] args) {
	// ContentType[] contentTypeArray = ContentType.values();
	// Precondition.ensureNotEmpty(contentTypeArray, "Content Type");
	// for (ContentType contentType : contentTypeArray) {
	// EconPaperDaemon econPaperDaemon = new EconPaperDaemon(contentType);
	// econPaperDaemon.initialize();
	// econPaperDaemon.extract();
	// }
	// }

	public static void main(String[] args) {
		EconPaperDaemon econPaperDaemon = new EconPaperDaemon(ContentType.EMAIL);
		econPaperDaemon.initialize();
		econPaperDaemon.extract();
	}

	protected void execute(EconPaperExecutor argEconPaperExecutor) {
		boolean queued = false;
		do {
			try {
				this.getExecutorService().execute(argEconPaperExecutor);
				queued = true;
			} catch (RejectedExecutionException e) {
				try {
					Thread.sleep(DEFAULT_WAIT_TIME_TO_QUEUE);
				} catch (InterruptedException ignore) {
					// ignore
				}
			}
		} while (!queued);
	}

	private ExecutorService createExecutorService() {
		BlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingDeque<Runnable>(
				this.getThreads());
		ThreadPoolExecutor threadPoolExecutor = new DataGeneratorThreadPoolExecutor(
				this.getThreads(), this.getThreads(), DEFAULT_KEEP_ALIVE,
				TimeUnit.SECONDS, linkedBlockingQueue);
		return threadPoolExecutor;
	}

	public void extract() {
		String htmlContent = this.webContentDownloader
				.getHtmlContent("http://econpapers.repec.org/RAS/");
		Map<String, String> labelVsLinksMap = this.econPaperListViewExtractor
				.getListViewLinksBasedOnHtml(htmlContent);
		Precondition.ensureNotNull(labelVsLinksMap, "Lebel Vs Links Map");
		Iterator<Entry<String, String>> iterator = labelVsLinksMap.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			EconPaperExecutor econPaperExecutor = new EconPaperExecutor(
					"./output/", this.getContentType().getContentType() + "-"
							+ entry.getKey(), ".txt", entry.getValue());
			econPaperExecutor.setContentType(this.getContentType());
			this.execute(econPaperExecutor);

		}
	}

	public long getTimeout() {
		return this.timeout;
	}

	public void setTimeout(long argTimeout) {
		this.timeout = argTimeout;
	}

	public int getThreads() {
		return this.threads;
	}

	public void setThreads(int argThreads) {
		this.threads = argThreads;
	}

	public int getQueueSize() {
		return this.queueSize;
	}

	public void setQueueSize(int argQueueSize) {
		this.queueSize = argQueueSize;
	}

	public ExecutorService getExecutorService() {
		return this.executorService;
	}

	public void setExecutorService(ExecutorService argExecutorService) {
		this.executorService = argExecutorService;
	}

	public ContentType getContentType() {
		return this.contentType;
	}

}
