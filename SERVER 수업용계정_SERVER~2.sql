-- ATTACHMENT ���̺� INSERT
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
    , ������ ������
    , ���� ������ ���ε�� ������
    , ������
    )
    
    -- ����ȸ ��û�� ������ sql��
    -- ��ȸ�� ������
    UPDATE
        BOARD
     SET COUNT = COUNT + 1
    WHERE BOARD_NO = ?
     AND STATUS ='Y'
     
     -- �Խñ� ���� ��ȸ
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
        
        -- ÷������ ��ȸ
        SELECT 
              FILE_NO
            , ORIGIN_NAME
            , CHANGE_NAME
            , FILE_PATH
        FROM ATTACHMENT
        WHERE REF_BNO = ?
        AND STATUS = 'Y'
        
     
     
     
     