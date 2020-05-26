package persistence;

import entities.Party;
import interceptors.ExceptionCaughtInvocation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class PartyDAO {

    @Inject
    private EntityManager em;

    @ExceptionCaughtInvocation
    public void create(Party party) throws SQLException {
        if (party.getName().equals("exception")) {
            throw new SQLException("This is a test exception");
        } else {
            this.em.persist(party);
        }
    }

    public Party findOne(Integer id){
        return em.find(Party.class, id);
    }

    public List<Party> loadAll() {
        return em.createNamedQuery("Party.findAll", Party.class).getResultList();
    }

    public Party update(Party party){
        return em.merge(party);
    }
}
