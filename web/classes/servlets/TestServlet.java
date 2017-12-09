package servlets;

import transactions.dao.ImageDAO;
import transactions.entity.Image;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by andrei on 29.11.17.
 */
@WebServlet("/test/*")
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 3208415682126821808L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        ImageDAO imageDAO = ImageDAO.getInstance();
        List<Image> image = imageDAO.getImagesByCarId(Integer.parseInt(id));
        req.setAttribute("images", image);
//        getServletContext().getRequestDispatcher("/test.jsp").forward(req, resp);
        byte[] blob = image.get(0).getImage();
        resp.setContentType("image/jpg");
//        resp.setContentLength(blob.length);
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(blob);
        outputStream.flush();
        outputStream.close();
//        resp.getOutputStream().write(rs.getBytes("photo"));
    }
}
