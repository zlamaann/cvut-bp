package upd.security.model;

import upd.model.Performance;
import upd.model.PersonType;
import upd.model.PersonTypeEnum;
import upd.model.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class PersonDetails implements org.springframework.security.core.userdetails.UserDetails {

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

    public PersonDetails(Person person) {
        Objects.requireNonNull(person);
        this.person = person;
        this.authorities = new HashSet<>();
        List<PersonType> roles = person.getRoles();
        for (PersonType pt: roles) {
            String role = pt.getName();
            if (role.equals(PersonTypeEnum.OFFICE.name())) {
                addOfficeRole();
            } else if (role.equals(PersonTypeEnum.ADMIN.name())) {
                addAdminRole();
            } else if (role.equals(PersonTypeEnum.ACTOR.name())) {
                addActorRole();
            } else if (role.equals(PersonTypeEnum.MUSICIAN.name())) {
                addMusicianRole();
            } else if (role.equals(PersonTypeEnum.USHERETTE.name())) {
                addUsheretteRole();
            } else if (role.equals(PersonTypeEnum.COSTUMIER.name())) {
                addCostumierRole();
            } else if (role.equals(PersonTypeEnum.LIGHTING.name())) {
                addLightingRole();
            } else if (role.equals(PersonTypeEnum.SOUND.name())) {
                addSoundRole();
            } else if (role.equals(PersonTypeEnum.TECHNICIAN.name())) {
                addTechnicianRole();
            }
        }

    }

    public PersonDetails(Person person, Collection<GrantedAuthority> authorities) {
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
        return person.getEmail();
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
