<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h1>AJAX의 개요</h1>
	<p>
		Asynchronous Javascript And XML의 약자로 <br>
		서버로부터 데이터를 가져와 전체 페이지를 새로 고치지 않고 일부만 로드할 수 있게 하는 기법 <br>
		우리가 기존에 a태그로 요청 및 form submit 요청 방식은 "동기식 요청" => 응답페이지가 돌아와야만 볼 수 있음 (페이지 깜빡거림) <br> <br>
		
		* 동기식 / 비동기식 <br>
		> 동기식(a, form submit)
		<ul>
			<li>요청 처리 후 그에 해당하는 응답페이지가 돌아와야지만 그 다음 작업 가능</li>
			<li>서버에 요청한 결과까지의 시간이 지연되면 무작정 계속 기다려야됨 (흰 페이지로 보여질 거임)</li>
			<li>전체 페이지를 응답해주기 때문에 기본적으로 페이지가 깜빡거림</li>
		</ul>
		
		> 비동기식 (ajax)
		<ul>
			<li>현재 페이지를 그대로 유지하면서 중간중간마다 추가적으로 필요한 요청을 보내줄 수 있음</li>
			<li>요청을 보냈다고 해서 다른 페이지로 넘어가지 않음 (현재 페이지 그대로 유지)</li>
			<li>요청을 보내놓고 그에 해당하는 응답(데이터)이 돌아올 때까지 현재 페이지에서 다른 작업을 할 수 있음</li>
			<li>페이지 깜빡거리지 않음
			</li>
		</ul>
		ex) 실시간 급상승 검색어 로딩, 검색어 자동완성, 아이디 중복체크, 찜하기/해제하기, 추천, 댓글, 무한스크롤링(페이지대체)
		<br><br>
		
		* 비동기식의 단점 <br>
		- 현재 페이지에 지속적으로 리소스가 쌓임 => 페이지가 느려질 수 있음 <br>
		- 에러 발생시 디버깅 어려움 <br>
		- 요청 처리 후에 돌아온 응답데이터를 가지고 현재 페이지에 새로운 요소를 만들어서 뿌려줘야될 거임 => dom 요소들을 새로이 만들어내는 구문을 잘 익혀둬야됨
		<br><br>
		
		* AJAX 구현 방식 => 순수 Javascript 방식/ jQuery(코드가 간결하고 사용하기 쉬움) 방식
	</p>
	
	<pre>
	* jQuery 방식으로 AJAX 통신
	$.ajax({
		속성명:속성값,
		속성명:속성값,
		속성명:속성값
		...
	});
	
	* 주요속성
	- url : 요청할 url (필수속성!!)
	- type|method : 요청전송방식 (get/post)
	- data : 요청시 전달할 값
	- success : ajax통신 성공했을 때 실행할 함수 정의
	- error : ajax통신 실패했을 때 실행할 함수 정의
	- complete : ajax통신 실패했든 성공했든간에 무조건 실행할 함수 정의
	
	* 부수적인 속성
      - async : 서버와의 비동기 처리 방식 설정 여부 (기본값 true)
      - contentType : request 의 데이터 인코딩 방식 정의 (보내는 측의 데이터 인코딩)
      - dataType : 서버에서 response 로 오는 데이터의 데이터 형 설정, 값이 없다면 스마트하게 판단함
                      xml : 트리 형태의 구조
                      json : 맵 형태의 데이터 구조 (일반적인 데이터 구조)
                      script : javascript 및 일반 String 형태의 데이터
                      html : html 태그 자체를 return 하는 방식
                      text : String 데이터
      - accept : 파라미터의 타입을 설정 (사용자 특화 된 파라미터 타입 설정 가능)
      - beforeSend : ajax 요청을 하기 전 실행되는 이벤트 callback 함수 (데이터 가공 및 header 관련 설정)
      - cache : 요청 및 결과값을 scope 에서 갖고 있지 않도록 하는 것 (기본값 true)
      - contents : jQuery 에서 response 의 데이터를 파싱하는 방식 정의
      - context : ajax 메소드 내 모든 영역에서 파싱 방식 정의
      - crossDomain : 타 도메인 호출 가능 여부 설정 (기본값 false)
      - dataFilter : response 를 받았을 때 정상적인 값을 return 할 수 있도록 데이터와 데이터 타입 설정
      - global : 기본 이벤트 사용 여부 (ajaxStart, ajaxStop) (버퍼링 같이 시작과 끝을 나타낼 때, 선처리 작업)
      - password : 서버에 접속 권한 (비밀번호) 가 필요한 경우
      - processData : 서버로 보내는 값에 대한 형태 설정 여부 (기본 데이터를 원하는 경우 false 설정)
      - timeout : 서버 요청 시 응답 대기 시간 (milisecond)
	</pre>
	
	<h1>jQuery 방식을 이용한 AJAX 테스트</h1>
	<h3>1. 버튼 클릭시 get방식으로 서버에 요청 및 응답</h3>
	
	입력 : <input type="text" id="input1">
	<button id="btn1">전송</button>
	<br>
	
	응답 : <span id="output1">현재 응답 없음</span>
	
	<script>
		$(function(){
			$("#btn1").click(function(){
				
				// 기존에 동기식 통신
				// location.href = 'jqAjax1.do?input=' + $("#input1").val();
				
				// 비동기식 통식
				$.ajax({
					
					// 요청 보내기
					url:"jqAjax1.do", // 어느 url로 보낼 것인지
					data:{input:$("#input1").val()}, // 보낼 데이터, 키:벨류 세트로 보내야함
					type:"get", // 요청 방식 지정
					
					// 응답을 받아 주기 
					success:function(result){ // 성공시 응답 데이터가 자동으로 매개변수로 넘어옴
						console.log("ajax 통신성공!");
						console.log(result);
						$("#output1").text(result);
					},
					error:function(){
						console.log("ajax 통신실패! ㅜㅜ..")
					},
					complete:function(){
						console.log("ajax통신 성공여부와 관계없이 무조건 호출!")
					}
				})
			})
		})
	</script>
	<br>
	
	<h3>2. 버튼 클릭시 post 방식으로 서버에 여러개의 데이터 전송 및 응답</h3>
	
	이름 : <input type ="text" id="input2_1"> <br>
	나이 : <input type ="number" id="input2_2"> <br>
	<button onclick="test2();">전송</button> <br>
	
	<!--
	응답 : <label id="output2"></label>
	
	<script>
		function test2(){
			$.ajax({
				url:"jqAjax2.do",
				data:{
					name:$("#input2_1").val(),
					age:$("#input2_2").val(),
				},
				type:"post", 
				success:function(a){
					$("#output2").text(a);
					$("#input2_1").val("");
					$("#input2_2").val("");
				},
				error:function(){
					console.log("ajax 통신 실패!")
				}
			})
		}
	</script>
	 -->
	
	<!-- v2 -->
	응답
	<ul id="output2">
	
	</ul>
	
	<script>
		function test2(){
			$.ajax({
				url:"jqAjax2.do",
				data: {
					name:$("#input2_1").val(),
					age:$("#input2_2").val()
				},
				type:"post",
				success:function(a){
					/* JSONArray의 경우
					console.log(a)
					console.log(a[0])
					console.log(a[1])
					*/
					
					console.log(a);
					console.log(a.name);
					console.log(a.age);
					
					const value = "<li>" + a.name + "</li>"
								+ "<li>" + a.age  + "</li>";
					
					$("#output2").html(value);
					
					
				}, error:function(){
					console.log("ajax 통신 실패!")
				}
			})
		}
	</script>
	<br>
	
	<h3>3. 서버에 데이터 전송 후 조회된 vo 객체를 응답데이터로</h3>
	
	검색하고자 하는 회원번호 : <input type="number" id="input3">
	<button onclick="test3();">조회</button>
	
	<div id="output3"></div>
	
	<script>
		function test3(){
			$.ajax({
				url:"jqAjax3.do",
				data:{
					no:$("#input3").val()
				},
				success:function(result){
					console.log(result);
					
					const value = "이름 : " + result.userName + "<br>"
								  + ", 나이 : " + result.age + "<br>"
								  + ", 성별 : " + result.gender + "<br>"
								  
					$("#output3").html(value);
				},
				error:function(){
					console.log("ajax 통신 실패!")
				}
			})
		}
	</script>
	<br>
	
	<h3>4. 응답데이터로 조회된 여러 vo객체들이 담겨있는 ArrayList 받기</h3>
	
	<button onclick="test4();">회원전체조회</button>
	<br><br>
	
	<table id="output4" border="1">
		<thead>
			<th>번호</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
		</thead>
		<tbody>
		
		</tbody>
	
	</table>
	
	<script>
		function test4(){
			$.ajax({
				url:"jqAjax4.do",
				success:function(result){
					console.log(result);
				
					let value= "";
					
					for(let i = 0; i < result.length; i++){
						value += "<tr>"
								+ "<td>" + result[i].userNo + "</td>"
								+ "<td>" + result[i].userName + "</td>"
								+ "<td>" + result[i].age + "</td>"
								+ "<td>" + result[i].gender + "</td>"
								+ "</tr>"
					}
					
					$("#output4 tbody").html(value);

				}, error:function(){
					cosole.log("ajax 통신 실패!!");
				}
				
			})
			
		}
		
	</script>
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br>
	
</body>
</html>