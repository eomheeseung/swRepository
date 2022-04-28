package hello.se.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Oid {
    @Id
    @Column(name = "last_id", nullable = false)
    private Integer last_id;
}
