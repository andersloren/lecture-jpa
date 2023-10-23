package se.lexicon.lecturejpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.lecturejpa.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    // select * from student where first_name like % ? %;
    List<Student> findByFirstNameContains(String firstName);

    @Query("Select s from Student s where s.firstName = :fn")
    List<Student> selectStudentByFirstName(@Param("fn") String firstName);

    // select * from student where status = true;
    List<Student> findByStatusTrue();
    // select * from student where email = email
    Student findByEmailIgnoreCase(String email);

    // update student set status = true where id = :id
    @Modifying
    @Query("UPDATE s Student s set s.status = true where s.id = :id") // Note that UPDATE is first1
    void updateStudentStatusToTrue(@Param("id") String id);

}
