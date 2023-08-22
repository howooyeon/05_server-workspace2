-- BOARD�� UPDATE
UPDATE BOARD 
 SET CATEGORY_NO = ?
 , BOARD_TITLE =?
 , BOARD_CONTENT =?
 
 WHERE BOARD_NO =? 
 ;
 
 -- ATTACHMENT ���̺� UPDATE
 UPDATE ATTACHMENT 
  SET ORIGIN_NAME = ?
    , CHANGE_NAME = ?
    , FILE_PATH = ?
    WHERE FILE_NO = ?
    
    ;
    
-- ������ �����µ� �ٽ� ����� ��� => ATTACHMENT INSERT
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
        
        
        
        