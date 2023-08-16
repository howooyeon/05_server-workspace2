SELECT * FROM MEMBER;

DELETE FROM MEMBER WHERE USER_NO = 9;


UPDATE MEMBER
    SET STATUS = 'Y'
    WHERE USER_NAME = '장구';
    
SELECT * FROM NOTICE;

    SELECT 
      NOTICE_NO
    , NOTICE_TITLE
    , USER_ID
    , COUNT
    , CREATE_DATE
    FROM NOTICE N
    JOIN MEMBER ON (NOTICE_WRITER = USER_NO)
    WHERE N.STATUS ='Y'
    ORDER
        BY NOTICE_NO DESC
        
    INSERT
      INTO NOTICE
      (
       NOTICE_NO
       ,NOTICE_TITLE
       ,NOTICE_CONTENT
       ,NOTICE_WRITER
       )
       VALUES
       (
        SEQ_NNO.NEXTVAL
        , ?
        , ?
        , ?
       )
       
    UPDATE NOTICE
        SET COUNT = COUNT + 1
    WHERE NOTICE_NO = ?
    AND STATUS = 'Y'
    
    
    SELECT 
      NOTICE_NO
    , NOTICE_TITLE
    , USER_ID
    , COUNT
    , CREATE_DATE
    FROM NOTICE N
    JOIN MEMBER ON (NOTICE_WRITER = USER_NO)
    WHERE N.STATUS ='Y'
    ORDER
        BY NOTICE_NO DESC
        
        
        SELECT 
	      NOTICE_NO
	    , NOTICE_TITLE
	    , NOTICE_CONTENT
	    , USER_ID
	    , CREATE_DATE
	    FROM NOTICE
	    JOIN MEMBER ON (NOTICE_WRITER = USER_NO)
	    WHERE NOTICE_NO = 3
        
        
        UPDATE NOTICE
            SET NOTICE_TITLE = ?
            , NOTICE_CONTENT = ?
          WHERE NOTICE_NO = ?
          
          -- 공지사항 삭제 쿼리
          UDATE NOTICE
          SET STATUS = 'N'
          WHERE NOTICE_NO = ?;
    
    SELECT * FROM BOARD;
    
        