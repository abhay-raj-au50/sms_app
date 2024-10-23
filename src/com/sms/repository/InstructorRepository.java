package com.sms.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sms.exception.ResourceNotFoundException;
import com.sms.model.Instructor;
import com.sms.utility.DbConnection;

public class InstructorRepository {
    public void insert(Instructor instructor) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO instructor(name, contact, salary, job_title) VALUES (?, ?, ?, ?)";

        try {
            con = DbConnection.dbConnect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getContact());
            pstmt.setDouble(3, instructor.getSalary());
            pstmt.setString(4, instructor.getJobTitle());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DbConnection.dbClose();
        }
    }

    public List<Instructor> getAllInstructors() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        List<Instructor> list = new ArrayList<>();
        String sql = "SELECT * FROM instructor";

        try {
            con = DbConnection.dbConnect();
            pstmt = con.prepareStatement(sql);
            rst = pstmt.executeQuery();

            while (rst.next()) {
                Instructor instructor = new Instructor();
                instructor.setId(rst.getInt("id"));
                instructor.setName(rst.getString("name"));
                instructor.setSalary(rst.getDouble("salary"));
                instructor.setContact(rst.getString("contact"));
                instructor.setJobTitle(rst.getString("job_title"));
                list.add(instructor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rst != null) {
                try {
                    rst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DbConnection.dbClose();
        }

        return list;
    }
    public Instructor validateIdAndFetchRecord(int id) throws ResourceNotFoundException {
		Connection con = DbConnection.dbConnect();
		String sql="select * from instructor where id=?";
		Instructor instructor = new Instructor();
		try {
			PreparedStatement pstmt =  con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rst =  pstmt.executeQuery();
			if(rst.next() == true) {
				instructor.setId(rst.getInt("id"));
				instructor.setName(rst.getString("name"));
				instructor.setSalary(rst.getDouble("salary"));
				instructor.setContact(rst.getString("contact"));
				instructor.setJobTitle(rst.getString("job_title"));
			}
			else  
				throw new ResourceNotFoundException("Id is invalid");
		}
		catch(SQLException e) {}
			
		DbConnection.dbClose();
		return instructor;
	}
	public void update(Instructor instructor) {
		Connection con = DbConnection.dbConnect();
		String sql="update instructor SET name=?,contact=?,salary=? where id=?"; 
		try {
			PreparedStatement pstmt =  con.prepareStatement(sql);
			//assign values of ?
			pstmt.setString(1, instructor.getName());
			pstmt.setString(2, instructor.getContact());
			pstmt.setDouble(3, instructor.getSalary());
			pstmt.setInt(4, instructor.getId());
			//run the pstmt
			pstmt.executeUpdate();
		} catch (SQLException e) {
			 e.printStackTrace();
		}
		
		DbConnection.dbClose();
	}

	public boolean getInstructorById(int id) {
		// TODO Auto-generated method stub
		  Connection con = DbConnection.dbConnect();
	        String sql = "select 1 FROM instructor WHERE id = ?";
	        boolean Inst2 = false;

	        try {
	            PreparedStatement pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            Inst2 = rs.next();
	            rs.close();
	            pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DbConnection.dbClose();
	        }

	        return Inst2 ;
	    
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Connection con = DbConnection.dbConnect();
		String sql="delete from instructor where id=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbConnection.dbClose();
	}
	}
 

