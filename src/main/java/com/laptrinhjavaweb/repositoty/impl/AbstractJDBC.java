package com.laptrinhjavaweb.repositoty.impl;

import java.awt.print.Pageable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.repository.GenericJDBC;

public class AbstractJDBC<T> implements GenericJDBC<T> {
	
	private Class<T> zClass;
	@SuppressWarnings("unchecked")
	public AbstractJDBC() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/estate4month2019";
			String user = "root";
			String password = "1998";
			return DriverManager.getConnection(url, user, password);
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> select(String sql, Object... parameters) {	
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();	
		try(Connection conn = getConnection();
				PreparedStatement ptmt = conn.prepareStatement(sql);
				ResultSet resultSet = ptmt.executeQuery()
				){
			if(conn!=null) {			
				for (int i = 0 ; i < parameters.length ; i++) {
					int index = i + 1;
					ptmt.setObject(index, parameters[i]);
				}	
				return resultSetMapper.mapRow(resultSet, this.zClass);	
			}	
		} catch (SQLException e) { 
			System.out.println(e.getMessage());
		}	
		return null;
	}

	@Override
	public void update(String sql, Object... parameters) {	
		Connection conn = null;
		PreparedStatement ptmt = null;	
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ptmt = conn.prepareStatement(sql);
			Class.forName("com.mysql.jdbc.Driver");	
			if(conn!=null) {
				for( int i = 0 ; i < parameters.length ; i++) {
					int index = i + 1;
					ptmt.setObject(index, parameters[i]);
				}
			}
			ptmt.executeUpdate();{
				System.out.println("thanh cong");
			}
			conn.commit();
		} catch (SQLException | ClassNotFoundException e) {
			if(conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) {
					conn.close();
				}
				if(ptmt!=null) {
					ptmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}			
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ptmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			if(conn!=null) {
				for (int i = 0 ; i < parameters.length ; i++) {
					int index = i + 1;
					ptmt.setObject(index, parameters[i]);
				}			
				int rowInserted = ptmt.executeUpdate();
				resultSet = ptmt.getGeneratedKeys();
				conn.commit();
				
				if(rowInserted!=0) {
					if(resultSet.next()) {
						long id = resultSet.getLong(1);
						return id;
					}
				}
			}
		} catch (SQLException e) {
			if(conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {		
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if(conn!=null) {
					conn.close();
				}
				if(ptmt!=null) {
					ptmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Long insert(Object object) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = createSQLInsert();
			ptmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			if(conn!=null) {
				//set paremeters to ptmt
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
				for (int i = 0 ; i < fields.length ; i++) {
					int index = i + 1;
					fields[i].setAccessible(true);
					ptmt.setObject(index, fields[i].get(object));
				}			
				//check parent class
				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;
				while(parentClass != null) {
					Field[] fieldParents = parentClass.getDeclaredFields();
					for(int i = 0; i < fieldParents.length; i++) {
						fieldParents[i].setAccessible(true);
						ptmt.setObject(indexParent, fieldParents[i].get(object));
						indexParent++;
					}
					parentClass = parentClass.getSuperclass();
				}
				int rowInserted = ptmt.executeUpdate();
				resultSet = ptmt.getGeneratedKeys();
				conn.commit();
				
				if(rowInserted!=0) {
					if(resultSet.next()) {
						long id = resultSet.getLong(1);
						return id;
					}
				}
			}
		} catch (SQLException | IllegalAccessException e) {
			if(conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {		
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(ptmt != null) {
					ptmt.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private String createSQLInsert() {
		String table = "";
		if(zClass.isAnnotationPresent(Table.class)) {
			table = zClass.getAnnotation(Table.class).name();
		}
		StringBuilder fields = new StringBuilder();
		StringBuilder params = new StringBuilder();
		//get current fields
		for(Field field : zClass.getDeclaredFields()) {
			if(fields.length() > 1) {
				fields.append(",");
				params.append(",");
			}
			if(field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
			}
		}
		Class<?> parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			for(Field field : parentClass.getDeclaredFields()) {
				if(fields.length() > 1) {
					fields.append(",");
					params.append(",");
				}
				if(field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					fields.append(column.name());
					params.append("?");
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		String sql = "INSERT INTO "+table+"("+fields.toString()+") VALUES("+params.toString()+")";	
		return sql;
	}
	//update 
	public void update(Object object) {	
		Connection conn = null;
		PreparedStatement ptmt = null;	
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = createSQLUpdate();
			ptmt = conn.prepareStatement(sql);
			if(conn!=null) {
				//current class
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
				for(int i = 0; i < fields.length; i++) {
					int index = i + 1;
					Column column = fields[i].getAnnotation(Column.class);
					String columnName = column.name();
					if(!columnName.equals("id")) {
						//nho phan quyen cho field
						fields[i].setAccessible(true);
						ptmt.setObject(index, fields[i].get(object));		
					}
				}
				//parent class
				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;		
				Object id = null;
				while(parentClass != null) {
					Field[] fieldParents = parentClass.getDeclaredFields();
					for(int i = 0; i < fieldParents.length; i++) {
						Column column = fieldParents[i].getAnnotation(Column.class);
						String columnName = column.name();
						if(!columnName.equals("id")) {
							fieldParents[i].setAccessible(true);
							ptmt.setObject(indexParent, fieldParents[i].get(object));
							indexParent++;
						}else {
							fieldParents[i].setAccessible(true);
							id = fieldParents[i].get(object);
						}			
					}
					parentClass = parentClass.getSuperclass();
				}
				ptmt.setObject(indexParent, id);
			}
			ptmt.executeUpdate();
			conn.commit();
		} catch (SQLException | IllegalAccessException  e) {
			if(conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) {
					conn.close();
				}
				if(ptmt!=null) {
					ptmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}			
	}
	private String createSQLUpdate() {
		String sql = "";
		String table = "";
		if(zClass.isAnnotationPresent(Table.class)) {
			table = zClass.getAnnotation(Table.class).name();
		}
		StringBuilder sets = new StringBuilder();
		String where = "";
		//get current fields
		for(Field field : zClass.getDeclaredFields()) {
			if(sets.length() > 1) {
				sets.append(",");
			}
			if(field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				if(!column.name().equals("id")) {
					sets.append(column.name() + "=?");
				}
			}
		}
		Class<?> parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			for(Field field : parentClass.getDeclaredFields()) {
				if(field.isAnnotationPresent(Column.class)) {	
					Column column = field.getAnnotation(Column.class);
					if(!column.name().equals("id"))	{
						if(sets.length() > 1) {
							sets.append(",");
						}		
						sets.append(column.name() + "=?");
					}
				}
			} 
			parentClass = parentClass.getSuperclass();
		}
		where = " where id = ?";
		sql = "UPDATE "+table+" set "+sets.toString() + where;	
		return sql;
	}
//	//qua dai dong
//	public void delete(Object object) {	
//		Connection conn = null;
//		PreparedStatement ptmt = null;	
//		try {
//			conn = getConnection();
//			conn.setAutoCommit(false);
//			String sql = createSQLDelete();
//			ptmt = conn.prepareStatement(sql);
//			if(conn!=null) {
//				Class<?> zClass = object.getClass();
//				Class<?> parentClass = zClass.getSuperclass();	
//				while(parentClass != null) {
//					Field[] fieldParents = parentClass.getDeclaredFields();
//					for(int i = 0; i < fieldParents.length; i++) {
//						Column column = fieldParents[i].getAnnotation(Column.class);
//						String columnName = column.name();
//						if(columnName.equals("id")) {
//							fieldParents[i].setAccessible(true);
//							ptmt.setObject(1, fieldParents[i].get(object));
//							break;
//						}		
//					}
//					parentClass = parentClass.getSuperclass();
//				}
//			}
//			ptmt.executeUpdate();
//			conn.commit();
//		} catch (SQLException | IllegalAccessException  e) {
//			if(conn!=null) {
//				try {
//					conn.rollback();
//				} catch (SQLException e1) {				
//					e1.printStackTrace();
//				}
//			}
//			e.printStackTrace();
//		} finally {
//			try {
//				if(conn!=null) {
//					conn.close();
//				}
//				if(ptmt!=null) {
//					ptmt.close();
//				}
//			} catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}			
//	}
//	private String createSQLDelete() {
//		String table = "";
//		if(zClass.isAnnotationPresent(Table.class)) {
//			table = zClass.getAnnotation(Table.class).name();
//		}
//		String where = " where id = ?";
//		//delete from building where id = ?
//		String sql = "DELETE FROM "+table + where;
//		return sql;
//	}
//	//findById
//	public List<T> findById(Object object) {	
//		Connection conn = null;
//		PreparedStatement ptmt = null;
//		ResultSet resultSet = null;
//		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();	
//		String sql = createSQLFindById();
//		try{
//			conn = getConnection();
//			ptmt = conn.prepareStatement(sql);
//			if(conn!=null) {			
//				Class<?> zClass = object.getClass();
//				Class<?> parentClass = zClass.getSuperclass();	
//				while(parentClass != null) {
//					Field[] fieldParents = parentClass.getDeclaredFields();
//					for(int i = 0; i < fieldParents.length; i++) {
//						Column column = fieldParents[i].getAnnotation(Column.class);
//						String columnName = column.name();
//						if(columnName.equals("id")) {
//							fieldParents[i].setAccessible(true);
//							ptmt.setObject(1, fieldParents[i].get(object));
//							break;
//						}		
//					}
//					parentClass = parentClass.getSuperclass();
//				}
//				resultSet = ptmt.executeQuery();
//				return resultSetMapper.mapRow(resultSet, this.zClass);	
//			}	
//		} catch (SQLException | IllegalAccessException e) { 
//			e.printStackTrace();
//		}	
//		return null;
//	}
//	private String createSQLFindById() {
//		String table = "";
//		if(zClass.isAnnotationPresent(Table.class)) {
//			table = zClass.getAnnotation(Table.class).name();
//		}
//		String where = " where id = ?";
//		//delete from building where id = ?
//		String sql = "SELECT * FROM "+table + where;
//		return sql;
//	}
//	
//	//select
//	public List<T> select(Object object,String orderByName,int pageid) {	
//		Connection conn = null;
//		PreparedStatement ptmt = null;
//		ResultSet resultSet = null;
//		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();	
//		String sql = createSQLSelect(object,orderByName,pageid);
//		int indexSetObject = 0;
//		try{
//			conn = getConnection();
//			ptmt = conn.prepareStatement(sql);
//			if(conn!=null) {
//				//current class
//				Class<?> zClass = object.getClass();
//				Field[] fields = zClass.getDeclaredFields();			
//				for(int i = 0; i < fields.length; i++) {
//					if(fields[i].isAnnotationPresent(Column.class)) {
//						fields[i].setAccessible(true);
//						String column = fields[i].getAnnotation(Column.class).name();
//						Object fieldValue = fields[i].get(object);
//						if(fields[i].get(object) != null) {
//							ptmt.setObject(indexSetObject + 1,"%"+ fields[i].get(object)+"%");
//							indexSetObject++;
//						}		
//					}
//				}
//				//parent class
//				Class<?> parentClass = zClass.getSuperclass();
//				while(parentClass != null) {
//					Field[] fieldParents = parentClass.getDeclaredFields();
//					for(int i = 0; i < fieldParents.length; i++) {
//						if(fieldParents[i].isAnnotationPresent(Column.class)) {
//								fieldParents[i].setAccessible(true);
//								if(fieldParents[i].get(object) != null) {
//									ptmt.setObject(indexSetObject + 1,"%"+ fieldParents[i].get(object)+"%");
//									indexSetObject++;
//								}	
//						}		
//					}
//					parentClass = parentClass.getSuperclass();
//				}
//				resultSet = ptmt.executeQuery();
//				return resultSetMapper.mapRow(resultSet, this.zClass);	
//			}	
//		} catch (SQLException | IllegalAccessException e) { 
//			e.printStackTrace();
//		}	
//		return null;
//	}
//	private String createSQLSelect(Object object, String orderByName,int pageid) {
//		String sql ="";
//		String table = "";
//		String limit = "";
//		String orderBy = "";
//		StringBuilder likes = new StringBuilder();
//		if(zClass.isAnnotationPresent(Table.class)) {
//			table = zClass.getAnnotation(Table.class).name();
//		}
//		//curent class
//		Class<?> zClass = object.getClass();
//		Field[] fields = zClass.getDeclaredFields();
//		try {
//			for(int i = 0 ; i < fields.length; i++) {
//				if(fields[i].isAnnotationPresent(Column.class)) {
//					Column column = fields[i].getAnnotation(Column.class);
//					String columnName = column.name();			
//					fields[i].setAccessible(true);				
//					if(fields[i].get(object) != null) {
//						if(likes.length() > 1) {
//							likes.append(" and ");
//						}
//						if(likes.length() < 1) {
//							likes.append(" where ");
//						}						
//						likes.append(columnName+" like ? ");
//					}
//				}
//			}
//			//parent class
//			Class<?> parentClass = zClass.getSuperclass();
//			while(parentClass != null) {
//				Field[] fieldParents = parentClass.getDeclaredFields();
//				for(int i = 0 ; i < fieldParents.length; i++) {
//					if(fieldParents[i].isAnnotationPresent(Column.class)) {
//						Column column = fieldParents[i].getAnnotation(Column.class);
//						String columnName = column.name();
//						
//						fieldParents[i].setAccessible(true);				
//						if(fieldParents[i].get(object) != null) {
//							if(likes.length() > 1) {
//								likes.append(" and ");
//							}
//							if(likes.length() < 1) {
//								likes.append(" where ");
//							}
//							likes.append(columnName+" like ? ");
//						}
//					}
//				}
//				parentClass = parentClass.getSuperclass();
//			}
//			if(orderBy != null) {
//				orderBy = " order by "+orderByName;
//			}
//			limit = paging(pageid);
//			
//		}catch (IllegalArgumentException | IllegalAccessException e) {
//			e.printStackTrace();
//		}		
//		sql = "SELECT * FROM "+table+likes.toString()+orderBy+limit;
//		return sql;
//	}
//	private String paging(int pageid) {
//		int count = 3;	
//		if(pageid != 1)  {
//			pageid = pageid - 1;
//			pageid = pageid*count+1;
//		}
//		int start = pageid;
//		int end = pageid + count;
//		String limit = " limit "+(start - 1)+","+(end - 1);
//		return limit;
//	}

	@Override
	public void delete(long id) {
		Connection conn = null;
		PreparedStatement ptmt = null;	
		try {
			conn = getConnection();	
			conn.setAutoCommit(false);
			String table = "";
			if(zClass.isAnnotationPresent(Table.class)) {
				table = zClass.getAnnotation(Table.class).name();	
			}
			String sql = "DELETE FROM "+table+" WHERE id = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setObject(1, id);
			ptmt.execute();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {	
					conn.close();
				}
				if(ptmt != null) {
					ptmt.close();
				}
			} catch(SQLException e){
				
			}
		}
		
		
	}

	@Override
	public T findById(long id) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet resultSet = null;
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		conn = getConnection();
		String table = "";
		if(zClass.isAnnotationPresent(Table.class)) {
			table = zClass.getAnnotation(Table.class).name();
		}
		String sql = "SELECT * FROM "+table+" WHERE id = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setObject(1, id);
			resultSet = ptmt.executeQuery();
			if(conn != null) {
				return resultSetMapper.mapRow(resultSet, this.zClass).get(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {	
					conn.close();
				}
				if(ptmt != null) {
					ptmt.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		return null;
	}	
	public List<T> findAll(Map<String, Object> properties,PageRequest pageRequest, Object...where){
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet resultSet = null;
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		
		StringBuilder sql = new StringBuilder();
		sql = createSQlFindAll(properties);
		if (where != null && where.length > 1) {
			sql.append(where[0]);
		}
		if (pageRequest != null) {
			if(pageRequest.getSorter() != null) {
				Sorter sorter = pageRequest.getSorter();
				sql.append(" ORDER BY "+sorter.getSortName()+" "+sorter.getSortBy());
			}
			if(pageRequest.getOffset() != null && pageRequest.getLimit() != null) {
				sql.append(" LIMIT "+pageRequest.getOffset()+","+pageRequest.getLimit()+"");
			}
		}
		try {
			conn = getConnection();
			ptmt = conn.prepareStatement(sql.toString());
			resultSet = ptmt.executeQuery();
			if(conn != null) {
				return resultSetMapper.mapRow(resultSet, this.zClass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {	
					conn.close();
				}
				if (ptmt != null) {
					ptmt.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private StringBuilder createSQlFindAll(Map<String, Object> properties) {
		String table = "";
		if(zClass.isAnnotationPresent(Table.class)) {
			table = zClass.getAnnotation(Table.class).name();
		}
		StringBuilder sql = new StringBuilder("SELECT * FROM "+table+" WHERE 1 = 1");
		if(properties != null && properties.size() > 1) {
			String[] params = new String[properties.size()];
			Object[] values = new Object[properties.size()];
			int i = 0;
			for(Map.Entry<?, ?> item : properties.entrySet()) {
				params[i] = (String) item.getKey();
				values[i] = item.getValue();
				i++;
			}
			for(int j = 0; j < params.length; j++) {
				if(values[j] instanceof String) {
					sql.append(" AND LOWER("+params[j]+") LIKE LOWER('%"+values[j]+"%')");
				} else if(values[j] instanceof Integer) {
					sql.append(" AND "+params[j]+" = "+values[j]+" ");
				}
			}
		}
		return sql;
	}
}


