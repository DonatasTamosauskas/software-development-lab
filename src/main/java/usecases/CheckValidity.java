package usecases;

import entities.Party;
import interceptors.ExceptionCaughtInvocation;
import lombok.Getter;
import lombok.Setter;
import persistence.PartyDAO;
import services.governmentId.GovernmentIdValidator;
import services.onlineGovernment.OnlineGovernmentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Model
@SessionScoped
public class CheckValidity implements Serializable {

    @Getter
    private String governmentId;

    @Inject
    private OnlineGovernmentService onlineGovernmentService;
    private CompletableFuture<Boolean> validationResults = null;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String governmentId = requestParameters.get("governmentId");

        this.governmentId = governmentId;
        checkGovernmentIdValidity(governmentId);
    }

    public void checkGovernmentIdValidity(String govId) {
        validationResults = CompletableFuture.supplyAsync(() -> onlineGovernmentService.checkGovernmentIdValidity(govId));
    }

    public String getValidationStatus() throws ExecutionException, InterruptedException {
        if (validationResults == null) {
            return null;
        } else if (!validationResults.isDone()) {
            return "Validation in progress...";
        }

        if ((validationResults.get())) {
            return "Validation completed, government id is Valid";
        } else {
            return "Validation complete, government id is Invalid";
        }
    }

    public void resetResults() {
        this.validationResults = null;
    }
}
