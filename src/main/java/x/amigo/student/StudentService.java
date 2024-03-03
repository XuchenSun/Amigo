package x.amigo.student;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    static final XLogger LOGGER= XLoggerFactory.getXLogger(StudentService.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
               // System.out.println(student);

                Optional<Student> studentOptional=studentRepository
                        .findStudentByEmail(student.getEmail());
                if(studentOptional.isPresent()){
                    LOGGER.info("email taken");
                    throw  new IllegalStateException("email taken");
                }
                else
                {
                    studentRepository.save(student);
                    LOGGER.info("add New Student "+student.toString());
                }
    }

    public void deleteStudent(Long studentId) {

        boolean exists=studentRepository.existsById(studentId);
        if(!exists){
            LOGGER.info("Student with id of "+studentId+" does not exist");
        }
        else {
            studentRepository.deleteById(studentId);
            LOGGER.info("Student with id of "+studentId+" is deleted");
        }
    }

    public void updateStudent(Long studentId, String name, String email, String dob) {
        boolean exists=studentRepository.existsById(studentId);
        if(!exists){
            LOGGER.info("Student with id of "+studentId+" does not exist");
        }
        else {

            Student student = new Student(studentId,name,email,LocalDate.parse(dob));

            studentRepository.save(student);
            LOGGER.info("Student with id of "+studentId+" is update");
        }
    }
}
