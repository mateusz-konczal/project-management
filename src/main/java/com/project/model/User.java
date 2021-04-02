package com.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Configurable
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @Enumerated
    @NotNull
    private UserRole userRole;

    @NotNull
    protected String lastName;

    @NotNull
    protected String firstName;

    @Email
    @Column(unique = true)
    protected String email;

    public User(Long ID, String username, String password, UserRole userRole, String lastName, String firstName, String email) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    @Override
    public final Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public final boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public final boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public final boolean isEnabled() {
        return true;
    }

}
