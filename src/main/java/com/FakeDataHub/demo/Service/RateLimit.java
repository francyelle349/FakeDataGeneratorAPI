package com.FakeDataHub.demo.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimit implements Filter {
	private final Map<String, AtomicInteger> requestPerIp = new ConcurrentHashMap<>();
	
	private static final int MAX_REQUESTS_PER_MINUTE = 10;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			  throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String clientIpAddress = httpServletRequest.getRemoteAddr();
        
        // Initialize request count for the client IP address
        requestPerIp.putIfAbsent(clientIpAddress, new AtomicInteger(0));
        AtomicInteger requestCount = requestPerIp.get(clientIpAddress);
        
        // Increment the request count
        int requests = requestCount.incrementAndGet();

        // Check if the request limit has been exceeded
        if (requests > MAX_REQUESTS_PER_MINUTE) {
            httpServletResponse.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
            httpServletResponse.getWriter().write("Too many requests. Please try again later.");
            return;
        }

        // Allow the request to proceed
        chain.doFilter(request, response);
	
	
}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Optional: Initialization logic, if needed
    }

    @Override
    public void destroy() {
        // Optional: Cleanup resources, if needed
    }
}
