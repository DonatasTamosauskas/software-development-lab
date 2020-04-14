package usecases;

import entities.LeaseAgreement;
import entities.Premises;
import lombok.Getter;
import persistence.LeaseAgreementDAO;
import persistence.PremisesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
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

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer premisesId = Integer.parseInt(requestParameters.get("premisesId"));

        this.selectedPremises = premisesDAO.findById(premisesId);
        this.premisesLeasingAgreements = leaseAgreementDAO.loadAllByPremises(this.selectedPremises);

        System.out.println(this.premisesLeasingAgreements.size());
    }

}
