package kr.schedule.domain;

public class AlarmDto {
	private int al_idx;
	//private ScheduleDto sc_idx;
	private int sc_idx;
	private int al_timer;
	private String al_re;
	
	public int getAl_idx() {
		return al_idx;
	}
	public void setAl_idx(int al_idx) {
		this.al_idx = al_idx;
	}
	
	
	
	public int getSc_idx() {
		return sc_idx;
	}
	public void setSc_idx(int sc_idx) {
		this.sc_idx = sc_idx;
	}
	public int getAl_timer() {
		return al_timer;
	}
	public void setAl_timer(int al_timer) {
		this.al_timer = al_timer;
	}
	public String getAl_re() {
		return al_re;
	}
	public void setAl_re(String al_re) {
		this.al_re = al_re;
	}
	
	
	
}
