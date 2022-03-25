package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // 애플리케이션 에서 한개만 만들어 져야된다.

        EntityManager em = emf.createEntityManager(); //하나의 단위를 만들때마다 만들어 줘야된다.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            System.out.println("================");
            Member memberA = new Member();
            memberA.setName("memberA");
            em.persist(memberA);
            System.out.println("MemberA : " + memberA.getId());

            Member memberB = new Member();
            memberB.setName("memberB");
            em.persist(memberB);
            System.out.println("memberB : " + memberB.getId());

            Member memberC = new Member();
            memberC.setName("memberC");
            em.persist(memberC);
            System.out.println("memberC : " + memberC.getId());

            Member memberD = new Member();
            memberD.setName("memberD");
            em.persist(memberD);
            System.out.println("memberD : " + memberD.getId());
            System.out.println("================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
