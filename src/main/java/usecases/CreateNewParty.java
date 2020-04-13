package usecases;

import entities.Party;
import lombok.Getter;
import lombok.Setter;
import persistence.PartyDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CreateNewParty {

    @Inject
    private PartyDAO partyDAO;

    @Getter
    private List<Party> allParties;

    @Getter @Setter
    private Party newParty = new Party();

    @PostConstruct
    public void init(){
        loadAllParties();
    }

    private void loadAllParties() {
        this.allParties = partyDAO.loadAll();
    }

    @Transactional
    public void createParty() {
        this.partyDAO.create(this.newParty);
    }

}
