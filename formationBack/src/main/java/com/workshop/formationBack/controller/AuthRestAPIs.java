package com.workshop.formationBack.controller;

import com.workshop.formationBack.configuration.JwtProvider;
import com.workshop.formationBack.message.JwtResponse;
import com.workshop.formationBack.message.LoginForm;
import com.workshop.formationBack.message.SignUpForm;
import com.workshop.formationBack.model.Personne;
import com.workshop.formationBack.model.Role;
import com.workshop.formationBack.model.RoleName;
import com.workshop.formationBack.repository.PersonneRepository;
import com.workshop.formationBack.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
 
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        Optional<Personne> personne = personneRepository.findByUsername(loginRequest.getUsername());
        Personne personnedetails = personne.get();
        if(!personnedetails.getEtat()){
            return ResponseEntity.ok("Not activated");
        }
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
 
    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
       if(personneRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }
        // Creating person's account
        Personne personne = new Personne(signUpRequest.getNom(), signUpRequest.getPrenom(),  signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
          switch(role) {
          case "admin":
            Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Role not found."));
            roles.add(adminRole);
            break;
          case "pm":
              Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Role not found."));
              roles.add(pmRole);
            break;
          default:
              Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Role not found."));
              roles.add(userRole);              
          }
        });
        personne.setRoles(roles);
        personneRepository.save(personne);
        return ResponseEntity.ok("Person Registred !");
    }
}
