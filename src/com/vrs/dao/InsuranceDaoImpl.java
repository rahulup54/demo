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
import com.vrs.vo.Insurance;
import com.vrs.vo.User;

public class InsuranceDaoImpl implements VrsDao<Insurance> {

	@Override
	public void add(Insurance ins) 
	{
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			String sql = "INSERT INTO tbl_insurance (user_id, provider, insurance_number, valid_date) " +
						 "VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ins.getUser().getId());
			ps.setString(2, ins.getProvider());
			ps.setString(3, ins.getInsuranceNumber());
			ps.setString(4, ins.getInsuranceValidDate());
			int update = ps.executeUpdate();
			System.out.println("Add Insurance : " + update);
		}
		catch (SQLException e) 
		{
			System.err.println("Error in adding insurance : " + e);
		}
		ConnectionUtil.closeConnection(con);
	}

	@Override
	public void update(Insurance ins) {
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			String sql = "UPDATE tbl_insurance SET provider = ?, insurance_number = ?, " +
						 "valid_date = ? " +
						 "WHERE id = ? AND user_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ins.getProvider());
			ps.setString(2, ins.getInsuranceNumber());
			ps.setString(3, ins.getInsuranceValidDate());
			ps.setInt(4, ins.getId());
			ps.setInt(5, ins.getUser().getId());
			int update = ps.executeUpdate();
			System.out.println("Update Insurance : " + update);
		}
		catch (SQLException e) 
		{
			System.err.println("Error in updating insurance : " + e);
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
			String sql = "DELETE FROM tbl_insurance WHERE id = " + id;
			int update = stmt.executeUpdate(sql);
			System.out.println("Insurance deleted : " + update);
		}
		catch (SQLException e) 
		{
			System.err.println("Error in deleting insurance : " + e);
		}
		ConnectionUtil.closeConnection(con);
	}

	@Override
	public List<Insurance> load() {
		List<Insurance> insurances = new ArrayList<Insurance>(0);
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT inc.*, u.name AS name " +
					"FROM tbl_insurance inc, tbl_users u " +
					"WHERE u.id = inc.user_id " +
					"ORDER BY inc.id DESC LIMIT 20";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Insurance ins = new Insurance();
				User user = new User(rs.getInt("user_id"), rs.getString("name"));
				ins.setUser(user);
				ins.setId(rs.getInt("id"));
				ins.setProvider(rs.getString("provider"));
				ins.setInsuranceNumber(rs.getString("insurance_number"));
				ins.setInsuranceValidDate(rs.getString("valid_date"));
				insurances.add(ins);
			}
		}
		catch (SQLException e) 
		{
			System.err.println("Error in loading all insurance records : " + e);
		}
		ConnectionUtil.closeConnection(con);
		return insurances;
	}

	@Override
	public Insurance get(Serializable id)
	{
		Insurance ins = null;
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT inc.*, u.name FROM tbl_insurance inc, tbl_users u " +
					"WHERE u.id = inc.user_id AND inc.id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				ins = new Insurance();
				ins.setId(rs.getInt("id"));
				ins.setUser(new User(rs.getInt("user_id"), rs.getString("name")));
				ins.setProvider(rs.getString("provider"));
				ins.setInsuranceNumber(rs.getString("insurance_number"));
				ins.setInsuranceValidDate(rs.getString("valid_date"));
			}
		}
		catch (SQLException e) 
		{
			System.err.println("Error in getting user record : " + e);
		}
		ConnectionUtil.closeConnection(con);
		return ins;
	}
	
	public Insurance getInsuranceByUser(Serializable id)
	{
		Insurance ins = null;
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT inc.*, u.name FROM tbl_insurance inc, tbl_users u " +
						 "WHERE u.id = inc.user_id AND u.id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				ins = new Insurance();
				ins.setId(rs.getInt("id"));
				ins.setUser(new User(rs.getInt("user_id"), rs.getString("name")));
				ins.setProvider(rs.getString("provider"));
				ins.setInsuranceNumber(rs.getString("insurance_number"));
				ins.setInsuranceValidDate(rs.getString("valid_date"));
			}
		}
		catch (SQLException e) 
		{
			System.err.println("Error in getting user record : " + e);
		}
		ConnectionUtil.closeConnection(con);
		return ins;
	}

}
