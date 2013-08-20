package com.rise.daemon.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.http.HttpScheme;
import org.eclipse.jetty.server.AbstractConnectionFactory;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.LowResourceMonitor;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.server.session.AbstractSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

import com.rise.common.util.checker.Precondition;
import com.rise.daemon.server.practice.ExampleServer.HelloServlet;
import com.rise.daemon.server.practice.HelloHandler;

public class RiseServer {

	private RiseServerConfiguration riseServerConfiguration;
	private Server server;

	public RiseServer() {
		// Default Constructor
	}

	public void init() {
		Server newServer = new Server(this.getRiseServerConfiguration()
				.getHttpPort());

		((QueuedThreadPool) newServer.getThreadPool()).setMaxThreads(this
				.getRiseServerConfiguration().getThreadPoolSize());
		((QueuedThreadPool) newServer.getThreadPool()).setMaxThreads(this
				.getRiseServerConfiguration().getThreadPoolSize());
		newServer.removeConnector(newServer.getConnectors()[0]);
		ServerConnector httpConnector = getHttpConnector(newServer);
		newServer.addConnector(httpConnector);

		addHttpsConnector(newServer);

		addWebApps(newServer);

		LowResourceMonitor lowResourceMonitor = new LowResourceMonitor(
				newServer);
		lowResourceMonitor.setPeriod(1000);
		lowResourceMonitor.setLowResourcesIdleTimeout(200);
		lowResourceMonitor.setMonitorThreads(true);
		lowResourceMonitor.setMaxConnections(0);
		lowResourceMonitor.setMaxMemory(0);
		lowResourceMonitor.setMaxLowResourcesTime(5000);
		newServer.addBean(lowResourceMonitor);
		
//		newServer.setHandler(new HelloHandler("Hi", "Hello"));
//		ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
//		HandlerCollection handlerCollection = new HandlerCollection();
//		ServletHandler handler = new ServletHandler();
//		handler.addServletWithMapping(HelloServlet.class, "/one1");
//		contextHandlerCollection.setHandlers(new Handler[]{new HelloHandler("Hi", "Hello"),handler});
//		handlerCollection.setHandlers(new Handler[]{new HelloHandler("Hi", "Hello"),handler});
//		newServer.setHandler(handlerCollection);
		

		if (Precondition.checkNotNull(newServer)) {
			this.server = newServer;
		}
	}

	private void addWebApps(Server argNewServer) {
		List<Handler> handlersList = getWebappHandlers();
		RequestLogHandler requestLogHandler = getRequestLogHandler();
		HandlerCollection handlerCollection = new HandlerCollection();
		if (Precondition.checkNotNull(requestLogHandler)) {
			handlersList.add(requestLogHandler);
			Handler[] handlersArray = new Handler[handlersList.size()];
			handlersList.toArray(handlersArray);
			handlerCollection.setHandlers(handlersArray);
			argNewServer.setHandler(handlerCollection);
		} else {
			Handler[] handlersArray = new Handler[handlersList.size()];
			handlersList.toArray(handlersArray);
			handlerCollection.setHandlers(handlersArray);
			argNewServer.setHandler(handlerCollection);
		}
	}

	private List<Handler> getWebappHandlers() {
		List<Handler> handlersList = new ArrayList<Handler>();
		WebAppConfiguration webAppConfiguration = this
				.getRiseServerConfiguration().getWebAppConfiguration();
		if (Precondition.checkNotNull(webAppConfiguration)) {
			List<WebApp> webAppsList = webAppConfiguration.getWebApps();
			if (Precondition.checkNotEmpty(webAppsList)) {
				for (WebApp webApp : webAppsList) {
					if (webApp.customContextHandler) {
						ContextHandler contextHandler = new ContextHandler();
						contextHandler.setContextPath(webApp.contextPath);
						contextHandler.setHandler(webApp.jettyHandler);
						contextHandler.setVirtualHosts(webApp.hostName);
						handlersList.add(contextHandler);
					} else {
						WebAppContext webAppContext = new WebAppContext();
						webAppContext.setResourceBase(webApp.appDir);
						webAppContext.setDescriptor(webApp.appDir
								+ "/WEB-INF/web.xml");
						webAppContext.setContextPath(webApp.contextPath);
						webAppContext.setParentLoaderPriority(true);
						// webAppContext.setWar(webApp.appDir);
						webAppContext.setVirtualHosts(webApp.hostName);
						SessionHandler sessionHandler = webAppContext
								.getSessionHandler();
						SessionManager sessionManager = sessionHandler
								.getSessionManager();
						if (sessionManager instanceof AbstractSessionManager) {
							AbstractSessionManager abstractSessionManager = (AbstractSessionManager) sessionManager;
							abstractSessionManager.setUsingCookies(true);
							abstractSessionManager.setHttpOnly(true);
						}
						handlersList.add(webAppContext);
					}
				}
			}
		}
		return handlersList;
	}

