package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by andrei on 26.11.17.
 */
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = -1812520515331248229L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        if (session.isNew()) {
            getServletContext().getRequestDispatcher("/auth.jsp")
                    .forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/index")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Auth POST");
        String username = req.getParameter("username");
        HttpSession session = req.getSession(true);
        System.out.println(username);
        session.setAttribute("username", username);
        session.setMaxInactiveInterval(30);
        getServletContext().getRequestDispatcher("/auth.jsp")
                .forward(req, resp);
        resp.sendRedirect("/index");
    }
}
