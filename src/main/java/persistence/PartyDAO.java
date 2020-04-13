package persistence;

import entities.Party;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class PartyDAO {

    @Inject
    private EntityManager em;

    public void create(Party party){
        this.em.persist(party);
    }

    public Party findOne(Integer id){
        return em.find(Party.class, id);
    }

    public List<Party> loadAll() {
        return em.createNamedQuery("Party.findAll", Party.class).getResultList();
    }

//    public List<Party> loadTest() {
//        List<Party> fakeParties = new ArrayList<Party>();
//        fakeParties.add(new Party(2, "Bernie Sanders", "1111111111", new Date(), true));
//        return fakeParties;
//    }

    public Party update(Party party){
        return em.merge(party);
    }
}
