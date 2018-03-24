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

<b>Strona signing XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</b>
<a href="<c:url value="/connect/twitter" />">Connect to Twitter</a>

<form action="<c:url value="/connect/twitter" />" method="POST">
    <p>You haven't created any connections with Twitter yet. Click the button to create
        a connection between your account and your Twitter profile.
        (You'll be redirected to Twitter where you'll be asked to authorize the connection.)</p>
    <p>
        <button type="submit"><img src="https://help.twitter.com/content/dam/help-twitter/brand/logo.png"/>
        </button>
    </p>
</form>

<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
------------------------------------------
------------------------------------------
<br/>
<p><a href="<c:url value="/auth/twitter"/>"><img
        src="https://1.bp.blogspot.com/-b_SfXKvo7uQ/WR5lIAr3LFI/AAAAAAAABFk/Y94kiT6-DeM1zb5glYZN9fOt42wMRsmvACLcB/s1600/twitter_login_button.png"
        border="0"/></a></p>
<p><a href="<c:url value="/auth/facebook"/>"><img src="https://i.stack.imgur.com/LKMP7.png" border="0"/></a></p>
<br/>
<br/>
------------------------------------------
<br/>
-->${pageContext.request.userPrincipal.name}

</body>
</html>
