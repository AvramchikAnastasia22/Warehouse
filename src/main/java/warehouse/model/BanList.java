package warehouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BanList {
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Id
    int id_supplier;
    String FIO;
    String reason;
    String name_photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(int id_supplier) {
        this.id_supplier= id_supplier;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getName_photo() {
        return name_photo;
    }

    public void setName_photo(String name_photo) {
        this.name_photo = name_photo;
    }
}
