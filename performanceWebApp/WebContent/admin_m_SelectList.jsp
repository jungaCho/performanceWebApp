<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원정보 리스트</title>

<style>

	h2 {
		
		color : black;
	}

	form {
	
		color : black;
	
	}
	.wrap{padding:50px;}
	
	.table {
	color:black;
	border: 1px solid;
	width:1100px;
	font-size:15px;
	margin-top:30px;
	
}

select{height:30px; font-size:15px;}
tr{height:40px;}

td{font-size:15px; line-height:40px;}
td a{font-size:15px; line-height:40px; font-weight:100; color:black;}
.table th{
	background-color:#FFBB00;
	font-size:15px;
	line-height:40px;
	
}
input{height: 30px;
    font-size: 15px;
    border: 1px solid #ddd;}

#btn3{    width: 70px;
    height: 30px;
    margin-right: 10px;
    font-size: 15px;
    background: gray;
    color: white;
    border-radius: 5px;}
    
 #btn1{    width: 100px;
    height: 30px;
    margin-right: 10px;
    font-size: 15px;
    background: gray;
    color: white;
    border-radius: 5px;}   

</style>

<script src="js/jquery-3.2.1.min.js"></script>
<script>

	$(document).ready(function(){
	
		
		$('#table1').on("click", '#btn2', function(){
			var memberNo = $(this).parents("tr:first").attr("id");		
			window.open("${pageContext.request.contextPath}/retrieveMemberDetail.do?mNo="+memberNo,"회원정보상세조회","width=700, height=600, top=200, left=200");
		});
		
		
		$('#btn1').on("click",function(){
			
	
			
			$.ajax({
				
				url: "${pageContext.request.contextPath}/selectMemberList.do"
				,
				method : "POST"
				,
				dataType: 'json'
				,
				data: {
					
					sortkey: $('#view').find('option:selected').val()
				},
				
				success: function(data){
					
						$('#table1').find('tr:not(:first)').remove(); 
						
						var htmlStr = "";
						
						for(var i=0; i<data.length; i++){
							
							htmlStr += "<tr id=" + data[i].mNo + ">";
							htmlStr += "<td>" + data[i].mNo + "</td>";
							htmlStr += "<td>" + data[i].mName + "</td>";
							htmlStr += "<td>" + data[i].mId + "</td>";
							htmlStr += "<td>" + data[i].mPw + "</td>";
							htmlStr += "<td>" + data[i].email + "</td>";
							htmlStr += "<td>" + data[i].address + "</td>";
							htmlStr += "<td>" + data[i].rankNo + "</td>";
						    htmlStr += "<td>" + "비고" +"<button type='button'>회원정보상세조회</button>"+ "</td>";
							htmlStr += "<tr>";
							
							$(htmlStr).appendTo('#table1');
							htmlStr = "";
							
						}
						
				}
			
				,
				error: function(jaXHR){
					alert("error: " + jaXHR.error );
					
				}
				
			});
			
		});
		
		
		//검색어 입력 처리
		
			$('#btn3').on("click",function(){
			
	
			
			$.ajax({
				
				url: "${pageContext.request.contextPath}/searchMember.do"
				,
				method : "POST"
				,
				dataType: 'json'
				,
				data: {
				
					sortkey: $('#filter').find('option:selected').val(),
					keyword: $('#keyword').val(),
					startRow: 1,
					endRow: 15
					
					
				},
				
				success: function(data){
					
						$('#table1').find('tr:not(:first)').remove(); 
						
						var htmlStr = "";
						
						for(var i=0; i<data.length; i++){
							
							htmlStr += "<tr id=" + data[i].mNo + ">";
							htmlStr += "<td>" + data[i].mNo + "</td>";
							htmlStr += "<td>" + data[i].mName + "</td>";
							htmlStr += "<td>" + data[i].mId + "</td>";
							htmlStr += "<td>" + data[i].mPw + "</td>";
							htmlStr += "<td>" + data[i].email + "</td>";
							htmlStr += "<td>" + data[i].address + "</td>";
							htmlStr += "<td>" + data[i].rankNo + "</td>";
						    htmlStr += "<td>" + "비고" +"<button type='button'>회원정보상세조회</button>"+ "</td>";
							htmlStr += "<tr>";
							
							$(htmlStr).appendTo('#table1');
							htmlStr = "";
							
						}
						
				}
			
				,
				error: function(jaXHR){
					alert("error: " + jaXHR.error );
					
				}
				
			});
			
			});
		
	});
	
		
		
		
</script>

</head>
<body>
	<div class="wrap">
	<h2>회원관리</h2>
	<br>
	<form>
	<select id="view">
	<option value="m_no">회원번호</option>
	<option value="m_name">이름</option>
	<option value="rank_no">등급</option>
	<option value="withdrawal">탈퇴여부</option>
		
	</select>
	<button type="button" id="btn1">순으로 정렬</button>
	
	<select id="filter" >
	<option value="m_no">회원번호</option>
	<option value="m_name">이름</option>
	<option value="m_id">아이디</option>
	<option value="rank_no">등급</option>
	<option value="withdrawal">탈퇴여부</option>
	
		
	</select>
	
	<input type="text" id="keyword" placeholder="검색어를 입력해주십시오">
	
	<button type="button" id="btn3">검색</button>
	
	<table id="table1" border = "1"  class="table" cellspacing=0>
		
		<tr>
		
		<th>회원번호</th>
		<th>이름</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이메일</th>
		<th>주소</th>
		<th>등급</th>
		<th>비고</th>
		
		
		</tr>
		
		<c:forEach var="members" items= "${requestScope.memberList }" varStatus="loop">
		<tr id="${pageScope.members.mNo }" >		
			<td>${pageScope.members.mNo }</td>
			<td>${pageScope.members.mName }</td>
			<td>${pageScope.members.mId }</td>
			<td>${pageScope.members.mPw }</td>
			<td>${pageScope.members.email }</td>
			<td>${pageScope.members.address }</td>
			<td>${pageScope.members.rankNo }</td>
			
			<td>비고<button type="button" id="btn2">회원정보상세조회</button></td>		
		</tr>
		</c:forEach>
		
	</table>
	</form>
</div>
</body>
</html>