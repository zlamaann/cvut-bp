package upd.security.model;

import upd.model.PersonType;
import upd.model.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private static final String DEFAULT_ROLE = "ROLE_USER";

    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    private Person person;

    protected final Set<GrantedAuthority> authorities;

    public UserDetails(Person person) {
        Objects.requireNonNull(person);
        this.person = person;
        this.authorities = new HashSet<>();
        if (person.getRole().equals(PersonType.USER_ROLE)) {
            addDefaultRole();
        } else if (person.getRole().equals(PersonType.ADMIN_ROLE)) {
            addAdminRole();
        }
    }

    public UserDetails(Person person, Collection<GrantedAuthority> authorities) {
        Objects.requireNonNull(person);
        Objects.requireNonNull(authorities);
        this.person = person;
        this.authorities = new HashSet<>();
        addDefaultRole();
        this.authorities.addAll(authorities);
    }

    private void addDefaultRole() {
        authorities.add(new SimpleGrantedAuthority(DEFAULT_ROLE));
    }

    private void addAdminRole() {
        authorities.add(new SimpleGrantedAuthority(ADMIN_ROLE));
    }

    public void eraseCredentials() {
        person.erasePassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableCollection(authorities);
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Person getUser() {
        return person;
    }
}
