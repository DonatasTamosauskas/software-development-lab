package usecases;

import entities.City;
import entities.Party;
import entities.Premises;
import lombok.Getter;
import lombok.Setter;
import persistence.CityDAO;
import persistence.PartyDAO;
import persistence.PremisesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CreateNewPremises {

    @Inject
    private PremisesDAO premisesDAO;

    @Inject
    private PartyDAO partyDAO;

    @Inject
    private CityDAO cityDAO;

    @Getter
    private List<Premises> allPremises;

    @Getter
    private List<Party> allParties;

    @Getter
    private List<City> allCities;

    @Getter
    @Setter
    private Premises newPremises = new Premises();

    @Getter
    @Setter
    private Integer ownerId;

    @Getter
    @Setter
    private Integer cityId;

    @PostConstruct
    public void init() {
        loadAllPremises();
        loadAllParties();
        loadAllCities();
    }

    private void loadAllPremises() {
        this.allPremises = premisesDAO.loadAll();
    }

    private void loadAllParties() {
        this.allParties = partyDAO.loadAll();
    }

    private void loadAllCities() {
        this.allCities = cityDAO.loadAll();
    }

    @Transactional
    public void createPremises() {
        Party owner = this.partyDAO.findOne(this.ownerId);
        City city = this.cityDAO.findOne(this.cityId);

        this.newPremises.setOwner(owner);
        this.newPremises.setCity(city);
        this.premisesDAO.create(this.newPremises);
    }

}
