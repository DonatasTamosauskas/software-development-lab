package persistence;

import entities.City;
import entities.LeaseAgreement;
import entities.Party;
import entities.Premises;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LeaseAgreementDAO {

    @Inject
    private EntityManager em;

    public void create(LeaseAgreement leaseAgreement) {
        this.em.persist(leaseAgreement);
    }

    public List<LeaseAgreement> loadAllByPremises(Premises premises) {
        return this.em.createNamedQuery("lease_agreement.findPartiesByPremises", LeaseAgreement.class)
                .setParameter("premises", premises).getResultList();
    }
}