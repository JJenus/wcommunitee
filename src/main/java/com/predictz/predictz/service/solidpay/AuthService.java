package com.predictz.predictz.service.solidpay;

import com.predictz.predictz.model.solidpay.AppUser;
import com.predictz.predictz.model.AuthToken;
import com.predictz.predictz.model.ROLE;
import com.predictz.predictz.model.solidpay.Role;
import com.predictz.predictz.repository.solidpay.RoleRepo;
import com.predictz.predictz.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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

        user.setStatus("new");
        user = appUserService.save(user);

        String token = jwtUtil.generateToken(user.getUUser());

        AuthToken authToken = new AuthToken();
        authToken.setUser(user.getUUser());
        authToken.setToken(token);

        return authToken;
    }

    public AuthToken login(AppUser user){
        try {
            AppUser User = appUserService.getUserByEmail(user.getEmail());
            if ( User == null || (User.getStatus() != null
            && User.getStatus().equalsIgnoreCase("deleted"))) {
                return null;
            }

            if (!passwordEncoder.matches(user.getPassword(), User.getPassword())){
                return new AuthToken();
            }

            String token = jwtUtil.generateToken(User.getUUser());

            AuthToken authToken = new AuthToken();
            authToken.setToken(token);
            authToken.setUser(appUserService.getUserByEmail(user.getEmail()).getUUser());

            return authToken;
        }catch (AuthenticationException authExc){
            authExc.printStackTrace();
//            throw new RuntimeException("Invalid Login Credentials");
        }
        return new AuthToken();
    }
}