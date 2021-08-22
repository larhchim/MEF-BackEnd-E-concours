package erecrutement.finances.gov.ma.MEF.Security;

import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private IAccountService iAccountService;

    @Autowired
    public void setiAccountService(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Gestionnaires gestionnaires = iAccountService.loadUserByLogin(s);
        if (gestionnaires == null) throw new UsernameNotFoundException("USER NOT FOUND INVALID USERNAME/LOGIN");

        System.out.println("le fameux ***********===>:"+gestionnaires);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        gestionnaires.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        System.out.println("Autorisations *********==>:"+authorities);

        System.out.println( "voila ce quil va retourner=====>"+new User(gestionnaires.getLogin(),gestionnaires.getMotDePasse(),authorities));

        return new User(gestionnaires.getLogin(),gestionnaires.getMotDePasse(),authorities);

    }
}
