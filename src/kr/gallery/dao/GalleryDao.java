package kr.gallery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.gallery.domain.GalleryDto;
import kr.util.FileUtil;

public class GalleryDao {
	//싱글턴 패턴
	private static GalleryDao instance = new GalleryDao();
	public static GalleryDao getInstance(){
		return instance;
	}

	private GalleryDao(){}

	//커넥션 풀로부터 커넥션을 할당
	private Connection getConnection()throws Exception{
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/xe");

		return ds.getConnection();
	}

	//자원정리
	private void executeClose(ResultSet rs,PreparedStatement pstmt,Connection conn){
		if(rs!=null)try{rs.close();}catch(SQLException e){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		if(conn!=null)try{conn.close();}catch(SQLException e){}
	}

	//===갤러리 등록===
	public void uploadGallery(GalleryDto gallery)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();
			
			sql = "INSERT INTO gallery "
					+ "(g_idx,g_title,id,g_content,g_photo1) "
					+ " VALUES (gallery_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gallery.getG_title());
			pstmt.setString(2, gallery.getId());
			pstmt.setString(3, gallery.getG_content());
			pstmt.setString(4, gallery.getG_photo1());

			pstmt.executeUpdate();			
		}catch(Exception e) {
			if(gallery.getG_photo1()!=null) {
				FileUtil.removeFile(gallery.getFilename());
			}
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}

	}
	
	//===갤러리 갯수===
	public int getGalleryCount(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn=getConnection();
			sql="SELECT COUNT(*) FROM gallery WHERE id=?";
			
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
	
	//===갤러리 목록===
	public List<GalleryDto> getGalleryList(int start,int end,String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<GalleryDto> list=null;
		GalleryDto gallery=null;
		
		try {
			conn=getConnection();
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
	                + "FROM (SELECT * FROM gallery d WHERE id=? order by g_reg_date DESC )a) "
	               + "WHERE rnum >= ? AND rnum <= ? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs=pstmt.executeQuery();
			
			list=new ArrayList<GalleryDto>();
			while (rs.next()) {
				gallery=new GalleryDto();

				gallery.setG_idx(rs.getInt("g_idx"));
				gallery.setG_title(rs.getString("g_title"));
				gallery.setG_reg_date(rs.getDate("g_reg_date"));
				gallery.setG_content(rs.getString("g_content"));
				gallery.setG_photo1(rs.getString("g_photo1"));
				gallery.setId(rs.getString("id"));
				
				list.add(gallery);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//===갤러리 상세===
	public GalleryDto getGalleryDetail(int g_idx)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		GalleryDto gallery= null;

		try {
			//커넥션 풀로부터 커넥션 할당
			conn = getConnection();

			sql = "SELECT * FROM gallery WHERE g_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, g_idx);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				gallery=new GalleryDto();
				
				gallery.setG_idx(rs.getInt("g_idx"));
				gallery.setG_title(rs.getString("g_title"));
				gallery.setG_reg_date(rs.getDate("g_reg_date"));
				gallery.setG_content(rs.getString("g_content"));
				gallery.setId(rs.getString("id"));
				gallery.setG_photo1(rs.getString("g_photo1"));
				
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(rs, pstmt, conn);
		}
		return gallery;
	}
	
	//===갤러리 가져오기===
	/*
	 * public GalleryDto getGallery(String id)throws Exception{ Connection conn =
	 * null; PreparedStatement pstmt = null; ResultSet rs = null; String sql = null;
	 * GalleryDto gallery= null;
	 * 
	 * try { //커넥션 풀로부터 커넥션 할당 conn = getConnection();
	 * 
	 * sql = "SELECT * FROM gallery WHERE id=?";
	 * 
	 * pstmt = conn.prepareStatement(sql);
	 * 
	 * pstmt.setString(1, id);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * while(rs.next()) { gallery=new GalleryDto();
	 * 
	 * gallery.setG_idx(rs.getInt("g_idx"));
	 * gallery.setG_title(rs.getString("g_title"));
	 * gallery.setG_reg_date(rs.getDate("g_reg_date"));
	 * gallery.setG_content(rs.getString("g_content"));
	 * gallery.setId(rs.getString("id"));
	 * gallery.setFilename(rs.getString("filename"));
	 * 
	 * } }catch(Exception e) { throw new Exception(e); }finally { executeClose(rs,
	 * pstmt, conn); } return gallery; }
	 */
	
	//===갤러리 수정===
	public void updateGallery(GalleryDto gallery)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = getConnection();

			sql = "UPDATE gallery SET g_title=?,g_content=?,"
					+ "g_modify_date=SYSDATE,g_photo1=? WHERE g_idx=?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, gallery.getG_title());
			pstmt.setString(2, gallery.getG_content());
			pstmt.setString(3, gallery.getG_photo1());
			pstmt.setInt(4, gallery.getG_idx());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			if(gallery.getG_photo1()!=null) {
				FileUtil.removeFile(gallery.getFilename());
			}
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
	
	//===갤러리 삭제===
	public void deleteGallery(int g_idx)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		try {
			conn=getConnection();
			sql="DELETE FROM gallery WHERE g_idx=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, g_idx);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			executeClose(null, pstmt, conn);
		}
	}
}
