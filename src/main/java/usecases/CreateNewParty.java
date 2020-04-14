package usecases;

import entities.Party;
import lombok.Getter;
import lombok.Setter;
import persistence.PartyDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
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

    public boolean correctGovernmentId(Party party) {
        SimpleDateFormat checkPattern = new SimpleDateFormat("yyMMdd");
        String checkDate = checkPattern.format(party.getBirthDate());
        return party.getGovernmentId().contains(checkDate);
    }

    @Transactional
    public void createParty() {
        if (correctGovernmentId(this.newParty)) {
            this.partyDAO.create(this.newParty);
        } else {
            System.out.println("Wrong government ID format!");
        }
    }

}
