package servlets;

import transactions.dao.ViewCarsDao;
import transactions.entity.FullCarInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by andrei on 01.12.17.
 */
@WebServlet("/view-cars")
public class ViewCarsServlet extends HttpServlet {
    private static final long serialVersionUID = -6465919763111335L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterNames().hasMoreElements()) {
            System.out.println("Есть параметры");
            for (Map.Entry<String, String[]> item : req.getParameterMap().entrySet()) {
                System.out.println(item.getKey() + "=" + Arrays.toString(item.getValue()));
            }
        } else {
            System.out.println("No parameters!");
            ViewCarsDao carsDao = ViewCarsDao.getInstance();
            List<FullCarInfo> fullCarInfos = carsDao.getAllCars().get();
            req.setAttribute("cars", fullCarInfos);
        }
        getServletContext().getRequestDispatcher("/view-cars.jsp").forward(req, resp);
    }
}
