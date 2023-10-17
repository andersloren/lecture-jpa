package se.lexicon.lecturejpa.dao;

import se.lexicon.lecturejpa.entity.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentDao {

    Student persist(Student student);
    Optional<Student> findById(String id);
    Optional<Student> findByEmail(String email);
    Collection<Student> findByNameContains(String name);
    Collection<Student> findAll();
    void update(Student student);
    void remove(String id);

}
