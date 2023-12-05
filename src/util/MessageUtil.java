package util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
	private static final String messageKey = "ActionMessage";
	private static final String confirmMsg = "confirmMsg";
	private static final String forwardUrlKey = "ForwardUrlKey";
	private static final String callbackType = "callbackType";
	private static final String closeCurrent = "closeCurrent";
	private static final String navTabId = "navTabId";// rel="dlg_page"

	public static void addCloseMessage(Map<String, Object> scope, String message) {
		scope.put(messageKey, message);
		scope.put(callbackType, "closeCurrent");
	}
	/**
	 * 关闭当前标签，刷新指定标签
	 * @param scope
	 * @param message 消息提示
	 * @param rel 刷新的标签ID
	 */
	public static void addCloseMessage(Map<String, Object> scope, String message,String rel) {
		scope.put(messageKey, message);
		scope.put(closeCurrent, true);
		scope.put(navTabId, rel);
	}

	public static void addMessage(Map<String, Object> scope, String message) {
		scope.put(messageKey, message);
	}

	public static void addMessage(Map<String, Object> scope, String message,
			String rel) {
		scope.put(messageKey, message);
		if (StringUtil.isEmpty(rel)) {
			scope.put(navTabId, "navTab");
		} else {
			scope.put(navTabId, rel);
		}
	}
	public static void addRelMessage(HttpServletRequest scope, String message,
			String rel) {
		scope.setAttribute(messageKey, message);
		if (StringUtil.isEmpty(rel)) {
			scope.setAttribute(navTabId, "navTab");
		} else { 
			scope.setAttribute(navTabId, rel);
		}
	}
	public static void addCloseMessage(HttpServletRequest scope, String message,String rel) {
		scope.setAttribute(messageKey, message);
		scope.setAttribute(closeCurrent, true);
		scope.setAttribute(navTabId, rel);
	}
	/**
	 * unixbox 局部刷新中提交后刷新父页面
	 * @param scope
	 * @param message
	 * @param rel
	 */
	public static void addRelMessage(Map<String, Object> scope, String message,
			String rel) {
		scope.put(messageKey, message);
		scope.put("rel", rel);
	}

	public static void addForwardUrl(Map<String, Object> scope, String url) {
		if (StringUtil.notEmpty(url)) {
			scope.put(forwardUrlKey, url);
			scope.put(callbackType, "forward");
		}
	}

	public static void addMessages(Map<String, Object> scope, String message,
			String url) {
		scope.put(messageKey, message);
		addForwardUrl(scope, url);
	}

	/**
	 * 关闭当前标签
	 * 
	 * @param scope
	 * @param message
	 * @param url
	 */
	public static void addCloseMessages(Map<String, Object> scope,
			String message, String url) {
		scope.put(messageKey, message);
		if (StringUtil.notEmpty(url)) {
			scope.put(forwardUrlKey, url);
			scope.put(callbackType, "closeCurrent");
		}
	}

	public static void addMessage(HttpServletRequest scope, String message) {
		scope.setAttribute(messageKey, message);
	}

	public static void addForwardUrl(HttpServletRequest scope, String url) {
		if (StringUtil.notEmpty(url)) {
			scope.setAttribute(forwardUrlKey, url);
			scope.setAttribute(callbackType, "forward");
		}
	}

	public static void addMessages(HttpServletRequest scope, String message,
			String url) {
		scope.setAttribute(messageKey, message);
		addForwardUrl(scope, url);
	}
	public static void addRedirectMessages(HttpServletRequest scope, String message,
			String url ) {
		scope.setAttribute(messageKey, message);
		if (StringUtil.notEmpty(url)) {
			scope.setAttribute(forwardUrlKey, url);
			scope.setAttribute(callbackType, "redirect");
		}
	}
	public static void addConfirmMessages(Map<String, Object> scope, String message,
			String url) {
		scope.put(confirmMsg, message);
		scope.put(forwardUrlKey, url);
		scope.put(callbackType, "forwardConfirm");
	}
	public static void addConfirmMessages(Map<String, Object> scope, String message,
			String url,String rel) {
		scope.put(confirmMsg, message);
		scope.put(forwardUrlKey, url);
		scope.put(callbackType, "forwardConfirm");
		if (StringUtil.isEmpty(rel)) {
			scope.put(navTabId, "navTab");
		} else {
			scope.put(navTabId, rel);
		}
	}
}
