package com.data.generator.google.place.api.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.data.generator.google.place.api.exception.BaseUncheckedException;
import com.data.generator.util.GenericJaxbHelper;
import com.data.generator.util.Precondition;
import com.generator.data.xmlns.gplace.api.v1.PlaceDetailsResponse;

/**
 * The Class HttpUtil.
 */
public class HttpUtil {

	private static final List<String> MSIE_VERSIONS = Arrays
			.asList(new String[] { "MSIE 7.0", "MSIE 8.0", "MSIE 9.0" });
	/** The Constant SEMI_COLON. */
	private static final String SEMI_COLON = ";";

	/** The Constant EQUALS. */
	private static final String EQUALS = "=";

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/** The Constant UTF_8. */
	private static final String UTF_8 = "UTF-8";

	/**
	 * Checks if is post.
	 * 
	 * @param argRequestMethod
	 *            the request method
	 * @return true, if is post
	 */
	public static boolean isPost(String argRequestMethod) {
		return HttpConstants.POST.equalsIgnoreCase(argRequestMethod);
	}

	/**
	 * Checks if is gets the.
	 * 
	 * @param argRequestMethod
	 *            the request method
	 * @return true, if is gets the
	 */
	public static boolean isGet(String argRequestMethod) {
		return HttpConstants.GET.equalsIgnoreCase(argRequestMethod);
	}

	/**
	 * Checks if is post.
	 * 
	 * @param argRequest
	 *            the request
	 * @return true, if is post
	 */
	public static boolean isPost(HttpServletRequest argRequest) {
		return HttpConstants.POST.equalsIgnoreCase(argRequest.getMethod());
	}

	/**
	 * Checks if is gets the.
	 * 
	 * @param argRequest
	 *            the request
	 * @return true, if is gets the
	 */
	public static boolean isGet(HttpServletRequest argRequest) {
		return HttpConstants.GET.equalsIgnoreCase(argRequest.getMethod());
	}

	/**
	 * Checks if is connect.
	 * 
	 * @param argRequest
	 *            the request
	 * @return true, if is connect
	 */
	public static boolean isConnect(HttpServletRequest argRequest) {
		return HttpConstants.CONNECT.equalsIgnoreCase(argRequest.getMethod());
	}

	/**
	 * Checks if is connect.
	 * 
	 * @param argMethod
	 *            the method
	 * @return true, if is connect
	 */
	public static boolean isConnect(String argMethod) {
		return HttpConstants.CONNECT.equalsIgnoreCase(argMethod);
	}

	/**
	 * Checks if is delete.
	 * 
	 * @param argRequest
	 *            the request
	 * @return true, if is delete
	 */
	public static boolean isDelete(HttpServletRequest argRequest) {
		return HttpConstants.DELETE.equalsIgnoreCase(argRequest.getMethod());
	}

	/**
	 * Checks if is delete.
	 * 
	 * @param argMethod
	 *            the method
	 * @return true, if is delete
	 */
	public static boolean isDelete(String argMethod) {
		return HttpConstants.DELETE.equalsIgnoreCase(argMethod);
	}

	/**
	 * Gets the request url.
	 * 
	 * @param argRequest
	 *            the request
	 * @return the request url
	 */
	public static String getRequestUrl(HttpServletRequest argRequest) {
		StringBuffer requestURL = argRequest.getRequestURL();
		String queryString = argRequest.getQueryString();
		if (requestURL != null) {
			if (queryString != null && !queryString.isEmpty()) {
				requestURL.append(HttpConstants.QMARK).append(queryString);
			}
			return requestURL.toString();
		}
		return null;
	}

	/**
	 * Gets the request host name.
	 * 
	 * @param argRequest
	 *            the request
	 * @return the request host name
	 */
	public static String getRequestHostName(HttpServletRequest argRequest) {
		String urlStr = getRequestUrl(argRequest);
		if (urlStr != null) {
			try {
				URL url = new URL(urlStr);
				String host = url.getHost();
				return host;
			} catch (MalformedURLException e) {
				// ignore
			}
		}
		return null;
	}

	/**
	 * Gets the request url as http.
	 * 
	 * @param argRequest
	 *            the request
	 * @return the request url as http
	 */
	public static String getRequestUrlAsHttp(HttpServletRequest argRequest) {
		StringBuffer requestURL = argRequest.getRequestURL();
		String queryString = argRequest.getQueryString();
		if (requestURL != null) {
			if (queryString != null && !queryString.isEmpty()) {
				requestURL.append(HttpConstants.QMARK).append(queryString);
			}
			String result = requestURL.toString();
			// result = result.replace("https://", "http://");
			result = StringUtils.replace(result, "https://", "http://");
			return result;
		}
		return null;
	}

