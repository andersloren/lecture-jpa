package se.lexicon.lecturejpa.dao;

import jakarta.transaction.Transactional;
//import static org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import se.lexicon.lecturejpa.entity.Student;

@SpringBootTest
@Transactional
@Rollback
public class StudentDaoImplTest {

    @Autowired
    StudentDaoImpl studentDao;

    @Test
    public void testPersistStudent() {
        //  Arrange
        Student student = new Student("John", "Doe", "john@test.se");

        // Act
        Student insertedStudent = studentDao.persist(student);

        // Assert
        Assertions.assertNotNull(insertedStudent);
    }

    // TODO: 17/10/2023 Add more tests...

}
