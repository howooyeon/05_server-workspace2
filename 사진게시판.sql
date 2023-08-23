-- BOARD에 UPDATE
UPDATE BOARD 
 SET CATEGORY_NO = ?
 , BOARD_TITLE =?
 , BOARD_CONTENT =?
 
 WHERE BOARD_NO =? 
 ;
 
 -- ATTACHMENT 테이블 UPDATE
 UPDATE ATTACHMENT 
  SET ORIGIN_NAME = ?
    , CHANGE_NAME = ?
    , FILE_PATH = ?
    WHERE FILE_NO = ?
    
    ;
    
-- 파일이 없었는데 다시 생기는 경우 => ATTACHMENT INSERT
INSERT
    INTO ATTACHMENT
    (
          FILE_NO
        , REF_BNO
        , ORIGIN_NAME
        , CHANGE_NAME
        , FILE_PATH
    )
    VALUES
    (
     SEQ_FNO.NEXTVAL
    ,SEQ_BNO.CURRVAL
    , ?
    , ?
    , ?
    )
    
    -- BOARD INSERT
    INSERT
        INTO BOARD
        (
        BOARD_NO
        , BOARD_TYPE
        , BOARD_TITLE
        , BOARD_CONTENT
        , BOARD_WRITER
        )
        
        VALUES
        (
        SEQ_BNO.NEXTVAL
        , 2
        , ?
        , ?
        , ?
        )
        
        -- INSERT ATTACHMENT
        INSERT
            INTO ATTACHMENT
            (
             FILE_NO
             , REF_BNO
             , ORIGIN_NAME
             , CHANGE_NAME
             , FILE_PATH
             , FILE_LEVEL
            )
            VALUES
            (
             SEQ_FNO.NEXTVAL
             , SEQ_BNO.CURRVAL
             , ?
             , ?
             , ?
             , ?
            )
            
        -- 사진게시글 목록 조회용 SQL
        SELECT
              BOARD_NO
            , BOARD_TITLE
            , COUNT
            , FILE_PATH || '/'  || CHANGE_NAME "TITLEIMG"
        FROM BOARD B
        JOIN ATTACHMENT ON (BOARD_NO = REF_BNO)
        WHERE BOARD_TYPE = 2
        AND B.STATUS = 'Y'
        AND FILE_LEVEL = 1
        ORDER
            BY BOARD_NO DESC
        ;
        
        SELECT 
            BOARD_NO
            , CATEGORY_NAME
            , BOARD_TITLE
            , BOARD_CONTENT
            , USER_ID
            , TO_CHAR(CREATE_DATE, 'YYYY/MM/DD') "CREATE_DATE"
        FROM BOARD
        LEFT JOIN CATEGORY USING(CATEGORY_NO)
        JOIN MEMBER ON (BOARD_WRITER = USER_NO)
        WHERE BOARD_NO = 100
        ;
        
        SELECT 
              FILE_NO
            , ORIGIN_NAME
            , CHANGE_NAME
            , FILE_PATH
        FROM ATTACHMENT
        WHERE REF_BNO = 116
        AND STATUS = 'Y'
        ;
        
        SELECT 
            COUNT(*) "COUNT"
        FROM MEMBER
        WHERE USER_ID = 'user01'
        