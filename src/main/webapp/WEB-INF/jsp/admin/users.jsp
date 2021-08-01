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
                    <th scope="col">email</th>
                    <th scope="col">
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#changeStatusModal">
                            <fmt:message key="profile.status"/>
                        </button></th>
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
                            <th scope="row">${user.email}</th>
                            <th scope="row">${user.status}</th>
                        <tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="modal fade" id="changeStatusModal" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title"><fmt:message key="button.deactivateAccount"/></h4>
                        </div>

                        <div class="modal-body">
                            <form id="deactivateForm" method="POST" action="deactivate_account.do">
                                <div class="mt-1">
                                    <h5><fmt:message key="profile.deactivateMsg"/></h5>
                                </div>
                                <div class="mt-3">
                                    <label for="inputCurrentPassword2"><fmt:message
                                            key="profile.currentPassword"/> </label>
                                </div>
                                <div class="form-group mt-1">
                                    <input type="password" class="form-control field" id="inputCurrentPassword2"
                                           name="password"
                                           placeholder=
                                                   "<fmt:message key="register.inputPasswordPlaceholder"/>"
                                           required
                                           pattern="[а-яА-Я\w\s\p{Punct}]{6,80}">
                                </div>
                            </form>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                <fmt:message key="button.close"/>
                            </button>
                            <button type="submit" class="btn btn-primary" form="deactivateForm">
                                <fmt:message key="button.deactivate"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
<%--TODO: deal with jquery imports--%>
</html>
