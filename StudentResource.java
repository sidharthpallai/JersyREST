package com.christ.service;

import java.util.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.christ.dao.Student;
import com.christ.dao.StudentDAO;
import com.christ.rest.util.StudentConstants;

/**
 * @author spallai
 *
 */

@Path("/studentservice")
public class StudentResource implements StudentService {

	// http://localhost:8080/StudentRESTService/rest/studentservice/Testing...REST
	@GET
	@Path("/{name}")
	public Response sayHello(@PathParam("name") String msg) {
		String output = "Hello, " + msg + "!";
		return Response.status(200).entity(output).build();
	}

	/*
	 * http://localhost:8080/StudentRESTService/rest/studentservice/add
	 * 
	 * <student> 
	 * <firstName>Sidharth</firstName>
	 * <lastName>Pallai</lastName> 
	 * </student>
	 * 
	 */
	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public Response addStudent(Student stud) {
		int id = StudentDAO.getInstance().create(stud);
		return Response.status(200).entity(StudentConstants.STUDENT_ADDED + ". Student ID = " + id).build();
	}

	// http://localhost:8080/StudentRESTService/rest/studentservice/student/3
	@GET
	@Path("/student/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	@Override
	public Student getStudent(@PathParam("id") int id) {
		return StudentDAO.getInstance().read(id);
	}

	// http://localhost:8080/StudentRESTService/rest/studentservice/students
	@GET
	@Path("/students")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	@Override
	public List<Student> getStudents() {
		return StudentDAO.getInstance().readAll();
	}

	// http://localhost:8080/StudentRESTService/rest/studentservice/1
	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public Response deleteStudent(@PathParam("id") int id) {

		if (id == StudentDAO.getInstance().delete(id).getId()) {
			return Response.status(200).entity(StudentConstants.STUDENT_RMVD + ". Student ID = " + id).build();
		}
		return Response.status(201).entity(StudentConstants.STUDENT_DONT_EXIST).build();
	}

	// http://localhost:8080/StudentRESTService/rest/studentservice/updateStudent/
	// <student>
	// <firstName>sidhhhhhhh</firstName>
	// <lastName>Pallaiiiiiiiiiiii</lastName>
	// </student>
	@PUT
	@Path("/updateStudent")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public Student updateStudent(Student stud) {
		return StudentDAO.getInstance().update(stud);
	}

}
