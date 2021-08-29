package erecrutement.finances.gov.ma.MEF;

import erecrutement.finances.gov.ma.MEF.DAO.AppRoleRepository;
import erecrutement.finances.gov.ma.MEF.DAO.DirectionsDAO;
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

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
public class MefApplication  {

	int i=0;

	IGradesService grade;

	IGestionnaireService iGestionnaireService;

	private GestionnaireDAO gg ;

	private DirectionsDAO dd;

	private AppRoleRepository appRoleRepository;

	@Autowired
	public void setDd(DirectionsDAO dd) {
		this.dd = dd;
	}

	@Autowired
	public void setAppRoleRepository(AppRoleRepository appRoleRepository) {
		this.appRoleRepository = appRoleRepository;
	}
/*
	@Autowired
	public void setGg(GestionnaireDAO gg) {
		this.gg = gg;
	}

	@Autowired
	public void setiGestionnaireService(IGestionnaireService iGestionnaireService) {
		this.iGestionnaireService = iGestionnaireService;
	}*/

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

	/*	System.out.println("Nombre total Stats1===============>"+gg.Stats1());
		System.out.println("Admins Stats2===============>"+gg.Stats2());
		System.out.println("GEST LVL1 Stats3===============>"+gg.Stats3());
		System.out.println("GEST LVL2 Stats4===============>"+gg.Stats4());
		System.out.println("direction1============>"+gg.stats5(dd.getOne(1)));
		System.out.println("direction2============>"+gg.stats5(dd.getOne(2)));
		System.out.println("direction3============>"+gg.stats5(dd.getOne(3)));
		System.out.println("direction4============>"+gg.stats5(dd.getOne(4)));
		System.out.println("direction5============>"+gg.stats5(dd.getOne(5)));
		System.out.println("direction6============>"+gg.stats5(dd.getOne(6)));
		System.out.println("direction7============>"+gg.stats5(dd.getOne(7)));
		System.out.println("direction8============>"+gg.stats5(dd.getOne(8)));
		System.out.println("direction9============>"+gg.stats5(dd.getOne(9)));
		System.out.println("direction10============>"+gg.stats5(dd.getOne(10)));
		System.out.println("direction11============>"+gg.stats5(dd.getOne(11)));
		System.out.println("direction12============>"+gg.stats5(dd.getOne(12)));
*/



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

	@Bean
	List<String> getAllowedFiles(){
		List AllowedFiles= new ArrayList();
		AllowedFiles.add(".pdf");
		AllowedFiles.add(".png");
		AllowedFiles.add(".jpeg");
		AllowedFiles.add(".mp4");
		AllowedFiles.add(".mp3");
		AllowedFiles.add(".docx");
		AllowedFiles.add(".doc");
		AllowedFiles.add(".csv");
		AllowedFiles.add(".xls");
		AllowedFiles.add(".xlsx");
		AllowedFiles.add(".ppt");
		AllowedFiles.add(".pptx");
		return AllowedFiles;
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
