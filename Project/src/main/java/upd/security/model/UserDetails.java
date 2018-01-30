package upd.security.model;

import upd.model.PersonType;
import upd.model.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private static final String OFFICE_ROLE = "OFFICE";

    private static final String ACTOR_ROLE = "ACTOR";

    private static final String MUSICIAN_ROLE = "MUSICIAN";

    private static final String USHERETTE_ROLE = "USHERETTE";

    private static final String COSTUMIER_ROLE = "COSTUMIER";

    private static final String LIGHTING_ROLE = "LIGHTING";

    private static final String SOUND_ROLE = "SOUND";

    private static final String TECHNICIAN_ROLE = "TECHNICIAN";

    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    private Person person;

    protected final Set<GrantedAuthority> authorities;

    public UserDetails(Person person) {
        Objects.requireNonNull(person);
        this.person = person;
        this.authorities = new HashSet<>();
        if (person.getRole().equals(PersonType.OFFICE)) {
            addOfficeRole();
        } else if (person.getRole().equals(PersonType.ADMIN)) {
            addAdminRole();
        } else if (person.getRole().equals(PersonType.ACTOR)) {
            addActorRole();
        } else if (person.getRole().equals(PersonType.MUSICIAN)) {
            addMusicianRole();
        } else if (person.getRole().equals(PersonType.USHERETTE)) {
            addUsheretteRole();
        } else if (person.getRole().equals(PersonType.COSTUMIER)) {
            addCostumierRole();
        } else if (person.getRole().equals(PersonType.LIGHTING)) {
            addLightingRole();
        } else if (person.getRole().equals(PersonType.SOUND)) {
            addSoundRole();
        } else if (person.getRole().equals(PersonType.TECHNICIAN)) {
            addTechnicianRole();
        }
    }

    public UserDetails(Person person, Collection<GrantedAuthority> authorities) {
        Objects.requireNonNull(person);
        Objects.requireNonNull(authorities);
        this.person = person;
        this.authorities = new HashSet<>();
        addOfficeRole();
        this.authorities.addAll(authorities);
    }

    private void addOfficeRole() {
        authorities.add(new SimpleGrantedAuthority(OFFICE_ROLE));
    }

    private void addAdminRole() {
        authorities.add(new SimpleGrantedAuthority(ADMIN_ROLE));
    }

    private void addActorRole() {
        authorities.add(new SimpleGrantedAuthority(ACTOR_ROLE));
    }

    private void addMusicianRole() {
        authorities.add(new SimpleGrantedAuthority(MUSICIAN_ROLE));
    }

    private void addUsheretteRole() {
        authorities.add(new SimpleGrantedAuthority(USHERETTE_ROLE));
    }

    private void addCostumierRole() {
        authorities.add(new SimpleGrantedAuthority(COSTUMIER_ROLE));
    }

    private void addLightingRole() {
        authorities.add(new SimpleGrantedAuthority(LIGHTING_ROLE));
    }

    private void addSoundRole() {
        authorities.add(new SimpleGrantedAuthority(SOUND_ROLE));
    }

    private void addTechnicianRole() {
        authorities.add(new SimpleGrantedAuthority(TECHNICIAN_ROLE));
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
