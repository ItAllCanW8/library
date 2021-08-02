<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 01.08.2021
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <!-- Basic -->
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <!-- Site Metas -->
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <title>Joson</title>

    <!-- bootstrap core css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>

    <!-- fonts style -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Poppins:400,600,700&display=swap"
          rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>
    <!-- responsive style -->
    <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet"/>

    <script src="http://code.jquery.com/jquery-3.6.0.min.js" type="text/javascript"></script>
    <script src="http://cdn.datatables.net/1.10.3/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <link href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>

    <script>
        $(document).ready(function () {
            $('#usersTable').DataTable();
        });
    </script>

</head>

<body class="sub_page">

<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
    <c:set var="users" scope="session" value="${users}"/>
</div>

<section class="event_section layout_padding">
    <div class="container">
        <div class="heading_container">
            <h3>
                <fmt:message key="header.users"/>
            </h3>
        </div>
        <div class="event_container">
            <table id="example" class="table table-dark table-bordered border-secondary">
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
                <c:forEach var="user" items="${users}">
                    <tr class="table-secondary">
                        <th scope="row"><a href="<c:url value="load_user_profile.do?userId=${user.id}"/>">
                                ${user.username}</a></th>
                        <th scope="row">
                            <div>
                                <img src="${pageContext.request.contextPath}/load_profile_photo.do?fileName=${user.userDetails.photoPath}"
                                     alt="" style="max-height: 250px;max-width: 250px"></div>
                        </th>
                        <th scope="row">${user.role}<br>
<%--                            <input type="hidden" name="userRole" value="${user.role}">--%>
                            <c:if test="${user.role.toString().equals(reader)}"><br>
                                <a href="change_role_to_librarian.do?userId=${user.id}"><fmt:message key="users.changeRole"/>
                                </a>
                            </c:if>
                            <c:if test="${user.role.toString().equals(librarian)}"><br>
                                <a href="change_role_to_reader.do?userId=${user.id}"><fmt:message key="users.changeRole"/></a>
                            </c:if>
                        </th>
                        <th scope="row">${user.email}</th>
                        <th scope="row">${user.status}
                            <c:if test="${user.status.value.equals('active')}"><br>
                                <a href="deactivate_user_account.do?userId=${user.id}"><fmt:message key="users.deactivate"/></a>
                            </c:if>
                            <c:if test="${user.status.value.equals('deactivated')}"><br>
                                <a href="activate_user_account.do?userId=${user.id}"><fmt:message key="users.activate"/></a>
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
<%--TODO: deal with jquery imports--%>
</html>
