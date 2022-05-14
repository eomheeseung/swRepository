package hello.se.domain.DBdata;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


//예약정보
@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_oid")
    private Integer oid;

    @Column(name = "name")
    private String name;

    @Column(name = "covers")
    private Integer covers;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "table_id")
    private Integer table_id;

    /*@Column(name = "customer_id")
    private Integer customer_id;*/

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "arrivalTime")
    private LocalDateTime arrivalTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_key")
    private Login login;

   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resTable_oid")
    private ResTable resTable;*/

    public Reservation() {
    }

    public Reservation(Integer oid, Integer covers, Date date, LocalDateTime time,
                       Integer table_id, Integer customer_id, LocalDateTime arrivalTime) {
        this.oid = oid;
        this.covers = covers;
        this.date = date;
        this.time = time;
        this.table_id = table_id;
//        this.customer_id = customer_id;
        this.arrivalTime = arrivalTime;
    }

    public void setLogin(Login login) {
        this.login = login;
        login.getReservations().add(this);
    }
}
