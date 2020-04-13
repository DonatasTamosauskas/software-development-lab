package persistence;

import entities.Premises;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PremisesDAO {

    @Inject
    private EntityManager em;

    public void create(Premises premises) {
        this.em.persist(premises);
    }

    public Premises findById(Integer id) {
        return this.em.find(Premises.class, id);
    }

    public void update(Premises premises) {
        this.em.merge(premises);
    }

    public List<Premises> loadAll() {
        return this.em.createNamedQuery("Premises.findAll", Premises.class).getResultList();
    }

}
