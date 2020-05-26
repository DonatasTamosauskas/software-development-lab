package services;

import entities.Party;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class LoggingGovernmentIdDecorator implements GovernmentIdValidator {
    @Inject @Delegate @Any
    private GovernmentIdValidator governmentIdValidator;

    public boolean governmentIdCorrect(Party party) {
        boolean checkResult = governmentIdValidator.governmentIdCorrect(party);

        if (!checkResult) {
            System.out.println("WARNING: User " + party.getName() + " tried to use an incorrect government id: " + party.getGovernmentId());
        }

        return checkResult;
    }
}
