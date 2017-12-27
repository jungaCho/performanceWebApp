<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>로그인화면</title>
<style>

#box {
	
	width: 300px;
	height: 400px;
	background-color: yellow;
	margin: 0 auto; /*박스요소를 수평으로 가운데 정렬해주는 코드 */
	margin-bottom: 10px; /*아래 여백을 10px로 잡아줌 */
	padding: 20px; /* 안쪽 여백을 20px로 잡아줌 */
	text-align : center;

}

button {

	width: 150px;
	height : 50px;

}

input {

	text-align : center;
}


</style>
<%--
<script src = "js/jquery-3.2.1.min.js"></script>
<script>



	$(document).ready(function(){
		
		$('#btn1').on("click",function(){
			
		/* 	$.ajax ({
				
				url : "${pageContext.request.contextPath }/member_m_newMember.jsp",
				method : 'POST',
				dataType : "json",
				
				data : {
					
					id : $("#id").val(),
					pwd : $("#pwd").val()
					
				},
				
				success : function(data){
					if(data.success == true){
						alert("로그인에 성공하셨습니다!");
						location.href= "${pageContext.request.contextPath}/adminLogin.do?id="+data.id+"&pwd="+data.pwd;
						
					}
				},
				
				error :  function(jqXHR){
					
					alert("에러 : " + jqXHR);
				} */
			/* 	
			var id= $("#id").val();
			var pwd = $("#pwd").val();
			
			location.href= "${pageContext.request.contextPath}/adminLogin.do?id="+id+"&pwd="+pwd;
			}); */
		});
		
	
	});
		
		
		
		
	});
	
	</script> --%>
	  
 
</head>
<body>

	<form action="${pageContext.request.contextPath }/adminLogin.do" method = "post">

		<div id="box">

			아이디 : <input id="id" type="text" name="id" sizes="20"
				placeholder="관리자사번입력" autofocus></input><br> 
				비밀번호 : <input id="pwd"
				type="password" name="pwd" sizes="20" placeholder="비밀번호입력"></input><br>


			<br>

			<button type="submit" id="btn1">관리자로그인</button>
		</div>

	</form>


</body>
</html>