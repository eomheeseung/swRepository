package hello.se.domain.respository;

import hello.se.domain.DBdata.Login;
import hello.se.domain.DBdata.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class ReservationRepository {
    @PersistenceContext
    EntityManager em;

    public Reservation save(Reservation reservation) {
        if (reservation.getOid() == null) {
            em.persist(reservation);
        } else {
            em.merge(reservation);
        }
//        em.persist(reservation);
        return reservation;
    }

    /*public Reservation bothSaveLogin(Reservation reservation, Login login) {
        if (reservation.getOid() == null) {
            reservation.setLogin(login);
            em.persist(reservation);
        } else {
            em.merge(reservation);
        }
//        em.persist(reservation);
        return reservation;
    }*/

    public Reservation findReservation(int oid) {
        return em.find(Reservation.class, oid);
    }

    public List<Reservation> findAll(Login login) {
        return em.createQuery("select r from Reservation r inner join Login l on r.loginKey =: key", Reservation.class)
                .setParameter("key", login.getKey())
                .getResultList();
    }

    public List<Reservation> findForOid(int oid) {
        return em.createQuery("select r from Reservation r where r.oid = :oid", Reservation.class)
                .setParameter("oid", oid)
                .getResultList();
    }

    public List<Reservation> findForTime(LocalDateTime time) {
        return em.createQuery("select r from Reservation r where r.time = :time", Reservation.class)
                .setParameter("time", time)
                .getResultList();
    }

    public List<Reservation> findForDate(LocalDateTime date) {
        return em.createQuery("select r from Reservation r where r.date = :date", Reservation.class)
                .setParameter("date", date)
                .getResultList();
    }

    public List<Reservation> findForArriveTime(LocalDateTime arrival) {
        return em.createQuery("select r from Reservation r where r.arrivalTime = :arrival", Reservation.class)
                .setParameter("arrival", arrival)
                .getResultList();
    }

    public List<Reservation> findForTableId(int tableId) {
        return em.createQuery("select r from Reservation r where r.table_id = :tableId", Reservation.class)
                .setParameter("tableId", tableId)
                .getResultList();
    }

    public void remove(Reservation reservation) {
        em.remove(reservation);
    }
}