	private RequestLogHandler getRequestLogHandler() {
		RequestLogHandler requestLogHandler = null;
		if (this.getRiseServerConfiguration().isAccessLogEnabled()) {
			requestLogHandler = new RequestLogHandler();
			NCSARequestLog ncsaRequestLog = new NCSARequestLog(getLogFileName());
			ncsaRequestLog.setFilenameDateFormat("yyyy-MM-dd");
			ncsaRequestLog.setRetainDays(this.getRiseServerConfiguration()
					.getAccessLogRetainDays());
			ncsaRequestLog.setAppend(this.getRiseServerConfiguration()
					.isAccessLogAppend());
			ncsaRequestLog.setExtended(this.getRiseServerConfiguration()
					.isAccessLogExtended());
			ncsaRequestLog.setLogCookies(this.getRiseServerConfiguration()
					.isAccessLogCookies());
			ncsaRequestLog.setLogTimeZone(this.getRiseServerConfiguration()
					.getAccessLogTimeZone());
			ncsaRequestLog.setLogServer(this.getRiseServerConfiguration()
					.isAccessLogServerName());
			ncsaRequestLog.setLogLatency(this.getRiseServerConfiguration()
					.isAccessLogEnableLatency());
		}
		return requestLogHandler;
	}

	private String getLogFileName() {
		return this.getRiseServerConfiguration().getAccessLogDirectory()
				+ File.separator
				+ this.getRiseServerConfiguration().getAccessLogFilename();
	}

	private void addHttpsConnector(Server argNewServer) {
		if (Precondition.checkNotEmpty(this.getRiseServerConfiguration()
				.getKeystoreFile())
				&& Precondition.checkNotEmpty(this.getRiseServerConfiguration()
						.getKeystorePassword())) {
			SslContextFactory sslContextFactory = new SslContextFactory();
			sslContextFactory.setKeyStorePath(this.getKeystorePath());
			sslContextFactory.setKeyStorePassword(this
					.getRiseServerConfiguration().getKeystorePassword());
			sslContextFactory.setKeyManagerPassword(this
					.getRiseServerConfiguration().getKeyPassowrd());
			sslContextFactory.setTrustStorePath(this.getKeystorePath());
			sslContextFactory.setTrustStorePassword(this
					.getRiseServerConfiguration().getKeyPassowrd());
			String[] excluded = this.getRiseServerConfiguration().getExcluded();
			if (excluded != null && excluded.length > 0) {
				sslContextFactory.setExcludeCipherSuites(excluded);
			}
			String[] included = this.getRiseServerConfiguration().getIncluded();
			if (included != null && included.length > 0) {
				sslContextFactory.setIncludeCipherSuites(included);
			}
			ServerConnector httpsConnector = getHttpsConnector(argNewServer,
					sslContextFactory);
			argNewServer.addConnector(httpsConnector);
		}
	}

//	private String[] getExcludedCipherSuites() {
//		String[] excluded = this.getRiseServerConfiguration().getExcluded();
//		if (excluded != null && excluded.length > 0) {
//			return excluded;
//		}
//		return null;
//	}

	private String getKeystorePath() {
		String keystoreFile = this.getRiseServerConfiguration()
				.getKeystoreFile();
		return this.getClass().getResource(keystoreFile).toExternalForm();
	}

	private ServerConnector getHttpConnector(Server argServer) {
		org.eclipse.jetty.server.HttpConfiguration httpConfiguration = getHttpConfiguration();
		ServerConnector httpConnector = initializeHttpServerConnector(
				argServer, httpConfiguration);
		String httpHost = this.getRiseServerConfiguration().getHttpHost();
		if (Precondition.checkNotEmpty(httpHost)) {
			httpConnector.setHost(httpHost);
		}
		httpConnector.setAcceptQueueSize(this.getRiseServerConfiguration()
				.getAcceptorQueueSize());
		httpConnector.setPort(this.getRiseServerConfiguration().getHttpPort());
		httpConnector.setIdleTimeout(this.getRiseServerConfiguration()
				.getMaxIdleTime());
		httpConnector.setName(HttpScheme.HTTP.name());
		return httpConnector;
	}

