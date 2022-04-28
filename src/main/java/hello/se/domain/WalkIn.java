package hello.se.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class WalkIn {
    @Id
    @Column(name = "oid", nullable = false)
    private Integer oid;

    @Column(name = "covers")
    private Integer covers;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "table_id")
    private Integer table_id;


}
