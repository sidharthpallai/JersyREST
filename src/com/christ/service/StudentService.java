package com.christ.service;

import java.util.List;
import javax.ws.rs.core.Response;
import com.christ.dao.Student;

/**
 * @author spallai
 *
 */
public interface StudentService {

	public Response addStudent(Student stud);

	public Student getStudent(int id);

	public List<Student> getStudents();

	public Response deleteStudent(int id);

	public Student updateStudent(Student stud);
}
