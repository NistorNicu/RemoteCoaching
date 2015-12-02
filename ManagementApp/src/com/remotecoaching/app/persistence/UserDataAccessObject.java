package com.remotecoaching.app.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.remotecoaching.app.models.Group;
import com.remotecoaching.app.models.User;

public class UserDataAccessObject implements DataAccessObjectGenericInterface<User, Integer> {
	
	

	@Override
	public void create(User newInstance) {
		Connection connection= null;
		PreparedStatement statement = null;
		String query = "INSERT INTO users"
				+ "user_name, email, group_id VALUES "
				+ "?,?,?";	
		try {
			connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, newInstance.getUserName());
			statement.setString(2, newInstance.getEmail());
			statement.setInt(3, newInstance.getGroup().getId());
			statement .executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseUtillity.close(connection);
		}
		
		
	}

	@Override
	public User get(Integer id) {
		GroupDataAccessObject groupDataAccessObject = new GroupDataAccessObject();
		Connection connection= null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		User user =null;
		String query = "SELECT * from users"
				+ " WHERE id=?";
		try {
			connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);			
			resultSet = statement.executeQuery();			
			if (resultSet.next()){
				user = new User();
				user.setId(id);
				user.setUserName(resultSet.getString("user_name"));
				user.setEmail(resultSet.getString("email"));
				user.setGroup(new Group(resultSet.getInt("group_id"))); 
			}else {
				//TODO handle no role for id in db
			}
			user.setGroup(groupDataAccessObject.get(user.getGroup().getId()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseUtillity.close(connection);
			DataBaseUtillity.close(statement);
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		GroupDataAccessObject groupDataAccessObject = new GroupDataAccessObject();
		Connection connection= null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		User user =null;
		List<User> allUsers = new ArrayList<>();
		String query = "SELECT * from users";
		try {
			connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);			
			resultSet = statement.executeQuery();			
			while (resultSet.next()){
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("user_name"));
				user.setEmail(resultSet.getString("email"));
				user.setGroup(new Group(resultSet.getInt("group_id")));
				allUsers.add(user);
			}
			for(User u : allUsers){
				u.setGroup(groupDataAccessObject.get(u.getGroup().getId()));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseUtillity.close(connection);
			DataBaseUtillity.close(statement);
		}
		return allUsers;
	}

	@Override
	public void update(User updatedInstance) {
		Connection connection= null;
		PreparedStatement statement = null;
		String query = "UPDATE users SET "
				+ "user_name=?, email=?, group_id=? "
				+ "where id=?";
		try {
			connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, updatedInstance.getUserName());
			statement.setString(2, updatedInstance.getEmail());
			statement.setInt(3, updatedInstance.getGroup().getId());
			statement.setInt(4, updatedInstance.getId());
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
	public void delete(User instanceToDelete) {
		delete(instanceToDelete.getId());
		
	}

	@Override
	public void delete(Integer instanceToDeleteID) {
		Connection connection= null;
		PreparedStatement statement = null;
		String query = "DELETE FROM users"
				+ "WHERE id=?";
		try {
			connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, instanceToDeleteID);
			statement .executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseUtillity.close(connection);
			DataBaseUtillity.close(statement);
		}
		
	}

}
