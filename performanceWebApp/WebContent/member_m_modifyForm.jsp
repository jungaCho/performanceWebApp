<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원정보조회페이지</title>
	<style>
	form {
		padding : 30px;
	}
	
	#pannel > h2 {
		
		display: inline-block;
		margin-right: 10px;
	}
	
	#div1{
		margin-bottom: 20px;
		text-align: left;
	}
	
	#div1 > #btn2 {
		margin-left: 10px;
	}
	
	#div2{
		margin-top:20px;
		text-align: left;
	}
	
	</style>
	<script src="js/jquery-3.2.1.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#btn1').click(function() {
				
				$.ajax({
					url: '${pageContext.request.contextPath}/modifyMember.do'
					,
					method: 'POST'
					,
					dataType: 'json'
					,
					data: $('form').serialize()
					,
					success: function(data) {
						if(data.success == true) {
							alert("회원정보가 수정되었습니다.");
							location.href="${pageContext.request.contextPath}/retrieveMember.do";
						}
					},
					error: function(jqXHR) {
						alert("정보를 정확히 입력해주세요!");
					}
				});
			});
			
			$('#btn2').click(function() {
				location.href="${pageContext.request.contextPath}/retrieveMember.do";		
			});
			/*
			$('#btn3').click(function(){
				var url = "${pageContext.request.contextPath}/modifyPwd.do";
				var width = 400;
				var height = 200;
				var ww = width/2;
				var wh = height/2;
				window.open(url,"비밀번호 변경","width=" + width + ",height=" + height + ",top="+(screen.availHeight/2-wh)+
													",left="+(screen.availWidth/2-ww)+"");
			});
			*/
		}); 
	</script>
</head>
<body>
	<form>
		<div id="pannel">
		<h2>회원정보수정</h2>
		<button type="button" id="btn1">확인</button>
		<button type="button" id="btn2">취소</button>
		</div>

		<div id="div1">
		아 이 디 : ${requestScope.member.mId }</input><br>
		비밀번호 : <input type="password" name="pwd" size="30" value="${requestScope.member.mPw }"readonly></input><button type="button" id="btn3">변경</button><br>
		
		</div>
		<hr width="500" align = "center" color = "black" size ="1">
		<div id="div2">
		이  름 : <input type="text" name="name" value="${requestScope.member.mName }" size="30"/><br>
		생  일 : <input type="text" name="phone" value="${requestScope.member.birthday }" size="30"/><br>
		이메일 :<input type="text" name="email" value="${requestScope.member.email }" size="30"/><br>
		주  소 : <input type="text" name="address" value="${requestScope.member.address }" size="30"/><br>
		</div>
	
	
	</form>
</body>
</html>