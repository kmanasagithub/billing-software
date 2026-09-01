package com.purchase.billingsoftware.controller;

import com.purchase.billingsoftware.dto.AuthRequest;
import com.purchase.billingsoftware.dto.AuthResponse;
import com.purchase.billingsoftware.service.UserService;
import com.purchase.billingsoftware.service.impl.AppUserDetailsService;
import com.purchase.billingsoftware.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService appUserDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) throws Exception{
        System.out.println("LOGIN CONTROLLER REACHED");
        System.out.println("Email: " + request.getEmail());
        System.out.println("Password: " + request.getPassword());

        System.out.println(
                "PASSWORD MATCH: " +
                        passwordEncoder.matches(
                                request.getPassword(),
                                "$2a$10$NRpgflBzrQoLilMmkyPSh.BdhdMVA0h.YP2BqGuKvJApxkLmH8AXm"
                        )
        );

        authenticate(request.getEmail(),request.getPassword());

        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(request.getEmail());

        System.out.println(userDetails.getUsername()+" "+userDetails.getPassword());
        final String jwtToken = jwtUtil.generateToken(userDetails);

        String role = userService.getUserRole(request.getEmail());
        return new AuthResponse(request.getEmail(),jwtToken,role);
    }

    @PostMapping("/encode")
    public String encodePassword(@RequestBody Map<String,String> request) {
        return passwordEncoder.encode(request.get("password"));
    }

    private void authenticate(String email, String password) throws Exception {
        try {

            System.out.println("AUTHENTICATING: " + email);

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    email,
                                    password
                            )
                    );

            System.out.println("AUTHENTICATION SUCCESS");
            System.out.println("Authenticated user: " + authentication.getName());

        } catch (Exception e) {

            System.out.println("========== AUTHENTICATION ERROR ==========");
            e.printStackTrace();
            System.out.println("==========================================");

            throw e;
        }
    }

//    private void authenticate(String email,String password) throws Exception{
//        try{
//
//
//            System.out.println("AUTHENTICATING: " + email);
//
//            Authentication authentication =
//                    authenticationManager.authenticate(
//                            new UsernamePasswordAuthenticationToken(
//                                    email,
//                                    password
//                            )
//                    );
//
//            System.out.println("AUTHENTICATION SUCCESS");
//            System.out.println(
//                    "Authenticated user: " +
//                            authentication.getName()
//            );
//
//        }
//        catch (DisabledException e) {
//            throw new Exception("User disabled");
//        }
//        catch (BadCredentialsException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email or password is incorrect");
//        }
//    }
}
