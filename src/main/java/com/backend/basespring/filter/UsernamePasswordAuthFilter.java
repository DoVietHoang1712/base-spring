package com.backend.basespring.filter;

import com.backend.basespring.config.UserAuthenticationProvider;
import com.backend.basespring.dto.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private UserAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if ("v1/auth/signIn".equals(httpServletRequest.getServletPath()) && HttpMethod.POST.matches(httpServletRequest.getMethod())) {
            LoginDto loginDto = OBJECT_MAPPER.readValue(httpServletRequest.getInputStream(), LoginDto.class);
            try {
                SecurityContextHolder.getContext().setAuthentication(
                        userAuthenticationProvider.validateCredential(loginDto)
                );
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
            }
        }
    }
}
