package com.laptrinhjavaweb.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;


public class ResultSetMapper<T> {
	
	public List<T> mapRow(ResultSet resultSet, Class<T> zClass){
		 List<T> list = new ArrayList<>();	
		 try {
			 
			 if(zClass.isAnnotationPresent(Entity.class)) {
				 try {
					 ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					 Field[] fields = zClass.getDeclaredFields();				
					 while(resultSet.next()) {
						 T object = (T) zClass.newInstance();
						 for(int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
							 String columnName = resultSetMetaData.getColumnName(i + 1);
							 Object columnValue = resultSet.getObject(i + 1);
							 //current class
							 convertResultSetToEntity(fields, columnName, columnValue, object);
							 //parent class
							 Class<?> parentClass = zClass.getSuperclass();
							 while (parentClass != null) {
								 Field[] fileParents = parentClass.getDeclaredFields();
								 convertResultSetToEntity(fileParents, columnName, columnValue, object);
								 parentClass = parentClass.getSuperclass();
							 }			 
						 }
						 list.add(object);
					 }
					 
				 } catch (SQLException e) {
					 e.printStackTrace();
				 } 
			 }	 
		 } catch(Exception e) {
			 
		 }
		return list; 
	}

	private void convertResultSetToEntity(Field[] fields, String columnName, Object columnValue, T object) throws IllegalAccessException, InvocationTargetException {
		 for(Field field : fields) {
			 if(field.isAnnotationPresent(Column.class)) {
				 Column column = field.getAnnotation(Column.class);
				 if(column.name().equals(columnName) && columnValue != null) {
					  BeanUtils.setProperty(object, field.getName(), columnValue);
					  break;
				 }
			 }
		 }
	}
}
