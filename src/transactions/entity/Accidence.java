package transactions.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by andrei on 27.11.17.
 */
public class Accidence {
    private int id_accidence;
    private int contract_id;
    private Timestamp date_accidence;
    private String collisions;
    private BigDecimal damage;

    public Accidence() {
    }

    public Accidence(int id_accidence, int contract_id, Timestamp date_accidence, String collisions, BigDecimal damage) {
        this.id_accidence = id_accidence;
        this.contract_id = contract_id;
        this.date_accidence = date_accidence;
        this.collisions = collisions;
        this.damage = damage;
    }

    public int getId_accidence() {
        return id_accidence;
    }

    public void setId_accidence(int id_accidence) {
        this.id_accidence = id_accidence;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public Timestamp getDate_accidence() {
        return date_accidence;
    }

    public void setDate_accidence(Timestamp date_accidence) {
        this.date_accidence = date_accidence;
    }

    public String getCollisions() {
        return collisions;
    }

    public void setCollisions(String collisions) {
        this.collisions = collisions;
    }

    public BigDecimal getDamage() {
        return damage;
    }

    public void setDamage(BigDecimal damage) {
        this.damage = damage;
    }
}
