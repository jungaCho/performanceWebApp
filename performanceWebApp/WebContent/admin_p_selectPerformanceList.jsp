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

#body{
	padding-left:30px;
}
#body2{
	padding-left:30px;
	overflow:hidden;
}
#btn1 {
	width: 70px;
    height: 30px;
    margin-right: 10px;
    font-size: 15px;
    background: blue;
    color: white;
    border-radius: 5px;
}

#btn2 {
	width: 70px;
    height: 30px;
    margin-right: 10px;
    font-size: 15px;
    background: gray;
    color: white;
    border-radius: 5px;
}

#btn3 {
	width: 70px;
    height: 30px;
    margin-right: 10px;
    font-size: 15px;
    background: gray;
    color: white;
    border-radius: 5px;
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

a:hover{
	color: black;
	text-decoration: none;
}


#table {
	color:black;
	border: 1px solid;
	font:15px;
	width:900px;
	font-size:15px;
	
}
tr{height:40px;}

td{font-size:15px; line-height:40px;}
td a{font-size:15px; line-height:40px; font-weight:100; color:black;}
#table th{
	background-color:#FFBB00;
	font-size:15px;
	line-height:40px;
	
}
input{height: 30px;
    font-size: 15px;
    border: 1px solid #ddd;}

.wrap{padding:50px;}

section article{height:100%; min-height:600px;}

select{font-size: 15px;
    height: 30px;
    border: 1px solid #ddd;}

#page{text-align:center; font-size:15px;}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
      $(document).ready(function() {
   
			$('#btn1').on("click",function(){
				
				//string[] 공연번호를 배열로 넘긴다.

				var pNoArrays = [];
				
				$('#checkSelect:checked').each(function(){
				
					pNoArrays.push($(this).val());		
				});	
				
				if(confirm("선택한 공연을 삭제하시겠습니까?")==true){
					location.href= "${pageContext.request.contextPath}/admin_p_removePerformanceList.do?checked="+pNoArrays;
				}else{
					return;
				}
			});
			
			$('#btn2').click(function(){
            	$("input[name=check]").prop("checked",false);
            });
    	  
    	  
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
      });
</script>

</head>
<body>
	<div class="wrap">
	<form id="body">
	<div id="pannel">
		<h1>공연 조회</h1><br>
		
		<button type="button" id="btn1">선택삭제</button>
		<button type="button" id="btn2">선택해제</button>
	


	<form id="search">
		<select id="keyfield" name="keyfield">
			<option value="title">제목</option>
			<option value="date">월</option>
			<option value="genre">장르</option>
		</select> <input id="keyword" type="text" name="keyword" placeholder="검색어를 입력하세요">
		<button id="btn3" type="button">검색</button>
	</form>
	</div>
</form>
	<br>
	<br>
	<form id="body2">
	<div id="div3">

	<input type="hidden" value="${pageScope.performance.pNo }">
		<table id="table"  cellspacing="0" cellpadding="0">
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
					<td><input type="checkbox" name="check" value="${pageScope.performance.pNo }" id="checkSelect"></td>
					<td>${pageScope.performance.pNo }</td>
					<td><a href="${pageScope.url }">${pageScope.performance.title }</a></td>
					<td>${pageScope.performance.startDate }</td>
					<td>${pageScope.performance.endDate }</td>
					<td>${pageScope.performance.genre }</td>
				</tr>
			</c:forEach>
		</table>
		
	</div>
	</form>
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
	</div>

</body>
</html>