package Texsmartly.Leave.Tracker.config;

import Texsmartly.Leave.Tracker.components.JWTUtils;

import Texsmartly.Leave.Tracker.service.AuthService.OurUserDetailsService;
import java.util.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private OurUserDetailsService ourUserDetailsService;

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
    try {
        // Try to get token from Authorization header first
        String headerToken = jwtUtils.extractJwtFromRequest(request);
        // If not found in header, try cookies
        String cookieToken = headerToken != null ? headerToken
                : getTokenFromCookies(request.getCookies(), "JWT-TOKEN");

        if (cookieToken != null && jwtUtils.validateToken(cookieToken)) {
            String username = jwtUtils.getUsernameFromToken(cookieToken);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    username, null, List.of(()-> jwtUtils.getRoleFromToken(cookieToken))); // Fixed argument syntax
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    } catch (Exception e) {
        System.out.println(e.getStackTrace());
        SecurityContextHolder.clearContext();
    }
    filterChain.doFilter(request, response);
}

    private String getTokenFromCookies(Cookie[] cookies, String cookieName) {
        {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookieName.equals(cookie.getName())) {
                        return cookie.getValue();
                    }
                }
            }
            return null;
        }
    }
    


}
