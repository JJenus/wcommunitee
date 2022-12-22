package com.predictz.winningcommunitee.service;

import com.predictz.winningcommunitee.model.AppUser;
import com.predictz.winningcommunitee.model.Role;
import com.predictz.winningcommunitee.repository.AppUserRepo;
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
public class AppUserService implements UserDetailsService {
    @Autowired
    AppUserRepo userRepo;

    public AppUser getUser(Long id){
        return userRepo.findById(id).orElse(null);
    }

    public List<AppUser> getUsers(){
        return userRepo.findAll();
    }

    public AppUser upadate(AppUser user){
        Optional<AppUser> optionalAppUser = userRepo.findById(user.getId());
        if (optionalAppUser.isPresent()) {
            return null;
        }
        return userRepo.save(user);
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
