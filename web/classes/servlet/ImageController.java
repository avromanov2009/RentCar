package servlet;

import transactions.dao.ImageDAO;
import transactions.entity.Image;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andrei on 29.11.17.
 */
@WebServlet("/image")
public class ImageController extends HttpServlet {
    private static final long serialVersionUID = -4596849751328828616L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_image = 0;
        if (req.getParameter("autoID") != null) {
            id_image = Integer.parseInt(req.getParameter("autoID"));
        } else if (req.getParameter("imageCarId") != null) {
            id_image = Integer.parseInt(req.getParameter("imageCarId"));
        }
//        int id = Integer.parseInt(req.getParameter("autoID"));
        resp.setContentType("image/jpeg");
        ImageDAO instanceImage = ImageDAO.getInstance();
//        instanceImage.getImagesByCarId(id);

        Image image = instanceImage.getImageById(id_image).get();
        byte[] blob = image.getImage();
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(blob);
        outputStream.flush();
        outputStream.close();
    }
}
