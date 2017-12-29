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
	
	span {
		color:red;
		font-size: 12px;
	}
	
	#none {
		color: black;
	}
	
	#pannel > h2 {
		
		display: inline-block;
		margin-right: 10px;
	}
	
	#div1{
		text-align: left;
		line-height:35px;
		margin-top:50px;
	}
	
	#div1 > #btn2 {
		margin-left: 10px;
	}
	
	#div2{
		    margin-top: 20px;
    text-align: left;
    line-height: 35px;
    border-top: 1px solid #5b5b5b;
    padding-top: 20px;
	}
	
	.wrap{padding:50px;}
	.content{font-size:15px; overflow:hidden; width:950px; }
	#btn1{background:blue;width: 50px;
    height: 30px;
    color: white;
    border-radius: 5px;
    margin-top: -3px;}
    
    
    #btn2{background:gray;width: 50px;
    height: 30px;
    color: white;
    border-radius: 5px;
    margin-top: -3px;}
    
    input{border:1px solid #5b5b5b; margin-top:5px; margin-left:15px; height:25px; font-size:15px;}
	
	</style>
	<script src="js/jquery-3.2.1.min.js"></script>
	<script>
		$(document).ready(function() {
			
			
			$('#newPwd').on('focus',function(){
				$(this).next('span').remove();
			});

			$('#npCheck').on('focus',function(){
				$(this).next('span').remove();
			});
				
			$('#name').on('focus',function(){
				$(this).next('span').remove();
			});

			$('#birthday').on('focus',function(){
				$(this).next('span').remove();
			});

			$('#email').on('focus',function(){
				$(this).next('span').remove();
			});

			$('#address').on('focus',function(){
				$(this).next('span').remove();
			});

			$('#newPwd').on('blur',function(){
				if($(this).val().length < 5 && $(this).val().length < 16) {
					$(this).after("<span id='problem'> 최소8~12글자 동일숫자 연속 3자리 불가 </span>");
				}
			});

			$('#npCheck').on('blur',function(){
				if($(this).val() == 0 ) {
					$(this).after("<span>비밀번호 한번더 입력해주세요</span>");
				} else if($('#pwd').val() != $(this).val()) {
					$(this).after("<span id='problem'> 비밀번호가 일치하지 않습니다</span>")
				}
			});

			$('#name').on('blur',function(){
				if($(this).val() == 0 ) {
					$(this).after("<span> 이름을 입력하세요</span>");
				}
			});

			$('#birthday').on('blur',function(){
				if($(this).val() == 0 ) {
					$(this).after("<span> 생일을 입력하세요</span>");
				}
			});

			$('#email').on('blur',function(){
				if($(this).val() == 0 ) {
					$('this').after("<span> 이메일를 입력하세요</span>");
				}
			});
				
			$('#address').on('blur',function(){
				if($(this).val() == 0 ) {
					$(this).after("<span> 주소를 입력하세요</span>");
				}
			});	

			$('#btn1').click(function() {
				
				if( 8 > $('#newPwd').val().length < 12) {
					alert("비밀번호 양식을 확인해주세요!")	
					return false;
				}
				
				if($('#npCheck').val().length == 0) {
					alert("비밀번호를 한번더 입력해주세요!!");
					return false;
				} 
					
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
		}); 
	</script>
</head>
<body>
	<div class="wrap">
	<form>
		<div id="pannel">
		<h2>회원정보수정</h2>
		<button type="button" id="btn1">확인</button>
		<button type="button" id="btn2">취소</button>
		</div>
		
		<div class="content">
		<div id="div1">
		아 이 디 : ${requestScope.member.mId }</input><br>
		비밀번호 : <input type="password" id="newPwd" name="newPwd" value="${requestScope.member.mPw }" size="25"  placeholder="최소 8~12글자 동일숫자 연속 3자리 불가"/><br>
		비밀번호 확인 : <input type="password" id="npCheck" name="npCheck" size="30"  placeholder="비밀번호를 한번더 입력해주세요"/><br>
		<span id="none">본인확인을 위해 변경하지 않을시에도 한번더 입력해주시기 바랍니다</span><br>
		</div>
		<hr width="500" align = "center" color = "black" size ="1">
		<div id="div2">
		이  름 : <input type="text" name="name" value="${requestScope.member.mName }" size="30"/><br>
		생  일 : <input type="text" name="birthday" value="${requestScope.member.birthday }" size="30"/><br>
		이메일 :<input type="text" name="email" value="${requestScope.member.email }" size="30"/><br>
		주  소 : <input type="text" name="address" value="${requestScope.member.address }" size="30"/><br>
		</div>
		</div>
	
	</form>
	</div>
</body>
</html>