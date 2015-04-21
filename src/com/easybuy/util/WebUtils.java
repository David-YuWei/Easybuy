/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easybuy.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http protocol related functions
 * @author weiyu
 */
public class WebUtils {

	public WebUtils() {
	}

	public static void cache(HttpServletResponse response, int second) {
		long currentTime = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", currentTime);
		response.setDateHeader("Expires", currentTime + second * 1000);
		response.setHeader("Cache-Control", "max-age=" + second);
	}

	public static void noCache(HttpServletResponse response) {
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		response.setDateHeader("Expires", 1L);
	}

	public static void ETag(HttpServletResponse response, String token) {
		ETag(response, token, false);
	}

	public static void ETag(HttpServletResponse response, String token, boolean weak) {
		StringBuilder buf = new StringBuilder();
		if (weak) {
			buf.append("W/\"");
		} else {
			buf.append("\"");
		}
		buf.append(token);
		buf.append("\"");
		response.setHeader("ETag", buf.toString());
	}

	public static void XSendfile(HttpServletResponse response, String location) {
		response.addHeader("X-Accel-Redirect", location);
	}

	public static String remoteAddress(HttpServletRequest request) {
		String ip = null;
		String xff = request.getHeader("x-forwarded-for");
		if (xff != null && xff.length() != 0 && !xff.equalsIgnoreCase("unknown")) {
			for (String entry : xff.split(",")) {
				entry = entry.trim();
				if (!entry.equalsIgnoreCase("unknown")) {
					ip = entry;
					break;
				}
			}
		} else {
			ip = request.getHeader("Proxy-Client-IP");
			if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
				ip = request.getRemoteAddr();
				if (ip.equals("127.0.0.1")) {
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
						ip = inet.getHostAddress();
					} catch (UnknownHostException e) {
					}
				}
			}
		}
		if (ip != null && ip.length() > 0) {
			int ci = ip.indexOf(':');
			if (ci > 0) {
				ip = ip.substring(0, ci);
			}
		}
		return ip;
	}
}
