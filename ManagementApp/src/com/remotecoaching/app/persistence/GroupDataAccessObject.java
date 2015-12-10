package com.remotecoaching.app.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.remotecoaching.app.exceptions.EntityNotFoundException;
import com.remotecoaching.app.models.Group;
import com.remotecoaching.app.models.Role;

public class GroupDataAccessObject implements DataAccessObjectGenericInterface<Group, Integer> {

	@Override
	public Group create(Group newInstance) {
		Connection connection = null;
		PreparedStatement groupInsertStatement = null;
		PreparedStatement updateGroupRolesTableStatement = null;
		Group group = new Group();
		String groupsInsertString = "INSERT INTO groups" + "(name) VALUES" + "(?)";
		String groupRolesInsertString = "INSERT INTO group_role " + "(group_id, role_id) " + "SELECT id, ? "
				+ "FROM groups " + "WHERE name=?";

		try {
			// if (newInstance.getRoles().isEmpty()){
			// throw new Exception("Group roles cannot be empty");
			// }
			connection = MyDataSource.getInstance().getConnection();
			connection.setAutoCommit(false);
			groupInsertStatement = connection.prepareStatement(groupsInsertString);
			groupInsertStatement.setString(1, newInstance.getName());
			groupInsertStatement.executeUpdate();
			for (Role role : newInstance.getRoles()) {
				updateGroupRolesTableStatement = connection.prepareStatement(groupRolesInsertString);
				updateGroupRolesTableStatement.setInt(1, role.getId());
				updateGroupRolesTableStatement.setString(2, newInstance.getName());
				try {
					updateGroupRolesTableStatement.executeUpdate();
				} catch (Exception e) {

					System.out.println(e.getMessage());
					connection.rollback();
				} finally {
					DataBaseUtillity.close(updateGroupRolesTableStatement);
				}
			}
			connection.commit();
			group = getByName(newInstance.getName());

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
			int errCode = e.getErrorCode();
			if (errCode == 1062) {
				System.out.println("Group with name " + newInstance.getName() + " already exists");
			} else if (errCode == 1048) {
				System.out.println("Group name cannot de null");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			DataBaseUtillity.close(connection);
		}
		return group;

	}

	@Override
	public Group get(Integer id) throws EntityNotFoundException {
		Connection connection = null;
		ResultSet groupsResultSet = null;
		ResultSet groupRolesResultSet = null;
		PreparedStatement groupQueryStatement = null;
		PreparedStatement groupRolesQueryStatement = null;
		Group group = null;
		Role role = null;
		String query = "SELECT * from groups" + " WHERE id=?";
		String groupRolesQuery = "SELECT roles.* FROM roles " + "INNER JOIN " + "group_role WHERE "
				+ "group_role.group_id =? AND " + "group_role.role_id=roles.id";

		try {
			connection = MyDataSource.getInstance().getConnection();
			connection.setAutoCommit(false);
			groupQueryStatement = connection.prepareStatement(query);
			groupQueryStatement.setInt(1, id);
			groupsResultSet = groupQueryStatement.executeQuery();
			if (groupsResultSet.next()) {
				group = new Group();
				group.setId(id);
				group.setName(groupsResultSet.getString("name"));
				groupRolesQueryStatement = connection.prepareStatement(groupRolesQuery);
				groupRolesQueryStatement.setInt(1, id);
				groupRolesResultSet = groupRolesQueryStatement.executeQuery();
				while (groupRolesResultSet.next()) {
					role = new Role();
					role.setId(groupRolesResultSet.getInt("id"));
					role.setName(groupRolesResultSet.getString("role_name"));
					group.getRoles().add(role);
				}
			} else {
				throw new EntityNotFoundException("No group found for ID " + id);
			}
			connection.commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			DataBaseUtillity.close(connection);
		}
		return group;
	}

	@Override
	public Group getByName(String name) throws EntityNotFoundException {
		Connection connection = null;
		ResultSet groupsResultSet = null;
		ResultSet groupRolesResultSet = null;
		PreparedStatement groupQueryStatement = null;
		PreparedStatement groupRolesQueryStatement = null;
		Group group = null;
		Role role = null;
		String query = "SELECT * from groups" + " WHERE name=?";
		String groupRolesQuery = "SELECT roles.* FROM roles " + "INNER JOIN " + "group_role WHERE "
				+ "group_role.group_id =? AND " + "group_role.role_id=roles.id";

		try {
			connection = MyDataSource.getInstance().getConnection();
			connection.setAutoCommit(false);
			groupQueryStatement = connection.prepareStatement(query);
			groupQueryStatement.setString(1, name);
			groupsResultSet = groupQueryStatement.executeQuery();
			if (groupsResultSet.next()) {
				group = new Group();
				group.setId(groupsResultSet.getInt("id"));
				group.setName(groupsResultSet.getString("name"));
				groupRolesQueryStatement = connection.prepareStatement(groupRolesQuery);
				groupRolesQueryStatement.setInt(1, groupsResultSet.getInt("id"));
				groupRolesResultSet = groupRolesQueryStatement.executeQuery();
				while (groupRolesResultSet.next()) {
					role = new Role();
					role.setId(groupRolesResultSet.getInt("id"));
					role.setName(groupRolesResultSet.getString("role_name"));
					group.getRoles().add(role);
				}
			} else {
				throw new EntityNotFoundException("No group found for name: " + name);
			}
			connection.commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			DataBaseUtillity.close(connection);
		}
		return group;
	}

	@Override
	public List<Group> getAll() {
		Connection connection = null;
		List<Group> allGroups = new ArrayList<>();
		ResultSet groupsResultSet = null;
		ResultSet groupRolesResultSet = null;
		PreparedStatement groupQueryStatement = null;
		PreparedStatement groupRolesQueryStatement = null;
		Group group = null;
		Role role = null;
		String query = "SELECT * from groups";
		String groupRolesQuery = "SELECT roles.* FROM roles " + "INNER JOIN " + "group_role WHERE "
				+ "group_role.group_id =? AND " + "group_role.role_id=roles.id";

		try {
			connection = MyDataSource.getInstance().getConnection();
			connection.setAutoCommit(false);
			groupQueryStatement = connection.prepareStatement(query);
			groupsResultSet = groupQueryStatement.executeQuery();

			while (groupsResultSet.next()) {
				group = new Group();
				group.setId(groupsResultSet.getInt("id"));
				group.setName(groupsResultSet.getString("name"));
				groupRolesQueryStatement = connection.prepareStatement(groupRolesQuery);
				groupRolesQueryStatement.setInt(1, group.getId());
				groupRolesResultSet = groupRolesQueryStatement.executeQuery();
				while (groupRolesResultSet.next()) {
					role = new Role();
					role.setId(groupRolesResultSet.getInt("id"));
					role.setName(groupRolesResultSet.getString("role_name"));
					group.getRoles().add(role);
				}
				allGroups.add(group);
			}
			connection.commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			DataBaseUtillity.close(connection);
		}
		return allGroups;
	}

	@Override
	public void update(Group updatedInstance) {
		Connection connection = null;
		PreparedStatement groupQueryStatement = null;
		String query = "UPDATE groups SET " + "name=? " + "WHERE id=?";
		try {
			connection = MyDataSource.getInstance().getConnection();
			groupQueryStatement = connection.prepareStatement(query);
			groupQueryStatement.setString(1, updatedInstance.getName());
			groupQueryStatement.setInt(2, updatedInstance.getId());
			groupQueryStatement.executeUpdate();
			updateGroupRoles(updatedInstance);

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
			int errCode = e.getErrorCode();
			if (errCode == 1062) {
				System.out.println("Group with name " + updatedInstance.getName() + " already exists");
			} else if (errCode == 1048) {
				System.out.println("Group name cannot de null");
			}
			// e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DataBaseUtillity.close(connection);
		}

	}

	@Override
	public void delete(Group instanceToDelete) {
		delete(instanceToDelete.getId());

	}

	@Override
	public void delete(Integer instanceToDeleteID) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "DELETE FROM groups " + "WHERE id=?";
		try {
			connection = MyDataSource.getInstance().getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, instanceToDeleteID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			DataBaseUtillity.close(connection);
		}

	}

