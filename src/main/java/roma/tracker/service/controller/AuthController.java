package roma.tracker.service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roma.tracker.service.config.security.JwtTokenUtils;
import roma.tracker.service.dto.auth.request.LoginRequest;
import roma.tracker.service.dto.auth.request.SignupRequest;
import roma.tracker.service.dto.auth.response.JWTTokenResponse;
import roma.tracker.service.service.UserService;
import roma.tracker.service.service.implementation.UserDetailService;
import roma.tracker.service.validation.ResponseErrorValidation;
import roma.tracker.service.exceptions.ValidException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserDetailService userDetailService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final ResponseErrorValidation responseErrorValidation;
    private final JwtTokenUtils jwtTokenUtils;


    @Autowired
    public AuthController(UserDetailService userDetailService, UserService userService, AuthenticationManager authenticationManager, ResponseErrorValidation responseErrorValidation, JwtTokenUtils jwtTokenUtils) {
        this.userDetailService = userDetailService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.responseErrorValidation = responseErrorValidation;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest,
                                               BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            throw new ValidException(HttpStatus.BAD_REQUEST, "Validation error", "Incorrectly filled in fields");
        }
        userService.createUser(signupRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTTokenResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                                             BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            throw new ValidException(HttpStatus.BAD_REQUEST, "Validation error", "Incorrectly filled in fields");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JWTTokenResponse(token));
    }

}
