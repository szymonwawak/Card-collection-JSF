import dao.UserDao;
import entities.User;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EditProfileBB {

    @Inject
    FacesContext facesContext;

    @EJB
    UserDao userDao;

    private String name;
    private String email;
    private String oldPassword;
    private String newPassword;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void saveChanges() {
        User currentUser = userDao.getCurrentUser();
        User user = userDao.findUserByLoginCredentials(currentUser.getEmail(), oldPassword);
        if (user == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Podano błędne hasło!", null));
            return;
        }
        if (userDao.findByNameOrEmail(name, email) != null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niestety ten login lub e-mail jest już zajęty!", null));
            return;
        }
        userDao.updateProfile(user, name, email, newPassword);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dane zostały zmienione!", null));
    }
}
