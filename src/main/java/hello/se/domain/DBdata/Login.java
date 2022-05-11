package hello.se.domain.DBdata;

import hello.se.web.Form.LoginForm;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Login")
public class Login {
    @Id
    @GeneratedValue
    @Column(name = "login_key")
    private Long key;

    @Column(name = "login_id", nullable = false)
    private String id;

    @Column(name = "password")
    private String password;

    /*@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "login")*/
    /*@OneToOne
    @JoinTable(name = "login_res",
    joinColumns = {@JoinColumn (name = "login_key",referencedColumnName = "login_key")},
    inverseJoinColumns = {@JoinColumn(name = "res_oid",referencedColumnName = "res_oid")})*/
    @ManyToOne
    @JoinColumn(name = "reservation_res_oid")
    private Reservation reservation;

    @Column(name = "username")
    private String username;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id")
    private Customer customer;*/

    public void setLogin(LoginForm loginForm) {
        this.id = loginForm.getId();
        this.password = loginForm.getPassword();
        this.username = loginForm.getUsername();
        this.phoneNumber = loginForm.getPhoneNumber();
    }

    public Login() {
    }
}
