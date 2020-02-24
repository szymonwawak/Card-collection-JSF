import security.UserSessionData;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {
    public static long getCurrentUserId() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        UserSessionData userData = (UserSessionData) session.getAttribute("userData");
        return userData.getId();
    }
}