	private void updateGroupRoles(Group updatedInstance) {
		Connection connection = MyDataSource.getInstance().getConnection();
		Set<Role> roles = updatedInstance.getRoles();
		PreparedStatement groupRolesQueryStatement = null;
		ResultSet groupRolesIDResultSet = null;
		String groupRolesQuery = "SELECT roles.id FROM roles " + "INNER JOIN " + "group_role WHERE "
				+ "group_role.group_id =? AND " + "group_role.role_id=roles.id";

		try {
			groupRolesQueryStatement = connection.prepareStatement(groupRolesQuery);
			groupRolesQueryStatement.setInt(1, updatedInstance.getId());
			groupRolesIDResultSet = groupRolesQueryStatement.executeQuery();

			while (groupRolesIDResultSet.next()) {
				int roleId = groupRolesIDResultSet.getInt("id");
				if (!roles.contains(new Role(roleId))) {
					deleteGroupRole(updatedInstance, roleId);
				} else {
					roles.remove(new Role(roleId));
				}
			}
			if (!roles.isEmpty()) {
				for (Role r : roles) {
					addRoleToGroup(updatedInstance, r);
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private void addRoleToGroup(Group groupToAddRole, Role role) {
		Connection databaseConection = MyDataSource.getInstance().getConnection();
		PreparedStatement insertGroupRoleStatement = null;
		String insertGroupRoleSql = "INSERT INTO group_role " + "(group_id, role_id) VALUES " + "(?,?)";
		try {
			insertGroupRoleStatement = databaseConection.prepareStatement(insertGroupRoleSql);
			insertGroupRoleStatement.setInt(1, groupToAddRole.getId());
			insertGroupRoleStatement.setInt(2, role.getId());
			insertGroupRoleStatement.executeUpdate();
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private void deleteGroupRole(Group groupToDeleteRole, int roleToDeleteId) {
		Connection databaseConection = MyDataSource.getInstance().getConnection();
		PreparedStatement deleteGroupRoleStatement = null;
		String deleteGroupRoleSql = "DELETE FROM group_role " + "WHERE group_id=? " + "AND 	 role_id=?";
		try {
			deleteGroupRoleStatement = databaseConection.prepareStatement(deleteGroupRoleSql);
			deleteGroupRoleStatement.setInt(1, groupToDeleteRole.getId());
			deleteGroupRoleStatement.setInt(2, roleToDeleteId);
			deleteGroupRoleStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
