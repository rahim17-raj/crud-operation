package org.jsp.crud_operation.repository;

import org.jsp.crud_operation.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
