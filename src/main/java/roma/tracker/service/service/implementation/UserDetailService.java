package roma.tracker.service.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roma.tracker.service.entity.UserEntity;
import roma.tracker.service.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepo;

    @Autowired
    public UserDetailService(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        }
        return userEntity;
    }
}
