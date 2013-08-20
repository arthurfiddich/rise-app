package com.rise.daemon.server;

import java.util.List;

import org.eclipse.jetty.server.handler.AbstractHandler;

import static com.rise.daemon.server.RiseServerDefaults.*;

public class RiseServerConfiguration {
	private HostConfiguration hostConfiguration;
	private HttpConfiguration httpConfiguration;
	private KeystoreConfiguration keystoreConfiguration;
	private CipherSuitesConfiguration cipherSuitesConfiguration;
	private ProcessConfiguration processConfiguration;
	private ThreadPoolConfiguration threadPoolConfiguration;
	private WebAppConfiguration webAppConfiguration;
	private RequestLogConfiguration requestLogConfiguration;

	public RiseServerConfiguration() {
		// Default Constructor
	}

	public RiseServerConfiguration(boolean argUseDefaults) {
		super();
		this.hostConfiguration = new HostConfiguration();
		this.httpConfiguration = new HttpConfiguration();
		this.keystoreConfiguration = new KeystoreConfiguration();
		this.cipherSuitesConfiguration = new CipherSuitesConfiguration();
		this.processConfiguration = new ProcessConfiguration();
		this.threadPoolConfiguration = new ThreadPoolConfiguration();
		this.webAppConfiguration = new WebAppConfiguration();
		this.requestLogConfiguration = new RequestLogConfiguration();
		if (argUseDefaults) {
			this.setHttpHost(DEFAULT_HTTP_HOST)
					.setHttpPort(DEFAULT_HTTP_PORT)
					.setHttpsPort(DEFAULT_HTTPS_PORT)
					.setHttpRequestHeaderSize(DEFAULT_HTTP_REQUEST_HEADER_SIZE)
					.setHttpsRequestHeaderSize(
							DEFAULT_HTTPS_REQUEST_HEADER_SIZE)
					.setHttpResponseHeaderSize(
							DEFAULT_HTTP_RESPONSE_HEADER_SIZE)
					.setHttpsResponseHeaderSize(
							DEFAULT_HTTPS_RESPONSE_HEADER_SIZE)
					.setKeystoreFile(DEFAULT_KEYSTORE_FILE)
					.setKeystorePassword(DEFAULT_KEYSTORE_PASSWORD)
					.setKeyPassowrd(DEFAULT_KEY_PASSOWRD)
					.setIncluded(DEFAULT_INCLUDED_CIPHER_SUITES)
					.setExcluded(DEFAULT_EXCLUDED_CIPHER_SUITES)
					.setStatsOn(DEFAULT_STATS_ON)
					.setThreadPoolSize(DEFAULT_THREAD_POOL_SIZE)
					.setAcceptorThreadSize(DEFAULT_ACCEPTOR_THREAD_SIZE)
					.setAcceptorQueueSize(DEFAULT_ACCEPTOR_QUEUE_SIZE)
					.setMaxIdleTime(DEFAULT_MAX_IDLE_TIME)
					.setAccessLogEnabled(DEFAULT_ACCESS_LOG_ENABLED)
					.setAccessLogDirectory(DEFAULT_ACCESS_LOG_DIRECTORY)
					.setAccessLogFilename(DEFAULT_ACCESS_LOG_FILENAME)
					.setAccessLogRetainDays(DEFAULT_ACCESS_LOG_RETAIN_DAYS)
					.setAccessLogAppend(DEFAULT_ACCESS_LOG_APPEND)
					.setAccessLogTimeZone(DEFAULT_ACCESS_LOG_TIME_ZONE)
					.setAccessLogServerName(DEFAULT_ACCESS_LOG_SERVER_NAME)
					.setAccessLogCookies(DEFAULT_ACCESS_LOG_COOKIES)
					.setAccessLogExtended(DEFAULT_ACCESS_LOG_EXTENDED)
					.setAccessLogEnableLatency(
							DEFAULT_ACCESS_LOG_ENABLE_LATENCY);
		}
	}

	public HostConfiguration getHostConfiguration() {
		return this.hostConfiguration;
	}

	public RiseServerConfiguration setHostConfiguration(
			HostConfiguration argHostConfiguration) {
		this.hostConfiguration = argHostConfiguration;
		return this;
	}

