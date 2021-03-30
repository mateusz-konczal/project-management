package com.project.services;

import com.project.model.User;
import com.project.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "Users service")
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<User> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public User save(User user) {
        return usersRepository.save(user);
    }

    public Optional<User> findById(Long ID) {
        return usersRepository.findById(ID);
    }

    public boolean existsById(Long ID) {
        return usersRepository.existsById(ID);
    }

    public long count() {
        return usersRepository.count();
    }

    public void delete(User user) {
        usersRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User named '%s' not found!", username)));
    }

//    @PostConstruct
//    private void initializeUserIfNoUsersFound() {
//        if (this.count() == 0) {
//            this.save(new User(
//                    0L,
//                    "admin",
//                    "admin",
//                    null,
//                    UserRole.LECTURER
//            ));
//            log.warn("Initial user \"admin\" with password \"admin\" was created due to no users available in database");
//        }
//    }
}
