package security;

import entities.Role;
import entities.User;

import java.util.Collection;
import java.util.HashSet;

public class UserSessionData {

    private String email;
    private String name;
    private String remoteHost;
    private HashSet<String> roles = new HashSet<String>();

    public UserSessionData(User user, String remoteHost) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.remoteHost = remoteHost;
        setRoles(user.getRoles());
    }

    private void setRoles(Collection<Role> roles) {
        for (Role role : roles) {
            this.roles.add(role.getName());
        }
    }

    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    public boolean hasRole(HashSet<String> roles) {
        for (String role : roles) {
            if (this.roles.contains(role))
                return true;
        }
        return false;
    }

}
