package security;

import dao.UserDao;
import entities.User;

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

    @EJB
    UserDao userDao;

    @Inject
    FacesContext facesContext;

    private String email;
    private String username;
    private String password;
    private boolean visible = false;
    User user;

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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String register() {
        if (!validate()) {
            setVisible(true);
            return null;
        }
        user = userDao.registerUser(username, email, password);
        facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        UserSessionData userSessionData = new UserSessionData(user);
        HttpSession session = request.getSession();
        session.setAttribute("userData", userSessionData);
        return "/app/main.xhtml";
    }

    public boolean validate() {
        if (!email.matches("[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]")) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Uwaga! Podano błędny adres e-mail!", ""));
            return false;
        }
        user = userDao.findByNameOrEmail(username, email);
        if (user != null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd! Użytkownik o takim adresie e-mail lub loginie już istnieje!",
                    ""));
            return false;
        }
        return true;
    }
}
