package ua.foxminded.bootstrap.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final User user;
    private final Collection<GrantedAuthority> roles;
    private final PasswordEncoder passwordEncoder;


    public MyUserDetails(User user, PasswordEncoder passwordEncoder) {
        this.user = user;
        this.roles = Optional.ofNullable(user)
                .map(it -> Stream.of(it)
                        .map(User::getRole)
                        .map(role -> (GrantedAuthority) new SimpleGrantedAuthority("ROLE_%s".formatted(role)))
                        .toList())
                .orElse(Collections.emptyList());
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.user == null ? null : passwordEncoder.encode(user.getPasswordHash());
    }

    @Override
    public String getUsername() {
        return this.user == null ? null : user.getFirstName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user != null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user != null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user != null;
    }

    @Override
    public boolean isEnabled() {
        return user != null;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "CustomUserDetails [user=" + user + "]";
    }
}
