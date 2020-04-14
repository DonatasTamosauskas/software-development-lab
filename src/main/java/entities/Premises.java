package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Premises.findAll", query = "select id from Premises as id")
})
@Table(name = "premises")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Premises implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private City city;

    @Size(max = 255)
    private String street;

    @Size(max = 255)
    @Column(name = "house_number")
    private String houseNumber;

    @Size(max = 255)
    private String apartament;

    @ManyToOne
    private Party owner;

    @Size(max = 255)
    private String type;

    public Premises() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Party getOwner() {
        return owner;
    }

    public void setOwner(Party owner) {
        this.owner = owner;
    }
}
