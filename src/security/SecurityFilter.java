package security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class SecurityFilter implements Filter {
    private String loginPage;
    private HashMap<String, HashSet<String>> accessRules = new HashMap<>();
    private HashSet<String> publicResources = new HashSet<>();

    @Override
    public void init(FilterConfig config) {
        setLoginPage(config.getInitParameter("loginPage"));
        String accessConfig = config.getInitParameter("accessConfig");
        if (accessConfig != null)
            setAccessRules(accessConfig);
    }

    private void setLoginPage(String loginPage) {
        if (loginPage != null)
            this.loginPage = loginPage;
        else
            this.loginPage = "/index.xhtml";
        this.loginPage = this.loginPage + "?faces-redirect=true";
    }

    private void setAccessRules(String accessConfig) {
        accessConfig = accessConfig.replaceAll("[\\n\\r\\s]+", "");
        String[] urlAccessArray = accessConfig.split(";");
        for (String urlConfig : urlAccessArray) {
            if (!urlConfig.isEmpty()) {
                setUrlRoles(urlConfig);
            }
        }
    }

    private void setUrlRoles(String urlConfig) {
        String[] config = urlConfig.split(":");
        String url = config[0];
        if (!url.isEmpty()) {
            if (config.length == 1) {
                publicResources.add(url);
            } else {
                HashSet<String> roles = extractRoles(config[1]);
                if (roles.size() > 0)
                    accessRules.put(url, roles);
                else
                    publicResources.add(url);
            }
        }
    }

    private HashSet<String> extractRoles(String roles) {
        HashSet<String> rolesSet = new HashSet<>();
        String[] rolesArray = roles.split(",");
        for (String role : rolesArray) {
            if (!role.isEmpty())
                rolesSet.add(role);
        }
        return rolesSet;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws
            ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();

        UserSessionData client = (UserSessionData) session.getAttribute("userData");
        String path = request.getServletPath();
        if (!isAuthorized(client, path)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            request.getServletContext().getRequestDispatcher(loginPage).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isAuthorized(UserSessionData client, String path) {
        return isPublicResource(path) || hasPermissionsToResource(client, path);
    }

    private boolean isPublicResource(String path) {
        for (String publicResource : publicResources) {
            if (path.startsWith(publicResource))
                return true;
        }
        return false;
    }

    private boolean hasPermissionsToResource(UserSessionData client, String path) {
        if (client != null) {
            while (path.length() > 1) {
                if (accessRules.containsKey(path) && client.hasRole(accessRules.get(path)))
                    return true;
                path = path.substring(0, path.lastIndexOf("/"));
            }
        }
        return false;
    }

    @Override
    public void destroy() {
    }
}
