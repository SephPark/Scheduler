-- 일정 등록 프로시저
create or replace PROCEDURE SP_INSERT_SCHEDULE(
    p_title in varchar2,
    p_id in varchar2,
    p_space in varchar2,
    p_start_date in varchar2,
    p_start_time in varchar2,
    p_end_date in varchar2,
    p_end_time in varchar2,
    p_all_day in char,
    p_content in clob
) AS
    v_idx int;
    v_start_dt date;
    v_end_dt date;
BEGIN

    SELECT NVL(MAX(SC_IDX) + 1,1) INTO v_idx FROM SCHEDULE;

    v_start_dt := to_date(p_start_date || ' ' || p_start_time,'YYYY-MM-DD HH24:MI:SS');
    v_end_dt := to_date(p_end_date || ' ' || p_end_time,'YYYY-MM-DD HH24:MI:SS');
    
    INSERT INTO SCHEDULE (SC_IDX, SC_TITLE, ID, SC_PLACE, SC_START, SC_END, SC_ALL_DAY, SC_CONTENT)
    VALUES (v_idx, p_title, p_id, p_space, v_start_dt, v_end_dt, p_all_day, p_content);
    
    COMMIT;
    
END SP_INSERT_SCHEDULE;

-- 일정 수정 프로시저
CREATE OR REPLACE PROCEDURE SP_UPDATE_SCHEDULE(
    p_idx in int,
    p_title in varchar2,
    p_space in varchar2,
    p_start_date in varchar2,
    p_start_time in varchar2,
    p_end_date in varchar2,
    p_end_time in varchar2,
    p_all_day in char,
    p_content in clob,
    p_usable in char
) AS
    v_idx int;
    v_start_dt date;
    v_end_dt date;
BEGIN

    v_start_dt := to_date(p_start_date || ' ' || p_start_time,'YYYY-MM-DD HH24:MI:SS');
    v_end_dt := to_date(p_end_date || ' ' || p_end_time,'YYYY-MM-DD HH24:MI:SS');
    
    UPDATE SCHEDULE SET
        SC_TITLE = p_title,
        SC_PLACE = p_space,
        SC_START = v_start_dt,
        SC_END = v_end_dt,
        SC_ALL_DAY = p_all_day,
        SC_CONTENT = p_content,
        SC_USABLE = p_usable
    WHERE SC_IDX = p_idx;
    
    COMMIT;
    
END SP_UPDATE_SCHEDULE;