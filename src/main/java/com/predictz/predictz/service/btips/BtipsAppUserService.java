package com.predictz.predictz.service.btips;

import com.predictz.predictz.model.UUser;
import com.predictz.predictz.model.btips.AppUser;
import com.predictz.predictz.model.btips.Role;
import com.predictz.predictz.repository.btips.BtipsAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BtipsAppUserService implements UserDetailsService {
    @Autowired
    BtipsAppUserRepo userRepo;

    public AppUser getUser(Long id){
        return userRepo.findById(id).orElse(null);
    }

    public List<UUser> getUsers(){
        List<UUser> users = new ArrayList<>();
        userRepo.findAll().forEach(e -> users.add(e.getUUser()));

        return users;
    }

    public UUser update(AppUser user){
        Optional<AppUser> optionalAppUser = userRepo.findById(user.getId());
        if (!optionalAppUser.isPresent()) {
            return null;
        }
        return userRepo.save(user).getUUser();
    }

    public void delUser(Long id){
        userRepo.deleteById(id);
    }

    public AppUser getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    public AppUser save(AppUser user) {
        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userRes = userRepo.findByEmail(email);
        if(!userRes.isPresent())
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        AppUser user = userRes.get();

        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        for (Role role: user.getRoles()){
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(email, user.getPassword(), roles);
    }
}
