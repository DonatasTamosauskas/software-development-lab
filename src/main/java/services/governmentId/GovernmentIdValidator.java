package services.governmentId;

import entities.Party;

import java.io.Serializable;

public interface GovernmentIdValidator extends Serializable {
    boolean governmentIdCorrect(Party party);
}
