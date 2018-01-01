<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>예매</title>
<style>
body {
	margin: 0;
	padding: 0;
	background: #ddd;
}

.title_bg {
	width: 700px;
	height: 40px;
	font-size: 25px;
	color: #fff;
	background: gray;
	margin: 0;
	padding: 0;
	text-align: center;
}

.reservation_padding {
	padding: 10px;
	overflow: hidden;
}

.reservation_content01 {
	float: left;
}

.reservation_content01>dl {
	width: 350px;
}

.reservation_content01>dl>dt {
	height: 340px;
	background: #fff;
}

.reservation_content02 {
	float: left;
}

.reservation_title {
	height: 30px;
	line-height: 25px;
	background: gray;
	text-align: center;
	font-size: 18px;
	color: #fff;
	width: 100%;
	margin: 0;
}

.reservation_text {
	font-size: 13px;
	text-align: center;
	margin: 0;
	width: 100%;
	margin-top: 5px;
}

.reservation_content02 {
	width: 200px;
	margin-left: 20px;
	margin-top: 15px;
}

#closeBtn {
	width: 80px;
	background: gray;
	color: #fff;
	display: inline-block;
	height: 50px;
	text-align: center;
	line-height: 50px;
	text-decoration: none;
	border-radius: 5px;
}

#selectBtn {
	width: 80px;
	background: #368AFF;
	color: #fff;
	display: inline-block;
	height: 50px;
	text-align: center;
	line-height: 50px;
	text-decoration: none;
	border-radius: 5px;
}

a:hover {
	color: #fff;
}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		 
		
		$('#closeBtn').click(function() {
			close();
		});
		
		
		$('#sDate').on('change',  function(){
			    
				 $.ajax({

					url : "${pageContext.request.contextPath}/order.do"
					,
					method : "GET" 
					,
					dataType : "json"
					,
					data : {
						pNo: '${param.pNo}' ,
						sNo : $(this).find('option:selected').val()
					}
					,
					success : function(data){
						 $('#oTime').empty(); 
						
						var htmlStr = "";

						for(var i = 0; i<data.length; i++){							
							htmlStr  += "<option value= " + data[i].oNo + ">" + data[i].oTime + "</option>";		
							console.log(i);
							$('#oTime').append(htmlStr);
							htmlStr = "";
						};									
					}
					,
					error : function(jqXHR){
						alert('Error : ' + jqXHR.status);
					}

				});
	 
		}); 
		
		

		$('#selectBtn').click(function(){	
			 var oNo =	 $('#oTime').find('option:selected').val();
			 var oTime = $('#oTime option:selected').text();
			 var sDate = $('#sDate option:selected').text();
			 var fileImg = $('img').attr('src');
			 
			 console.log("oNo :" + oNo);
			 console.log("fileImg : " + fileImg);
			$(location).attr('href', '${pageContext.request.contextPath}/member_r_reservationStart2.do?pNo=${param.pNo}&tNo=${requestScope.performance.tNo}&oNo='+oNo+'&oTime='+oTime+'&title=${requestScope.performance.title}&sDate='+sDate);
	
		}); 
		
 	
	});
</script>
</head>
<body>
		<div class="title_bg">예 매</div>
		<div class="reservation_padding">
			<div class="reservation_content01">
				<%-- <c:if test="${fn:length(requestScope.performance.posters) > 0 }"> --%>
					<dl>
						<dt>
							<c:forEach var="poster" items="${requestScope.performance.posters}" >
								<c:if test="${pageScope.poster.mainPoster == 1}">
									<img src="${pageContext.request.contextPath}/upload/${pageScope.poster.systemFileName}" 
											width="350px" height="340px" id="img">
								</c:if>
							</c:forEach>

						</dt>
						<dd class="reservation_title">${requestScope.performance.title}</dd>
						<dd class="reservation_text">※만석일 경우, 공연시간이 선택되지 않습니다.</dd>
					</dl>
				<%-- </c:if> --%>
			</div>
			<div class="reservation_content02">
				<table>
					<tr>
						<td>공연장소</td>
						<td>${requestScope.performance.tName}</td>
					</tr>
					<tr>
						<td>금 액</td>
						<td>${requestScope.performance.price}원</td>
					</tr>


					<tr>
						<td>공연일</td>
						<td>
							<select name="sDate"   id="sDate">
								<c:forEach var="scheduleSdate" items="${requestScope.performance.schedules }">
									<option value="${scheduleSdate.sNo}">${pageScope.scheduleSdate.sDate}</option>
								</c:forEach>
							</select>
						</td>
					</tr>

					<tr>
						<td>공연시간</td>
						<td>
							<select name="oTime" id="oTime">
								<c:forEach var="schedule" items="${requestScope.performance.schedules }" varStatus="loop">
									<c:forEach var="orders" items="${pageScope.schedule.orders}" > 	
										<option value="${orders.oNo}">${pageScope.orders.oTime }</option>	
									</c:forEach>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td><a href="#" id="closeBtn">닫기</a></td>
						<td>
							<%-- <c:url var="url" value="/member_r_reservationStart2.do">
								<c:param name="tNo" value="${requestScope.performance.tNo}" />
								<c:param name="pNo" value="${param.pNo }" />
							</c:url> --%>
							<a href="#" id="selectBtn">좌석선택</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
</body>
</html>










