package servlets;

import transactions.dao.ViewCarsDao;
import transactions.entity.FullCarInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andrei on 03.12.17.
 */
@WebServlet(value = "/car", name = "CarServlet")
public class CarServlet extends HttpServlet {
    private static final long serialVersionUID = -561389347038394415L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ViewCarsDao carsDao = ViewCarsDao.getInstance();
        FullCarInfo car = carsDao.getFullInfoByCarId(Integer.parseInt(req.getParameter("id"))).get();
        req.setAttribute("car", car);
        getServletContext().getRequestDispatcher("/car.jsp").forward(req, resp);
    }
}
