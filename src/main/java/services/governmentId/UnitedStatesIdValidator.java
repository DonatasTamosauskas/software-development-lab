package services.governmentId;

import entities.Party;

import javax.enterprise.inject.Alternative;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Alternative
public class UnitedStatesIdValidator implements GovernmentIdValidator {

    @Override
    public boolean governmentIdCorrect(Party party) {
        String regex = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(party.getGovernmentId());
        return matcher.matches();
    }

}