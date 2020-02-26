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
import java.io.Serializable;

@Named
@RequestScoped
public class LoginBB implements Serializable {

    private String email;
    private String password;
    private boolean visible = false;
    User user;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @EJB
    UserDao userDAO;

    @Inject
    FacesContext facesContext;

    public String login() {
        if (!validate()) {
            setVisible(true);
            return null;
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        UserSessionData userSessionData = new UserSessionData(user);
        HttpSession session = request.getSession();
        session.setAttribute("userData", userSessionData);
        return "/app/main.xhtml";
    }

    public boolean validate() {
        user = userDAO.findUserByLoginCredentials(email, password);
        if (user == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd! Niepoprawny adres e-mail lub hasło!",
                    ""));
            return false;
        }
        return true;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/index";
    }
}
