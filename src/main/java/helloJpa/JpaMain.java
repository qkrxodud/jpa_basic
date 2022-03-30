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
            Member member = new Member();
            member.setName("hello ");

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("============call getReference START ==========");
            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println("============call getReference END ==========");
            System.out.println("============call findMember.getName() START ==========");
            System.out.println("findMember.userName = " + findMember.getName());
            System.out.println("============call findMember.getName() END ==========");
            System.out.println("findMember.userName = " + findMember.getName());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
