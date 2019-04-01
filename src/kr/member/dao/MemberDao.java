package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.member.domain.MemberDto;

public class MemberDao {
		//싱글턴 패턴
		private static MemberDao instance = new MemberDao();
		public static MemberDao getInstance(){
			return instance;
		}
		private MemberDao(){}

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
		public void insertMember(MemberDto member) throws Exception{
			Connection conn=null;
			PreparedStatement pstmt=null;
			PreparedStatement pstmt2=null;
			String sql=null;
			
			try {
				conn=getConnection();
				conn.setAutoCommit(false);
				
				sql="INSERT INTO user_info (id,name,pwd,phone,email,reg_date) VALUES"
						+ " (?,?,?,?,?,SYSDATE)";
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getName());
				pstmt.setString(3, member.getPwd());
				pstmt.setString(4, member.getPhone());
				pstmt.setString(5, member.getEmail());
				pstmt.executeUpdate();
				
				sql="INSERT INTO user_auth (id,auth) VALUES (?,1)";
				pstmt2=conn.prepareStatement(sql);
				pstmt2.setString(1, member.getId());
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
		//====회원상세정보====
		public MemberDto getMember(String id)throws Exception{
			return null;
			
		}
		//====회원정보수정====
		public void updateMember(MemberDto member)throws Exception{
		}
		//====회원탈퇴,회원권한 변경,회원정보삭제====
		public void deleteMember(String id)throws Exception{
		}
}
