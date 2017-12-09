package servlets;

import transactions.dao.ViewCarsDao;
import transactions.entity.Client;
import transactions.entity.FullCarInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by andrei on 25.11.17.
 */
@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 6657406239187513823L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Locale locale = req.getLocale();
        resp.setHeader("Content-Language", "en");
        ViewCarsDao carsDao = ViewCarsDao.getInstance();
        List<FullCarInfo> fullCarInfos = carsDao.getAllCars().get();
        req.setAttribute("cars", fullCarInfos);
        getServletContext().getRequestDispatcher("/index2.jsp").forward(req, resp);
    }
}
