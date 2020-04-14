package usecases;

import entities.LeaseAgreement;
import entities.Party;
import entities.Premises;
import lombok.Getter;
import persistence.LeaseAgreementDAO;
import persistence.PremisesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class LeasingHistory {

    @Inject
    private PremisesDAO premisesDAO;

    @Inject
    private LeaseAgreementDAO leaseAgreementDAO;

    @Getter
    private Premises selectedPremises;

    @Getter
    private List<LeaseAgreement> premisesLeasingAgreements;

    @Getter
    private List<DisplayElement> displayElements;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer premisesId = Integer.parseInt(requestParameters.get("premisesId"));

        this.selectedPremises = premisesDAO.findById(premisesId);
        this.premisesLeasingAgreements = leaseAgreementDAO.loadAllByPremises(this.selectedPremises);

        this.displayElements = generateDisplayLeaseList();
    }

    public List<DisplayElement> generateDisplayLeaseList() {
        List<DisplayElement> displayElements = new ArrayList<>();

        for (int i = 0; i < this.premisesLeasingAgreements.size(); i++) {
            LeaseAgreement current = this.premisesLeasingAgreements.get(i);
            DisplayElement displayElement = new DisplayElement(current);

            if (i < this.premisesLeasingAgreements.size() - 1) {
                // TODO: Convert into a iterative approach to work with more than 1 duplicate
                LeaseAgreement next = this.premisesLeasingAgreements.get(i + 1);

                if (current.getStartDate().equals(next.getStartDate())) {
                    displayElement.addParty(next.getParty());
                    i = i + 1;
                }
            }
            displayElements.add(displayElement);
        }

        return displayElements;
    }

}