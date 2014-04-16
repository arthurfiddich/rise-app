package com.data.generator.google.place.api.http;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.util.Precondition;

public class HttpClientImpl {

	private static Logger logger = LoggerFactory
			.getLogger(HttpClientImpl.class);
	public static HttpClientImpl httpClientImpl;
	public static HttpClient httpClient;

	public static HttpClientImpl getInstance() {
		if (Precondition.checkNull(httpClientImpl)) {
			createInstance();
			return new HttpClientImpl();
		}
		return httpClientImpl;
	}

	private static void createInstance() {
		httpClient = HttpClientBuilder.create().build();
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public HttpRequest createRequest(String argMethod, String argUrlStr) {
		if (HttpUtil.isPost(argMethod)) {
			return createPostRequest(argUrlStr);
		} else if (HttpUtil.isGet(argMethod)) {
			return createGetRequest(argUrlStr);
		} else if (HttpUtil.isDelete(argMethod)) {
			return createDeleteRequest(argUrlStr);
		} else if (HttpUtil.isOptions(argMethod)) {
			return createOptionsRequest(argUrlStr);
		} else if (HttpUtil.isHead(argMethod)) {
			return createHeadRequest(argUrlStr);
		} else if (HttpUtil.isPut(argMethod)) {
			return createPutRequest(argUrlStr);
		} else if (HttpUtil.isTrace(argMethod)) {
			return createTraceRequest(argUrlStr);
		} else if (HttpUtil.isPatch(argMethod)) {
			return createPatchRequest(argUrlStr);
		} else {
			try {
				throw new HttpException("unsupported HTTP Method: " + argMethod);
			} catch (HttpException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}

	public HttpRequest createPostRequest(String argUrlStr) {
		return new HttpPost(argUrlStr);
	}

	public HttpRequest createGetRequest(String argUrlStr) {
		return new HttpGet(argUrlStr);
	}

	public HttpRequest createDeleteRequest(String argUrlStr) {
		return new HttpDelete(argUrlStr);
	}

	private HttpRequest createTraceRequest(String argUrlStr) {
		return new HttpTrace(argUrlStr);
	}

	private HttpRequest createPatchRequest(String argUrlStr) {
		return new HttpPatch(argUrlStr);
	}

	private HttpRequest createPutRequest(String argUrlStr) {
		return new HttpPut(argUrlStr);
	}

	private HttpRequest createHeadRequest(String argUrlStr) {
		return new HttpHead(argUrlStr);
	}

	private HttpRequest createOptionsRequest(String argUrlStr) {
		return new HttpOptions(argUrlStr);
	}

}