	public HttpConfiguration getHttpConfiguration() {
		return this.httpConfiguration;
	}

	public RiseServerConfiguration setHttpConfiguration(
			HttpConfiguration argHttpConfiguration) {
		this.httpConfiguration = argHttpConfiguration;
		return this;
	}

	public KeystoreConfiguration getKeystoreConfiguration() {
		return this.keystoreConfiguration;
	}

	public RiseServerConfiguration setKeystoreConfiguration(
			KeystoreConfiguration argKeystoreConfiguration) {
		this.keystoreConfiguration = argKeystoreConfiguration;
		return this;
	}

	public CipherSuitesConfiguration getCipherSuitesConfiguration() {
		return this.cipherSuitesConfiguration;
	}

	public RiseServerConfiguration setCipherSuitesConfiguration(
			CipherSuitesConfiguration argCipherSuitesConfiguration) {
		this.cipherSuitesConfiguration = argCipherSuitesConfiguration;
		return this;
	}

	public ProcessConfiguration getProcessConfiguration() {
		return this.processConfiguration;
	}

	public RiseServerConfiguration setProcessConfiguration(
			ProcessConfiguration argProcessConfiguration) {
		this.processConfiguration = argProcessConfiguration;
		return this;
	}

	public ThreadPoolConfiguration getThreadPoolConfiguration() {
		return this.threadPoolConfiguration;
	}

	public RiseServerConfiguration setThreadPoolConfiguration(
			ThreadPoolConfiguration argThreadPoolConfiguration) {
		this.threadPoolConfiguration = argThreadPoolConfiguration;
		return this;
	}

	public WebAppConfiguration getWebAppConfiguration() {
		return this.webAppConfiguration;
	}

	public RiseServerConfiguration setWebAppConfiguration(
			WebAppConfiguration argWebAppConfiguration) {
		this.webAppConfiguration = argWebAppConfiguration;
		return this;
	}

	public RequestLogConfiguration getRequestLogConfiguration() {
		return this.requestLogConfiguration;
	}

	public RiseServerConfiguration setRequestLogConfiguration(
			RequestLogConfiguration argLogginConfiguration) {
		this.requestLogConfiguration = argLogginConfiguration;
		return this;
	}

	public String getHttpHost() {
		return this.hostConfiguration.getHttpHost();
	}

	public RiseServerConfiguration setHttpHost(String argHttpHost) {
		this.hostConfiguration.setHttpHost(argHttpHost);
		return this;
	}

	public int getHttpPort() {
		return this.hostConfiguration.getHttpPort();
	}

	public RiseServerConfiguration setHttpPort(int argHttpPort) {
		this.hostConfiguration.setHttpPort(argHttpPort);
		return this;
	}

	public int getHttpsPort() {
		return this.hostConfiguration.getHttpsPort();
	}

	public RiseServerConfiguration setHttpsPort(int argHttpsPort) {
		this.hostConfiguration.setHttpsPort(argHttpsPort);
		return this;
	}

	public int getHttpRequestHeaderSize() {
		return this.httpConfiguration.getHttpRequestHeaderSize();
	}

	public RiseServerConfiguration setHttpRequestHeaderSize(
			int argHttpRequestHeaderSize) {
		this.httpConfiguration
				.setHttpRequestHeaderSize(argHttpRequestHeaderSize);
		return this;
	}

	public int getHttpsRequestHeaderSize() {
		return this.httpConfiguration.getHttpsRequestHeaderSize();
	}

	public RiseServerConfiguration setHttpsRequestHeaderSize(
			int argHttpsRequestHeaderSize) {
		this.httpConfiguration
				.setHttpsRequestHeaderSize(argHttpsRequestHeaderSize);
		return this;
	}

	public int getHttpResponseHeaderSize() {
		return this.httpConfiguration.getHttpResponseHeaderSize();
	}

	public RiseServerConfiguration setHttpResponseHeaderSize(
			int argHttpResponseHeaderSize) {
		this.httpConfiguration
				.setHttpResponseHeaderSize(argHttpResponseHeaderSize);
		return this;
	}

	public int getHttpsResponseHeaderSize() {
		return this.httpConfiguration.getHttpsResponseHeaderSize();
	}

