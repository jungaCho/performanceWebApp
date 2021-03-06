<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>일정 등록</title>

<style>
form {
	color: gray;
	width: 900px;
	height:100%;
}
input{height:20px; margin-top:5px; margin-left:15px;}
select{height:20px;  margin-top:5px; margin-left:15px;}
#btn1 {
	display: inline-block;
    text-align: center;
    vertical-align: middle;
    text-decoration: none;
    font-size: 12px;
    color: #000;
    /* border: 1px solid #000; */
    width:65px;
    height: 30px;
    line-height: 30px;
    color: white;
    background-color: blue;
    border-radius: 5px;
}

#btn2 {
	display: inline-block;
    text-align: center;
    vertical-align: middle;
    text-decoration: none;
    font-size: 12px;
    color: #000;
    /* border: 1px solid #000; */
    width:65px;
    height: 30px;
    line-height: 30px;
    color: white;
    background-color: gray;
    border-radius: 5px;
}

#btn3 {
	display: inline-block;
    text-align: center;
    vertical-align: middle;
    text-decoration: none;
    font-size: 12px;
    color: #000;
    /* border: 1px solid #000; */
    width:65px;
    height: 30px;
    line-height: 30px;
    color: white;
    background-color: blue;
    border-radius: 5px;
}

#btn4 {
	margin-left:10px;
	display: inline-block;
    text-align: center;
    vertical-align: middle;
    text-decoration: none;
    font-size: 12px;
    color: #000;
    /* border: 1px solid #000; */
    width:65px;
    height: 30px;
    line-height: 30px;
    color: white;
    background-color: gray;
    border-radius: 5px;
}

h2 {
	color:#000;
}

#div1 {
	margin-bottom: 20px;
	text-align: left;
	margin-top:30px;
	font-size:15px;
	line-height:30px;

}

#div1>#btn2 {
	margin-left: 10px;
}

#div2 {
	margin-top: 20px;
	text-align: left;
	font-size:15px;
}

.insertTable th{background: #ddd;
    width: 120px;
    font-size: 15px;
    border-bottom: 1px solid gray;
    height: 30px;
    line-height: 30px;}
    
.insertTable td{font-size:15px; background:#f0f0f0;border-bottom:1px solid gray;  height: 30px;
    line-height: 30px; }    
.content1{overflow:hidden}
#pannel{font-size: 15px; color: #000;}
.title{float:left; font-size:15px; color:#000;}
.wrap{padding:50px;}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		
		
		
		var sDateList = [];   //공연 일자
		var oTimeList = [];   //공연 회차
		
		$("#btn1").on('click', function() {

			var sDate = $('#sDate').val();
			var oTime1 = $('#oTime1').val();
			var oTime2 = $('#oTime2').val();
			

			
			if(sDate != "" && oTime1 != "") {
				if(oTime2!=""){
					$('#table1').append("<tr><td id='td1'>"+ sDate + "</td><td id='td2'>"+ oTime1 + " , " + oTime2 + "</td></tr>");
					sDateList.push(sDate);
					oTimeList.push(oTime1 + "," + oTime2 );
				}else{
					$('#table1').append("<tr><td id='td1'>"+ sDate + "</td><td id='td2'>"+ oTime1 + "</td></tr>");
					sDateList.push(sDate);
					oTimeList.push(oTime1);
				}
				
			} 
			
		});
		
	
		$("#btn3").on('click', function() {
			 
			 console.log('tNo : ' + $('#tNo option:selected').val());
			 //console.log('pNo : ');
			 console.log('sDate : ' + sDateList.join("/"));
			 console.log('oTime : ' + oTimeList.join("/"));

			 $.ajax({
				url: '${pageContext.request.contextPath}/admin_p_insertSchedule.do'
				,
				method: 'GET'
				,
				dataType: 'json'
				,
				data: {
					tNo: $('#tNo option:selected').val(),
					pNo: $('#pNo option:selected').val(),
					sDate: sDateList.join("/"),
					oTime: oTimeList.join("/")
				}
				,
				success: function(data) {
					if(data.isSuccess=='true'){
						alert("일정이 등록되었습니다!!");
						location.href="${pageContext.request.contextPath}/admin_p_selectPerformanceList.do";
					}
				}
				,
				error: function(jqXHR) {
					alert("error : " + jqXHR.status);
				}
				
			 });
			
		});
		
		$('#btn4').click(function() {
 			location.href="${pageContext.request.contextPath}/admin_p_selectPerformanceList.do";
 	  });
	});
</script>

</head>
<body>
	<div class="wrap">
	<form method="get" action="${pageContext.request.contextPath}/admin_p_insertSchedule.do" >

		<div id="pannel">
			<h2>공연 일정 등록</h2>
		</div>

		<div id="div1">
			공연장소 : <select id="tNo" name="tNo">
				<option value="T001">소극장</option>
				<option value="T002">대극장</option>
				<option value="T003">콘서트홀1</option>
				<option value="T004">콘서트홀2</option>
				<option value="T005">뮤지컬관</option>
			</select><br>
			공연제목 : <select id="pNo" name="pNo">
				<c:forEach var="performances" items="${requestScope.performances}" varStatus="loop">
					<option value="${pageScope.performances.pNo }" }>${pageScope.performances.title}</option>
				</c:forEach>
			</select><br>
			공연일자 : <input type="date" id="sDate" name="sDate" size="30"></input><br>
			공연시간 : <input type="text" id="oTime1" name="oTime1" size="10" placeholder="시간과 분을 붙여"></input>
					   <input type="text" id="oTime2" name="oTime2" size="10" placeholder="쓰세요ex)15:30"></input>
					 
			<br> <br>
			<button type="button" id="btn1">확인</button>
			<button type="reset" id="btn2">취소</button>
			<br> <br>

			<hr width="600" align="center" color="black" size="1">
		</div>

		<div id="div2">			
			<table border="1" width=400 id="table1">
				<tr>
					<th>일자</th>
					<th>시간</th>
				</tr>
				<!-- <tr id="tr">
					<td id="sDate1"></td>
					<td id="oTime1"></td>
				</tr> -->
			</table>
		</div>
		<br>
		<button type="button" id="btn3">등록</button>
		<button type="reset" id="btn4">뒤로가기</button>
	</form>
	</div>
</body>
</html>