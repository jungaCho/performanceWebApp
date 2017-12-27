<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공연 수정</title>

<style>
form {
	padding: 30px;
	background-color: gray;
	width: 900px;
	height: 100%;
}

#btn1 {
	width: 80px;
	height: 40px;
	margin-right: 50px;
}

#btn2 {
	width: 80px;
	height: 40px;
	margin-right: 50px;
}

#pannel>h2 {
	display: inline-block;
	margin-right: 10px;
}

#div1 {
	margin-bottom: 20px;
	text-align: left;
}

#div1>#btn2 {
	margin-left: 10px;
}

#div2 {
	margin-top: 20px;
	text-align: left;
}

#posterList {
	float: right;
	margin-right: 500px;
}
</style>

<script src="js/jquery-3.2.1.min.js"></script>
<script>
      $(document).ready(function() {
    	  
    	  
    	  
    	   $('#detailFileList  a').on('click', function() {
    		  var temp = $('input[name=removeDetailFile]').val();
    		  if(temp.length != 0) {
    			  $('input[name=removeDetailFile]').val(temp + ","+ $(this).attr('id')); 
    		  } else {
    			  $('input[name=removeDetailFile]').val($(this).attr('id'));  
    		  }    	     		 
    		  $(this).parents('tr:first').empty();
    	   })
    	   
    	   $('#posterList  a').on('click', function() {
     		  var temp = $('input[name=removePoster]').val();
     		  if(temp.length != 0) {
     			  $('input[name=removePoster]').val(temp + ","+ $(this).attr('id')); 
     		  } else {
     			  $('input[name=removePoster]').val($(this).attr('id'));  
     		  }    	     		 
     		  $(this).parents('tr:first').empty();
     	   })
    	   
    	   $('#btn2').click(function() {
   			location.href="${pageContext.request.contextPath}/admin_p_detailPerformance.do?pNo=${requestScope.performance.pNo }";
   		   });
    	   
      });
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/modifyPerformance" enctype="multipart/form-data"
		 method="post">
		<input type="hidden" name="removeDetailFile">
		<input type="hidden" name="removePoster">		
		<h1>공연 수정</h1>
		<%-- 업로드 된 포스터 목록 조회 --%>
		<table id="posterList" border="1">
			<c:forEach var="poster"
				items="${requestScope.posters }" varStatus="loop">				
				<tr>
					<td>포스터${pageScope.loop.count }</td>
					<td>${pageScope.poster.originalFileName }</td>
					<td><a href="#"  id="${pageScope.poster.posterNo }">삭제</a></td>
				</tr>
			</c:forEach>
		</table><br>
		<div id="pannel">
			메인포스터<br><input type="file" name="mainPoster" size="20"></input><br>
			 <input type="hidden" name="no" value="${requestScope.performance.pNo }">
			부가포스터<br><input type="file" name="poster" size="20"></input><br> <input
				type="file" name="poster" size="20"></input><br> <input
				type="file" name="poster" size="20"></input><br>
		</div>

		<div id="div1">
			<h3>
				공연제목 : <input type="text" name="title" size="20"
					value="${requestScope.performance.title }"></input>
			</h3>

			<table border="1" width=550>
				<tr>
					<th colspan='2'>동영상</th>
					<td colspan='2'><input type="url" name="video" size="40"
						value="${requestScope.performance.video }"></input></td>
				</tr>
				<tr>
					<th>시작일</th>
					<td><input type="date" name="startDate" size="15"
						value="${requestScope.performance.startDate }"></td>
					<th>종료일</th>
					<td><input type="date" name="endDate" size="15"
						value="${requestScope.performance.endDate }"></td>
				</tr>
				<tr>
					<th>제작사</th>
					<td><input type="text" name="production" size="15"
						value="${requestScope.performance.production }"></td>
					<th>가격</th>
					<td><input type="text" name="price" size="15"
						value="${requestScope.performance.price }"></input></td>
				</tr>
				<tr>
					<th>관람등급</th>
					<td><select id="view_Class" name="viewClass"
						value="${requestScope.performance.viewNo }">
							<option value="V001">전체관람가</option>
							<option value="V002">12세 이상</option>
							<option value="V003">15세 이상</option>
							<option value="V004">청소년관람불가</option>
					</select></td>
					<th>장르</th>
					<td><select id="genre" name="genre"
						value="${requestScope.performance.genreNo }">
							<option value="G001">연극</option>
							<option value="G002">뮤지컬</option>
							<option value="G003">콘서트</option>
					</select></td>
				</tr>
				<tr>
					<th>담당자</th>
					<td><input type="text" name="contactName" size="15"
						value="${requestScope.performance.contactName }"></td>
					<th>담당자 전화번호</th>
					<td><input type="text" name="contactNumber" size="15"
						value="${requestScope.performance.contactNumber }"></td>
				</tr>
				<tr>
					<th>런닝타임</th>
					<td><input type="text" name="runningTime" size="15"
						value="${requestScope.performance.runningTime }"></td>
					<th>비고</th>
					<td><input type="text" name="note" size="15"
						value="${requestScope.performance.note }"></td>
				</tr>
			</table>
		</div>
		<br>
		<button type="submit" id="btn1">등록</button>
		<button type="reset" id="btn2">취소</button>

		<br>

		<hr width="900" align="center" color="black" size="1">

		<div id="div2">
			<h2>상세설명</h2>
			<input type="file" name="detailFile" size="20"></input><br> <input
				type="file" name="detailFile" size="20"></input><br> <input
				type="file" name="detailFile" size="20"></input><br> <input
				type="file" name="detailFile" size="20"></input><br>

		</div>
		<br>

		<%-- 업로드 된 상세설명 목록 조회 --%>
		<table id="detailFileList" border="1">
			<c:forEach var="detailFile"
				items="${requestScope.detailFiles }" varStatus="loop">				
				<tr>
					<td>상세설명${pageScope.loop.count }</td>
					<td>${pageScope.detailFile.originalFileName }</td>
					<td><a href="#"  id="${pageScope.detailFile.fileNo }">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>