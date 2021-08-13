package erecrutement.finances.gov.ma.MEF.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {

    public final static String LOCATION = System.getProperty("user.home") +"/Downloads/";

    @GetMapping("/location/{filename}")
    public String getLocation(@PathVariable("filename") String file){
        return LOCATION + file;
    }
}
