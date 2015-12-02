package com.remotecoaching.app.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.remotecoaching.app.models.Role;

public class RoleDataAccessObject implements DataAccessObjectGenericInterface<Role, Integer> {
	
	@Override
	public void create(Role newInstance) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "INSERT INTO roles"
				+ "(role_name) VALUES"
				+ "(?)";
		try {
			connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, newInstance.getName());
			
			statement .executeUpdate();
		}catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
			System.out.println("Role name already exists");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
			System.out.println("Cannot create a null Role");
			e.printStackTrace();
		}finally {
			DataBaseUtillity.close(connection);
			DataBaseUtillity.close(statement);
		}
		
		
	}

	@Override
	public Role get(Integer id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Role role =null;
		String query = "SELECT * from roles"
				+ " WHERE id=?";
		try {
			 connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			resultSet = statement .executeQuery();
			
			if (resultSet.next()){
				role = new Role();
				role.setId(id);
				role.setName(resultSet.getString("role_name"));
			}else {
				//TODO handle no role for id in db
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseUtillity.close(connection);
			DataBaseUtillity.close(statement);
		}
		return role;
	}

	@Override
	public List<Role> getAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Role role =null;
		List<Role> allRolesList = new ArrayList<>();
		String query = "SELECT * from roles";
		try {
			connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement .executeQuery();
			
			while (resultSet.next()){
				role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("role_name"));
				allRolesList.add(role);
			}
			if (allRolesList.isEmpty()){
				//TODO handle case if no roles in db
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseUtillity.close(connection);
			DataBaseUtillity.close(statement);
		}
		return allRolesList;
	}

	@Override
	public void update(Role updatedInstance) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "UPDATE roles SET "
				+ "role_name=? "
				+ "where id=?";
		try {
			connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, updatedInstance.getName());
			statement.setInt(2, updatedInstance.getId());
			statement .executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseUtillity.close(connection);
			DataBaseUtillity.close(statement);
		}
		
		
		
	}

	@Override
	public void delete(Role instanceToDelete) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "DELETE FROM roles "
				+ "WHERE id=? OR "
				+ "role_name=?";
		try {
			connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, instanceToDelete.getId());
			statement.setString(2, instanceToDelete.getName());
			if(statement .executeUpdate()==0){
				throw new Exception("No role to delete for " + instanceToDelete.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			DataBaseUtillity.close(connection);
			DataBaseUtillity.close(statement);
		}
		
	}

	@Override
	public void delete(Integer instanceToDeleteID) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "DELETE FROM roles "
				+ "WHERE id=?";
		try {
			connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, instanceToDeleteID);
			if(statement .executeUpdate()==0){
				throw new Exception("No role to delete for ID "+ instanceToDeleteID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			DataBaseUtillity.close(connection);
			DataBaseUtillity.close(statement);
		}
		
		
		
	}

}
