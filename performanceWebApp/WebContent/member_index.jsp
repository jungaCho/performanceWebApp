<%-- member_index.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>

<jsp:forward page ="/member_layout.jsp">
	<jsp:param name="nav" value="member_menu" />
	<jsp:param name="article" value="member_welcome"/>
</jsp:forward>