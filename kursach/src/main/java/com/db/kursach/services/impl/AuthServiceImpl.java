//package com.db.kursach.services.impl;
//
//import com.db.kursach.dto.AuthRequest;
//import com.db.kursach.dto.AuthResponse;
//import com.db.kursach.dto.RegisterRequest;
//import com.db.kursach.enums.Role;
//import com.db.kursach.jwt.JwtUtil;
//import com.db.kursach.models.Employee;
//import com.db.kursach.models.User;
//import com.db.kursach.repositories.EmployeeRepository;
//import com.db.kursach.repositories.UserRepository;
//import lombok.AllArgsConstructor;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.security.Principal;
//
//@Service
//@AllArgsConstructor
//public class AuthServiceImpl {
//    private final UserRepository userRepository;
//    private final EmployeeRepository employeeRepository;
//    //private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//    //private final AuthenticationManager authenticationManager;
//
//    public User createUser(RegisterRequest request){
//        if(userRepository.findByEmail(request.getEmail())!=null || employeeRepository.findByEmail(request.getEmail())==null||userRepository.findByLogin(request.getLogin())!=null){
//            return null;
//        }
//        Employee employee = employeeRepository.findByEmail(request.getEmail());
//        User user = new User();
//        user.setLogin(request.getLogin());
//        user.setEmail(request.getEmail());
//        user.setEmployee(employee);
//        user=setUserRole(employee, user);
//        //user.setPassword(passwordEncoder.encode(request.getPassword()));
//        return user;
//    }
//
//    public User setUserRole(Employee employee, User user) {
//        switch (Math.toIntExact(employee.getPosition1().getId())) {
//            case 1 -> user.setRole(Role.ROLE_DIRECTOR);
//            case 2, 6 -> user.setRole(Role.ROLE_ADMINISTRATOR);
//            case 5 -> user.setRole(Role.ROLE_ACCOUNTANT);
//            default -> user.setRole(Role.ROLE_WAITER);
//        }
//        return user;
//    }
//
//    public Boolean registration(RegisterRequest request) {
//        User user = createUser(request);
//        if (user==null) return false;
//        userRepository.save(user);
//        return true;
//    }
//
//    public AuthResponse authentication(AuthRequest request) {
////        authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(
////                        request.getLogin(),
////                        request.getPassword()
////                )
////        );
//        var user = userRepository.findByLogin(request.getLogin());
//        var jwtToken = jwtUtil.generateToken(user);
//        return AuthResponse.builder()
//                .token(jwtToken)
//                .user(user)
//                .build();
//    }
//
//    public User getUserByPrincipal(Principal principal) {
//        if(principal==null)return new User();
//        return userRepository.findByLogin(principal.getName());
//    }
//}
