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
public class Reception {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int id;
    String address;
    String town;
    String name_photo;
    String timeWork;

}
