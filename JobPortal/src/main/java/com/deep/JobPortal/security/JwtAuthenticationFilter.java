package com.deep.JobPortal.security;

import com.deep.JobPortal.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
            )throws ServletException, IOException {

        //Step 1: Extract Authorization Header
         String authHeader = request.getHeader("Authorization");

        // If no header Skip Jwt
        if (authHeader == null && !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        //Step 2: Extract JWT token remove bearer
        final String jwt = authHeader.substring(7);

        // Step 3: Extract username(email) from token
        String userEmail = null;
        try{
            userEmail =jwtService.extractEmail(jwt);

        }catch (Exception e){
            //Token is invalid
            filterChain.doFilter(request, response);
            return;
        }

        // Step 4: If email exist and user is not already authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        }

        // Step 5: Load user from database using email
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

        //Step 6: Validate token
        /*
        Checks
          Token signature is valid
          Token has not expired
          Username in token matches loaded user
         */

        if (jwtService.isTokenValid(jwt, userDetails)) {

            //Step 7: Create Authentication token
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,                             // Principal who is the user
                    null,                                   //  Credentials we already validate via Jwt
                    userDetails.getAuthorities()            //  Roles
            );

            // Add request details (IP address, session info)
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            //Step 8: Set authentication in SecurityContext
            // Now Spring Security Knows this request is authenticated
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        //Step 9: Continue to next filter or Controller
        filterChain.doFilter(request, response);
    }
}
