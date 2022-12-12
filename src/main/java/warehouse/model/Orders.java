package warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int id_supplier;
    int id_user;
    int id_product;
    int id_reception;
    String status;
    String type;
    String date;
    String FIO_supplier;
    String FIO_user;
    String address;
    double weightOrder;
    double price;
}
