package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lease_agreement")
@NamedQueries({
        @NamedQuery(name = "lease_agreement.findPartiesByPremises",
                query = "select la from LeaseAgreement la where la.premises = :premises order by la.startDate"),
        @NamedQuery(name = "lease_agreement.findPremisesByParty",
                query = "select la from LeaseAgreement la where la.party = :party"),
        @NamedQuery(name = "lease_agreement.findMultipleLeasersByDates",
                query = "select la.party from LeaseAgreement la where la.premises = :premises and la.startDate = :startDate")
})
@Getter
@Setter
@EqualsAndHashCode(exclude = "primary_leaser end_date")
public class LeaseAgreement implements Serializable {
    @Id
    @ManyToOne()
    private Premises premises;

    @Id
    @ManyToOne
    private Party party;

    @Id
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "primary_leaser")
    private Boolean primaryLeaser;

    public LeaseAgreement() {

    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Premises getPremises() {
        return premises;
    }

    public void setPremises(Premises premises) {
        this.premises = premises;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getPrimaryLeaser() {
        return primaryLeaser;
    }

    public void setPrimaryLeaser(Boolean primaryLeaser) {
        this.primaryLeaser = primaryLeaser;
    }
}
