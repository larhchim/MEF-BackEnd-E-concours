package erecrutement.finances.gov.ma.MEF.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
       // http.formLogin();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

       http.authorizeRequests().antMatchers("/AllProfiles","/SearchBYProf/**","/file/**","/login/**","/AddInscription/**","**/**").permitAll();

        http.authorizeRequests().antMatchers("/SearchGestionnaires","/UpdateGest/**","/AddGest","/ModifyDir/**").hasAuthority("ADMIN");



        http.authorizeRequests().antMatchers("/ListDirs","/SearchOneDirection/**","/SearchDirections","/SearchConcours","/ListVilles","/listCentres","/SearchCentres","/SearchGrade","/SearchProfils","/ALLGrades")
                .access("hasAuthority('ADMIN') or hasAuthority('GESTLV1') or hasAuthority('GESTLV2')");


        http.authorizeRequests().antMatchers("/addCentre","/addConcours","/ProfilsManqueGrade/**","UpdateGrade/**","/AddGrade","/addProfile","/UpdateProfile/**","/AllSubscriptions")
                .access("hasAuthority('ADMIN') or hasAuthority('GESTLV1')");


        http.authorizeRequests().antMatchers("/ListGests","/deleteGest/**",
                "/AddDirs","Directions/**","/listcnc","/cncs/**","/deleteConc/**",
                "/updateConc/**","/addVille","/ville/**","/UpdateVille/**","/SearchVilles"
                ,"/Centre/**","/UpdateCentre/**","/file/delete/**","/HistoryG/**").hasAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();


        http.addFilter(new JWTAthentificationFilter(authenticationManager()));
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
