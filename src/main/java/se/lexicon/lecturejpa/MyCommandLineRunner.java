package se.lexicon.lecturejpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.lecturejpa.dao.StudentDao;
import se.lexicon.lecturejpa.dao.StudentDaoImpl;
import se.lexicon.lecturejpa.entity.Student;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired // TODO: 17/10/2023 what should I do instead? 
    StudentDao studentDao;

    @Override
    public void run(String... args) throws Exception {
        Student student = new Student("Test", "Testsson", "test@test.se");
        studentDao.persist(student);
    }
}
