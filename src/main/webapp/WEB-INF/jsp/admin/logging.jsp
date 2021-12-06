<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 29.10.2021
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body class="sub_page">

<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
    <c:set var="loggingNotes" scope="request" value="${loggingNotes}"/>
</div>

<section class="event_section layout_padding">
    <div class="container">
        <div class="heading_container">
            <h3>
                <%--                <fmt:message key="header.users"/>--%>
                Logging Notes
            </h3>

            <hr style="width:100%;text-align:left;margin-left:0">
        </div>
        <div class="event_container">
            <table class="table table-dark table-bordered border-secondary">
                <thead>
                <tr>
                    <th scope="col">Event</th>
                    <th scope="col">Datetime</th>
                    <th scope="col">User</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="loggingNotes" items="${loggingNotes}">
                <tr class="table-secondary">
                    <th scope="row"><ctg:out text="${loggingNotes.event}"/></th>
                    <th scope="row"><ctg:out text="${loggingNotes.dateTime}"/></th>
                    <th scope="row"><a
                            href="${pageContext.request.contextPath}/load_user_profile.do?userId=${loggingNotes.userIdFk}">
                        <ctg:out text="${loggingNotes.userIdFk}"/></a></th>
                <tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

</body>

</html>
