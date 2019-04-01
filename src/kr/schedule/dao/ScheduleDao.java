package kr.schedule.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.schedule.domain.AlarmDto;
import kr.schedule.domain.ScheduleDto;
import kr.schedule.domain.ScheduleInvitedUserDto;
import kr.user.domain.UserDto;

public class ScheduleDao {
	//싱글턴 패턴
	private static ScheduleDao instance = new ScheduleDao();
	public static ScheduleDao getInstance(){
		return instance;
	}
	private ScheduleDao(){}

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
	
	private void executeClose(ResultSet rs,
			CallableStatement cstmt,Connection conn){
		if(rs!=null)try{rs.close();}catch(SQLException e){}
		if(cstmt!=null)try{cstmt.close();}catch(SQLException e){}
		if(conn!=null)try{conn.close();}catch(SQLException e){}
	}
	//===sc_idx 가져오기===
	public int getSc_idx()throws Exception{
		int sc_idx=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		
		try {
			conn=getConnection();
			
			sql="SELECT sc_idx FROM schedule";
			
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				sc_idx=rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
		return sc_idx;
	}
	
	//===일정 갯수 ===
	public int getScheduleCount(String id)throws Exception{
		int count=0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn=getConnection();
			
			sql="SELECT COUNT(*) FROM SCHEDULE WHERE ID=?";
			
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
	
	//===일정추가===(일정 초대,알림 설정)
	public void addSchedule(ScheduleDto schedule)throws Exception {
		Connection conn=null;
		CallableStatement cstmt=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		
		String sql=null;
		
		
		try {
			conn=getConnection();
			conn.setAutoCommit(false);
			
			//schedule에 추가
			sql="{CALL sp_insert_schedule (?,?,?,?,?,?,?,?,?) } ";
			
			cstmt=conn.prepareCall(sql);
			cstmt.setString(1, schedule.getSc_title());
			cstmt.setString(2, schedule.getId());
			cstmt.setString(3, schedule.getSc_place());
			cstmt.setString(4, schedule.getSc_start_date());
			cstmt.setString(5, schedule.getSc_start_time());
			cstmt.setString(6, schedule.getSc_end_date());
			cstmt.setString(7, schedule.getSc_end_time());
			cstmt.setString(8, schedule.getSc_all_day());
			cstmt.setString(9, schedule.getSc_content());
			
			cstmt.executeUpdate();
			
			
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt3, null);
			executeClose(null, pstmt2, null);
			executeClose(null, cstmt, conn);
		}
	}
	
	//===멤버 초대 목록====
	public List<UserDto> getInvitedUsers(String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UserDto> list = null;
		String sql = null;
		
		try {
			conn=getConnection();
			
			sql="SELECT * FROM user_info WHERE"
					+ " id=?"
					+ " or name=?"
					+ " or email=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setString(3, keyword);
			
			rs=pstmt.executeQuery();
			
			list=new ArrayList<UserDto>();
			
			while (rs.next()) {
				UserDto user=new UserDto();
				
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setId(rs.getString("id"));
				
				list.add(user);
				
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	//===일정 보기===
	public List<ScheduleDto> getSchedule(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<ScheduleDto> list=null;
		
		ScheduleDto schedule=null;
		try {
			conn=getConnection();
			
			//sql="SELECT * FROM schedule WHERE id=?";
			sql="SELECT SC_IDX, ID, SC_TITLE, SC_PLACE, SC_START,"
					+ " UF_GET_DATE(SC_START) SC_START_DATE, UF_GET_TIME(SC_START) SC_START_TIME,SC_END, "
					+ " UF_GET_DATE(SC_END) SC_END_DATE,UF_GET_TIME(SC_END) SC_END_TIME,"
					+ "SC_CONTENT,SC_ALL_DAY,SC_REG_DATE "
					+ "FROM schedule WHERE id=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			list=new ArrayList<ScheduleDto>();
			while (rs.next()) {
				schedule=new ScheduleDto();
				
				schedule.setId(rs.getString("id"));
				schedule.setSc_title(rs.getString("sc_title"));
				schedule.setSc_place(rs.getString("sc_place"));
				schedule.setSc_start(rs.getDate("sc_start"));
				schedule.setSc_start_date(rs.getString("sc_start_date"));
				schedule.setSc_start_time(rs.getString("sc_start_time"));
				
				schedule.setSc_end(rs.getDate("sc_end"));
				schedule.setSc_end_date(rs.getString("sc_end_date"));
				schedule.setSc_end_time(rs.getString("sc_end_time"));
				
				schedule.setSc_content(rs.getString("sc_content"));
				schedule.setSc_all_day(rs.getString("sc_all_day"));
				schedule.setSc_reg_date(rs.getDate("sc_reg_date"));
				schedule.setSc_idx(rs.getInt("sc_idx"));
				
				list.add(schedule);
				
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//===일정 상세 보기===
	public ScheduleDto getSchedule(int sc_idx)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ScheduleDto schedule=null;
		
		try {
			conn=getConnection();
			
			sql="SELECT SC_IDX, ID, SC_TITLE, SC_PLACE, SC_START,"
					+ " UF_GET_DATE(SC_START) SC_START_DATE, UF_GET_TIME(SC_START) SC_START_TIME,SC_END, "
					+ " UF_GET_DATE(SC_END) SC_END_DATE,UF_GET_TIME(SC_END) SC_END_TIME,"
					+ "SC_CONTENT,SC_ALL_DAY,SC_REG_DATE "
					+ "FROM schedule WHERE SC_IDX=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sc_idx);
			
			
			rs=pstmt.executeQuery();
			if (rs.next()) {
				schedule =new ScheduleDto();
				
				schedule.setSc_idx(rs.getInt("sc_idx"));
				schedule.setId(rs.getString("id"));
				schedule.setSc_title(rs.getString("sc_title"));
				schedule.setSc_place(rs.getString("sc_place"));
				schedule.setSc_start(rs.getDate("sc_start"));
				schedule.setSc_start_date(rs.getString("sc_start_date"));
				schedule.setSc_start_time(rs.getString("sc_start_time"));
				
				schedule.setSc_end(rs.getDate("sc_end"));
				schedule.setSc_end_date(rs.getString("sc_end_date"));
				schedule.setSc_end_time(rs.getString("sc_end_time"));
				
				schedule.setSc_content(rs.getString("sc_content"));
				schedule.setSc_all_day(rs.getString("sc_all_day"));
				schedule.setSc_reg_date(rs.getDate("sc_reg_date"));
			}
			
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return schedule;
	}
	
	//===일정수정===
	public void modifySchedule(ScheduleDto schedule)throws Exception{
		Connection conn=null;
		CallableStatement cstmt=null;
		String sql=null;
		
		try {
			conn=getConnection();
			//프로시져 사용
			
			sql="{CALL sp_update_schedule(?,?,?,?,?,?,?,?,?,'Y')}";
			
			cstmt=conn.prepareCall(sql);
			cstmt.setInt(1, schedule.getSc_idx());
			cstmt.setString(2, schedule.getSc_title());
			cstmt.setString(3, schedule.getSc_place());
			cstmt.setString(4, schedule.getSc_start_date());
			cstmt.setString(5, schedule.getSc_start_time());
			cstmt.setString(6, schedule.getSc_end_date());
			cstmt.setString(7, schedule.getSc_end_time());
			cstmt.setString(8, schedule.getSc_all_day());
			cstmt.setString(9, schedule.getSc_content());
			
			cstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, cstmt, conn);
		}
	}
	
	//===일정삭제===
	public void deleteSchedule(int sc_idx)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		try {
			conn=getConnection();
			
			sql="DELETE FROM schedule WHERE sc_idx=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sc_idx);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
}