	private ServerConnector getHttpsConnector(Server newServer,
			SslContextFactory argSslContextFactory) {
		org.eclipse.jetty.server.HttpConfiguration httpsConfiguration = getHttpsConfiguration();
		ServerConnector httpConnector = initializeHttpsServerConnector(
				newServer, httpsConfiguration, argSslContextFactory);
		String httpHost = this.getRiseServerConfiguration().getHttpHost();
		if (Precondition.checkNotEmpty(httpHost)) {
			httpConnector.setHost(httpHost);
		}
		httpConnector.setAcceptQueueSize(this.getRiseServerConfiguration()
				.getAcceptorQueueSize());
		httpConnector.setPort(this.getRiseServerConfiguration().getHttpsPort());
		httpConnector.setIdleTimeout(this.getRiseServerConfiguration()
				.getMaxIdleTime());
		httpConnector.setName(HttpScheme.HTTPS.name());
		return httpConnector;
	}

	private ServerConnector initializeHttpsServerConnector(Server argNewServer,
			HttpConfiguration argHttpConfiguration,
			SslContextFactory argSslContextFactory) {
		HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(
				argHttpConfiguration);
		ConnectionFactory[] connectionFactories = AbstractConnectionFactory
				.getFactories(argSslContextFactory, httpConnectionFactory);
		int acceptorThreadSize = this.getRiseServerConfiguration()
				.getAcceptorThreadSize();
		if (Precondition.checkNonNegative(acceptorThreadSize)) {
			return new ServerConnector(argNewServer, null, null, null,
					acceptorThreadSize, 0, connectionFactories);
		}
		return new ServerConnector(argNewServer, connectionFactories);
	}

	private ServerConnector initializeHttpServerConnector(Server argNewServer,
			HttpConfiguration argHttpConfiguration) {
		int acceptorThreadSize = this.getRiseServerConfiguration()
				.getAcceptorThreadSize();
		HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(
				argHttpConfiguration);
		if (Precondition.checkNonNegative(acceptorThreadSize)) {
			return new ServerConnector(argNewServer, null, null, null,
					acceptorThreadSize, 0, httpConnectionFactory);
		}
		return new ServerConnector(argNewServer, httpConnectionFactory);
	}

	private org.eclipse.jetty.server.HttpConfiguration getHttpConfiguration() {
		org.eclipse.jetty.server.HttpConfiguration httpConfiguration = new org.eclipse.jetty.server.HttpConfiguration();
		httpConfiguration.setOutputBufferSize(32 * 1024);
		httpConfiguration.setRequestHeaderSize(this
				.getRiseServerConfiguration().getHttpRequestHeaderSize());
		httpConfiguration.setResponseHeaderSize(this
				.getRiseServerConfiguration().getHttpResponseHeaderSize());
		httpConfiguration.setSendServerVersion(true);
		httpConfiguration.setSendDateHeader(false);
		return httpConfiguration;
	}

	private HttpConfiguration getHttpsConfiguration() {
		org.eclipse.jetty.server.HttpConfiguration httpsConfiguration = new org.eclipse.jetty.server.HttpConfiguration();
		httpsConfiguration.setSecureScheme(HttpScheme.HTTPS.asString());
		httpsConfiguration.setSecurePort(this.getRiseServerConfiguration().getHttpsPort());
		httpsConfiguration.setOutputBufferSize(32 * 1024);
		httpsConfiguration.setRequestHeaderSize(this
				.getRiseServerConfiguration().getHttpsRequestHeaderSize());
		httpsConfiguration.setResponseHeaderSize(this
				.getRiseServerConfiguration().getHttpsResponseHeaderSize());
		httpsConfiguration.setSendServerVersion(true);
		httpsConfiguration.setSendDateHeader(false);
		httpsConfiguration.addCustomizer(new SecureRequestCustomizer());
		return httpsConfiguration;
	}

	public void start() throws Exception {
		this.getServer().start();
		this.getServer().join();
	}

	public void stop() throws Exception {
		this.getServer().stop();
	}

	public RiseServerConfiguration getRiseServerConfiguration() {
		return this.riseServerConfiguration;
	}

	public void setRiseServerConfiguration(
			RiseServerConfiguration argRiseServerConfiguration) {
		this.riseServerConfiguration = argRiseServerConfiguration;
	}

	public Server getServer() {
		return this.server;
	}

	public void setServer(Server argServer) {
		this.server = argServer;
	}

}
