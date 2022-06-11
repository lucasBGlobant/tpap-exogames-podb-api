package com.exogames.rest_controller;


import com.exogames.entities.User;
import com.exogames.entities.constants.SecurityConstants;
import com.exogames.usecases.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.validation.Valid;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


@RestController
@RequiredArgsConstructor
//@RequestMapping(path = "/exogames/api/v1")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping("/user-register")
    @Operation(summary = "Register a user in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successful"),
            @ApiResponse(responseCode = "227", description = "User already exist")
    })
    public UserResponse createUser(@ParameterObject
                                       @RequestBody @Valid CreateUserRequest createUserRequest) throws NoSuchAlgorithmException {

        if(userService.findByUsername(createUserRequest.getUsername()).isEmpty()){
            userService.save(new User(createUserRequest.getUsername(),
                    passwordEncoder.encode(createUserRequest.getUserPassword()),
                    createUserRequest.getUserRole()));
            UserResponse userResponse = new UserResponse(
                    201,
                    "CREATED",
                    createUserRequest.getMessageId(),
                    "User created successful",
                    createUserRequest.getUsername(),
                    createUserRequest.getUserRole());

            return  userResponse;
        }else {
            UserResponse userResponse = new UserResponse(
                    227,
                    "User already exist",
                    createUserRequest.getMessageId(),
                    "Failed");
            return userResponse;
        }

    }

    @PostMapping("/user-retrieve")
    @Operation(summary = "Get a user by username")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Succes"),
            @ApiResponse(responseCode = "228", description = "User not found")
    })
    public UserResponse retrieveUser(@ParameterObject
                                         @RequestBody @Valid  RetrieveUserRequest retrieveUserRequest){


        if (!userService.findByUsername(retrieveUserRequest.getUsername()).isEmpty()) {
            User u = userService.findByUsername(retrieveUserRequest.getUsername()).get(0);
            UserResponse userResponse = new UserResponse(
                    200,
                    "OK",
                    retrieveUserRequest.getMessageId(),
                    "Success",
                    u.getUsername(),
                    u.getUserRole());
            return userResponse;
        }else {
            UserResponse userResponseb = new UserResponse(
                    228,
                    "NOT FOUNDED",
                    retrieveUserRequest.getMessageId(),
                    "User not founded");
            return userResponseb;
        }

    }

    @GetMapping
    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "229", description = "Failed login")
    })
    public AuthenticationResponse login(@ParameterObject
                                            @RequestBody AuthenticationRequest request) throws NoSuchAlgorithmException {
        User user = userService.findByUsername(request.getUsername()).get(0);
        if(passwordEncoder.matches(request.userPassword, user.getPassword())) {
            //build json string
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().setIssuer("Exogames").setSubject("JWT Token")
                    .claim("username", user.getUsername())
                    .claim("authorities", user.getUserRole())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + 1000000))
                    .signWith(key).compact();
            //create response json
            return  new AuthenticationResponse(
                    200,
                    "OK",
                    request.getMessageId(),
                    "Successful login",
                    user.getUsername(),
                    jwt,
                    user.getUserRole());
        } else {
            return new AuthenticationResponse(
                    229,
                    "OK",
                    request.getMessageId(),
                    "Failed login",
                    user.getUsername(),
                    user.getUserRole());
        }

    }


}
