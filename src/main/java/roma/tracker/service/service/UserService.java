package roma.tracker.service.service;

import roma.tracker.service.dto.auth.request.SignupRequest;
import roma.tracker.service.entity.UserEntity;

import java.security.Principal;

public interface UserService {
    public UserEntity createUser(SignupRequest signupRequest);
    public UserEntity getUserByPrincipal(Principal principal);
}
