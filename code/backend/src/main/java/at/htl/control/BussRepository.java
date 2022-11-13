package at.htl.control;

import at.htl.entity.Buss;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class BussRepository {

    @Inject
    EntityManager em;

    public Buss save(Buss buss) {
        return em.merge(buss);
    }

    public List<Buss> findAll() {
        TypedQuery<Buss> query = em
                .createNamedQuery("Buss.findAll", Buss.class);
        return query.getResultList();
    }

    public Buss findById(long id) {
        return em.find(Buss.class, id);
    }

    public List<Buss> findByFuelType(
            String fuelType
    ) {
        TypedQuery<Buss> query = em
                .createNamedQuery("Buss.findByFuelType", Buss.class)
                .setParameter("FIRST", fuelType);
        return query.getResultList();
    }


}

