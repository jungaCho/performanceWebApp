<%--error.jsp --%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%--@ page isErrorPage="true" : 필요없음, exception객체를 page영역에 바인딩 할 수 없으므로--%>
<%@ page import="java.io.*" %>
Error :<br>
<%
	Exception exception = (Exception)request.getAttribute("exception");
		
	PrintWriter s = new PrintWriter(out); // jsp 내장객체 out, 화면에 출력객체
	exception.printStackTrace(s);
%>
