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
    <wrong-message>
        ${errorInputData}
        ${invalidCurrentPassword}
    </wrong-message>

    <c:if test="${successMessage}">
        <success-message>
            <fmt:message key="successMessage"/>
        </success-message>
    </c:if>
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
                        <img src="${pageContext.request.contextPath}/load_profile_photo.do?fileName=${user.userDetails.photoPath}"
                             alt=""/>
                    </div>
                    <c:if test="${sessionScope.userId.equals(user.id)}">
                        <form action="upload_photo.do" method="post" enctype="multipart/form-data">
                            <input type="file" name="file" class="form-control-file"/>
                            <input type="submit" class="btn btn-outline-secondary" value="Upload"/>
                        </form>
                    </c:if>
                </div>
            </div>
            <div class="col-md-6">
                <div class="detail-box">
                    <div class="heading_container">
                        <h3>${user.username}</h3>
                        <h4>${user.email}</h4>
                        <hr style="width:100%;text-align:left;margin-left:0">
                        <h5><fmt:message key="profile.name"/>: ${user.userDetails.name}</h5>
                        <h5><fmt:message key="profile.surname"/>: ${user.userDetails.surname}</h5>
                        <h5><fmt:message key="profile.dateOfBirth"/>: ${user.userDetails.dateOfBirth}</h5>
                        <h5><fmt:message key="profile.phoneNumber"/>: ${user.userDetails.phoneNumber}</h5>
                        <hr style="width:100%;text-align:left;margin-left:0">
                    </div>

                    <c:if test="${sessionScope.userId.equals(user.id)}">
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#editProfileModal">
                            <fmt:message key="button.edit"/>
                        </button>

                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#changePassModal">
                            <fmt:message key="button.changePassword"/>
                        </button>
                    </c:if>

                    <div class="modal fade" id="editProfileModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">
                                        <fmt:message key="register.inputUsername"/></h5>
                                </div>

                                <div class="modal-body">
                                    <form method="POST" action="edit_user_profile.do" id="editForm">
                                        <label for="inputUsername"><fmt:message key="register.inputUsername"/> </label>
                                        <div class="form-group mt-1">
                                            <input type="text" class="form-control field" id="inputUsername"
                                                   name="username"
                                                   value="${user.username}"
                                                   placeholder=
                                                           "<fmt:message key="register.inputNamePlaceholder"/>" required
                                                   pattern="[a-zA-Zа-яА-Я]{3,35}">
                                        </div>

                                        <label for="inputName"><fmt:message key="register.inputName"/> </label>
                                        <div class="form-group mt-1">
                                            <input type="text" class="form-control field" id="inputName"
                                                   name="name"
                                                   value="${user.userDetails.name}"
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
                                                   value="${user.userDetails.surname}"
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
                                                   value="${user.userDetails.dateOfBirth}"
                                                   required>
                                        </div>

                                        <div class="mt-3">
                                            <label for="inputPhoneNumber"><fmt:message
                                                    key="register.inputPhoneNumber"/> </label>
                                        </div>
                                        <div class="form-group mt-1">
                                            <input type="text" class="form-control field" id="inputPhoneNumber"
                                                   name="phoneNumber"
                                                   value="${user.userDetails.phoneNumber}"
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
                                                   value="${user.email}"
                                                   placeholder=
                                                           "<fmt:message key="register.inputEmailPlaceholder"/>"
                                                   required
                                                   pattern="((\w)([-.](\w))?)+@((\w)([-.](\w))?)+.[a-zA-Zа-яА-Я]{2,4}">
                                        </div>
                                    </form>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                        <fmt:message key="button.close"/>
                                    </button>
                                    <button type="submit" class="btn btn-primary" form="editForm">
                                        <fmt:message key="button.save"/></button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal fade" id="changePassModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title"><fmt:message key="profile.changePassword"/></h4>
                                </div>

                                <div class="modal-body">
                                    <form id="changePassForm" method="POST" action="change_password.do">
                                        <label for="inputCurrentPassword"><fmt:message
                                                key="profile.currentPassword"/> </label>
                                        <div class="form-group mt-1">
                                            <input type="password" class="form-control field" id="inputCurrentPassword"
                                                   name="password"
                                                   placeholder=
                                                           "<fmt:message key="register.inputPasswordPlaceholder"/>"
                                                   required
                                                   pattern="[а-яА-Я\w\s\p{Punct}]{6,80}">
                                        </div>

                                        <div class="mt-3">
                                            <label for="inputNewPassword"><fmt:message
                                                    key="profile.newPassword"/> </label>
                                        </div>
                                        <div class="form-group mt-1">
                                            <input type="password" class="form-control field" id="inputNewPassword"
                                                   name="newPassword"
                                                   value="${newPassword}"
                                                   placeholder=
                                                           "<fmt:message key="register.inputPasswordPlaceholder"/>"
                                                   required
                                                   pattern="[а-яА-Я\w\s\p{Punct}]{6,80}">
                                        </div>

                                        <div class="mt-3">
                                            <label for="repeatNewPassword"><fmt:message
                                                    key="profile.repeatNewPassword"/> </label>
                                        </div>
                                        <div class="form-group mt-1">
                                            <input type="password" class="form-control field" id="repeatNewPassword"
                                                   name="repeatedPassword"
                                                   value="${repeatedPassword}"
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
                                    <button type="submit" class="btn btn-primary" form="changePassForm">
                                        <fmt:message key="button.save"/></button>
                                </div>
                            </div>
                        </div>
                    </div>

<%--                    <c:if test="${!user.role.toString().equals(admin)}">--%>
                    <c:if test="${!sessionScope.role.toString().equals(admin) &&
                    !sessionScope.role.toString().equals(guest)}">
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#deactivateModal">
                            <fmt:message key="button.deactivateAccount"/>
                        </button>
                    </c:if>

                    <div class="modal fade" id="deactivateModal" tabindex="-1" role="dialog"
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
        </div>
    </div>
</section>

<!-- end about section -->

</body>

</html>