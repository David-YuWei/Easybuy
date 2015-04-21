/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easybuy.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author weiyu
 */
public class HttpExtensionFilter implements Filter {

	public static final String MULTIPART = "multipart/";
	/** Default method head: <code>X-HTTP-Method-Override</code> */
	public static final String DEFAULT_METHOD_HEAD = "X-HTTP-Method-Override";
	/** Default method parameter: <code>_method</code> */
	public static final String DEFAULT_METHOD_PARAM = "_method";
	/** Default decode parameter: <code>_decode</code> */
	public static final String DEFAULT_DECODE_PARAM = "_decode";
	public static final String DEFAULT_DECODE_PARAM_VALUE = "UTF-8";
	private String methodHead = DEFAULT_METHOD_HEAD;
	private String methodParam = DEFAULT_METHOD_PARAM;
	private String decodeParam = DEFAULT_DECODE_PARAM;

	public HttpExtensionFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		initMethodHead(filterConfig.getInitParameter("methodHead"));
		initMethodParam(filterConfig.getInitParameter("methodParam"));
		initDecodeParam(filterConfig.getInitParameter("decodeParam"));
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if (!(req instanceof HttpServletRequest) || !(res instanceof HttpServletResponse)) {
			throw new ServletException("HttpExtensionFilter just supports HTTP requests");
		}
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		chain.doFilter(wrapRequest(request), response);
	}

	@Override
	public void destroy() {
	}

	private HttpServletRequest wrapRequest(HttpServletRequest request) throws UnsupportedEncodingException {
		HttpServletRequest wrapped = request;
		String method = null;
		String decode = null;
		boolean multipart = isMultipartContent(request);

		method = request.getHeader(methodHead);
		if (method == null) {
			if (multipart) {
				method = detectParam(request, this.methodParam);
			} else {
				method = request.getParameter(this.methodParam);
			}
		}

		if (multipart) {
			decode = detectParam(request, this.decodeParam);
		} else {
			decode = request.getParameter(this.decodeParam);
		}
		if (decode != null && decode.isEmpty()) {
			decode = DEFAULT_DECODE_PARAM_VALUE;
		}

		if (method != null || decode != null) {
			wrapped = new HttpRequestExtensionWrapper(request, method, decode);
		}
		return wrapped;
	}

	private boolean isMultipartContent(HttpServletRequest request) {
		if (!"POST".equalsIgnoreCase(request.getMethod())) {
			return false;
		}
		String contentType = request.getContentType();
		if (contentType == null) {
			return false;
		}
		if (contentType.toLowerCase().startsWith(MULTIPART)) {
			return true;
		}
		return false;
	}

	private static String detectParam(HttpServletRequest request, String param) {
		if ("GET".equalsIgnoreCase(request.getMethod())) {
			return request.getParameter(param);
		} else {
			String query = request.getQueryString();
			if (query == null) {
				return null;
			} else {
				String value = null;
				int pi = query.indexOf(param + "=");
				if (pi != -1) {
					int si = pi + param.length() + 1;
					int ei = query.indexOf('&', si);
					if (ei == -1) {
						value = query.substring(si);
					} else {
						value = query.substring(si, ei);
					}
				}
				return value;
			}
		}
	}

	/**
	 * Set the head name to look for HTTP methods.
	 * @see #DEFAULT_METHOD_HEAD
	 */
	private void initMethodHead(String methodHead) {
		if (methodHead != null) {
			if (methodHead.trim().isEmpty()) {
				throw new IllegalArgumentException("'methodHead' must not be empty");
			}
			this.methodHead = methodHead;
		}
	}

	/**
	 * Set the parameter name to look for HTTP methods.
	 * @see #DEFAULT_METHOD_PARAM
	 */
	private void initMethodParam(String methodParam) {
		if (methodParam != null) {
			if (methodParam.trim().isEmpty()) {
				throw new IllegalArgumentException("'methodParam' must not be empty");
			}
			this.methodParam = methodParam;
		}
	}

	/**
	 * Set the parameter name to look for decode.
	 * @see #DEFAULT_DECODE_PARAM
	 */
	private void initDecodeParam(String decodeParam) {
		if (decodeParam != null) {
			if (decodeParam.trim().isEmpty()) {
				throw new IllegalArgumentException("'decodeParam' must not be empty");
			}
			this.decodeParam = decodeParam;
		}
	}

	/**
	 * {@link HttpServletRequest} extension wrapper
	 */
	public static class HttpRequestExtensionWrapper extends HttpServletRequestWrapper {

		private final String method;
		private final String decode;
		private Map<String, String[]> params;

		public HttpRequestExtensionWrapper(HttpServletRequest request, String method, String decode) throws UnsupportedEncodingException {
			super(request);
			this.method = (method == null || method.isEmpty()) ? request.getMethod() : method.toUpperCase(Locale.ENGLISH);
			this.decode = decode;
		}

		@Override
		public String getMethod() {
			return this.method;
		}

		@Override
		public String getParameter(String name) {
			wrappParameters();
			String[] values = params.get(name);
			if (values == null || values.length == 0) {
				return null;
			} else {
				return values[0];
			}
		}

		@Override
		public Map getParameterMap() {
			wrappParameters();
			return params;
		}

		@Override
		public String[] getParameterValues(String name) {
			wrappParameters();
			return params.get(name);
		}

		private void wrappParameters() {
			if (params == null) {
				if (decode == null) {
					params = super.getParameterMap();
				} else {
					try {
						Map<String, String[]> undecodeMap = super.getParameterMap();
						Map<String, String[]> decodeMap = new HashMap<String, String[]>(undecodeMap.size());
						for (Map.Entry<String, String[]> entry : undecodeMap.entrySet()) {
							String[] values = entry.getValue();
							for (int i = 0; i < values.length; i++) {
								String value = values[i];
								values[i] = (value == null) ? null : URLDecoder.decode(value, decode);
							}
							decodeMap.put(entry.getKey(), values);
						}
						params = Collections.unmodifiableMap(decodeMap);
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException("Unsupported encoding '" + decode + "'. check your decodeParam.", e);
					}
				}
			}
		}
	}
}
