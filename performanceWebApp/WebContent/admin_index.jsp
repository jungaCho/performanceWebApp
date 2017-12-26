<%-- admin_index.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>

<jsp:forward page ="/admin_layout.jsp">
	<jsp:param name="nav" value="admin_p_menu" />
	<jsp:param name="article" value="admin_welcome"/>
</jsp:forward>