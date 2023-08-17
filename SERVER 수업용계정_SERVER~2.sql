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