<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="domain.performance.PerformanceVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공연 검색</title>

<style>
/* form {
	padding: 30px;
	background-color: gray;
	width: 900px;
	height: 750px;
}
 */
body {
	color: gray;
}

#btn1 {
	width: 70px;
	height: 30px;
	margin-right: 10px;
}

#btn2 {
	width: 70px;
	height: 30px;
	margin-right: 10px;
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

#search {
	float: right;
	margin-right: 100px;
}

#schedule {
	float: right;
	margin-right: 235px;
}

#div3 {
	float: left;
	text-align: center;
}

a {
	color: gray;
}

#page {
	margin-top: 290px;
}


</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
    	  	
		$('#btn1').click(function(){
	      	$("input[name=check]:checked").each(function() {
	      		var checked = $(this).val();
	      		alert(checked);
			});
		});
		
		/* $('#btn1').click(function(){
			var param = "";
			$("input[name=check]:checked").each(function() {
				if
			}
		} */
    	  
    	  
            $("#btn3").on('click', function() {
                  $.ajax({
                        url : '${pageContext.request.contextPath}/admin_p_findPerformance.do',
                        method : 'GET',
                        dataType : 'json',
                        data : $('#search').serialize(),
                        success : function(data) {                            	  
                        	  $("#table").find('tr:not(:first)').remove();
                              var htmlStr = "";
                              for (var i = 0; i < data.length; i++) {
                                    htmlStr += "<tr>";
                                    htmlStr += "<td><input type='checkbox' name='check'></td>";
                                    htmlStr += "<td>" + data[i].pNo + "</td>";
                                    htmlStr += "<td><a href=/performanceWebApp/admin_p_detailPerformance.do?pNo="+data[i].pNo+">" + data[i].title + "</td>";
                                    htmlStr += "<td>" + data[i].startDate + "</td>";
                                    htmlStr += "<td>" + data[i].endDate + "</td>";
                                    htmlStr += "<td>" + data[i].genre + "</td>";
                                    htmlStr += "</tr>";
                                    $(htmlStr).appendTo("#table");
                                    htmlStr = "";
                              }
                             
                        },
                        error : function(jqXHR) {
                              alert('Error : ' + jqXHR.status);
                        }
                  });
            });
            
            $('#btn2').click(function(){
            	$("input[name=check]").prop("checked",false);
            });
      });
</script>

</head>
<body>

	<div id="pannel">
		<h1>공연 조회</h1>
		
		<button type="button" id="btn1">선택삭제</button>
		<button type="button" id="btn2">선택해제</button>
	</div>


	<form id="search">
		<select id="keyfield" name="keyfield">
			<option value="title">제목</option>
			<option value="date">월</option>
			<option value="genre">장르</option>
		</select> <input id="keyword" type="text" name="keyword" placeholder="검색어를 입력하세요">
		<button id="btn3" type="button">검색</button>
	</form>

	<br>
	<br>
	<div id="div3">
		<table id="table" border="1" width=670>
			<tr>
				<th>선택</th>
				<th>공연번호</th>
				<th>공연제목</th>
				<th>시작일</th>
				<th>종료일</th>
				<th>장르</th>
			</tr>
			<c:forEach var="performance" items="${requestScope.performances }"
				varStatus="loop">
				<c:url var="url" value="/admin_p_detailPerformance.do" scope="page">
					<c:param name="pNo" value="${pageScope.performance.pNo }" />
				</c:url>
				<tr>
					<td><input type="checkbox" name="check" value="${pageScope.performance.pNo }"></td>
					<td>${pageScope.performance.pNo }</td>
					<td><a href="${pageScope.url }">${pageScope.performance.title }</a></td>
					<td>${pageScope.performance.startDate }</td>
					<td>${pageScope.performance.endDate }</td>
					<td>${pageScope.performance.genre }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<br>
	&nbsp;
	
	<%-- 페이지 네비게이션 처리  --%>
	<form id="page">
        <c:if test="${requestScope.paging.prevPage >= 1 }">
                <c:url var="prevUrl" value="/admin_p_selectPerformanceList.do" scope="page">
                        <c:param name="currentPage" value="${requestScope.paging.prevPage }" />
                </c:url>
                <a href="${pageScope.prevUrl }">[이전]</a>&nbsp;&nbsp;
        </c:if>
        <c:if test="${requestScope.paging.prevPage < 1 }">
                [이전]&nbsp;&nbsp;
        </c:if>
        <c:forEach var="i" begin="${requestScope.paging.startPage }"
                        end="${requestScope.paging.endPage }" step="1">
                <c:if test="${requestScope.paging.currentPage == pageScope.i }">
                        ${pageScope.i } &nbsp;&nbsp;
                </c:if>
                <c:if test="${requestScope.paging.currentPage != pageScope.i }">
                        <c:url var="url" value="/admin_p_selectPerformanceList.do" scope="page">
                                <c:param name="currentPage" value="${pageScope.i }" />
                        </c:url>
                        <a href="${pageScope.url }">${pageScope.i }</a> &nbsp;&nbsp;                      
                </c:if>        
        </c:forEach>
        <c:if test="${requestScope.paging.endPage < requestScope.paging.totalPage }">
                <c:url var="nextUrl" value="/admin_p_selectPerformanceList.do" scope="page">
                        <c:param name="currentPage" value="${requestScope.paging.nextPage }" />
                </c:url>
                <a href="${pageScope.nextUrl }">[다음]</a>
        </c:if>
        <c:if test="${requestScope.paging.endPage >= requestScope.paging.totalPage  }">
                [다음]&nbsp;&nbsp;
        </c:if>
	</form>


</body>
</html>