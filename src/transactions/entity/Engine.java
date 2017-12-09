package transactions.entity;

/**
 * Created by andrei on 27.11.17.
 */
public class Engine {
    private int id_engine;
    private float capacity;

    public Engine() {
    }

    public Engine(int id_engine, float capacity) {
        this.id_engine = id_engine;
        this.capacity = capacity;
    }

    public int getId_engine() {
        return id_engine;
    }

    public void setId_engine(int id_engine) {
        this.id_engine = id_engine;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }
}
