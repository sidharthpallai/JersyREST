package com.christ.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author spallai
 *
 */

/*
 * Singleton Student data access object
 */
public class StudentDAO implements GenericDAO<Student> {

	/*
	 * A singleton instance initialized only once
	 */
	private static StudentDAO studentDAO = new StudentDAO();
	private static Map<Integer, Student> store = null;
	private static int id = 3;

	/*
	 * Static student data store shared across all objects
	 */
	static {
		store = new HashMap<Integer, Student>();
		store.put(new Integer(1), new Student(1, "Sidharth", "Pallai"));
		store.put(new Integer(2), new Student(2, "Biswajit", "Pallai"));
		store.put(new Integer(3), new Student(3, "Rajesh", "Pallai"));
	}

	/*
	 * private constructor for singleton instance
	 */
	private StudentDAO() {

	}

	public static StudentDAO getInstance() {
		studentDAO.id = id + 1;
		return studentDAO;
	}

	public Map<Integer, Student> getStore() {
		return store;
	}

	@Override
	public int create(Student stud) {
		stud.setId(id);
		System.out.println("putting student=" + stud);
		System.out.println("map=" + getStore());
		getStore().put(stud.getId(), stud);
		return stud.getId();
	}

	@Override
	public Student read(int sid) {
		return getStore().get(sid);
	}

	@Override
	public List<Student> readAll() {
		List<Student> studs = new ArrayList<Student>();
		for (Map.Entry<Integer, Student> stud : getStore().entrySet()) {
			studs.add(stud.getValue());
			System.out.println("====" + stud.getValue().getFirstName());
		}
		return studs;
	}

	@Override
	public Student update(Student stud) {
		Student updatedStud = new Student(stud.getId(), stud.getFirstName(), stud.getLastName());
		Student oldStud = getStore().put(stud.getId(), stud);
		// not null
		if (null != oldStud
				// must be updated , so not same
				&& !oldStud.equals(updatedStud)
				// do have a new Id to avoid creation of new student
				&& null != read(updatedStud.getId())) {
			return updatedStud;
		}
		return new Student(-1, "--", "-");
	}

	@Override
	public Student delete(int sid) {
		return getStore().remove(sid);
	}

}
