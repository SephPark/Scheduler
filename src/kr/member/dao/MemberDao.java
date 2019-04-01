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
		//�̱��� ����
		private static MemberDao instance = new MemberDao();
		public static MemberDao getInstance(){
			return instance;
		}
		private MemberDao(){}

		//Ŀ�ؼ� Ǯ�κ��� Ŀ�ؼ��� �Ҵ�
		private Connection getConnection()throws Exception{
			Context initCtx = new InitialContext();
			DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/xe");

			return ds.getConnection();
		}
		//�ڿ�����
		private void executeClose(ResultSet rs,
				PreparedStatement pstmt,Connection conn){
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
			if(conn!=null)try{conn.close();}catch(SQLException e){}
		}

		//====ȸ�� ����====
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
		//====ȸ��������====
		public MemberDto getMember(String id)throws Exception{
			return null;
			
		}
		//====ȸ����������====
		public void updateMember(MemberDto member)throws Exception{
		}
		//====ȸ��Ż��,ȸ������ ����,ȸ����������====
		public void deleteMember(String id)throws Exception{
		}
}
