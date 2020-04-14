package usecases;

import entities.Party;
import entities.Premises;
import lombok.Getter;
import lombok.Setter;
import persistence.PartyDAO;
import persistence.PremisesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Model
public class DisplayInfo implements Serializable {

    @Inject
    private PartyDAO partyDAO;

    @Inject
    private PremisesDAO premisesDAO;

    @Getter
    private List<Party> allParties;

    @Getter
    private List<Premises> allPremises;

    @Getter
    @Setter
    private Party partyToCreate = new Party();

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
    public void createParty() {
        this.partyDAO.create(this.partyToCreate);
    }

}
