<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>예매</title>
<style>
	#page{font-size:15px; text-align:center;}
	.reservation {
	       overflow: hidden;
	       margin-top: 30px;
	       height: 100%;
	       margin-bottom:30px;
	}
	
	.reservation>.r_img {
	       float: left;
	       width: 90px;
	       height: 110px;
	       background: #000;
	}
	
	.reservation>.r_img>a {
	       display: inline-block;
	       width: 90px;
	       height: 110px;
	}
	
	.reservation>.r_text {
	       float: left;
	}
	
	.reservation>.r_text>ul {
	       margin: 0 auto;
	}
	
	.reservation>.r_text>ul>li {
	       list-style: none;
	       font-size: 15px;
	}
	
	li{line-height:35px;}
	
	h1{font-size:15px;}
	
	.reservation>.r_text>ul>.r_text_date {
	       margin-top: 15px;
	       color: #5D5D5D;
	}
	
	.reservation>.r_text>ul>.r_text_title {
	       font-weight: bold;
	}
	
	.reservation>.r_text>ul>.r_text_button {
	       margin-top: 14px;
	       width: 80px;
	       height: 30px;
	       background: gray;
	       text-align: center;
	       line-height: 30px;
	       border-radius: 5px;
	}
	
	.reservation>.r_text>ul>.r_text_button>a {
	       width: 80px;
	       height: 30px;
	       display: inline-block;
	       line-height: 30px;
	       text-decoration: none;
	       font-size: 15px;
	}
	
	.reservation>.r_text>ul>.r_text_button>a:hover {
	       color: #fff;
	}
	#ul{
		display:inline-block;
		list-style:none;
		color:gray;
		margin-left:30px;
	}
	.open{
		margin-top:10px;
	 	-webkit-appearance: button;
	      -moz-appearance: button;
	      appearance: button;
	      color:white;
	      background:blue;
	      width:80px;
	      height:25px;
	      line-height:25px;
	      text-align:center;
	      cursor:pointer;
	      font-size:15px;

	}
	
	a:hover{color:#fff;}
	
	.r_text_date{font-size:15px;}
	

		.wrap{padding:50px;}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
       $(document).ready(function() {
              $('.open').click(function() {
            	  	var p_no = $(this).attr('id');
            	  	var url = '${pageContext.request.contextPath}/member_r_reservationStart.do?pNo=' + p_no;
            	  	window.open(url, "예매확인","width=700, height=600");
              });
       });
</script>
</head>
<body>
	<div class="wrap">
       <div style="margin-left: 50px;">
              <h3>예매</h3><br>
              <span style="font-size: 17px;">공연 예매</span> <br>
              <!-- reservation -->
         
             
                     <!-- r_img -->
                    
                           <c:forEach var="performance" items="${requestScope.performances }"
                                  varStatus="loop">
                                  <c:url var="url" value="/member_p_detailPerformance.do" scope="page">
                                         <c:param name="pNo" value="${pageScope.performance.pNo }" />
                                  </c:url>
                                   <div class="reservation">
                                  <c:forEach var="poster" items="${pageScope.performance.posters}">
                                         <c:if test="${pageScope.poster.mainPoster == 1}">
                                            <a href="${pageScope.url}"><img
                                                       src="${pageContext.request.contextPath}/upload/${pageScope.poster.systemFileName}.jpg"
                                                       width="100px" height="150px" id="img"></a>
                                         </c:if>
                                    </c:forEach>     
                                          <ul id="ul">
			                                  <!-- r_text_title -->
			                                  <li class="r_text_title"><h1><bold>공연명 : ${pageScope.performance.title }</bold></h1></li>
			                                  <!-- t_text_date -->
			                                  <li class="r_text_date">기간 :
			                                         ${pageScope.performance.startDate}~${pageScope.performance.endDate }<br>
			                                         가격 : ${pageScope.performance.price}원
			                                  </li>
			                                  <!-- r_text_button -->
			                                  <li class="r_text_button">
			                                  	<a class="open" id="${pageScope.performance.pNo}">예매하기</a></li>
			                           </ul><br>
                                   </div>
                           </c:forEach>
                   
                     <!-- r_text -->
                   
             
           
              <!-- /reservation -->

       </div>

       <%-- 페이지 네비게이션 처리  --%>
       <form id="page">
              <c:if test="${requestScope.paging.prevPage >= 1 }">
                     <c:url var="prevUrl" value="/member_r_layout.do"
                           scope="page">
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
                           <c:url var="url" value="/member_r_layout.do"
                                  scope="page">
                                  <c:param name="currentPage" value="${pageScope.i }" />
                           </c:url>
                           <a href="${pageScope.url }">${pageScope.i }</a> &nbsp;&nbsp;
                </c:if>
              </c:forEach>
              <c:if
                     test="${requestScope.paging.endPage < requestScope.paging.totalPage }">
                     <c:url var="nextUrl" value="/member_r_layout.do"
                           scope="page">
                           <c:param name="currentPage" value="${requestScope.paging.nextPage }" />
                     </c:url>
                     <a href="${pageScope.nextUrl }">[다음]</a>
              </c:if>
              <c:if
                     test="${requestScope.paging.endPage >= requestScope.paging.totalPage  }">
                [다음]&nbsp;&nbsp;
        </c:if>
       </form>
	</div>
</body>
</html>