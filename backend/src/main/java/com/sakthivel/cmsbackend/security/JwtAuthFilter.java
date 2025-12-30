package com.sakthivel.cmsbackend.security;

import com.sakthivel.cmsbackend.Dao.UserPrincipal;
import com.sakthivel.cmsbackend.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final MyUserDetailsService myUserDetailsService;

    public JwtAuthFilter(
            @Autowired JwtUtil jwtUtil,
            @Autowired MyUserDetailsService myUserDetailsService
    ) {
        this.jwtUtil = jwtUtil;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null, username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtUtil.getSubject(token);
        }

        System.out.println("Token : "+token);
        System.out.println("Username : "+username);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserPrincipal userDetails = (UserPrincipal) myUserDetailsService.loadUserByUsername(username);

            System.out.println(userDetails);

            if (userDetails!=null && jwtUtil.validateToken(token, userDetails)) {
                System.out.println("OOOOH");

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                System.out.println(authenticationToken);

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                System.out.println("2OOOH");
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        System.out.println("3OOOOh");

        filterChain.doFilter(request, response);
    }
}