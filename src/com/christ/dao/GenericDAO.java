/**
 * 
 */
package com.christ.dao;

import java.util.List;

/**
 * @author spallai
 *
 */
public interface GenericDAO<T> {

	/*
	 * Generic CRUP Operations
	 */
	public int create(T object);

	public Object read(int id);

	public List<?> readAll();

	public Object update(T object);

	public Object delete(int id);

}
