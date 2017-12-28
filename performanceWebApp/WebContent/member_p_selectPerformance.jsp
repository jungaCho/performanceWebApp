<%@page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8>
<title>공연 이미지 조회 페이지</title>
<style>
form {
	padding: 30px;
	color: gray;
	width: 100%;
	height: 750px;
}

#div1 {
	width: 100%;
	height: 35px;
	background-color: yellow;
	text-align: center;
}

li {
	display: inline-block;
	margin: 5px 10px;
	color:black;
}

a {
	text-decoration: none;
}

#div2 {
	overflow: hidden;
}

#div2-1 {
	margin: 15px 150px;
	text-align: center;
	float: left;
	width: 65%;
}

#div2-2 {
	float: right;
	width: 35%;
	font-size: 15px;
}

#div2-2>ul {
	overflow: hidden;
}

#div2-2>ul li {
	display: inline-block;
	float: left;
}

#div3 {
	text-align: center;
}

#keyword {margin-le
	
}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		
		
		$('#btnText').on("click",function(){
		
			
		
		$.ajax({
			
			url: "${pageContext.request.contextPath}/member_r_selectpByview.do"
			,
			method : "POST"
			,
			dataType: 'json'
			,
			data: {
				
				sortkey: $('#view').find('option:selected').val(),
				startRow: 1,
				endRow: 10,
				mode: $('#btnText').val(),
				genre: $('#genre:selected').val(),
				keyword: $('#keyword').val(),
				month: $('div1').find('li').val()			
				
			},
			
			success: function(data){
				
					$('#datas').find('tr').remove(); 
					
					var htmlStr = "";
					
					 htmlStr += "<table id='table' border='1' width=600>"; 
					
					 for(var i=0; i<data.length; i++){
											
						
						htmlStr += "<tr>";
						htmlStr += "<th id="+ data[i].title + ">" +"제목"+ "</th>";
						htmlStr += "<th>" +"기간"+ "</th>";
						htmlStr += "<th>" +"장소"+ "</th>";
						htmlStr += "<th>" +"예매"+ "</th>";
						htmlStr += "</tr>";
						
						htmlStr += "<tr>";
						htmlStr += "<td>" + data[i].title +"</td>";
						htmlStr += "<td>" + data[i].startDate + " ~ " + data[i].endDate + "</td>";
						htmlStr += "<td>" + data[i].tName + "</td>";
						
					    htmlStr += "<td>" + "<button type='submit' id='btn2'>예매하기</button>"+ "</td>";
						htmlStr += "</tr>";
								
						
						$(htmlStr).appendTo('#datas');
						
						htmlStr = "";
						
					}
					 
				htmlStr += "</table>"; 
					
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
	<form>
		<div id="div1">
			<ul>
				<li value="month"><a href='#'>1월</a></li>
				<li value="month"><a href='#'>2월</a></li>
				<li value="month"><a href='#'>3월</a></li>
				<li value="month"><a href='#'>4월</a></li>
				<li value="month"><a href='#'>5월</a></li>
				<li value="month"><a href='#'>6월</a></li>
				<li value="month"><a href='#'>7월</a></li>
				<li value="month"><a href='#'>8월</a></li>
				<li value="month"><a href='#'>9월</a></li>
				<li value="month"><a href='#'>10월</a></li>
				<li value="month"><a href='#'>11월</a></li>
				<li value="month"><a href='#'>12월</a></li>
			</ul>
		</div>
		<div id="div2">
			<div id="div2-1">
				<input id="keyword" type="text" placeholder="검색어를 입력하세요">
				<button id="btn1" type="button">검색</button>
			</div>
			<div id="div2-2">
				<button type="button" id="btnImage" value="image">이미지보기</button>
				<button type="button" id="btnText" value="text">텍스트보기</button>
				<select id="genre">
					<option value="뮤지컬">뮤지컬</option>
					<option value="연극">연극</option>
					<option value="콘서트">콘서트</option>
				</select>
			</div>
		</div>
		<br> <br>
		<div class="reservation">
                     <!-- r_img -->
                    
                           <c:forEach var="performance" items="${requestScope.performances }"
                                  varStatus="loop">
                                  <c:url var="url" value="/member_p_detailPerformance.do" scope="page">
                                         <c:param name="pNo" value="${pageScope.performance.pNo }" />
                                  </c:url>
                                  <c:forEach var="poster" items="${pageScope.performance.posters}">
                                         <c:if test="${pageScope.poster.mainPoster == 1}">
                                            <a href="${pageScope.url}"><img
                                                       src="${pageContext.request.contextPath}/upload/${pageScope.poster.systemFileName}.jpg"
                                                       width="100px" height="150px" id="img"></a>
                                         </c:if>
                                    </c:forEach>     
                                          <ul id="ul">
			                                  <!-- r_text_title -->
			                                  <li class="r_text_title"><h1><a href="${pageScope.url}"><bold>공연명 : ${pageScope.performance.title }</bold></a></h1></li>
			                                  <!-- t_text_date -->
			                                  
			                                  <!-- r_text_button -->
			                                  <li class="r_text_button">
			                                  	<a href="${pageContext.request.contextPath}/member_r_reservationStart.do?pNo=${pageScope.performance.pNo}" id="open">예매하기</a></li>
			                           </ul><br>
                                  
                           </c:forEach>
                   
                     <!-- r_text -->
                   
              </div>
	</form>
</body>
</html>