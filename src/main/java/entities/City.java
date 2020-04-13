package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "city.findAll", query = "select id from City as id")
})
@Table(name = "city")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    @Column(name = "country_name")
    private String countryName;

    public City() {

    }
}
