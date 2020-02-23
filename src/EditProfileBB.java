import dao.UserDao;
import entities.User;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import security.UserSessionData;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Named
@RequestScoped
public class EditProfileBB {

    @Inject
    FacesContext facesContext;
    @EJB
    UserDao userDao;

    private String name;

    private String email;

    private String newPassword;
    private String oldPassword;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    private UploadedFile file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void fileListener(FileUploadEvent event) {
        event.getFile();
        System.out.println("dfsdfdf");
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void saveChanges() {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpSession session = request.getSession();
        UserSessionData userData = (UserSessionData) session.getAttribute("userData");
        User user = userDao.findUserByLoginCredentials(userData.getEmail(), oldPassword);
        userDao.updateProfile(user, name, email, newPassword);
    }
}
