package se.lexicon.lecturejpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.lecturejpa.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
