<%-- admin_index.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>

<jsp:forward page ="/member_m_layout.jsp">
	<jsp:param name="nav" value="member_m_menu" />
	<jsp:param name="article" value="member_welcome"/>
</jsp:forward>