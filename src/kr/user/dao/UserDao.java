package kr.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.user.domain.UserDto;

public class UserDao {
	//싱글턴 패턴
	private static UserDao instance = new UserDao();
	public static UserDao getInstance(){
		return instance;
	}
	private UserDao(){}

	//커넥션 풀로부터 커넥션을 할당
	private Connection getConnection()throws Exception{
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/xe");

		return ds.getConnection();
	}
	//자원정리
	private void executeClose(ResultSet rs,
			PreparedStatement pstmt,Connection conn){
		if(rs!=null)try{rs.close();}catch(SQLException e){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		if(conn!=null)try{conn.close();}catch(SQLException e){}
	}

	//====회원 가입====
	public void insertMember(UserDto user) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		String sql=null;
		
		try {
			conn=getConnection();
			conn.setAutoCommit(false);
			
			sql="INSERT INTO user_auth (id,auth) VALUES (?,1)";
			pstmt2=conn.prepareStatement(sql);
			pstmt2.setString(1, user.getId());
			pstmt2.executeUpdate();
			
			sql="INSERT INTO user_info (id,name,pwd,phone,email,reg_date) VALUES"
					+ " (?,?,?,?,?,SYSDATE)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPwd());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getEmail());
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, null);
			executeClose(null, pstmt2, conn);
		}
		
	}
	//====회원상세정보====
	public UserDto getMember(String id)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		ResultSet rs=null;
		UserDto user=null;
		
		try {
			conn=getConnection();
			sql="SELECT * FROM user_vw WHERE id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				user=new UserDto();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setReg_date(rs.getDate("reg_date"));
				user.setAuth(rs.getInt("auth"));
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return user;
		
	}

	public UserDto getMemberByName(String name)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		ResultSet rs=null;
		UserDto user=null;
		
		try {
			conn=getConnection();
			sql="SELECT * FROM user_vw WHERE name=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				user=new UserDto();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setReg_date(rs.getDate("reg_date"));
				user.setAuth(rs.getInt("auth"));
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return user;
		
	}
	
	
	
	//====회원정보수정====
	public void updateMember(UserDto user)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		try {
			conn=getConnection();
			sql="UPDATE user_info SET name=?,pwd=?,phone=?"
					+ ",email=? WHERE id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
		
	}
	//====회원탈퇴,회원권한 변경,회원정보삭제====
	public void deleteMember(String id)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		String sql=null;
		
		try {
			conn=getConnection();
			conn.setAutoCommit(false);
			
			sql="UPDATE user_auth SET auth=0 WHERE id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			sql="DELETE FROM user_info WHERE id=?";
			pstmt2=conn.prepareStatement(sql);
			pstmt2.setString(1, id);
			pstmt2.executeUpdate();

			conn.commit();
				
		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt2, null);
			executeClose(null, pstmt, conn);
		}
	}
	//====중복 이메일 체크====
	//이메일을 입력하면 해당 이메일을 가지고 있는 컬럼이 있는지 확인한다.
	public UserDto getMemberByEmail(String email)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		ResultSet rs=null;
		UserDto user=null;
		
		try {
			conn=getConnection();
			sql="SELECT * FROM user_vw WHERE email IN ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				user=new UserDto();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setReg_date(rs.getDate("reg_date"));
				user.setAuth(rs.getInt("auth"));
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return user;
	}
	
	
}
