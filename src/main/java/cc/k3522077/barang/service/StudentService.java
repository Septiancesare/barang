package cc.k3522077.barang.service;

import cc.k3522077.barang.dto.StudentDto;
import cc.k3522077.barang.dto.StudentRegisterDto;
import cc.k3522077.barang.entity.StudentEntity;
import cc.k3522077.barang.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDto registerNewStudent(StudentRegisterDto studentRegisterDto) {
        StudentEntity newStudentEntity = new StudentEntity();
        newStudentEntity.setAlamat(studentRegisterDto.getAlamat());
        newStudentEntity.setNama(studentRegisterDto.getNama());
        newStudentEntity.setNim(studentRegisterDto.getNim());
        newStudentEntity.setUsername(studentRegisterDto.getUsername());
        newStudentEntity.setEmail(studentRegisterDto.getEmail());
        newStudentEntity.setPassword(studentRegisterDto.getPassword());
        StudentEntity registeredStudent = studentRepository.save(newStudentEntity);

        StudentDto studentDto = new StudentDto();
        studentDto.setEmail(registeredStudent.getEmail());
        studentDto.setUsername(registeredStudent.getUsername());
        studentDto.setAlamat(registeredStudent.getAlamat());
        studentDto.setNama(registeredStudent.getNama());
        studentDto.setNim(registeredStudent.getNim());
        studentDto.setUsername(registeredStudent.getUsername());

        return studentDto;
    }

    public List<StudentDto> getAllStudents() {
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (int i = 0; i < studentEntityList.size(); i++) {
            StudentDto studentDto = new StudentDto();
            StudentEntity studentEntity = studentEntityList.get(i);
            studentDto.setUsername(studentEntity.getUsername());
            studentDto.setAlamat(studentEntity.getAlamat());
            studentDto.setNama(studentEntity.getNama());
            studentDto.setNim(studentEntity.getNim());
            studentDto.setUsername(studentEntity.getUsername());
            studentDtoList.add(studentDto);

        }
        return studentDtoList;
    }

    public StudentDto getStudent (Long id) {
        StudentDto studentDto = new StudentDto();
        Optional<StudentEntity> studentCheck =  studentRepository.findById(id);
        if (studentCheck.isPresent()) {
            StudentEntity studentEntity = studentCheck.get();
            studentDto.setUsername(studentEntity.getUsername());
            studentDto.setAlamat(studentEntity.getAlamat());
            studentDto.setNama(studentEntity.getNama());
            studentDto.setNim(studentEntity.getNim());
            studentDto.setEmail(studentEntity.getEmail());
            return studentDto;
        }else {
            return null;
        }

    }

    public void deleteStudent (Long id) {
        studentRepository.deleteById(id);
    }

    public StudentDto updateStudent (Long id, StudentDto studentDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(id);
        studentEntity.setAlamat(studentDto.getAlamat());
        studentEntity.setNama(studentDto.getNama());
        studentEntity.setNim(studentDto.getNim());
        studentEntity.setUsername(studentDto.getUsername());
        studentEntity.setEmail(studentDto.getEmail());
        StudentEntity result = studentRepository.save(studentEntity);
        studentDto.setUsername(result.getUsername());
        studentDto.setAlamat(result.getAlamat());
        studentDto.setNama(result.getNama());
        studentDto.setNim(result.getNim());
        studentDto.setEmail(result.getEmail());
        return studentDto;

    }

    public List<StudentDto> getStudentbyName (String name) {
        List<StudentDto> studentDtoList = new ArrayList<>();
        List<StudentEntity> studentEntityList = studentRepository.findStudentName(name);
        for (int i = 0; i < studentEntityList.size(); i++) {
            StudentDto studentDto = new StudentDto();
            StudentEntity studentEntity = studentEntityList.get(i);
            studentDto.setUsername(studentEntity.getUsername());
            studentDto.setAlamat(studentEntity.getAlamat());
            studentDto.setNama(studentEntity.getNama());
            studentDto.setNim(studentEntity.getNim());
            studentDto.setEmail(studentEntity.getEmail());
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }

}
