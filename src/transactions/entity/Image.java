package transactions.entity;

import java.io.InputStream;
import java.sql.Blob;

/**
 * Created by andrei on 28.11.17.
 */
public class Image {
    private int id_image;
    private String path;
    private byte[] image;

    public Image() {
    }

    public Image(int id_image, String path, byte[] image) {
        this.id_image = id_image;
        this.path = path;
        this.image = image;
    }

    public int getId_image() {
        return id_image;
    }

    public void setId_image(int id_image) {
        this.id_image = id_image;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
