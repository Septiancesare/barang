package cc.k3522077.barang.repository;

import cc.k3522077.barang.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    @Query(value = "select sa from StudentEntity sa where sa.nama like %:name%")
    List<StudentEntity> findStudentName(String name);



}