	/**
	 * Display origin request headers.
	 * 
	 * @param argOriginRequest
	 *            the origin request
	 */
	public static void displayOriginRequestHeaders(HttpRequest argOriginRequest) {
		if (logger.isDebugEnabled()) {
			Header[] headers = argOriginRequest.getAllHeaders();
			if (headers != null) {
				logger.debug("======================= ORIGIN REQUEST HEADERS : BEGIN  =============================");
				logger.debug(argOriginRequest.getRequestLine().toString());
				for (Header header : headers) {
					logger.debug(header.getName() + ": " + header.getValue());
				}
				logger.debug("======================= ORIGIN REQUEST HEADERS : END    =============================");
			}
		}
	}

	/**
	 * Display origin response headers.
	 * 
	 * @param argOriginResponse
	 *            the origin response
	 */
	public static void displayOriginResponseHeaders(
			HttpResponse argOriginResponse) {
		if (logger.isDebugEnabled()) {
			Header[] headers = argOriginResponse.getAllHeaders();
			if (headers != null) {
				logger.debug("======================= ORIGIN RESPONSE HEADERS : BEGIN =============================");
				logger.debug(argOriginResponse.getStatusLine().toString());
				for (Header header : headers) {
					logger.debug(header.getName() + ": " + header.getValue());
				}
				logger.debug("======================= ORIGIN RESPONSE HEADERS : END   =============================");
			}
		}
	}

