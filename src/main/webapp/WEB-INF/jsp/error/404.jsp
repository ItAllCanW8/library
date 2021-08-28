<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 28.08.2021
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body class="sub_page">
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
</div>

<h1 style="margin-top: 10%; display: flex; justify-content: center">
    <fmt:message key="error.status_404"/>
</h1>
<h1 style="margin-bottom: 5%; margin-top: 3%; display: flex; justify-content: center">
    <fmt:message key="error.status_404Msg"/></h1>
<div class="col-2 offset-5">
    <a class="btn btn-outline-secondary"
       style="width: 100%; display: flex; justify-content: center"
       href="${pageContext.request.contextPath}/home.do" role="button"><fmt:message
            key="header.home"/> </a>
</div>
</body>
</html>
