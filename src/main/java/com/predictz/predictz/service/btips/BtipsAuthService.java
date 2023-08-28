package com.predictz.predictz.service.btips;

import com.predictz.predictz.model.AuthToken;
import com.predictz.predictz.model.PasswordReset;
import com.predictz.predictz.model.ROLE;
import com.predictz.predictz.model.btips.AppUser;
import com.predictz.predictz.model.btips.Role;
import com.predictz.predictz.model.btips.Token;
import com.predictz.predictz.repository.btips.BtipsRoleRepo;
import com.predictz.predictz.repository.btips.BtipsTokenRepo;
import com.predictz.predictz.security.JWTUtil;
import com.predictz.predictz.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BtipsAuthService {
    @Autowired
    BtipsAppUserService appUserService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BtipsRoleRepo roleRepo;
    @Autowired
    EmailSender emailSender;
    @Autowired
    private BtipsTokenRepo tokenRepo;

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

    public void sendPasswordReset(PasswordReset passwordReset) throws IllegalAccessException {
        AppUser user = appUserService.getUserByEmail(passwordReset.getEmail());
        if(user != null){
            Token token = new Token();
            token.setUserId(user.getId());
            String tk;
            do {
                tk = generateToken()+"";
            } while (tokenRepo.findByToken(tk) != null);

            token.setToken(tk);
            token.setExpiresAt(LocalDateTime.now().plusMinutes(20));

            tokenRepo.save(token);
//            System.out.println("Saved Token");

            emailSender.sendReset(user, token.getToken());
        }else{
            throw new IllegalAccessException("Email does not exist");
        }
    }

    public void resetPassword(PasswordReset passwordReset) throws IllegalAccessException {
        Token token = tokenRepo.findByToken(passwordReset.getToken());
        if (token != null){
            if (LocalDateTime.now().isAfter(token.getExpiresAt())){
                throw new IllegalAccessException("Token expired");
            }

            AppUser user = appUserService.getUser(token.getUserId());
            if (user != null){
                user.setPassword(passwordEncoder.encode(passwordReset.getPassword()));
                appUserService.save(user);
            }else {
                throw new IllegalAccessException("Unauthorised access");
            }
        }
        else {
//            assert token != null;
            System.out.println(token +" isExpired?: "+ LocalDateTime.now().isAfter(token.getExpiresAt()));
            throw new IllegalAccessException("Invalid Token");
        }
    }

    private int generateToken(){
        SecureRandom secureRandom = new SecureRandom();

        int min = 100000; // Minimum 6-digit number
        int max = 999999; // Maximum 6-digit number

        return secureRandom.nextInt(max - min + 1) + min;
    }
}