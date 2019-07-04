package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.paging.Pageble;

public interface GenericJDBC<T> {
	 List<T> select(String sql, Object...parameters);
	 void update(String sql, Object...parameters);
	 public Long insert(String sql, Object...parameters);
	 
	 
	
	 void update(Object object); 

	 void delete(Long id);
	 void deleteByProperty(String where);
	 
	 Long insert(Object object);
	 
	 T findById(long id);
	 List<T> findAll(Map<String, Object> properties,Pageble pageble,Object...where);
}