	public RiseServerConfiguration setHttpsResponseHeaderSize(
			int argHttpsResponseHeaderSize) {
		this.httpConfiguration
				.setHttpsResponseHeaderSize(argHttpsResponseHeaderSize);
		return this;
	}

	public String getKeystoreFile() {
		return this.keystoreConfiguration.getKeystoreFile();
	}

	public RiseServerConfiguration setKeystoreFile(String argKeystoreFile) {
		this.keystoreConfiguration.setKeystoreFile(argKeystoreFile);
		return this;
	}

	public String getKeystorePassword() {
		return this.keystoreConfiguration.getKeystorePassword();
	}

	public RiseServerConfiguration setKeystorePassword(
			String argKeystorePassword) {
		this.keystoreConfiguration.setKeystorePassword(argKeystorePassword);
		return this;
	}

	public String getKeyPassowrd() {
		return this.keystoreConfiguration.getKeyPassowrd();
	}

	public RiseServerConfiguration setKeyPassowrd(String argKeyPassowrd) {
		this.keystoreConfiguration.setKeyPassowrd(argKeyPassowrd);
		return this;
	}

	public String[] getIncluded() {
		return this.cipherSuitesConfiguration.getIncluded();
	}

	public RiseServerConfiguration setIncluded(String[] argIncluded) {
		this.cipherSuitesConfiguration.setIncluded(argIncluded);
		return this;
	}

	public String[] getExcluded() {
		return this.cipherSuitesConfiguration.getExcluded();
	}

	public RiseServerConfiguration setExcluded(String[] argExcluded) {
		this.cipherSuitesConfiguration.setExcluded(argExcluded);
		return this;
	}

	public String getUserName() {
		return this.processConfiguration.getUserName();
	}

	public RiseServerConfiguration setUserName(String argUserName) {
		this.processConfiguration.setUserName(argUserName);
		return this;
	}

	public String getGroupName() {
		return this.processConfiguration.getGroupName();
	}

	public RiseServerConfiguration setGroupName(String argGroupName) {
		this.processConfiguration.setGroupName(argGroupName);
		return this;
	}

	public String getUmask() {
		return this.processConfiguration.getUmask();
	}

	public RiseServerConfiguration setUmask(String argUmask) {
		this.processConfiguration.setUmask(argUmask);
		return this;
	}

	public boolean isStartAsPrivileged() {
		return this.processConfiguration.isStartAsPrivileged();
	}

	public RiseServerConfiguration setStartAsPrivileged(
			boolean argStartAsPrivileged) {
		this.processConfiguration.setStartAsPrivileged(argStartAsPrivileged);
		return this;
	}

	public int getThreadPoolSize() {
		return this.threadPoolConfiguration.getThreadPoolSize();
	}

	public RiseServerConfiguration setThreadPoolSize(int argThreadPoolSize) {
		this.threadPoolConfiguration.setThreadPoolSize(argThreadPoolSize);
		return this;
	}

	public int getAcceptorThreadSize() {
		return this.threadPoolConfiguration.getAcceptorThreadSize();
	}

	public RiseServerConfiguration setAcceptorThreadSize(
			int argAcceptorThreadSize) {
		this.threadPoolConfiguration
				.setAcceptorThreadSize(argAcceptorThreadSize);
		return this;
	}

	public int getAcceptorQueueSize() {
		return this.threadPoolConfiguration.getAcceptorQueueSize();
	}

	public RiseServerConfiguration setAcceptorQueueSize(
			int argAcceptorQueueSize) {
		this.threadPoolConfiguration.setAcceptorQueueSize(argAcceptorQueueSize);
		return this;
	}

	public int getMaxIdleTime() {
		return this.threadPoolConfiguration.getMaxIdleTime();
	}

	public RiseServerConfiguration setMaxIdleTime(int argMaxIdleTime) {
		this.threadPoolConfiguration.setMaxIdleTime(argMaxIdleTime);
		return this;
	}

	public boolean isAccessLogEnabled() {
		return this.requestLogConfiguration.isAccessLogEnabled();
	}

	public RiseServerConfiguration setAccessLogEnabled(
			boolean argAccessLogEnabled) {
		this.requestLogConfiguration.setAccessLogEnabled(argAccessLogEnabled);
		return this;
	}

