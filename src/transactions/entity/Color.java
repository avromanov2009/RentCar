package transactions.entity;

/**
 * Created by andrei on 27.11.17.
 */
public class Color {
    private int id_color;
    private String color;

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Color() {
    }

    public Color(int id_color, String color) {
        this.id_color = id_color;
        this.color = color;
    }
}
