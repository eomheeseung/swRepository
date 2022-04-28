package hello.se.respository;

import hello.se.domain.ResTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ResTableRepository {
    @PersistenceContext
    EntityManager em;

    public ResTable save(int number,int places) {
        ResTable savedTable = new ResTable();
        savedTable.setNumber(number);
        savedTable.setPlaces(places);

        em.persist(savedTable);
        return savedTable;
    }

    public ResTable findTable(int id) {
        return em.find(ResTable.class, id);
    }
}