	public String getAccessLogDirectory() {
		return this.requestLogConfiguration.getAccessLogDirectory();
	}

	public RiseServerConfiguration setAccessLogDirectory(
			String argAccessLogDirectory) {
		this.requestLogConfiguration
				.setAccessLogDirectory(argAccessLogDirectory);
		return this;
	}

	public String getAccessLogFilename() {
		return this.requestLogConfiguration.getAccessLogFilename();
	}

	public RiseServerConfiguration setAccessLogFilename(
			String argAccessLogFilename) {
		this.requestLogConfiguration.setAccessLogFilename(argAccessLogFilename);
		return this;
	}

	public int getAccessLogRetainDays() {
		return this.requestLogConfiguration.getAccessLogRetainDays();
	}

	public RiseServerConfiguration setAccessLogRetainDays(
			int argAccessLogRetainDays) {
		this.requestLogConfiguration
				.setAccessLogRetainDays(argAccessLogRetainDays);
		return this;
	}

	public boolean isAccessLogAppend() {
		return this.requestLogConfiguration.isAccessLogAppend();
	}

	public RiseServerConfiguration setAccessLogAppend(
			boolean argAccessLogAppend) {
		this.requestLogConfiguration.setAccessLogAppend(argAccessLogAppend);
		return this;
	}

	public boolean isAccessLogExtended() {
		return this.requestLogConfiguration.isAccessLogExtended();
	}

	public RiseServerConfiguration setAccessLogExtended(
			boolean argAccessLogExtended) {
		this.requestLogConfiguration.setAccessLogExtended(argAccessLogExtended);
		return this;
	}

	public String getAccessLogTimeZone() {
		return this.requestLogConfiguration.getAccessLogTimeZone();
	}

	public RiseServerConfiguration setAccessLogTimeZone(
			String argAccessLogTimeZone) {
		this.requestLogConfiguration.setAccessLogTimeZone(argAccessLogTimeZone);
		return this;
	}

	public boolean isAccessLogServerName() {
		return this.requestLogConfiguration.isAccessLogServerName();
	}

	public RiseServerConfiguration setAccessLogServerName(
			boolean argAccessLogServerName) {
		this.requestLogConfiguration
				.setAccessLogServerName(argAccessLogServerName);
		return this;
	}

	public boolean isAccessLogCookies() {
		return this.requestLogConfiguration.isAccessLogCookies();
	}

	public RiseServerConfiguration setAccessLogCookies(
			boolean argAccessLogCookies) {
		this.requestLogConfiguration.setAccessLogCookies(argAccessLogCookies);
		return this;
	}

	public boolean isAccessLogEnableLatency() {
		return this.requestLogConfiguration.isAccessLogEnableLatency();
	}

	public RiseServerConfiguration setAccessLogEnableLatency(
			boolean argAccessLogEnableLatency) {
		this.requestLogConfiguration
				.setAccessLogEnableLatency(argAccessLogEnableLatency);
		return this;
	}

	public boolean isStatsOn() {
		return this.requestLogConfiguration.isStatsOn();
	}

	public RiseServerConfiguration setStatsOn(boolean argStatsOn) {
		this.requestLogConfiguration.setStatsOn(argStatsOn);
		return this;
	}

	public RiseServerConfiguration setWebApps(List<WebApp> argWebApps) {
		webAppConfiguration.setWebApps(argWebApps);
		return this;
	}

	public RiseServerConfiguration addCustomContextHandler(
			AbstractHandler argCustomContextHandler, String argContextPath,
			String... argHostName) {
		webAppConfiguration.addCustomContextHandler(argCustomContextHandler,
				argContextPath, argHostName);
		return this;
	}

	public RiseServerConfiguration addWebApp(String argContextPath,
			String argAppDir, String... argHostName) {
		webAppConfiguration.addWebApp(argContextPath, argAppDir, argHostName);
		return this;
	}

	public RiseServer build() {
		if (this.getWebAppConfiguration().getWebApps() != null && !this.getWebAppConfiguration().getWebApps().isEmpty()) {
			RiseServer riseServer = new RiseServer();
			riseServer.setRiseServerConfiguration(this);
			riseServer.init();
			return riseServer;
		} else {
			throw new RuntimeException("At least once WebApp needs to be configured.");
		}
	}

}
