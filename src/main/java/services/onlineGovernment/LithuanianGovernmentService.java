package services.onlineGovernment;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class LithuanianGovernmentService implements OnlineGovernmentService, Serializable {

    public boolean checkGovernmentIdValidity(String governmentId) {
        // Simulate calling the government api over here

        try {
            Thread.sleep(500);
        } catch (InterruptedException exception) {
            System.out.println("Government validation interrupted!");
            return false;
        }

        return !governmentId.equals("invalid");
    }

}
