package kr.schedule.domain;

import java.sql.Date;

public class ScheduleDto {
	//인덱스는 어떻게 처리하지? 자동으로 증가?
	private int sc_idx;
	private String sc_title;
	private String id;
	private String sc_place;
	private String sc_start_time;
	private String sc_start_date;
	private Date sc_start;
	private Date sc_end;
	
	private String sc_end_time;
	private String sc_end_date;
	private String sc_all_day;
	private String sc_content;
	private Date sc_reg_date;
	private String sc_modify_date;
	private String sc_favorite;
	private String sc_usable;
	
	public Date getSc_start() {
		return sc_start;
	}
	public void setSc_start(Date sc_start) {
		this.sc_start = sc_start;
	}
	public Date getSc_end() {
		return sc_end;
	}
	public void setSc_end(Date sc_end) {
		this.sc_end = sc_end;
	}
	public int getSc_idx() {
		return sc_idx;
	}
	public void setSc_idx(int sc_idx) {
		this.sc_idx = sc_idx;
	}
	public String getSc_title() {
		return sc_title;
	}
	public void setSc_title(String sc_title) {
		this.sc_title = sc_title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSc_place() {
		return sc_place;
	}
	public void setSc_place(String sc_place) {
		this.sc_place = sc_place;
	}
	public String getSc_start_time() {
		return sc_start_time;
	}
	public void setSc_start_time(String sc_start_time) {
		this.sc_start_time = sc_start_time;
	}
	public String getSc_start_date() {
		return sc_start_date;
	}
	public void setSc_start_date(String sc_start_date) {
		this.sc_start_date = sc_start_date;
	}
	public String getSc_end_time() {
		return sc_end_time;
	}
	public void setSc_end_time(String sc_end_time) {
		this.sc_end_time = sc_end_time;
	}
	public String getSc_end_date() {
		return sc_end_date;
	}
	public void setSc_end_date(String sc_end_date) {
		this.sc_end_date = sc_end_date;
	}
	public String getSc_all_day() {
		return sc_all_day;
	}
	public void setSc_all_day(String sc_all_day) {
		this.sc_all_day = sc_all_day;
	}
	public String getSc_content() {
		return sc_content;
	}
	public void setSc_content(String sc_content) {
		this.sc_content = sc_content;
	}
	public Date getSc_reg_date() {
		return sc_reg_date;
	}
	public void setSc_reg_date(Date sc_reg_date) {
		this.sc_reg_date = sc_reg_date;
	}
	public String getSc_modify_date() {
		return sc_modify_date;
	}
	public void setSc_modify_date(String sc_modify_date) {
		this.sc_modify_date = sc_modify_date;
	}
	public String getSc_favorite() {
		return sc_favorite;
	}
	public void setSc_favorite(String sc_favorite) {
		this.sc_favorite = sc_favorite;
	}
	public String getSc_usable() {
		return sc_usable;
	}
	public void setSc_usable(String sc_usable) {
		this.sc_usable = sc_usable;
	}
		
	
	
	
}
