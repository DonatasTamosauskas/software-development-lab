package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Party.findAll", query = "select id from Party as id")
})
@Table(name = "party")
@Getter
@Setter
public class Party implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    @Column(name = "government_id")
    private String governmentId;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Party() {
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(String governmentId) {
        this.governmentId = governmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Party(String name, String governmentId, Date birthDate, boolean isPrivate) {
        this.name = name;
        this.governmentId = governmentId;
        this.birthDate = birthDate;
        this.isPrivate = isPrivate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party player = (Party) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
