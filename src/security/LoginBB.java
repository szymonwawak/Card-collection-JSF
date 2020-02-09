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
public class LoginBB {
    private String email;
    private String password;

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

    @EJB
    UserDao userDAO;

    @Inject
    FacesContext facesContext;

    public String login() {
        User user = userDAO.findUserByLoginCredentials(email, password);
        if (user == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Niepoprawny adres e-mail lub hasło", null));
            return null;
        }
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        UserSessionData userSessionData = new UserSessionData(user, request.getRemoteHost());
        HttpSession session = request.getSession();
        session.setAttribute("userData", userSessionData);
        return "/app/main";
    }
}
