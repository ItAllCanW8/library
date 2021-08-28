<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 10.08.2021
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body class="sub_page">

<div class="hero_area">
  <%@ include file="../components/header.jsp" %>
</div>

<h1 style="margin-top: 10%; display: flex; justify-content: center">
  <fmt:message key="error.status_500"/>
</h1>
<h1 style="margin-bottom: 5%; margin-top: 3%; display: flex; justify-content: center">
  <fmt:message key="error.status500Msg"/></h1>

<h5 style="margin-bottom: 5%; margin-top: 3%; display: flex; color: red; justify-content: center">
  ${errorMsg}
</h5>

<div class="col-2 offset-5">
  <a class="btn btn-outline-secondary"
     style="width: 100%; display: flex; justify-content: center"
     href="${pageContext.request.contextPath}/home.do" role="button">
    <fmt:message key="header.home"/>
  </a>
</div>

</body>
</html>
