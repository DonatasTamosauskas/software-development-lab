package rest.contracts;

import entities.Party;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class PartyDTO {
    private String name;
    private String governmentId;
    private Date birthDate;
    private Boolean isPrivate;

    public PartyDTO() {
    }

    public PartyDTO(Party party) {
        name = party.getName();
        governmentId = party.getGovernmentId();
        birthDate = party.getBirthDate();
        isPrivate = party.getPrivate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(String governmentId) {
        this.governmentId = governmentId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
