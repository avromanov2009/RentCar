package servlet;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import transactions.dao.ClientDao;
import transactions.entity.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * Servlet implementation class Session
 */
@WebServlet(value = "/login", name = "LoginController")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 4227896450180136624L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String un = request.getParameter("uname");
        String pwd = cryptPassword(request.getParameter("pass"));
        ClientDao clientInstance = ClientDao.getInstance();
        Optional<Client> clientByLogin = clientInstance.getClientByLogin(un);
        Client client = clientByLogin.get();
        if (client.getUsername().equals(un) && client.getPassword().equals(pwd)) {
//            out.print("Welcome, " + un);
            HttpSession session = request.getSession(true); // reuse existing
            session.setAttribute("client", client);
            session.setMaxInactiveInterval(10 * 60); // 30 seconds
            response.sendRedirect("/");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }
    }

    private String cryptPassword(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return HexBin.encode(sha256.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}