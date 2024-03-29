-- ATTACHMENT 테이블에 INSERT
INSERT
    INTO ATTACHMENT
    (
          FILE_NO
        , REF_BNO
        , ORIGIN_NAME
        , CHANGE_NAME
        , FILEPATH
    )
    VALUES
    (
    SEQ_FNO.NEXTVAL
    ,SEQ_BNO.CURRVAL
    , 파일의 원본명
    , 실제 서버에 업로드된 수정명
    , 저장경로
    )
    
    -- 상세조회 요청시 실행할 sql문
    -- 조회수 증가용
    UPDATE
        BOARD
     SET COUNT = COUNT + 1
    WHERE BOARD_NO = ?
     AND STATUS ='Y'
     
     -- 게시글 정보 조회
     SELECT 
            BOARD_NO
            , CATEGORY_NO
            , BOARD_TITLE
            , BOARD_CONTENT
            , USER_ID
            , TO_CHAR(CREATE_DATE, 'YYYY/MM/DD') "CREATE_DATE"
        FROM BOARD
        JOIN CATEGORY USING(CATEGORY_NO)
        JOIN MEMBER ON (BOARD_WRITER = USER_NO)
        WHERE BOARD_NO = ?
        
        -- 첨부파일 조회
        SELECT 
              FILE_NO
            , ORIGIN_NAME
            , CHANGE_NAME
            , FILE_PATH
        FROM ATTACHMENT
        WHERE REF_BNO = ?
        AND STATUS = 'Y'
        
     
     
     
     