<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ����������ȸ</title>
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
		<th>ȸ����ȣ</th>
		<td>${sessionScope.member2.mNo }</td>
		</tr>
		<tr>
		<th>���̵�</th>
		<td>${sessionScope.member2.mId }</td>
		</tr>
		<tr>
		<th>��й�ȣ</th>
		<td>${sessionScope.member2.mPw }</td>
		</tr>
		<tr>
		<th>�̸�</th>
		<td>${sessionScope.member2.mName }</td>
		</tr>
		<tr>
		<th>�������</th>
		<td>${sessionScope.member2.birthday }</td>
		</tr>
		<tr>
		<th>�̸���</th>
		<td>${sessionScope.member2.email }</td>
		</tr>
		<tr>
		<th>�ּ�</th>
		<td>${sessionScope.member2.address }</td>
		</tr>
		<tr>
		<th>�Ѱ�����</th>
		<td>${sessionScope.member2.score }</td>
		</tr>
		<tr>
		<th>��޹�ȣ</th>
		<td>${sessionScope.member2.rankNo }</td>
		</tr>
		<tr>
		<th>����̸�</th>
		<td>${sessionScope.member2.rName }</td>
		</tr>
		<tr>
		<th>Ż�𿩺�</th>
		<td>${sessionScope.member2.withdrawal }</td>
		</tr>
		<tr>
		<th>Ż��¥</th>
		<td>${sessionScope.member2.wdDate }</td>
		</tr>
		<tr>
		<th>Ż�����</th>
		<td>${sessionScope.member2.wdReason }</td>
		</tr>	
		
		</table>
		<button type="button" id="prevButton">����</button>
	</form>

</body>
</html>