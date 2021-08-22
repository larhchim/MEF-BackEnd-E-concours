package erecrutement.finances.gov.ma.MEF.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAthentificationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAthentificationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            Gestionnaires appUser= new ObjectMapper().readValue(request.getInputStream(),Gestionnaires.class);
            System.out.println("*************************");
            System.out.println("*************************");
            System.out.println("Username==>"+appUser.getLogin());
            System.out.println("Password==>"+appUser.getMotDePasse());
            System.out.println("*************************");
            System.out.println("*************************");
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getLogin(),appUser.getMotDePasse()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("User not found Exception");
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User springUser = (User) authResult.getPrincipal();
        String jwt = Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+SecurityParams.EXPIRATION))
                .signWith(SignatureAlgorithm.HS256,SecurityParams.SECRET)
                .claim("roles",springUser.getAuthorities())
                .compact();
          response.addHeader(SecurityParams.JWT_HEADER_NAME,SecurityParams.HEADER_PREFIX+jwt);
    }
}
