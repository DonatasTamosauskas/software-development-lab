package usecases;

import entities.Party;
import interceptors.ExceptionCaughtInvocation;
import lombok.Getter;
import lombok.Setter;
import persistence.PartyDAO;
import services.GovernmentIdValidator;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@Model
public class CreateNewParty {

    @Inject
    private PartyDAO partyDAO;

    @Inject
    private GovernmentIdValidator governmentIdValidator;

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
    @ExceptionCaughtInvocation
    public void createParty() throws SQLException {
        if (governmentIdValidator.governmentIdCorrect(this.newParty)) {
            this.partyDAO.create(this.newParty);
        } else {
            // TODO: Remove the temporary government id check bypass
            this.partyDAO.create(this.newParty);
            System.out.println("Wrong government ID format!");
        }
    }

}
