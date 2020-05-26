package services;

import entities.Party;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Specializes;
import java.text.SimpleDateFormat;

@Specializes
public class OnlineLithuanianIdValidator extends LithuanianIdValidator {

    @Override
    public boolean governmentIdCorrect(Party party) {
        // Can call the Lithuanian registry api to check if the given government id is actually valid
        // Is safer to use than the in house validation, since the government id is actually checked with government
        // However, government services can go down, or we may have too many users and this service may no longer be desirable
        // Hence there's still a reason to keep the simpler check
        return true;
    }

}