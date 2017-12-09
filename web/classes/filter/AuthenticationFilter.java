package filter;

import transactions.entity.Client;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"CarServlet", "ProfileServlet", "AdminServlet"})
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            if (userAlreadyLoggedIn(servletRequest)) {
                allowAccess(servletRequest, servletResponse, filterChain);
            } else {
                redirectTo(servletRequest, servletResponse);
//                sendBack(servletRequest, servletResponse);
            }
        } else {
            allowAccess(servletRequest, servletResponse, filterChain);
        }
    }

    private void redirectTo(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        ((HttpServletResponse)servletResponse).sendRedirect("/login");
    }

    private void sendBack(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        String previousUrl = ((HttpServletRequest) servletRequest).getHeader("Referer");
        ((HttpServletResponse) servletResponse).sendRedirect(previousUrl);
    }

    private void allowAccess(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean userAlreadyLoggedIn(ServletRequest httpRequest) {
        return ((Client) ((HttpServletRequest) httpRequest).getSession().getAttribute("client")).getUsername() != null;
//        return (((HttpServletRequest) httpRequest).getSession().getAttribute("client"))  != null;
    }

    @Override
    public void destroy() {
    }
}
