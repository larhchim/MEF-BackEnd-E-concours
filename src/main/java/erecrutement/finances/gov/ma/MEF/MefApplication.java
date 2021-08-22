package erecrutement.finances.gov.ma.MEF;

import erecrutement.finances.gov.ma.MEF.DAO.AppRoleRepository;
import erecrutement.finances.gov.ma.MEF.DAO.GestionnaireDAO;
import erecrutement.finances.gov.ma.MEF.Models.AppRole;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.Profils;
import erecrutement.finances.gov.ma.MEF.Services.IAccountService;
import erecrutement.finances.gov.ma.MEF.Services.IGestionnaireService;
import erecrutement.finances.gov.ma.MEF.Services.IGradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

@SpringBootApplication
public class MefApplication  {

	IGradesService grade;

	IGestionnaireService iGestionnaireService;

	private GestionnaireDAO gg ;

	private AppRoleRepository appRoleRepository;

	@Autowired
	public void setAppRoleRepository(AppRoleRepository appRoleRepository) {
		this.appRoleRepository = appRoleRepository;
	}

	@Autowired
	public void setGg(GestionnaireDAO gg) {
		this.gg = gg;
	}

	@Autowired
	public void setiGestionnaireService(IGestionnaireService iGestionnaireService) {
		this.iGestionnaireService = iGestionnaireService;
	}

	@Autowired
	public void setGrade(IGradesService grade) {
		this.grade = grade;
	}

	public static void main(String[] args) {
		SpringApplication.run(MefApplication.class, args);
	}

	@PostConstruct
	public void LoadOnceFinished(){
		System.out.println("hello springgggggggggggggggggggggggggggg boooot data");

		ArrayList<Profils> a = new ArrayList<Profils>();
		a = (ArrayList<Profils>) grade.ProfilsManqueGrade(1);

		System.out.println(a);
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "File-Name"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

	}

	/*@Bean
	public CORS corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "File-Name"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CORS((CorsConfigurationSource) urlBasedCorsConfigurationSource);
	}*/
/*
SELECT Count(centres_concours.id_concours) AS 'nombre Concours', centres_concours.id_centre,centres.adresse,centres.salle,ville.nom_ville
FROM centres_concours,centres,ville
WHERE centres_concours.id_centre = centres.id_centre
AND centres.id_ville = ville.id_ville
GROUP BY centres_concours.id_centre;
 */

	@Bean
	BCryptPasswordEncoder getBCR(){
		return new BCryptPasswordEncoder();
	}

	/*@Bean
	CommandLineRunner begin(IAccountService accountService){

		return args -> {





			Stream.of("ismaillarhchim864@gmail.com").forEach(r->{

				Gestionnaires gest = new Gestionnaires();
				gest.setLogin(r);
				gest.setEtatcCompte(true);
 				iGestionnaireService.addGestionnaire(gest);
			});

			accountService.AddRoleToGestionnaire("ismaillarhchim864@gmail.com","GESTLV1");
			accountService.AddRoleToGestionnaire("ismaillarhchim864@gmail.com","GESTLV2");


		};

	}*/

}
