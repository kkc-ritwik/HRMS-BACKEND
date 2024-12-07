// package com.texsmartly.PayrollPage.service.authentication;

// import com.texsmartly.PayrollPage.dto.ReqRes;
// import com.texsmartly.PayrollPage.model.authentication.User;
// import com.texsmartly.PayrollPage.repository.authentication.UsersRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import java.util.HashMap;

// @Service
// public class AutheticationService {

//     @Autowired
//     private final UsersRepository usersRepo;
//     @Autowired
//     private JwtUtils jwtUtils;
//     @Autowired
//     private AuthenticationManager authenticationManager;
//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Autowired
//     public AutheticationService(UsersRepository usersRepo) {
//         this.usersRepo = usersRepo;
//     }

//     public ReqRes register(ReqRes registrationRequest) {
//         ReqRes resp = new ReqRes();

//         try {
//             User user = new User();
//             user.setEmail(registrationRequest.getEmail());
//             user.setRole(registrationRequest.getRole());
//             user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
//             User usersResult = usersRepo.save(user);
//             if (usersResult.getId() != null) {
//                 resp.setUser((usersResult));
//                 resp.setMessage("User Saved Successfully");
//                 resp.setStatusCode(200);
//             }

//         } catch (Exception e) {
//             resp.setStatusCode(500);
//             resp.setError(e.getMessage());
//         }
//         return resp;
//     }


//     public ReqRes login(ReqRes loginRequest) {
//         ReqRes response = new ReqRes();
//         try {
//             authenticationManager
//                     .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
//                             loginRequest.getPassword()));
//             var user = usersRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
//             var jwt = jwtUtils.generateToken(user);
//             var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
//             response.setStatusCode(200);
//             response.setToken(jwt);
//             response.setRole(user.getRole());
//             response.setRefreshToken(refreshToken);
//             response.setExpirationTime("24Hrs");
//             response.setMessage("Successfully Logged In");

//         } catch (Exception e) {
//             response.setStatusCode(500);
//             response.setMessage(e.getMessage());
//         }
//         return response;
//     }


//     public ReqRes refreshToken(ReqRes refreshTokenRequest) {
//         ReqRes response = new ReqRes();
//         try {
//             String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
//             User users = usersRepo.findByEmail(ourEmail).orElseThrow();
//             if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)) {
//                 var jwt = jwtUtils.generateToken(users);
//                 response.setStatusCode(200);
//                 response.setToken(jwt);
//                 response.setRefreshToken(refreshTokenRequest.getToken());
//                 response.setExpirationTime("24Hr");
//                 response.setMessage("Successfully Refreshed Token");
//             }
//             response.setStatusCode(200);
//             return response;

//         } catch (Exception e) {
//             response.setStatusCode(500);
//             response.setMessage(e.getMessage());
//             return response;
//         }
//     }

//     public ReqRes getProfile(User user) {
//         ReqRes response = new ReqRes();
//         try {
//             response.setStatusCode(200);
//             response.setExpirationTime("24Hr");
//             response.setMessage("Successfully Set Role");
//             response.setRole(user.getRole());
//             response.setUser(user);

//             return response;

//         } catch (Exception e) {
//             response.setStatusCode(500);
//             response.setMessage(e.getMessage());
//             return response;
//         }
//     }
// }