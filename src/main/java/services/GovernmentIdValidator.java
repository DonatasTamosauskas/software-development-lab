package services;

import entities.Party;

public interface GovernmentIdValidator {
    boolean governmentIdCorrect(Party party);
}
