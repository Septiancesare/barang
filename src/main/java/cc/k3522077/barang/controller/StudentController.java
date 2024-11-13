package cc.k3522077.barang.controller;

import cc.k3522077.barang.dto.OutputDto;
import cc.k3522077.barang.dto.StudentDto;
import cc.k3522077.barang.dto.StudentRegisterDto;
import cc.k3522077.barang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    public StudentService studentService;

    @PostMapping("/registration")
    public ResponseEntity<OutputDto<StudentDto>> registration(@RequestBody StudentRegisterDto studentRegisterDto) {
        StudentDto studentDto = studentService.registerNewStudent(studentRegisterDto);
        OutputDto<StudentDto> output = new OutputDto<>();
        output.setData(studentDto);
        output.setMessage("Data Berhasil disimpan");
        return ResponseEntity.ok(output);
    }

    @GetMapping("/show-all")
    public ResponseEntity<OutputDto<List<StudentDto>>> showAllStudents() {
        List<StudentDto> studentDtoList = studentService.getAllStudents();
        OutputDto<List<StudentDto>> output = new OutputDto<>();
        output.setData(studentDtoList);
        output.setMessage("Data Berhasil disimpan");
        return ResponseEntity.ok(output);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OutputDto<StudentDto>> findStudent(@PathVariable("id") Long id) {
        StudentDto studentDto = studentService.getStudent(id);
        OutputDto<StudentDto> output = new OutputDto<>();
        output.setData(studentDto);
        if (studentDto == null) {
            output.setMessage("Student tidak ditemukan");
        } else {
            output.setMessage("Student berhasil ditemukan");
        }
        return ResponseEntity.ok(output);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<OutputDto<StudentDto>> destroyStudent(@PathVariable("id") Long id) {
        StudentDto studentDto = new StudentDto();
        OutputDto<StudentDto> output = new OutputDto<>();
        try {
            studentService.deleteStudent(id);
            studentDto = studentService.getStudent(id);
            output.setData(studentDto);
            output.setMessage("Data Berhasil dihapus");
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            output.setData(studentDto);
            output.setMessage(e.getMessage());
            return ResponseEntity.ok(output);
        }

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<OutputDto<StudentDto>> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto studentDto) {
        studentService.updateStudent(id, studentDto);
        OutputDto<StudentDto> output = new OutputDto<>();
        output.setData(studentDto);

        output.setMessage("Data Berhasil diupdate");
        return ResponseEntity.ok(output);
    }
    @GetMapping("/find-name/{name}")
    public ResponseEntity<OutputDto<List<StudentDto>>> showStudentByName(@PathVariable("name") String name) {
        List<StudentDto> studentDtoList = studentService.getStudentbyName(name);
        OutputDto<List<StudentDto>> output = new OutputDto<>();
        output.setData(studentDtoList);
        output.setMessage("Data Berhasil diambil!!");
        return ResponseEntity.ok(output);
    }
}
