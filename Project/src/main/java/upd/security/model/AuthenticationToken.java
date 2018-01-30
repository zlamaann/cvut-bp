package upd.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;

public class AuthenticationToken extends AbstractAuthenticationToken implements Principal {

    private PersonDetails personDetails;

    public AuthenticationToken(Collection<? extends GrantedAuthority> authorities, PersonDetails personDetails) {
        super(authorities);
        this.personDetails = personDetails;
        super.setAuthenticated(true);
        super.setDetails(personDetails);
    }

    @Override
    public Object getCredentials() {
        return personDetails.getPassword();
    }

    @Override
    public Object getPrincipal() {
        return personDetails;
    }
}
