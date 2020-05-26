package services.governmentId;

import entities.Party;

import javax.enterprise.inject.Specializes;

@Specializes
public class OnlineLithuanianIdValidator extends LithuanianIdValidator {

    @Override
    public boolean governmentIdCorrect(Party party) {
        // Can call the Lithuanian registry api to check if the given government id is actually valid
        // Is safer to use than the in house validation, since the government id is actually checked with government
        // However, government services can go down, or we may have too many users and this service may no longer be desirable
        // Hence there's still a reason to keep the simpler check


        // The idea for async here is to: invoke lithuanian government api to check if the government id actually exists
        // Or create a button in 'create new party' secction that allows to check if the government is valid

        return true;
    }

}