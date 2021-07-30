<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 28.07.2021
  Time: 15:56
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
</head>

<body class="sub_page">
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
    <%--    <c:set var="user" scope="request" value="${user}"/>--%>
</div>

<!-- about section -->
<section class="about_section layout_padding">
    <div class="side_img">
        <img src="${pageContext.request.contextPath}/images/layout/side-img.png" alt=""/>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="img_container">
                    <div class="img-box b1">
                        <img src="${pageContext.request.contextPath}/load_profile_photo.do?fileName=${sessionScope.user.userDetails.photoPath}"
                             alt=""/>
                    </div>
                    <form action="upload_photo.do" method="post" enctype="multipart/form-data">
                        <input type="file" name="file" class="form-control-file"/>
                        <input type="submit" class="btn btn-outline-secondary" value="Upload"/>
                    </form>
                </div>
            </div>
            <div class="col-md-6">
                <div class="detail-box">
                    <div class="heading_container">
                        <h3>${username}</h3>
                        <h4>${email}</h4>
                        <hr style="width:100%;text-align:left;margin-left:0">
                        <h5>Name: ${userDetails.name}</h5>
                        <h5>Surname: ${userDetails.surname}</h5>
                        <h5>Date of birth: ${userDetails.dateOfBirth}</h5>
                        <h5>Phone number: ${userDetails.phoneNumber}</h5>
                    </div>

                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editProfileModal">
                        <fmt:message key="button.edit"/>
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="editProfileModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    </button>
                                </div>

                                <div class="modal-body">
                                    <form method="POST" action="edit_user_profile.do" id="editForm">
                                        <label for="inputUsername"><fmt:message key="register.inputUsername"/> </label>
                                        <div class="form-group mt-1">
                                            <input type="text" class="form-control field" id="inputUsername"
                                                   name="username"
                                                   value="${username}"
                                                   placeholder=
                                                           "<fmt:message key="register.inputNamePlaceholder"/>" required
                                                   pattern="[a-zA-Zа-яА-Я]{3,35}">
                                        </div>

                                        <label for="inputName"><fmt:message key="register.inputName"/> </label>
                                        <div class="form-group mt-1">
                                            <input type="text" class="form-control field" id="inputName"
                                                   name="name"
                                                   value="${userDetails.name}"
                                                   placeholder=
                                                           "<fmt:message key="register.inputNamePlaceholder"/>" required
                                                   pattern="[a-zA-Zа-яА-Я]{3,35}">
                                        </div>

                                        <div class="mt-3">
                                            <label for="inputSurname"><fmt:message
                                                    key="register.inputSurname"/> </label>
                                        </div>
                                        <div class="form-group mt-1">
                                            <input type="text" class="form-control field" id="inputSurname"
                                                   name="surname"
                                                   value="${userDetails.surname}"
                                                   placeholder=
                                                           "<fmt:message key="register.inputNamePlaceholder"/>" required
                                                   pattern="[a-zA-Zа-яА-Я]{3,35}">
                                        </div>

                                        <div class="mt-3">
                                            <label for="inputDate"><fmt:message
                                                    key="register.inputDateOfBirth"/> </label>
                                        </div>
                                        <div class="form-group mt-1">
                                            <input type="date" id="inputDate" class="form-control" name="dateOfBirth"
                                                   value="${userDetails.dateOfBirth}"
                                                   required>
                                        </div>

                                        <div class="mt-3">
                                            <label for="inputPhoneNumber"><fmt:message
                                                    key="register.inputPhoneNumber"/> </label>
                                        </div>
                                        <div class="form-group mt-1">
                                            <input type="text" class="form-control field" id="inputPhoneNumber"
                                                   name="phoneNumber"
                                                   value="${userDetails.phoneNumber}"
                                                   placeholder=
                                                           "<fmt:message key="register.inputPhoneNumberPlaceholder"/>"
                                                   required
                                                   pattern="(\+?(((\d+-\d+)+)|(\d{2,20})|((\d+\s\d+)+)))|(\(\+?\d+\)[-\s]?(((\d+-\d+)+)|(\d+)|((\d+\s\d+)+)))">
                                        </div>

                                        <div class="mt-3">
                                            <label for="inputEmail"><fmt:message key="register.inputEmail"/> </label>
                                        </div>
                                        <div class="form-group mt-1">
                                            <input type="text" class="form-control field" id="inputEmail" name="email"
                                                   value="${email}"
                                                   placeholder=
                                                           "<fmt:message key="register.inputEmailPlaceholder"/>"
                                                   required
                                                   pattern="((\w)([-.](\w))?)+@((\w)([-.](\w))?)+.[a-zA-Zа-яА-Я]{2,4}">
                                        </div>
<%--                                        <div class="col-4">--%>
<%--                                            <button class="btn btn-outline-success button mt-4"--%>
<%--                                                    style="margin-left: 100%"--%>
<%--                                                    type="submit">--%>
<%--                                                <fmt:message key="button.save"/></button>--%>
<%--                                        </div>--%>
                                    </form>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary" form="editForm">
                                        <fmt:message key="button.save"/></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- end about section -->

<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>--%>

</body>

</html>