package roma.tracker.service.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import roma.tracker.service.dto.auth.request.SignupRequest;
import roma.tracker.service.entity.RoleEntity;
import roma.tracker.service.entity.UserEntity;
import roma.tracker.service.entity.enums.RoleEnum;
import roma.tracker.service.exceptions.UserExistException;
import roma.tracker.service.repository.RoleRepository;
import roma.tracker.service.repository.UserRepository;
import roma.tracker.service.service.UserService;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }


    /*
    метод созранения пользователя
    если почта уже существует и пароли не совпадают то выбрасываем исключение
*/
    @Override
    public UserEntity createUser(SignupRequest signupRequest) {
        if (!signupRequest.getConfirmPassword().equals(signupRequest.getPassword())) {
            throw new UserExistException(HttpStatus.BAD_REQUEST, "wrong password", "Please check password and confirmPassword");
        }
        if (userRepo.findByEmail(signupRequest.getEmail()) != null) {
            throw new UserExistException(HttpStatus.BAD_REQUEST, "wrong email", "The user" + signupRequest.getEmail() + " already exist.");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signupRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        List<RoleEntity> rolesList = userEntity.getRoleEntityList();
        rolesList.add(getRole(RoleEnum.USER));
        userEntity.setRoleEntityList(rolesList);
        userEntity.setActive(true);
        userEntity.setFirstName(signupRequest.getName());
        userEntity.setLastName(signupRequest.getSurname());


        return userRepo.save(userEntity);
    }

    @Override
    public UserEntity getUserByPrincipal(Principal principal) {
        String email = principal.getName();
        UserEntity userEntity = userRepo.findByEmail(email);
        if (userEntity == null) {
            new UsernameNotFoundException("Email not found with email - " + email);
        }
        return userEntity;
    }

    private RoleEntity getRole(RoleEnum roleEnum) {
        RoleEntity role = roleRepo.findByRoleEnum(roleEnum).orElseThrow();
        return role;
    }
}
