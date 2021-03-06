<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 01.08.2021
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<body class="sub_page">

<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
    <c:set var="users" scope="request" value="${users}"/>
</div>

<section class="event_section layout_padding">
    <div class="container">

        <wrong-message>
            ${errorInputData}
            ${errorChangingRole}
            ${noUsers}
        </wrong-message>

        <div class="heading_container">
            <h3>
                <fmt:message key="header.users"/>
            </h3>

            <div style="display: flex;justify-content: center; margin-top: 20px">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/users.do" role="button">
                    <fmt:message key="button.allUsers"/> </a>
            </div>
            <hr style="width:100%;text-align:left;margin-left:0">
        </div>
        <div class="event_container">
            <table class="table table-dark table-bordered border-secondary">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="profile.username"/></th>
                    <th scope="col"><fmt:message key="profile.photo"/></th>
                    <th scope="col"><fmt:message key="profile.role"/></th>
                    <th scope="col">email</th>
                    <th scope="col"><fmt:message key="profile.status"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="loggingNotes" items="${users}">
                    <tr class="table-secondary">
                        <th scope="row"><a href="${pageContext.request.contextPath}/load_user_profile.do?userId=${loggingNotes.id}">
                                <ctg:out text="${loggingNotes.username}"/></a></th>
                        <th scope="row">
                            <div>
                                <img src="${pageContext.request.contextPath}/load_profile_photo.do?fileName=${loggingNotes.userDetails.photoPath}"
                                     alt="" style="max-height: 250px;max-width: 250px"></div>
                        </th>
                        <th scope="row">
                            <a href="${pageContext.request.contextPath}/find_users_by_role.do?userRole=${loggingNotes.role.toString()}">
                                <ctg:out text="${loggingNotes.role}"/><br>
                            </a>
                            <c:if test="${loggingNotes.role.toString().equals(reader)}"><br>
                                <a href="${pageContext.request.contextPath}/change_role_to_librarian.do?userId=${loggingNotes.id}"><fmt:message key="users.changeRole"/>
                                </a>
                            </c:if>
                            <c:if test="${loggingNotes.role.toString().equals(librarian)}"><br>
                                <a href="${pageContext.request.contextPath}/change_role_to_reader.do?userId=${loggingNotes.id}"><fmt:message key="users.changeRole"/></a>
                            </c:if>
                        </th>
                        <th scope="row"><ctg:out text="${loggingNotes.email}"/></th>
                        <th scope="row">
                            <a href="${pageContext.request.contextPath}/find_users_by_status.do?userStatus=${loggingNotes.status.value}">
                                <ctg:out text="${loggingNotes.status}"/>
                            </a>
                            <c:if test="${!loggingNotes.role.toString().equals(admin)}">
                                <c:if test="${loggingNotes.status.value.equals(activeStatus)}"><br>
                                    <a href="${pageContext.request.contextPath}/change_user_status.do?userId=${loggingNotes.id}&username=${loggingNotes.username}&userStatus=deactivated">
                                        <fmt:message key="users.deactivate"/></a>
                                </c:if>
                                <c:if test="${!loggingNotes.status.value.equals(activeStatus)}"><br>
                                    <a href="${pageContext.request.contextPath}/change_user_status.do?userId=${loggingNotes.id}&username=${loggingNotes.username}&userStatus=active">
                                        <fmt:message key="users.activate"/></a>
                                </c:if>
                            </c:if>
                        </th>
                    <tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

</body>
</html>
