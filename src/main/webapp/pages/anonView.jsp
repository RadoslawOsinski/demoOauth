<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html dir="ltr" lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="UTF-8">
</head>
<body>
   anon
    <a href="${pageContext.request.contextPath}/login">Logowanie link</a>
    <a href="${pageContext.request.contextPath}/user/main">user</a>
   -->${pageContext.request.userPrincipal.name}

</body>
</html>
