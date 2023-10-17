package se.lexicon.lecturejpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.lexicon.lecturejpa.entity.Address;

import java.util.Collection;
import java.util.Optional;

@Repository
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Address persist(Address address) {
        entityManager.persist(address);
        return address;
    }

    @Override
    public Optional<Address> findById(Long id) {
        Address foundAddress = entityManager.find(Address.class, id);
        return Optional.ofNullable(foundAddress);
    }

    @Override
    public Collection<Address> findAll() {
        return entityManager.createQuery("select a from Address a", Address.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void update(Address address) {
        entityManager.merge(address);
    }

    @Override
    public void remove(Long id) {
        Address foundAddress = entityManager.find(Address.class, id);
        if (foundAddress != null) {
            entityManager.remove(foundAddress);
        } else {
            throw new NullPointerException();
        }
    }
}
