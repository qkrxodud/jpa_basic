package helloJpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {
    @Id @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "locker")
    private Member member;
    private String name;
}