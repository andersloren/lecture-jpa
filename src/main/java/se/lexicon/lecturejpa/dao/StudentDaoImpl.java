package se.lexicon.lecturejpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.lexicon.lecturejpa.entity.Student;

import java.util.Collection;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Student persist(Student student) {
        entityManager.persist(student); // insert into student (id, first_name, last_name, status, createDate) value (?, ?, ?, ?, ?)
        return student;
    }

    @Override
    public Optional<Student> findById(String id) {
        Student foundStudent = entityManager.find(Student.class, id);
        return Optional.ofNullable(foundStudent); // TODO: 17/10/2023 what does ofNullable mean?
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return entityManager.createQuery("select s from Student s where s.email = :email", Student.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Collection<Student> findByFirstNameContains(String firstName) {
        return entityManager.createQuery("select s from Student s where s.firstName like :fn", Student.class)
                .setParameter("fn", "%" + firstName + "%")
                .getResultList();
    }

    @Override
    public Collection<Student> findAll() {
        return entityManager.createQuery("select s from Student s", Student.class)
                .getResultList()
                .stream()
                .toList();
    }

    @Override
    @Transactional //use when we want to write to database, but not required when we try to only read to
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void remove(String id) {
        Student foundStudent = entityManager.find(Student.class, id);
        if (foundStudent != null) {
            entityManager.remove(foundStudent);
        } else throw new NullPointerException("Student did not exist!");
    }
}

