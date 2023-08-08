<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보 검색(회원 번호 검색)</h1>
	<form action="/Test1/search.do" method="GET">
	<input type="text" name="userNo" placeholder="회원 번호 입력"> <!-- 무조건 키값 name을 줬어야했음 -->
	<input type="submit" value ="조회">
	</form>
	
	<!-- db로 넘겨야할 값 무조건!! name 줘야함 -->
	
	<form action="/Test1/search1.do" method="GET">
		아이디 : <input type="text" name="userId"> <br><br>
		나이 : <input type="number" name="userAge"> <br><br>
		<input type="submit">
	</form>
	
	
</body>
</html>