package com.example.demo.zuul.filter;

import java.net.SocketTimeoutException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

//@Component
public class TimeoutFilter extends ZuulFilter {

	protected static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletResponse servletResponse = ctx.getResponse();
		servletResponse.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);
		ctx.setThrowable(null); // response is not returned unless
		ctx.remove("error.status_code");

		ctx.setResponseBody("Error");
		ctx.getResponse().setContentType("text/plain");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		boolean should = false;
		RequestContext ctx = RequestContext.getCurrentContext();
		if (ctx.getThrowable() != null) {
			try {
				throw getRootException(ctx.getThrowable());
			} catch (SocketTimeoutException e) {
				should = true;
				ctx.setThrowable(null);
			} catch (Throwable t) {
				should = false;
			}
		}
		return should;
	}

	@Override
	public int filterOrder() {
		return -1;
	}

	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}

	public Throwable getRootException(Throwable exception) {
		Throwable rootException = exception;
		while (rootException.getCause() != null) {
			rootException = rootException.getCause();
		}
		return rootException;
	}
}
