package com.predictz.winningcommunitee.service;

import com.predictz.winningcommunitee.model.AppUser;
import com.predictz.winningcommunitee.model.AuthToken;
import com.predictz.winningcommunitee.model.ROLE;
import com.predictz.winningcommunitee.model.Role;
import com.predictz.winningcommunitee.repository.RoleRepo;
import com.predictz.winningcommunitee.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    AppUserService appUserService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

    public AuthToken registerUser(AppUser user){
        if (null != appUserService.getUserByEmail(user.getEmail()))
            return null;

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles() == null || user.getRoles().isEmpty()){
            List<Role> roles = new ArrayList<>();
            Role role = roleRepo.findByName(ROLE.PUNTER.name())
                    .orElse(new Role(ROLE.PUNTER.name()));
            roles.add(role);
            user.setRoles(roles);
        }
        else{
            List<Role> roles = new ArrayList<>();
            Role role = roleRepo.findByName(ROLE.ADMIN.name())
                    .orElse(new Role(ROLE.ADMIN.name()));
            roles.add(role);
            user.setRoles(roles);
        }

        user = appUserService.save(user);
        user.setSubscriptions(new ArrayList<>());

        String token = jwtUtil.generateToken(user);

        AuthToken authToken = new AuthToken();
        authToken.setUser(user);
        authToken.setToken(token);

        return authToken;
    }

    public AuthToken login(AppUser user){
        try {
            if (appUserService.getUserByEmail(user.getEmail()) == null) {
                System.out.println("Empty user");
                return null;
            }

            System.out.println("User was found");

            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(user);

            AuthToken authToken = new AuthToken();
            authToken.setToken(token);
            authToken.setUser(appUserService.getUserByEmail(user.getEmail()));

            return authToken;
        }catch (AuthenticationException authExc){
            authExc.printStackTrace();
//            throw new RuntimeException("Invalid Login Credentials");
        }
        return new AuthToken();
    }
}