package helloJpa;

import org.hibernate.Hibernate;

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
            Member member1 = new Member();
            member1.setName("member1");
            member1.setHomeAddress(new Address("homeCity", "street", "zipcode"));
            member1.getFavoriteFoods().add("치킨");
            member1.getFavoriteFoods().add("족발");
            member1.getFavoriteFoods().add("피자");

            member1.getAddressHistory().add(new Address("old1", "street", "zipcode"));
            member1.getAddressHistory().add(new Address("old2", "street", "zipcode"));

            em.persist(member1);

            em.flush();
            em.clear();

            System.out.println("======================================");

            //조회
            Member findMember = em.find(Member.class, member1.getId());
            List<Address> addressHistory = findMember.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println("address...-==>" + address.getCity());
            }

            Address address = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", address.getStreet(), address.getZipcode()));

            //Map 수정
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            //List 수정b
            findMember.getAddressHistory().remove(new Address("old1", "street", "zipcode"));
            findMember.getAddressHistory().add(new Address("old1", "street", "zipcode"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
