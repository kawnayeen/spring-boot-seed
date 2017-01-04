package com.kawnayeen.logger.security.token.auth;

import com.google.common.util.concurrent.RateLimiter;
import com.kawnayeen.logger.model.LoggerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by kawnayeen on 1/3/17.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenRateLimiter tokenRateLimiter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null) {
            if (authHeader.startsWith(TOKEN_PREFIX)) {
                authHeader = authHeader.replace(TOKEN_PREFIX, "");
                LoggerUser loggerUser = jwtUtil.getLoggerUser(authHeader);

                if (loggerUser != null) {
                    RateLimiter rateLimiter = tokenRateLimiter.getRateLimiter(authHeader);
                    if (rateLimiter.tryAcquire()) {
                        rateLimiter.acquire();
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loggerUser, null, loggerUser.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Rate Limit Exceeded");
                        return;
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }
}
