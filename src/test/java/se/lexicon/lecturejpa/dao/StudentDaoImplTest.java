package se.lexicon.lecturejpa.dao;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import se.lexicon.lecturejpa.entity.Address;
import se.lexicon.lecturejpa.entity.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@Rollback
public class StudentDaoImplTest {

    @Autowired
    StudentDaoImpl studentDao;

    @Autowired
    AddressDaoImpl addressDao;

    @Test
    @DisplayName("Adds student to student table")
    public void testPersistStudent() {
        //  Arrange
        Student student = new Student("John", "Doe", "john@test.se");

        // Act
        Student insertedStudent = studentDao.persist(student);

        // Assert
        Assertions.assertNotNull(insertedStudent);
    }

    @Test
    @DisplayName("Adds two students to student table, retrieves the second student and compares it with the second student being used in persist()")
    public void testFindById() {
        // Arrange
        Student student1 = new Student("Test", "Testsson", "test@test.se");
        Student student2 = new Student("Testare", "Testssonson", "testare@test.se");
        studentDao.persist(student1);
        studentDao.persist(student2);

        // Act
        if (studentDao.findById(student2.getId()).isPresent()) {
            Student foundStudent = studentDao.findById(student2.getId()).get();
            System.out.println(student2);
            System.out.println(foundStudent);
            // Assert
            Assertions.assertEquals(student2, foundStudent);
        } else {
            System.out.println("Student not found!");
        }
    }

    @Test
    @DisplayName("Adds a student to student table, the student's email being passed into findByEmail() and returned student object being compared with the student that was added to the table")
    public void testFindByEmail() {
        // Arrange
        Student student = new Student("Test", "Testsson", "test@test.se");
        studentDao.persist(student);

        // Act
        if (studentDao.findByEmail(student.getEmail()).isPresent()) {
            Student foundStudent = studentDao.findByEmail(student.getEmail()).get();
            System.out.println(student.getEmail());
            System.out.println(foundStudent.getEmail());
            // Assert
            Assertions.assertEquals(student, foundStudent);
        } else {
            System.out.println("Student not found!");
        }
    }

    @Test
    @DisplayName("Three students added, two of them has \"es\" in their names, so a list of 2 elements should be returned")
    public void testFindByFirstNameContains() {
        // Arrange
        Student student1 = new Student("Test", "Testsson", "test@test.se");
        Student student2 = new Student("Anders", "Loren", "anders@loren.se");
        Student student3 = new Student("Testare", "Testssonson", "testare@test.se");
        studentDao.persist(student1);
        studentDao.persist(student2);
        studentDao.persist(student3);

        // Act

        List<Student> foundStudents = new ArrayList<>(studentDao.findByFirstNameContains("es"));

        // Assert
        System.out.println("Elements found: ");
        foundStudents.forEach(System.out::println);
        System.out.println("Number of elements: " + foundStudents.size());
        Assertions.assertEquals(2, foundStudents.size());
    }

    @Test
    @DisplayName("Three students added to student table, findAll() should return list of 3 elements")
    public void testFindAll() {
        // Arrange
        Student student1 = new Student("Test", "Testsson", "test@test.se");
        Student student2 = new Student("Anders", "Loren", "anders@loren.se");
        Student student3 = new Student("Testare", "Testssonson", "testare@test.se");
        studentDao.persist(student1);
        studentDao.persist(student2);
        studentDao.persist(student3);

        // Act

        List<Student> foundStudents = new ArrayList<>(studentDao.findAll());

        // Assert
        foundStudents.forEach(System.out::println);
        Assertions.assertEquals(3, foundStudents.size());
    }

    @Test
    @DisplayName("Address is being added to student, then student updates student in table")
    public void testUpdateAddingAddressToStudent() {

        // Arrange
        Address address = new Address("Jutevägen 5", "Örby", "511 31");
        Student student = new Student("John", "Doe", "john@test.se");

        // Act

        studentDao.persist(student);
        addressDao.persist(address);

        student.setAddress(address);

        studentDao.update(student);

        // Assert

        if (studentDao.findById(student.getId()).isPresent()) {
            Student studentFound = studentDao.findById(student.getId()).get();

            System.out.println(address);
            System.out.println(studentFound.getAddress());

            Assertions.assertEquals(address, studentFound.getAddress());

        } else throw new NullPointerException("Student did not exist!");
    }

    @Test
    @DisplayName("Trying to remove non-existing student Id to trigger NullPointerException in remove() implementation")
    public void testRemoveNonExistingStudent() {
            studentDao.remove("This-Id-Does-Not-Exist");
    }

    @Test
    @DisplayName("Adds a student to student table, then removes it, and expects an empty list of all students to be returned")
    public void testRemoveExistingStudent() {
        // Arrange

        Student student = new Student("Test", "Testsson", "test@test.se");
        studentDao.persist(student);

        // Act

        studentDao.remove(student.getId());

        // Assert

        List<Student> foundStudents = new ArrayList<>(studentDao.findAll());
        System.out.println("findAll returned number of students: " + foundStudents.size());
        assertTrue(foundStudents.isEmpty());
    }
}