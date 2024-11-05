package students.Controllers;

import students.Entity.Students;
import students.Exceptions.NotFoundException;
import students.Services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping("")
    public ResponseEntity<Iterable<Students>> getAllStudents() {
        List<Students> students = studentsService.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{std_Id}")
    public ResponseEntity<Students> getStudentById(@PathVariable("std_Id") Integer stdId) {
        Students student = studentsService.findById(stdId);
        if (student == null) {
            throw new NotFoundException("Student not found with ID: " + stdId);
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping("")
    public ResponseEntity<Students> createStudent(@RequestBody Students student) {
        Students createdStudent = studentsService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @PutMapping("/{std_Id}")
    public ResponseEntity<Students> updateStudent(@PathVariable("std_Id") Integer stdId, @RequestBody Students studentDetails) {
        Students updatedStudent = studentsService.updateStudent(stdId, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{std_Id}")
    public ResponseEntity<Students> deleteStudent(@PathVariable("std_Id") Integer stdId) {
        Students student = studentsService.findById(stdId);
        studentsService.deleteStudent(stdId);
        return ResponseEntity.ok(student);
    }
}
