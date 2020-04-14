package usecases;

import entities.LeaseAgreement;
import entities.Party;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisplayElement {
    private Date startDate;
    private Date endDate;
    private List<String> leasers;

    public DisplayElement(LeaseAgreement leaseAgreement) {
        this.leasers = new ArrayList<>();

        this.startDate = leaseAgreement.getStartDate();
        this.endDate = leaseAgreement.getEndDate();
        this.leasers.add(leaseAgreement.getParty().getName());
    }

    public void addParty(Party party) {
        this.leasers.add(party.getName());
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getLeasers() {
        return leasers;
    }

    public void setLeasers(List<String> leasers) {
        this.leasers = leasers;
    }
}