	/*
	 * @SuppressWarnings("unchecked") public static void
	 * displayHttpConveration(String argFileName) { GenericJaxbHelper jaxbHelper
	 * = new GenericJaxbHelper<HttpConversation>(
	 * "com.ciphercloud.xmlns.http.v1"); try { JAXBElement<HttpConversation>
	 * httpConversationElement = jaxbHelper .unmarshal(argFileName); if
	 * (httpConversationElement != null) { HttpConversation httpConversation =
	 * httpConversationElement .getValue();
	 * displayHttpConversation(httpConversation); } } catch (Exception e) {
	 * logger.error(e.getMessage(), e); } }
	 * 
	 * @SuppressWarnings("unchecked") public static HttpConversation
	 * getHttpConveration(String argFileName) { GenericJaxbHelper jaxbHelper =
	 * new GenericJaxbHelper<HttpConversation>(
	 * "com.ciphercloud.xmlns.http.v1"); try { JAXBElement<HttpConversation>
	 * httpConversationElement = jaxbHelper .unmarshal(argFileName); if
	 * (httpConversationElement != null) { HttpConversation httpConversation =
	 * httpConversationElement .getValue(); return httpConversation; } } catch
	 * (Exception e) { logger.error(e.getMessage(), e); } return null; } public
	 * static void displayHttpConversation( HttpConversation
	 * argHttpConversation) { if (argHttpConversation != null) {
	 * List<com.ciphercloud.xmlns.http.v1.HttpRequest> httpRequests =
	 * argHttpConversation .getHttpRequest(); if (httpRequests != null &&
	 * !httpRequests.isEmpty()) { int i = 0; for
	 * (com.ciphercloud.xmlns.http.v1.HttpRequest httpRequest : httpRequests) {
	 * System.out.println("Request #" + ++i); System.out .println(
	 * "=========================================================================="
	 * ); if (httpRequest != null) {
	 * System.out.println(formatHttpRequest(httpRequest)); } System.out
	 * .println(
	 * "=========================================================================="
	 * ); } } } } public static String formatHttpRequest(
	 * com.ciphercloud.xmlns.http.v1.HttpRequest argHttpRequest) { if
	 * (argHttpRequest != null) { StringBuilder sb = new StringBuilder(); String
	 * uri = getUri(argHttpRequest.getUrl());
	 * sb.append(CRLF).append(argHttpRequest.getMethod()).append(SPACE)
	 * .append(uri).append(SPACE).append(HTTP_1_1); List<Nvp> headers =
	 * argHttpRequest.getHeader(); for (Nvp nvp : headers) {
	 * sb.append(CRLF).append(nvp.getName()).append(COLON).append(
	 * SPACE).append(nvp.getValue()); } if
	 * (HttpConstants.POST.equalsIgnoreCase(argHttpRequest.getMethod())) {
	 * sb.append(CRLF); List<Nvp> parameters = argHttpRequest.getParameter();
	 * boolean first = true; for (Nvp nvp : parameters) { if (!first) {
	 * sb.append(AMPERSAND); } else { first = false; }
	 * sb.append(nvp.getName()).append(EQUALS).append( nvp.getValue()); } }
	 * return sb.toString(); } return null; }
	 */
	/**
	 * Gets the uri.
	 * 
	 * @param url
	 *            the url
	 * @return the uri
	 */
	public static String getUri(String url) {
		try {
			URI uri = new URI(url);
			String path = uri.getPath();
			String query = uri.getQuery();
			path = path != null ? path : "";
			query = query != null ? query : "";
			return path + query;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Gets the content type.
	 * 
	 * @param argOriginResponse
	 *            the origin response
	 * @return the content type
	 */
	public static String getContentType(HttpResponse argOriginResponse) {
		String mime = null;
		Header header = argOriginResponse
				.getFirstHeader(HttpConstants.CONTENT_TYPE);
		if (header != null) {
			mime = header.getValue();
			int semiColonIndex = mime.indexOf(HttpConstants.SEMI_COLON);
			if (semiColonIndex != -1) {
				mime = mime.substring(0, semiColonIndex);
			}
		}
		return mime;
	}

	/**
	 * Gets the content type.
	 * 
	 * @param argClientRequest
	 *            the client request
	 * @return the content type
	 */
	public static String getContentType(HttpServletRequest argClientRequest) {
		String mimeType = null;
		if (argClientRequest.getContentType() != null
				&& argClientRequest.getContentType().length() > 0) {
			String contentType = argClientRequest.getContentType();
			String[] tokens = contentType.split(SEMI_COLON);
			mimeType = tokens[0];
		}
		return mimeType;
	}

	/**
	 * Gets the char set.
	 * 
	 * @param argClientRequest
	 *            the client request
	 * @return the char set
	 */
	public static String getCharSet(HttpServletRequest argClientRequest) {
		String charSet = null;
		if (argClientRequest.getContentType() != null
				&& argClientRequest.getContentType().length() > 0) {
			String contentType = argClientRequest.getContentType();
			charSet = getCharSet(contentType);
		}
		return charSet;
	}

	/**
	 * Gets the char set.
	 * 
	 * @param contentType
	 *            the content type
	 * @return the char set
	 */
	public static String getCharSet(String contentType) {
		String charSet = null;
		if (contentType != null && !contentType.isEmpty()) {
			String[] tokens = contentType.split(SEMI_COLON);
			if (tokens.length > 1) {
				String encodingType = tokens[1];
				int index = encodingType.indexOf(EQUALS);
				if (index != -1) {
					charSet = encodingType.substring(index + 1,
							encodingType.length());
				}
			}
		}
		return charSet;
	}

	/**
	 * Gets the mime type.
	 * 
	 * @param contentType
	 *            the content type
	 * @return the mime type
	 */
	public static String getMimeType(String contentType) {
		String mimeType = null;
		if (contentType != null && !contentType.isEmpty()) {
			// FIXME If SemiColon Is Not Present
			String[] tokens = contentType.split(SEMI_COLON);
			if (tokens.length > 0) {
				mimeType = tokens[0];
			}
		}
		return mimeType;
	}

	/**
	 * Gets the client request input stream.
	 * 
	 * @param argClientRequest
	 *            the client request
	 * @return the client request input stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static InputStream getClientRequestInputStream(
			HttpServletRequest argClientRequest) throws IOException {
		// String ae =
		// argClientRequest.getHeader(HttpConstants.ACCEPT_ENCODING);
		// if (ae != null && ae.indexOf(HttpConstants.GZIP) >= 0) {
		// return new GZIPInputStream(argClientRequest.getInputStream());
		// }
		return argClientRequest.getInputStream();
	}

	/**
	 * Gets the char set.
	 * 
	 * @param argOriginResponse
	 *            the origin response
	 * @return the char set
	 */
	public static String getCharSet(HttpResponse argOriginResponse) {
		String contentType = null;
		Header header = argOriginResponse
				.getFirstHeader(HttpConstants.CONTENT_TYPE);
		if (header != null) {
			contentType = header.getValue();
		}
		return getCharSet(contentType);
	}

	/**
	 * Gets the host address.
	 * 
	 * @param argHttpRequest
	 *            the http request
	 * @return the host address
	 */
	public static String getHostAddress(HttpRequest argHttpRequest) {
		String originHostName = getHostName(argHttpRequest);
		try {
			InetAddress hostInetAddress = InetAddress.getByName(originHostName);
			return hostInetAddress.getHostAddress();
		} catch (UnknownHostException e) {
			throw new BaseUncheckedException(
					"exception while resolving IP address", e);
		}
	}

	/**
	 * Gets the host name.
	 * 
	 * @param argHttpRequest
	 *            the http request
	 * @return the host name
	 */
	public static String getHostName(HttpRequest argHttpRequest) {
		if (argHttpRequest != null) {
			Header hostHeader = argHttpRequest
					.getFirstHeader(HttpConstants.HOST);
			String originHostName = hostHeader.getValue();
			return originHostName;
		}
		return null;
	}

	/**
	 * Gets the request url.
	 * 
	 * @param argHttpRequest
	 *            the http request
	 * @return the request url
	 */
	public static String getRequestUrl(HttpRequest argHttpRequest) {
		if (argHttpRequest != null) {
			String originHostName = getHostName(argHttpRequest);
			String originRequestUri = argHttpRequest.getRequestLine().getUri();
			return "https://" + originHostName + "/" + originRequestUri;
		}
		return null;
	}

	/**
	 * Decode.
	 * 
	 * @param argStr
	 *            the str
	 * @return the string
	 */
	public static String decode(String argStr) {
		String result = argStr;
		try {
			result = URLDecoder.decode(argStr, UTF_8);
		} catch (UnsupportedEncodingException e) {
			if (logger.isWarnEnabled()) {
				logger.warn(
						"exception while encoding rewritten tokenized query string: "
								+ argStr, e);
			}
		}
		return result;
	}

	/**
	 * Encode. Note: This Method first decodes and then encodes. If you want
	 * only encode, use encodeExplicit method.
	 * 
	 * @param argStr
	 *            the str
	 * @return the string
	 */
	public static String encode(String argStr) {
		String result = argStr;
		try {
			if (result != null && !result.isEmpty()) {
				result = URLDecoder.decode(result, UTF_8);
			}
		} catch (Exception e) {
			// ignore
		}
		result = encodeExplicit(result);
		return result;
	}

	/**
	 * Encode explicit.
	 * 
	 * @param argStr
	 *            the str
	 * @return the string
	 */
	public static String encodeExplicit(String argStr) {
		String result = argStr;
		try {
			if (result != null && !result.isEmpty()) {
				result = URLEncoder.encode(result, UTF_8);
			}
		} catch (UnsupportedEncodingException e) {
			if (logger.isWarnEnabled()) {
				logger.warn(
						"exception while encoding rewritten tokenized query string: "
								+ argStr, e);
			}
		}
		return result;
	}

	public static boolean isMsie8And9(HttpServletRequest argClientRequest) {
		String userAgent = argClientRequest.getHeader(HttpConstants.USER_AGENT);
		// return userAgent != null
		// && userAgent.toLowerCase().contains(MSIE_8_0.toLowerCase());
		if (userAgent != null) {
			for (String msieVersion : MSIE_VERSIONS) {
				if (userAgent.toLowerCase().contains(msieVersion.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isHead(String argRequestMethod) {
		return HttpConstants.HEAD.equalsIgnoreCase(argRequestMethod);
	}

	public static boolean isOptions(String argRequestMethod) {
		return HttpConstants.OPTIONS.equalsIgnoreCase(argRequestMethod);
	}

	public static boolean isPut(String argRequestMethod) {
		return HttpConstants.PUT.equalsIgnoreCase(argRequestMethod);
	}

	public static boolean isTrace(String argRequestMethod) {
		return HttpConstants.TRACE.equalsIgnoreCase(argRequestMethod);
	}

	public static boolean isPatch(String argRequestMethod) {
		return HttpConstants.PATCH.equalsIgnoreCase(argRequestMethod);
	}

	public static String getFullURL(HttpServletRequest req) {
		String scheme = req.getScheme(); // http
		String serverName = req.getServerName(); // hostname.com
		int serverPort = req.getServerPort(); // 80
		String contextPath = req.getContextPath(); // /mywebapp
		String servletPath = req.getServletPath(); // /servlet/MyServlet
		String pathInfo = req.getPathInfo(); // /a/b;c=123
		String queryString = req.getQueryString(); // d=789
		// Reconstruct original requesting URL
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);
		if (serverPort != 80) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath).append(servletPath);
		if (pathInfo != null) {
			url.append(pathInfo);
		}
		if (queryString != null) {
			url.append("?").append(queryString);
		}
		return url.toString();
	}

	public static boolean isBase64(byte[] argBytes) {
		if (argBytes.length > 0) {
			for (int i = 0; i < argBytes.length; i++) {
				boolean isBase64 = Base64.isBase64(argBytes[i]);
				if (!isBase64) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public static InputStream getContent(HttpResponse argResponse)
			throws IllegalStateException, IOException, JAXBException,
			SAXException, ParserConfigurationException {
		Precondition.ensureNotNull(argResponse, "HttpResponse");
		HttpEntity entity = argResponse.getEntity();
		return getContent(entity);
	}

	private static InputStream getContent(HttpEntity argEntity)
			throws IllegalStateException, IOException {
		return argEntity.getContent();
	}

}
