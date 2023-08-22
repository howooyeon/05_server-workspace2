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
        
        
        
        