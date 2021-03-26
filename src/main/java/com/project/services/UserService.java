package com.project.services;

import com.project.model.User;
import com.project.model.UserRole;
import com.project.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "User service")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long ID) {
        return userRepository.findById(ID);
    }

    public boolean existsById(Long ID) {
        return userRepository.existsById(ID);
    }

    public long count() {
        return userRepository.count();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User named '%s' not found!", username)));
    }

    @PostConstruct
    private void initializeUserIfNoUsersFound() {
        if (this.count() == 0) {
            this.save(new User(
                    0L,
                    "admin",
                    "admin",
                    null,
                    UserRole.LECTURER
            ));
            log.warn("Initial user \"admin\" with password \"admin\" was created due to no users available in database");
        }
    }
}
