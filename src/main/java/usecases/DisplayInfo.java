package usecases;

import entities.Party;
import entities.Premises;
import interceptors.ExceptionCaughtInvocation;
import lombok.Getter;
import lombok.Setter;
import persistence.PartyDAO;
import persistence.PremisesDAO;
import services.onlineGovernment.OnlineGovernmentService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

@Model
public class DisplayInfo implements Serializable {

    @Inject
    private PartyDAO partyDAO;

    @Inject
    private PremisesDAO premisesDAO;

    @Inject
    private CheckValidity checkValidity;

    @Getter
    private List<Party> allParties;

    @Getter
    private List<Premises> allPremises;

    @Getter
    @Setter
    private Party partyToCreate = new Party();

    @Getter
    @Setter
    private String governmentId = "";

    @PostConstruct
    public void init() {
        loadAllParties();
        loadAllPremises();
    }

    private void loadAllParties() {
        this.allParties = partyDAO.loadAll();
    }

    private void loadAllPremises() {
        this.allPremises = premisesDAO.loadAll();
    }

    @Transactional
    @ExceptionCaughtInvocation
    public void createParty() throws SQLException{
        this.partyDAO.create(this.partyToCreate);
    }

    public String openCheckIdValidity() {
        this.checkValidity.resetResults();
        this.checkValidity.checkGovernmentIdValidity(this.governmentId);
        return "checkValidity?faces-redirect=true&governmentId=" + this.governmentId;
    }

}
