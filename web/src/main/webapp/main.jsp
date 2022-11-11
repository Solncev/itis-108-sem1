<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<%
    String user = null;
    String sessionUser = (String) session.getAttribute("username");

    if (sessionUser == null) {
        response.sendRedirect("login.html");
    } else {
        user = sessionUser;
    }

    String cookieUser = null;
    String sessionId = null;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) cookieUser = cookie.getValue();
            if ("JSESSIONID".equals(cookie.getName())) sessionId = cookie.getValue();
        }
    } else {
        sessionId = session.getId();
    }
%>

<h3>
    Hello, <%=user%>! Login Successful!
    <br>
    Session ID = <%=sessionId%>
    <br>
    Cookie username = <%=cookieUser%>

</h3>

</body>
</html>
