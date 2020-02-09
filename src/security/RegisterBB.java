package security;

import entities.User;
import dao.UserDao;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class RegisterBB {
    private String email;
    private String username;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @EJB
    UserDao userDao;

    @Inject
    FacesContext facesContext;

    public String register() {
        User user = userDao.findByNameOrEmail(username, email);
        if (user != null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Niepoprawny adres e-mail lub hasło", null));
            return null;
        } else {
            user = userDao.registerUser(username, email, password);
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            UserSessionData userSessionData = new UserSessionData(user, request.getRemoteHost());
            HttpSession session = request.getSession();
            session.setAttribute("userData", userSessionData);
            return "/app/main";
        }
    }
}
