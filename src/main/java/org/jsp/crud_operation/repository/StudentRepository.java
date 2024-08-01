package org.jsp.crud_operation.repository;

import java.util.List;

import org.jsp.crud_operation.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	List<Student> findByName(String name);
	List<Student> findByMobile(long mobile);
	List<Student> findByGender(String gender);
	List<Student> findBySub1(int sub1);
	List<Student> findBySub2(int sub2);
	List<Student> findBySub3(int sub3);
	List<Student> findBySub1GreaterThanAndSub2GreaterThanAndSub3GreaterThan(int marks, int marks2,
			int marks3);
}
