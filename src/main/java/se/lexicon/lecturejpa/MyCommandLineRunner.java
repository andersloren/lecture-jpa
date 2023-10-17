package se.lexicon.lecturejpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.lecturejpa.dao.AddressDao;
import se.lexicon.lecturejpa.dao.StudentDao;
import se.lexicon.lecturejpa.dao.StudentDaoImpl;
import se.lexicon.lecturejpa.entity.Address;
import se.lexicon.lecturejpa.entity.Student;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired // TODO: 17/10/2023 what should I do instead? 
    StudentDao studentDao;
    @Autowired
    AddressDao addressDao;

    @Override
    public void run(String... args) throws Exception {

//        Address address1 = new Address("Jutevägen 5", "Örby", "511 31");
//        Address address2 = new Address("Fogdevägen 5A", "Karlskrona", "371 42");
//        addressDao.persist(address1);
//        addressDao.persist(address2);
//
//        Student student1 = new Student("Test", "Testsson", "test@test.se");
//        Student student2 = new Student("Testare", "Testssonson", "testare@test.se");
//        studentDao.persist(student1);
//        studentDao.persist(student2);
//
//        student1.setAddress(address1);
//        student2.setAddress(address2);
//        studentDao.update(student1);
//        studentDao.update(student2);
//
//
//        studentDao.findByFirstNameContains("test").forEach(System.out::println);
//
//        System.out.println(">>>>>\n\n " + student1.getAddress().toString());
    }
}
