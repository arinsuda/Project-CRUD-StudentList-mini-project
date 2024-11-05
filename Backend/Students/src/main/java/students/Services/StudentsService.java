package students.Services;

import students.Entity.Students;
import students.Exceptions.NotFoundException;
import students.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {
    @Autowired
    private StudentRepository studentRepository;

    public Students createStudent(Students student) {
        student.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        return studentRepository.save(student);
    }

    public List<Students> findAll() {
        return studentRepository.findAll();
    }

    public Students findById(Integer studentId) {
        return studentRepository.findById(studentId)
               .orElseThrow(() -> new NotFoundException("Student with ID " + studentId + " not found"));
    }

    public Students updateStudent(Integer studentId, Students updatedStudent) {
        Students existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student with ID " + studentId + " not found"));

        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setAge(updatedStudent.getAge());
        if (updatedStudent.getGender() != null && EnumSet.allOf(Students.Gender.class).contains(updatedStudent.getGender())) {
            existingStudent.setGender(updatedStudent.getGender());
        }

        updatedStudent.setId(existingStudent.getId());
        updatedStudent.setCreatedOn(existingStudent.getCreatedOn());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}
