<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원상세정보조회</title>
</head>
<script src="js/jquery-3.2.1.min.js"></script>
<script>

	$(document).ready(function(){
		
		$('#prevButton').on("click",function(){
			
			window.close();
			
		});
				
	});
	
</script>
<body>

	<form>
		<table border = "1">
		<tr>
		<th>회원번호</th>
		<td>${sessionScope.member2.mNo }</td>
		</tr>
		<tr>
		<th>아이디</th>
		<td>${sessionScope.member2.mId }</td>
		</tr>
		<tr>
		<th>비밀번호</th>
		<td>${sessionScope.member2.mPw }</td>
		</tr>
		<tr>
		<th>이름</th>
		<td>${sessionScope.member2.mName }</td>
		</tr>
		<tr>
		<th>생년월일</th>
		<td>${sessionScope.member2.birthday }</td>
		</tr>
		<tr>
		<th>이메일</th>
		<td>${sessionScope.member2.email }</td>
		</tr>
		<tr>
		<th>주소</th>
		<td>${sessionScope.member2.address }</td>
		</tr>
		<tr>
		<th>총결제액</th>
		<td>${sessionScope.member2.score }</td>
		</tr>
		<tr>
		<th>등급번호</th>
		<td>${sessionScope.member2.rankNo }</td>
		</tr>
		<tr>
		<th>등급이름</th>
		<td>${sessionScope.member2.rName }</td>
		</tr>
		<tr>
		<th>탈퇴여부</th>
		<td>${sessionScope.member2.withdrawal }</td>
		</tr>
		<tr>
		<th>탈퇴날짜</th>
		<td>${sessionScope.member2.wdDate }</td>
		</tr>
		<tr>
		<th>탈퇴사유</th>
		<td>${sessionScope.member2.wdReason }</td>
		</tr>	
		
		</table>
		<button type="button" id="prevButton">이전</button>
	</form>

</body>
</html>