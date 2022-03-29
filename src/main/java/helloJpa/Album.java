package helloJpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // 회사 규약에 따라 변경해주면 타입에 들어가는 값이 변경이된다.
public class Album extends Item {
    private String artist;
}
