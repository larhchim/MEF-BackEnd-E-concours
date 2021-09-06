package erecrutement.finances.gov.ma.MEF.Security;

import erecrutement.finances.gov.ma.MEF.DAO.ConcoursDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Date;

@Configuration
@EnableWebSecurity
@EnableScheduling
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    int i =0;

    private ConcoursDAO concoursDAO;

    @Autowired
    public void setConcoursDAO(ConcoursDAO concoursDAO) {
        this.concoursDAO = concoursDAO;
    }

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

       http.authorizeRequests().antMatchers("/AllProfiles","/SearchBYProf/**","/file/**","/login/**","/AddInscription/**","**/**","/users/**","/RecoveryPass/**","/UpdateRecover/**","/download/Results/**").permitAll();

        http.authorizeRequests().antMatchers("/SearchGestionnaires","/UpdateGest/**","/AddGest","/ModifyDir/**").hasAuthority("ADMIN");



        http.authorizeRequests().antMatchers("/ListDirs","/SearchOneDirection/**","/SearchDirections","/SearchConcours","/ListVilles","/listCentres","/SearchCentres","/SearchGrade","/SearchProfils","/ALLGrades","/GestGlobalStats","/GestDirectionStats/**","/ConcoursStatsDirection/**","/ConcoursOfProfileStats/**","/InscriptionsStats","/InscriptionsStatsConcours/**","/CentreStats/**","/listcnc","/SearchInscriptions/**","/AddHistoryCandidate/**","/SearchHistoryCandidate/**","/UpdateInscription/**","/HistoryChangeStatusOfCandidates","/HistoryChangeStatusOfCandidates/**","/Examinators")
                .access("hasAuthority('ADMIN') or hasAuthority('GESTLV1') or hasAuthority('GESTLV2')");


        http.authorizeRequests().antMatchers("/addCentre","/addConcours","/ProfilsManqueGrade/**","UpdateGrade/**","/AddGrade","/addProfile","/UpdateProfile/**","/AllSubscriptions","/exportResultats")
                .access("hasAuthority('ADMIN') or hasAuthority('GESTLV1')");


        http.authorizeRequests().antMatchers("/ListGests","/deleteGest/**",
                "/AddDirs","Directions/**","/cncs/**","/deleteConc/**",
                "/updateConc/**","/addVille","/ville/**","/UpdateVille/**","/SearchVilles"
                ,"/Centre/**","/UpdateCentre/**","/file/delete/**","/HistoryG/**").hasAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();


        http.addFilter(new JWTAthentificationFilter(authenticationManager()));
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Scheduled(cron=  "*/1 * * * * *")
    public void ModifyConcoursState(){

        System.out.println(i);
        i++;

        concoursDAO.Desactivate();

        System.out.println(new Date());

    }
}
