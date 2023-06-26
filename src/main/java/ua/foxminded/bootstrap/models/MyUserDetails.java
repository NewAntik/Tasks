package ua.foxminded.bootstrap.models;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    
    private final User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();

        if (user != null) {
            roles.add(new SimpleGrantedAuthority(user.getRole().toString()));
        }

        return roles;
    }

    @Override
    public String getPassword() {
        if (user == null) {
            return null;
        } else {
            return user.getPasswordHash();
        }
    }

    @Override
    public String getUsername() {
        if (user == null) {
            return null;
        } else {
            return user.getFirstName();
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isAccountNonLocked() {
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isEnabled() {
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "CustomUserDetails [user=" + user + "]";
    }
}
