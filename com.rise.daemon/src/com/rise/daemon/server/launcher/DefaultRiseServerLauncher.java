package com.rise.daemon.server.launcher;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rise.daemon.server.RiseServer;
import com.rise.daemon.server.RiseServerConfiguration;

public class DefaultRiseServerLauncher {

	private static Logger logger = LoggerFactory
			.getLogger(DefaultRiseServerLauncher.class);

	RiseServer server = null;

	public void start() {
		if (logger.isInfoEnabled()) {
			logger.info("Starting RiseServer");
		}
		Properties RiseProps = new Properties();
		InputStream is = null;
		try {
			is = this.getClass().getResourceAsStream("/rise.conf");
			RiseProps.load(is);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Error while initializing RiseServer Config.", e);
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception ignore) {
					// ignore
				}
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("Successfully loaded RiseServer Config");
			logger.info("Initializing RiseAppCache");
		}
		if (logger.isInfoEnabled()) {
			logger.info("RiseAppCache initialized successfully");
		}
		String confFileLocation = this.getClass().getResource("/rise.conf")
				.getFile();
		File confFile = new File(confFileLocation);
		File webAppDir = new File(confFile.getParentFile().getParentFile()
				.getAbsolutePath()
				+ "/webapp");
		try {
			if (webAppDir.exists() && webAppDir.isDirectory()) {
				this.server = new RiseServerConfiguration(true).addWebApp("/rise",
						webAppDir.getAbsolutePath(), "localhost").build();
			} else {
				throw new RuntimeException("WebApp Directory does not exists.");
			}
			this.server.start();
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Failed to start RiseServer", e);
			}
			e.printStackTrace();
//			throw new RuntimeException("Failed to start rise server: ", e);
		}
		if (logger.isInfoEnabled()) {
			logger.info("RiseServer started on port 80 and 443.");
		}
	}

	public void stop() throws Exception {
		if (server != null) {
			if (logger.isInfoEnabled()) {
				logger.info("Shutting down RiseServer.");
			}
			try {
				this.server.stop();
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("Failed to stop RiseServer properly", e);
				}
			}
			if (logger.isInfoEnabled()) {
				logger.info("RiseServer stopped.");
			}
		} else {
			if (logger.isErrorEnabled()) {
				logger.error("RiseServer not running. Nothing to stop.");
			}
		}
	}

	public static void main(String[] args) {
		DefaultRiseServerLauncher server = new DefaultRiseServerLauncher();
		server.start();
	}

}
