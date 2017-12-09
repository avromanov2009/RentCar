package transactions.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by andrei on 28.11.17.
 */
public class Contract {
    private int id_contract;
    private int car_id;
    private int client_id;
    private boolean isComfimed;
    private Timestamp date_start;
    private Timestamp date_end;
    private BigDecimal price;

    public Contract() {
    }

    public Contract(int id_contract, int car_id, int client_id, Timestamp date_start, Timestamp date_end, BigDecimal price) {
        this.id_contract = id_contract;
        this.car_id = car_id;
        this.client_id = client_id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.price = price;
        this.isComfimed = false;
    }

    public boolean isComfimed() {
        return isComfimed;
    }

    public void setComfimed(String comfimed) {
        isComfimed = Boolean.parseBoolean(comfimed);
    }

    public int getId_contract() {
        return id_contract;
    }

    public void setId_contract(int id_contract) {
        this.id_contract = id_contract;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public Timestamp getDate_start() {
        return date_start;
    }

    public void setDate_start(Timestamp date_start) {
        this.date_start = date_start;
    }

    public Timestamp getDate_end() {
        return date_end;
    }

    public void setDate_end(Timestamp date_end) {
        this.date_end = date_end;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
