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
import com.vrs.vo.User;
import com.vrs.vo.Vehicle;

public class VehicleDaoImpl implements VrsDao<Vehicle> {

	@Override
	public void add(Vehicle v) {
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			String sql = "INSERT INTO tbl_vehicles (user_id, source, category, plate_number, manufacture, type, color, registration_date, pending_fines) " +
						 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, v.getUser().getId());
			ps.setString(2, v.getSource());
			ps.setString(3, v.getCategory());
			ps.setString(4, v.getPlateNumber());
			ps.setString(5, v.getManufacture());
			ps.setString(6, v.getType());
			ps.setString(7, v.getColor());
			ps.setString(8, v.getRegistrationDate());
			ps.setString(9, v.getPendingFines());
			int update = ps.executeUpdate();
			System.out.println("Add vehicle : " + update);
		}
		catch (SQLException e) 
		{
			System.err.println("Error in adding vehicle : " + e);
		}
		ConnectionUtil.closeConnection(con);
	}

	@Override
	public void update(Vehicle v) {
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			String sql = "UPDATE tbl_vehicles SET source = ?, category = ?, " +
						 "plate_number = ?,  manufacture = ? " +
						 "type = ?,  color = ?,  registration_date = ?, pending_fines = ?" +
						 "WHERE id = ? AND user_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, v.getSource());
			ps.setString(2, v.getCategory());
			ps.setString(3, v.getPlateNumber());
			ps.setString(4, v.getManufacture());
			ps.setString(5, v.getType());
			ps.setString(6, v.getColor());
			ps.setString(7, v.getRegistrationDate());
			ps.setString(8, v.getPendingFines());
			ps.setInt(9, v.getId());
			ps.setInt(10, v.getUser().getId());
			int update = ps.executeUpdate();
			System.out.println("Update Insurance : " + update);
		}
		catch (SQLException e) 
		{
			System.err.println("Error in updating vehicle : " + e);
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
			String sql = "DELETE FROM tbl_vehicles WHERE id = " + id;
			int update = stmt.executeUpdate(sql);
			System.out.println("vehicle deleted : " + update);
		}
		catch (SQLException e) 
		{
			System.err.println("Error in deleting vehicle : " + e);
		}
		ConnectionUtil.closeConnection(con);
	}

	@Override
	public List<Vehicle> load() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>(0);
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT v.*, u.name AS name " +
					"FROM tbl_vehicles v, tbl_users u " +
					"WHERE u.id = v.user_id " +
					"ORDER BY v.id DESC LIMIT 20";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Vehicle v = new Vehicle();
				v.setId(rs.getInt("id"));
				v.setSource(rs.getString("source"));
				v.setCategory(rs.getString("category"));
				v.setPlateNumber(rs.getString("plate_number"));
				v.setManufacture(rs.getString("manufacture"));
				v.setType(rs.getString("type"));
				v.setColor(rs.getString("color"));
				v.setRegistrationDate(rs.getString("registration_date"));
				v.setPendingFines(rs.getString("pending_fines"));
				v.setUser(new User(rs.getInt("user_id"), rs.getString("name")));
				vehicles.add(v);
			}
		}
		catch (SQLException e) 
		{
			System.err.println("Error in loading all vehicle records : " + e);
		}
		ConnectionUtil.closeConnection(con);
		return vehicles;
	}

	@Override
	public Vehicle get(Serializable id) {
		Vehicle v = null;
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT v.*, u.name FROM tbl_vehicles v, tbl_users u " +
					"WHERE u.id = v.user_id AND v.id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				v = new Vehicle();
				v.setId(rs.getInt("id"));
				v.setSource(rs.getString("source"));
				v.setCategory(rs.getString("category"));
				v.setPlateNumber(rs.getString("plate_number"));
				v.setManufacture(rs.getString("manufacture"));
				v.setType(rs.getString("type"));
				v.setColor(rs.getString("color"));
				v.setRegistrationDate(rs.getString("registration_date"));
				v.setPendingFines(rs.getString("pending_fines"));
				v.setUser(new User(rs.getInt("user_id"), rs.getString("name")));
			}
		}
		catch (SQLException e) 
		{
			System.err.println("Error in getting vehicle record : " + e);
		}
		ConnectionUtil.closeConnection(con);
		return v;
	}
	
	public Vehicle getVehicleByUser(Serializable id) {
		Vehicle v = null;
		Connection con = null;
		
		try 
		{
			con = ConnectionUtil.getConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT v.*, u.name FROM tbl_vehicles v, tbl_users u " +
					"WHERE u.id = v.user_id AND u.id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				v = new Vehicle();
				v.setId(rs.getInt("id"));
				v.setSource(rs.getString("source"));
				v.setCategory(rs.getString("category"));
				v.setPlateNumber(rs.getString("plate_number"));
				v.setManufacture(rs.getString("manufacture"));
				v.setType(rs.getString("type"));
				v.setColor(rs.getString("color"));
				v.setRegistrationDate(rs.getString("registration_date"));
				v.setPendingFines(rs.getString("pending_fines"));
				v.setUser(new User(rs.getInt("user_id"), rs.getString("name")));
			}
		}
		catch (SQLException e) 
		{
			System.err.println("Error in getting vehicle record : " + e);
		}
		ConnectionUtil.closeConnection(con);
		return v;
	}

}
