package kr.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.diary.domain.DiaryDto;
import kr.schedule.domain.ScheduleDto;

public class DiaryDao {
	//싱글턴 패턴
	private static DiaryDao instance = new DiaryDao();
	public static DiaryDao getInstance(){
		return instance;
	}
	private DiaryDao(){}

	//커넥션 풀로부터 커넥션을 할당
	private Connection getConnection()throws Exception{
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/xe");

		return ds.getConnection();
	}
	//자원정리
	private void executeClose(ResultSet rs,	PreparedStatement pstmt,Connection conn){
		if(rs!=null)try{rs.close();}catch(SQLException e){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		if(conn!=null)try{conn.close();}catch(SQLException e){}
	}
	
	//===다이어리 등록===
	public void writeDiary(DiaryDto diary)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		try {
			conn=getConnection();
			
			sql="INSERT INTO diary"
					+ " (d_idx,d_title,id,d_content)"
					+ " VALUES "
					+ "(diary_seq.nextval,?,?,?)";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, diary.getD_title());
			pstmt.setString(2, diary.getId());
			pstmt.setString(3, diary.getD_content());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
	
	//===다이어리 갯수===
	public int getDiaryCount(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn=getConnection();
			sql="SELECT COUNT(*) FROM diary WHERE id=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			if (rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	//===다이어리 목록===
	public List<DiaryDto> getDiaryList(int start,int end,String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<DiaryDto> list=null;
		DiaryDto diary=null;
		
		try {
			conn=getConnection();
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
	                + "FROM (SELECT * FROM diary d WHERE id=?)a) "
	               + "WHERE rnum >= ? AND rnum <= ? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs=pstmt.executeQuery();
			
			list=new ArrayList<DiaryDto>();
			while (rs.next()) {
				diary=new DiaryDto();
				
				diary.setD_idx(rs.getInt("d_idx"));
				diary.setD_title(rs.getString("d_title"));
				diary.setId(rs.getString("id"));
				diary.setD_content(rs.getString("d_content"));
				diary.setD_favorite(rs.getString("d_favorite"));
				diary.setD_reg_date(rs.getDate("d_reg_date"));
				diary.setD_modify_date(rs.getDate("d_modify_date"));
				
				list.add(diary);
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}

	public List<DiaryDto> getDiary(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<DiaryDto> list=null;
		
		DiaryDto diary=null;
		try {
			conn=getConnection();
			
			sql="SELECT * FROM diary WHERE id=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			list=new ArrayList<DiaryDto>();
			while (rs.next()) {
				diary=new DiaryDto();
				
				diary.setD_title(rs.getString("d_title"));
				diary.setD_reg_date(rs.getDate("d_reg_date"));
				diary.setD_content(rs.getString("d_content"));
				diary.setD_idx(rs.getInt("d_idx"));
				
				list.add(diary);
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	//===다이어리 상세 보기===
	public DiaryDto getDiary(int d_idx)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=null;
		DiaryDto diary=null;
		
		try {
			conn=getConnection();
			sql="SELECT * FROM diary WHERE d_idx=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, d_idx);
			
			rs=pstmt.executeQuery();
			if (rs.next()) {
				diary=new DiaryDto();
				
				diary.setD_idx(rs.getInt("d_idx"));
				diary.setD_title(rs.getString("d_title"));
				diary.setId(rs.getString("id"));
				diary.setD_content(rs.getString("d_content"));
				diary.setD_favorite(rs.getString("d_favorite"));
				diary.setD_reg_date(rs.getDate("d_reg_date"));
				diary.setD_modify_date(rs.getDate("d_modify_date"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return diary;
	}
	
	//===다이어리 수정===
	public void modifyDiary(DiaryDto diary)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		try {
			conn=getConnection();
			sql="UPDATE diary SET d_title=?, d_content=?, d_modify_date=sysdate WHERE d_idx=?";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, diary.getD_title());
			pstmt.setString(2, diary.getD_content());
			pstmt.setInt(3, diary.getD_idx());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}

	}
	
	//===다이어리 삭제===
	public void deleteDiary(int d_idx)throws Exception{
			Connection conn=null;
			PreparedStatement pstmt=null;
			String sql=null;
			
			try {
				conn=getConnection();
				sql="DELETE FROM diary WHERE d_idx=?";
				
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, d_idx);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				throw new Exception(e);
			}finally {
				executeClose(null, pstmt, conn);
			}
		}
}
