<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	height: 100%;
}

#div1 {
	width: 100%;
	height: 35px;
	background-color: #f25c5c;
	text-align: center;
}

li {
	display: inline-block;
	margin: 5px 10px;
	color:black;
}

a {
	text-decoration: none;
	cursor:pointer;
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
span {
	color: black;
	font-weight: bolder;
}
#datas {
	color: black;
}
#datas a{
	color:black;
}
#datas{
	color: black;
	margin: auto;
	
}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		
		$('.open').click(function() {
    	  	var p_no = $(this).attr('id');
    	  	var url = '${pageContext.request.contextPath}/member_r_reservationStart.do?pNo=' + p_no;
    	  	window.open(url, "예매확인","width=700, height=600");
        });
		
		$('#datas').on("click",'button',function() {
    	  	var p_no = $(this).attr('id');
    	  	var url = '${pageContext.request.contextPath}/member_r_reservationStart.do?pNo=' + p_no;
    	  	window.open(url, "예매확인","width=700, height=600");
        });
		
		$('#btnImage').on("click", function(){
			
			location.href = "${pageContext.request.contextPath}/member_p_selectPerformance.do";
			
		});
		
		$('#datas').on("click",'a',function(){
			var pNo=$(this).attr('id');
			location.href = "${pageContext.request.contextPath}/member_p_detailPerformance.do?pNo="+pNo;
		});
		
		/* $('#btn2').on("click",function(){
			location.href = "${pageContext.request.contextPath}/member_p_detailPerformance.do";
		}); */
		
		$('#btnText').on("click",function(){
		
		
		$.ajax({
			
			url: "${pageContext.request.contextPath}/member_r_selectpByview.do"
			,
			method : "POST"
			,
			dataType: 'json'
			,
			data: {	
				
				startRow: 1,
				endRow: 10,
				mode: $('#btnText').val(),
				genre: $('#genre:selected').val(),
				keyword: $('#keyword').val(),
				month: $('div1').find('li').val()			
				
			},
			
			success: function(data){
				
			/* 	$('#datas').find('th').end().find('td').css({
					border: "1px solid black"
				});   
				
				 */
				
				$('#datas').css({
					
						border: "1px solid black",
						
						width: "1200px",
																
				});   
				 
				
				
				$('#datas').find('tr').remove();
				
				
					
					
					var htmlStr = "";
					
					 for(var i=0; i<data.length; i++) {
											
						
								
						htmlStr += "<tr>";
						htmlStr += "<th id="+ data[i].title + ">" +"제목"+ "</th>";
						htmlStr += "<th>" +"기간"+ "</th>";
						htmlStr += "<th>" +"장소"+ "</th>";
						htmlStr += "<th>" +"예매"+ "</th>";
						htmlStr += "</tr>";
						
						htmlStr += "<tr>";
						htmlStr += "<td><a id="+data[i].pNo+" >" + data[i].title +"</a></td>";
						htmlStr += "<td>" + data[i].startDate + " ~ " + data[i].endDate + "</td>";
						htmlStr += "<td>" + data[i].tName + "</td>";
						
					    htmlStr += "<td>" + "<button type='button' id="+data[i].pNo+">예매하기</button>"+ "</td>";
						htmlStr += "</tr>";
						
								
						
						$(htmlStr).appendTo('#datas');
						
						htmlStr = "";
						 
						
					}
					 

					 $('#datas th').add('#datas td').css({
							border: "1px solid black",
							padding: "20px 0px",
							width: "300px"
						}); 
					  						 
					
			}
		
			,
			error: function(jaXHR){
				alert("error: " + jaXHR.error );
				
			}
			
		});
		
		});
		
		
		//뮤지컬 장르 선택
		
		$('#btnGenre').on("click",function(){
			
			
			
			$.ajax({
				
				url: "${pageContext.request.contextPath}/member_r_selectpByview.do"
				,
				method : "POST"
				,
				dataType: 'json'
				,
				data: {
					
					
					startRow: 1,
					endRow: 10,
					mode: $('#btnText').val(),
					genre: $('#genre').find('option:selected').val(),
					keyword: $('#keyword').val(),
					month: $('div1').find('li').val()			
					
				},
				
				success: function(data){
					

					$('#datas').css({
						
							border: "1px solid black",
							width: "1200px",
																	
					});   
				 
					 
										
					 $('#datas').find('tr').remove();
						
						var htmlStr = "";
						
						 for(var i=0; i<data.length; i++) {
												
							
									
							htmlStr += "<tr>";
							htmlStr += "<th id="+ data[i].title + ">" +"제목"+ "</th>";
							htmlStr += "<th>" +"기간"+ "</th>";
							htmlStr += "<th>" +"장소"+ "</th>";
							htmlStr += "<th>" +"예매"+ "</th>";
							htmlStr += "</tr>";
							
							htmlStr += "<tr>";
							htmlStr += "<td><a id="+data[i].pNo+">" + data[i].title +"</a></td>";
							htmlStr += "<td>" + data[i].startDate + " ~ " + data[i].endDate + "</td>";
							htmlStr += "<td>" + data[i].tName + "</td>";
							
						    htmlStr += "<td>" + "<button type='submit' id='btn2'>예매하기</button>"+ "</td>";
							htmlStr += "</tr>";
							
									
							
							$(htmlStr).appendTo('#datas');
							
							htmlStr = "";
							 
							
						}
						 
							$('#datas th').add('#datas td').css({
								border: "1px solid black",
								padding: "20px 0px",
								width: "300px"
							});   
						 
						  						 
						
				}
				,
				error: function(jaXHR){
					alert("error: " + jaXHR.error );
					
				}
				
			});
			
			});
		
		//검색
	
		
		$('#btn1').on("click",function(){
			
			
			
			$.ajax({
				
				url: "${pageContext.request.contextPath}/member_r_selectpByview.do"
				,
				method : "POST"
				,
				dataType: 'json'
				,
				data: {
					
					
					startRow: 1,
					endRow: 10,
					mode: $('#btnText').val(),
					keyword: $('#keyword').val(),
					month: $('div1').find('li').val()			
					
				},
				
				success: function(data){
					

					$('#datas').css({
						
							border: "1px solid black",
							width: "1200px",
																	
					});   
				 
					 
										
					 $('#datas').find('tr').remove();
						
						var htmlStr = "";
						
						 for(var i=0; i<data.length; i++) {
												
							
									
							htmlStr += "<tr>";
							htmlStr += "<th id="+ data[i].title + ">" +"제목"+ "</th>";
							htmlStr += "<th>" +"기간"+ "</th>";
							htmlStr += "<th>" +"장소"+ "</th>";
							htmlStr += "<th>" +"예매"+ "</th>";
							htmlStr += "</tr>";
							
							htmlStr += "<tr>";
							htmlStr += "<td><a id="+data[i].pNo+">" + data[i].title +"</a></td>";
							htmlStr += "<td>" + data[i].startDate + " ~ " + data[i].endDate + "</td>";
							htmlStr += "<td>" + data[i].tName + "</td>";
							
						    htmlStr += "<td>" + "<button type='submit' id='btn2'>예매하기</button>"+ "</td>";
							htmlStr += "</tr>";
							
									
							
							$(htmlStr).appendTo('#datas');
							
							htmlStr = "";
							 
							
						}
						 
						 $('#datas th').add('#datas td').css({
								border: "1px solid black",
								padding: "20px 0px",
								width: "300px"
							});   
						 
						  						 
						
				}
				,
				error: function(jaXHR){
					alert("error: " + jaXHR.error );
					
				}
				
			});
			
			});
		
		
		//달에 해당하는 공연정보 보여주기.

		$('#div1').find('ul.monthlist').find('li').on("click",function(){


		$.ajax({

		url: "${pageContext.request.contextPath}/member_r_selectpByview.do"
		,
		method : "POST"
		,
		dataType: 'json'
		,
		data: {


		startRow: 1,
		endRow: 10,
		mode: $('#btnText').val(),
		keyword: $('#keyword').val(),
		month: $(this).val()

		},

		success: function(data){
		
	
			if(data.length == 0) {
				
 				alert("해당 월에 조회가능한 공연정보가 없습니다!");
				
			}

			$('#datas').css({
				
				border: "1px solid black",
				width: "1200px",
														
		});   


		$('#datas').find('tr').remove();

		var htmlStr = "";

		for(var i=0; i<data.length; i++) {



		htmlStr += "<tr>";
		htmlStr += "<th id="+ data[i].title + ">" +"제목"+ "</th>";
		htmlStr += "<th>" +"기간"+ "</th>";
		htmlStr += "<th>" +"장소"+ "</th>";
		htmlStr += "<th>" +"예매"+ "</th>";
		htmlStr += "</tr>";

		htmlStr += "<tr>";
		htmlStr += "<td><a id="+data[i].pNo+">" + data[i].title +"</a></td>";
		htmlStr += "<td>" + data[i].startDate + " ~ " + data[i].endDate + "</td>";
		htmlStr += "<td>" + data[i].tName + "</td>";

		htmlStr += "<td>" + "<button type='submit' id='btn2'>예매하기</button>"+ "</td>";
		htmlStr += "</tr>";



		$(htmlStr).appendTo('#datas');

		htmlStr = "";


		}
		
		 $('#datas th').add('#datas td').css({
				border: "1px solid black",
				padding: "20px 0px",
				width: "300px"
			});   
		 



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
	
		<div id="div1">
			<ul class="monthlist">
				<li value="1"><a>1월</a></li>
				<li value="2"><a>2월</a></li>
				<li value="3"><a>3월</a></li>
				<li value="4"><a>4월</a></li>
				<li value="5"><a>5월</a></li>
				<li value="6"><a>6월</a></li>
				<li value="7"><a>7월</a></li>
				<li value="8"><a>8월</a></li>
				<li value="9"><a>9월</a></li>
				<li value="10"><a>10월</a></li>
				<li value="11"><a>11월</a></li>
				<li value="12"><a>12월</a></li>
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
				
				<button type="button" id="btnGenre" value="text">만 보기</button>
				
			</div>
		</div>
		
		
		<br><br>
		<div id="div3">
        	<table id="datas">
        		<tr>
        			<c:forEach var="poster" items="${requestScope.posters}" varStatus="loop">     
        					<c:url var="url" value="/member_p_detailPerformance.do" scope="page">
                                         <c:param name="pNo" value="${pageScope.poster.pNo }" />
                             </c:url>  			        			
        				<td>
        					<a href="${pageScope.url}">
        					<img src="${pageContext.request.contextPath }/upload/${pageScope.poster.systemFileName }"
        					 width="200" height="200"></a><br>
        					<span>${pageScope.poster.title }</span><br>
        					<button class="open" id="${pageScope.poster.pNo }" type="button">예매</button>
        				</td>
        				<c:if test="${loop.count % 4  == 0 }">
        					</tr><tr>
        				</c:if>
        					        			
        		    </c:forEach>
        		</tr>
            </table>
       </div>       
                 
</body>
</html>





