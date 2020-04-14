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
                query = "select la from LeaseAgreement la where la.premises = :premises"),
        @NamedQuery(name = "lease_agreement.findPremisesByParty",
                query = "select la from LeaseAgreement la where la.party = :party")
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
}
