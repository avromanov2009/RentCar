package controller.mainPage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by andrei on 25.11.17.
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = -446787993447725240L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    getServletContext().getRequestDispatcher("/home.jsp")
            .forward(req, resp);
    }
}
