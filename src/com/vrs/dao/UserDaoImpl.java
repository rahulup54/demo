package com.vrs.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vrs.util.ConnectionUtil;
import com.vrs.util.RequestToVoUtil;
import com.vrs.vo.Insurance;
import com.vrs.vo.User;
import com.vrs.vo.Vehicle;

public class UserDaoImpl implements VrsDao<User> {

	public UserDaoImpl() {}
	
	@Override
	public void add(User user) 
	{
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			String sql = "INSERT INTO tbl_users (name, password, gender, nationality, licence) " +
						 "VALUES (?, PASSWORD(?), ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getGender());
			ps.setString(4, user.getNationality());
			ps.setString(5, user.getLicence());
			int update = ps.executeUpdate();
			System.out.println("Add User : " + update);
		}
		catch (SQLException e) 
		{
			System.err.println("Error in adding user : " + e);
		}
		ConnectionUtil.closeConnection(con);
	}

	@Override
	public void update(User user) 
	{
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			String sql = "UPDATE tbl_users SET name = ?, password = PASSWORD (?), " +
						 "gender = ?, nationality = ?, licence = ? " +
						 "WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getGender());
			ps.setString(4, user.getNationality());
			ps.setString(5, user.getLicence());
			ps.setInt(6, user.getId());
			int update = ps.executeUpdate();
			System.out.println("Update User : " + update);
		}
		catch (SQLException e) 
		{
			System.err.println("Error in updating user : " + e);
		}
		ConnectionUtil.closeConnection(con);
	}

	@Override
	public void delete(Serializable id) {
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM tbl_users WHERE id = " + id;
			int update = stmt.executeUpdate(sql);
			System.out.println("User deleted : " + update);
		}
		catch (SQLException e) 
		{
			System.err.println("Error in deleting user : " + e);
		}
		ConnectionUtil.closeConnection(con);
	}

	@Override
	public List<User> load() {
		
		List<User> users = new ArrayList<User>(0);
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM tbl_users ORDER BY id DESC LIMIT 20";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setNationality(rs.getString("nationality"));
				user.setLicence(rs.getString("licence"));
				users.add(user);
			}
		}
		catch (SQLException e) 
		{
			System.err.println("Error in loading all users records : " + e);
		}
		ConnectionUtil.closeConnection(con);
		return users;
	}

	@Override
	public User get(Serializable id) {
		User user = null;
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM tbl_users WHERE id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setNationality(rs.getString("nationality"));
				user.setLicence(rs.getString("licence"));
			}
		}
		catch (SQLException e) 
		{
			System.err.println("Error in getting user record : " + e);
		}
		ConnectionUtil.closeConnection(con);
		return user;
	}
	
	public User search(User u) 
	{
		
		User user = null;
		Connection con = null;
		List<User> users = new ArrayList<User>(0);
		
		String query = "";
		try 
		{
			String name = u.getName();
			String licence = u.getLicence();
			String numberPlate = u.getVehicle().getPlateNumber();
			
			if(RequestToVoUtil.isNotNull(name))
			{
				query += "AND u.name LIKE '%" + name+"%' ";
			}
			if(RequestToVoUtil.isNotNull(licence))
			{
				query += "AND u.licence LIKE '%" + licence+"%' ";
			}
			if(RequestToVoUtil.isNotNull(numberPlate))
			{
				query += "AND v.plate_number LIKE '%" + numberPlate+"%' ";
			}
			
			con = ConnectionUtil.getConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT u.id AS u_id, u.name AS u_name, u.gender AS u_gender, u.nationality AS u_nationality, u.licence AS u_licence, " +
					"i.id AS i_id, i.provider AS i_provider, i.insurance_number AS i_insurance_number, i.valid_date AS i_valid_date, "+
					"v.id AS v_id, v.source AS v_source, v.category AS v_category, v.plate_number AS v_plate_number, v.manufacture AS v_manufacture, " +
					"v.type AS v_type, v.color AS v_color, v.registration_date AS v_registration_date, v.pending_fines AS v_pending_fines " +
					"FROM tbl_insurance i, tbl_users u, tbl_vehicles v " +
					"WHERE u.id = i.user_id AND u.id = v.user_id AND v.user_id = i.user_id " + 
					query + " ORDER BY u.id DESC";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				user = new User();
				user.setId(rs.getInt("u_id"));
				user.setName(rs.getString("u_name"));
				user.setGender(rs.getString("u_gender"));
				user.setNationality(rs.getString("u_nationality"));
				user.setLicence(rs.getString("u_licence"));
				
				Insurance ins = new Insurance();
				ins.setId(rs.getInt("i_id"));
				ins.setProvider(rs.getString("i_provider"));
				ins.setInsuranceNumber(rs.getString("i_insurance_number"));
				ins.setInsuranceValidDate(rs.getString("i_valid_date"));
				user.setInsurance(ins);
				
				Vehicle v = new Vehicle();
				v.setId(rs.getInt("v_id"));
				v.setSource(rs.getString("v_source"));
				v.setCategory(rs.getString("v_category"));
				v.setPlateNumber(rs.getString("v_plate_number"));
				v.setManufacture(rs.getString("v_manufacture"));
				v.setType(rs.getString("v_type"));
				v.setColor(rs.getString("v_color"));
				v.setRegistrationDate(rs.getString("v_registration_date"));
				v.setPendingFines(rs.getString("v_pending_fines"));
				user.setVehicle(v);
				
				users.add(user);
			}
		}
		catch (SQLException e) 
		{
			System.err.println("Error in searching users records : " + e);
		}
		ConnectionUtil.closeConnection(con);
		user = users.size() > 1 ? users.get(0): user;
		return user;
	}

}
