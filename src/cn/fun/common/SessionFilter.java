package cn.fun.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.Constant;
import util.MessageUtil;
import cn.fun.entity.SessionBean;

/**
 * 验证用户权限
 * 
 * @author 
 * 
 */
public class SessionFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(SessionFilter.class);
	private FilterConfig config = null;

	public void destroy() {
		config = null;
		logger = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String _path = ((HttpServletRequest) request).getRequestURI();
		int splitIndex = _path.lastIndexOf("/");
		String path = _path.substring(splitIndex + 1, _path.length());
		String namespace = _path.substring(0, splitIndex);
		logger.info("namespace: "+namespace);

		logger.info("**PATH**: " + path);// carrier_index.action
		if (path == null) {
			forward((HttpServletRequest) request,
					(HttpServletResponse) response,"");
		}
		if (namespace.endsWith("com")) {
			chain.doFilter(request, response);
			return;
		}
		try {
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			SessionBean sb = (SessionBean)session
					.getAttribute(Constant.SESSION_BEAN);
			if(sb == null || sb.getUser()==null){
				login((HttpServletRequest) request,
						(HttpServletResponse) response);
				return;
			}
		} catch (Exception e) {
			login((HttpServletRequest) request,
					(HttpServletResponse) response);
			return;
		}
		chain.doFilter(request, response);
		return;

	}

	private static String stringVerification(String vs) {
		if (vs != null) {
			return vs.replaceAll("[^a-zA-Z0-9_]", "");
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(stringVerification("user_query.action"));
	}

	private void forward(HttpServletRequest request,
			HttpServletResponse response, String message)
			throws ServletException, IOException {
		String IPInfo = ((HttpServletRequest) request).getRemoteAddr();
		logger.info(IPInfo + " Access Denied!");
		MessageUtil.addMessage(request, message);
		request.getRequestDispatcher("/WEB-INF/pages/common/error.jsp")
				.forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String IPInfo = ((HttpServletRequest) request).getRemoteAddr();
		logger.info(IPInfo + " Access Denied.");
		request.setAttribute("message", "notlogin");
		request.getRequestDispatcher("/exit.jsp").forward(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
