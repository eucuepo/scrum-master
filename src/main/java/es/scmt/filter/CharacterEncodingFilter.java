package es.scmt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		// esta parte evita el cacheo de paginas
		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		httpResponse.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // HTTP
																						// 1.1
		httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
		httpResponse.setDateHeader("Expires", 0); // Proxies.
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}