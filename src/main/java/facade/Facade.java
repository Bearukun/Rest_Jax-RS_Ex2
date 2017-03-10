package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author bearu
 */
public class Facade implements IPersonFacade {

    private EntityManagerFactory emf;

    public Facade() {
    }

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {

        this.emf = emf;

    }

    @Override
    public Person addPerson(Person p) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        return p;

    }

    @Override
    public Person deletePerson(int id) {

        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> result = em.createNamedQuery("Person.findById", Person.class);
        Person person = result.setParameter("id", id).getSingleResult();
        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();

        return person;

    }

    @Override
    public Person getPerson(int id) {

        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> result = em.createNamedQuery("Person.findById", Person.class);
        Person person = result.setParameter("id", id).getSingleResult();
        return person;

    }

    @Override
    public List<Person> getPersons() {

        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> result = em.createNamedQuery("Person.findAll", Person.class);
        List<Person> persons = result.getResultList();
        return persons;

    }

    @Override
    public Person editPerson(Person p) {
      
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> result = em.createNamedQuery("Person.findById", Person.class);
        Person entityToEdit = result.setParameter("id", p.getId()).getSingleResult();
        em.getTransaction().begin();
        entityToEdit.setFName(p.getFName());
        entityToEdit.setLName(p.getLName());
        entityToEdit.setPhone(p.getPhone());
        em.getTransaction().commit();
        return entityToEdit;
        
        
    }

}
