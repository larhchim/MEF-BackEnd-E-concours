package erecrutement.finances.gov.ma.MEF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MefApplication {

	public static void main(String[] args) {
		SpringApplication.run(MefApplication.class, args);
	}

	@PostConstruct
	public void LoadOnceFinished(){
		System.out.println("hello springgggggggggggggggggggggggggggg boooot data");
	}

}
