package lesson_6;

import com.geekbrains.geekspring.entities.Course;
import com.geekbrains.geekspring.entities.Student;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends PagingAndSortingRepository<Student, Long> {
    @Query(value = "SELECT s.id\n" +
            "FROM students s\n" +
            "LEFT JOIN students_courses c on s.id = c.student_id\n" +
            "GROUP BY s.id\n" +
            "ORDER BY count(distinct c.course_id) DESC;", nativeQuery = true)
    List<Integer> getStudentsIdsByCoursesCountDesc(PageRequest pageRequest);

    @Query(value = "SELECT s FROM Student s LEFT JOIN s.courses c GROUP BY s.id ORDER BY count(c)")
    List<Student> getStudentsByCoursesCountDescHQL(Pageable pageable);

    Student findOneById(Long id);

    void deleteStudentById(Long id); // вот можно наверно еще один зачем то придумать

    @Query("SELECT s FROM Student s where s.id = :id")
    List<Course> findCoursesStudentId(@Param("id") Long id);

}
