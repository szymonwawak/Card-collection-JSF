package security;

import entities.Role;
import entities.User;

import java.util.Collection;
import java.util.HashSet;

public class UserSessionData {

    private long id;
    private String email;
    private String name;
    private String avatar;
    private HashSet<String> roles = new HashSet();

    public UserSessionData(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.id = user.getId();
        setRoles(user.getRoles());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
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
