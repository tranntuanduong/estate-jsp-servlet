package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;

public interface GenericJDBC<T> {
	 List<T> select(String sql, Object...parameters);
	 void update(String sql, Object...parameters);
	 public Long insert(String sql, Object...parameters);
	 Long insert(Object object);
	 void update(Object object);
	 //lam qua dai dong
//	 void delete(Object object);
//	 List<T> findById(Object object);
//	 List<T> select(Object object,String orderByName,int pageid);
	 
	 void delete(long id);
	 T findById(long id);
	 List<T> findAll(Map<String, Object> properties,PageRequest pageRequest,Object...where);
}
