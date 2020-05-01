package com.example.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.dao.GenericDAO;
import com.example.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T>{
//	ket noi voi file db.properties
	ResourceBundle resourceBundle =ResourceBundle.getBundle("db");
	
	public Connection getConnection() {
		try {
//			Class.forName("com.mysql.jdbc.Driver");
////			them ?characterEncoding=UTF-8 de khong loi Tieng Viet
//			String url = "jdbc:mysql://127.0.0.1:3306/new_servlet?characterEncoding=UTF-8";
//			String user = "root";
//			String password = "rootpasswordgiven";
			
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}	
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> row, Object... parameters) {
		List<T> results = new ArrayList<>(); 
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
//			set ? trong SQL
			setParamete(statement, parameters);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
//				gan du lieu tu database vao results
				results.add(row.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			return null;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				return null;
			}	
		}
	}

	private void setParamete(PreparedStatement statement, Object... parameters) {
		try {
			for(int i = 0; i < parameters.length; i++) {
				int index = i+1;
				Object para = parameters[i];
				if(para instanceof Long) {
					statement.setLong(index, (Long)para);
				} else if(para instanceof String) {
					statement.setString(index, (String)para);
				} else if(para instanceof Integer) {
					statement.setInt(index, (Integer)para);
				} else if(para instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp)para);
				}
			}
		}catch (SQLException e) {
			
		}
		
	}

	@Override
	public void update(String sql, Object... parameter) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			setParamete(statement, parameter);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					
				}
			}
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				
			} catch (SQLException e2) {
				
			}	
		}
		
		
	}

	@Override
	public Long insert(String sql, Object... parameter) {
		Connection connection = null;
		PreparedStatement statement = null;
		Long id = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParamete(statement, parameter);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				id =  resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					
				}
			}
			return null;
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				
			}	
		}
	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			int count = 0;
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParamete(statement, parameters);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			return 0;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				return 0;
			}	
		}
	}
	
	
	
}
