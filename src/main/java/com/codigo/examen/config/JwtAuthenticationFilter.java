package com.codigo.examen.config;

import com.codigo.examen.service.JWTService;
import com.codigo.examen.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UsuarioService usuarioService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String autHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if(StringUtils.isEmpty(autHeader) || !StringUtils.startsWithIgnoreCase(autHeader, "Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        jwt = autHeader.substring(7);
        username = jwtService.extractUserName(jwt);


        //METODO AUTENTICAR USER
        //verificar que el username no es nulo
        if(Objects.nonNull(username) && SecurityContextHolder.getContext().getAuthentication() == null){
            //carga los detalles del service
            UserDetails userDetails = usuarioService.userDetailsService().loadUserByUsername(username);
            //Validacion de token
            if(jwtService.validateToken(jwt,userDetails)){
                //si es valido procede a la creacion de usuario
                //se crea una seguridad vacia
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                //se crea el usuario con los detalles
                UsernamePasswordAuthenticationToken autToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                autToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                securityContext.setAuthentication(autToken);
                SecurityContextHolder.setContext(securityContext);
            }

        }
        filterChain.doFilter(request,response);
    }
}
