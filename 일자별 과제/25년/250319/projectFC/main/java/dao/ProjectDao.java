package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dto.Project;

public class ProjectDao {
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	public List<Project> selectList() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String query = null;
		
		try {
			conn = ds.getConnection();
			st  = conn.createStatement();
			query = "select * from projects order by pno desc";
			rs  = st.executeQuery(query);
			
			ArrayList<Project> projects = new ArrayList<Project>();
			
			while(rs.next()) {
				Project p = new Project();
				p.setPno(rs.getInt("pno"));
				p.setPname(rs.getString("pname"));
				p.setCreateddate(rs.getDate("createddate"));
				
				projects.add(p);
			}
			return projects;
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(rs!=null) rs.close();
				if(st!=null) st.close();
				if(conn!=null) conn.close(); //반환
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int insert(Project project) throws Exception {
		Connection conn = null;
		PreparedStatement pst =  null;
		String query = null;
		
		try {
			conn = ds.getConnection();
			query = "insert into projects values (seq_projects.nextval,?,?,?,?,?, sysdate,?)";
			pst = conn.prepareStatement(query);
			
			pst.setString(1, project.getPname());
			pst.setString(2, project.getContent());
			pst.setDate(3, (java.sql.Date)project.getStartdate());
			pst.setDate(4, (java.sql.Date)project.getEnddate());
			pst.setInt(5, project.getState());
			pst.setString(6, project.getTags());
			
			return pst.executeUpdate();
			
		} catch(Exception e) {
			throw e;
		} finally {
			try {
				if(pst != null) pst.close();
				if(conn != null ) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Project selectOne(int pno) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = null;
		
		try {
			query = "select * from projects where pno = ?";
			conn = ds.getConnection();
			pst = conn.prepareStatement(query);
			pst.setInt(1, pno);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				Project project = new Project();
				project.setPno(rs.getInt("pno"));
				project.setPname(rs.getString("pname"));
				project.setContent(rs.getString("content"));
				project.setStartdate(rs.getDate("startdate"));
				project.setEnddate(rs.getDate("enddate"));
				project.setState(rs.getInt("state"));
				project.setCreateddate(rs.getDate("createddate"));
				project.setTags(rs.getString("tags"));
				
				return project;
			} else {
				//return null;
				throw new Exception("해당 정보를 찾을 수 없습니다.");
			}
		}catch(Exception e) {
			throw e;
		} finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int  update(Project project) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		String query = null;
		
		try {
			conn = ds.getConnection();
			query = "update projects set pname=?, content=?, startdate=?, enddate=?, state=?, tags=? where pno=?";
			pst = conn.prepareStatement(query);
			pst.setString(1, project.getPname());
			pst.setString(2, project.getContent());
			pst.setDate(3, (java.sql.Date)project.getStartdate());
			pst.setDate(4, (java.sql.Date)project.getEnddate());
			pst.setInt(5, project.getState());
			pst.setString(6, project.getTags());
			pst.setInt(7, project.getPno());
			
			return pst.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			try {
				if(pst != null) pst.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public int delete(int pno) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		String query = null;
		
		try {
			query = "delete from projects where pno=?";
			conn = ds.getConnection();
			pst = conn.prepareStatement(query);
			pst.setInt(1, pno);
			
			return pst.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {
				if(pst != null) pst.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}








