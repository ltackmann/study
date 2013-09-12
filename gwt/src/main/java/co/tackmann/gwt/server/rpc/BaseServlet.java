package org.randompage.bookmarking.frontend.server.rpc;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class BaseServlet extends RemoteServiceServlet implements Controller, ServletContextAware {
	private static final long serialVersionUID = 6604641531464818184L;
	private ServletContext servletContext;

	public final ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		doPost(req, resp);
		return null;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
