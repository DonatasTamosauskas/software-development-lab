package persistence;

import entities.City;
import entities.Party;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CityDAO {

    @Inject
    private EntityManager em;

    public void create(City city) {
        this.em.persist(city);
    }

    public City findOne(Integer id){
        return em.find(City.class, id);
    }

    public List<City> loadAll() {
        return this.em.createNamedQuery("city.findAll", City.class).getResultList();
    }
}