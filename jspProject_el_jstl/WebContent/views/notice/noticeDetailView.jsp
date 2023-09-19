<%@page import="com.kh.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        background-color: black;
        color: white;
        width: 1000px;
        height: 500px;
        margin: auto;
        margin-top: 50px;
    }
</style>
</head>
<body>
<jsp:include page="../common/menubar.jsp"/>
    <div class="outer" align="center">
        <br>
        <h2 align="center">공지사항 상세보기</h2>
        <br>
        <table id="detail-area" border="1">
            <tr>
                <th width="70">제목</th>
                <td colspan="3" width="430">${ n.noticeTitle }</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${ n.noticeWriter }</td>
                <th>작성일</th>
                <td>${ n.createDate }</td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height: 150px;">${ n.noticeContent }</p>
                </td>
            </tr>
        </table>
        <br><br>

        <div>
            <a href="list.no" class="btn btn-sm btn-secondary">목록가기</a>
            <!-- 현재 로그인한 사용자가 해당 글을 쓴 본인일 경우 -->
            <c:if test="${not empty loginMember and n.noticeWriter eq loginMember.userId}">
            <a href="updateForm.no?num=${ n.noticeNo }" class="btn btn-sm btn-warning">수정하기</a>
            <a href="deleteForm.no?num=${ n.noticeNo }" class="btn btn-sm btn-danger">삭제하기</a>
            </c:if>
           
            <!-- 
            	삭제 구현해보기
            	url-mapping 맘대로
            	
            	요청 성공시 => 공지사항 목록페이지 alert(공지사항이 성공적으로 삭제됐습니다.)
            	요청 실패시 => 애러문구 보여지는 에러페이지 
             -->
        </div>
    </div>
</body>
</html>