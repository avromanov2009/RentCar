package transactions.connection;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import transactions.dao.BrandDAO;
import transactions.dao.ImageDAO;
import transactions.dao.ModelDAO;
import transactions.dao.ViewCarsDao;
import transactions.entity.Brand;
import transactions.entity.Car;
import transactions.entity.Image;
import transactions.entity.Model;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static javax.xml.ws.Endpoint.create;

/**
 * Created by andrei on 26.11.17.
 */
public class DBTest {
    public static void main(String[] args) throws IOException {
//        ImageDAO imageDAO = ImageDAO.getInstance();
//        String path = "web" + File.separator + "resources" + File.separator + "rs5-audi-speed-drive-2018-race-red-track-german-1.jpg";
//        FileInputStream is = new FileInputStream(path);
//        imageDAO.createImageForCar("test4", is, new Car(1, (byte) 1,"GG-4564", "dgfgdfgfg", 1, 1, 1));
//        Blob imageById = imageDAO.getImageById(32);
//        byte[] imageById = imageDAO.getImageById(32);
//        System.out.println(imageById.length);
        ImageDAO images = ImageDAO.getInstance();
        List<Image> imagesByCarId = images.getImagesByCarId(1);
        for (Image image : imagesByCarId) {
            System.out.println(image.getPath());
        }
    }
}
