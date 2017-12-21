<%-- member_welcome.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>

<style>
	article {
		color: black;
	}
</style>

session : ${not empty sessionScope.member}
