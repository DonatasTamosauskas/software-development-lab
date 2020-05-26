package services;

import entities.Party;

import javax.enterprise.inject.Default;
import java.text.SimpleDateFormat;

@Default
public class LithuanianIdValidator implements GovernmentIdValidator {

    @Override
    public boolean governmentIdCorrect(Party party) {
        SimpleDateFormat checkPattern = new SimpleDateFormat("yyMMdd");
        String checkDate = checkPattern.format(party.getBirthDate());
        return party.getGovernmentId().contains(checkDate);
    }

}