package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;

public interface RowMapper<T> {
	T mappRow(ResultSet resultSet);
}

