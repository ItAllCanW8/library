<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 18.07.2021
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${sessionScope.currentLocalization}"/>
<fmt:setBundle basename="locale.content"/>

<head>
    <title><fmt:message key="title.library"/></title>

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Poppins:400,600,700&display=swap"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<header class="header_section">
    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg custom_nav-container ">
            <c:set var="role" scope="session" value="${sessionScope.currentRole}"/>
            <c:set var="guest" scope="session" value="GUEST"/>
            <c:set var="admin" scope="session" value="ADMIN"/>
            <c:set var="librarian" scope="session" value="LIBRARIAN"/>
            <c:set var="reader" scope="session" value="READER"/>
            <c:set var="loggingNotes" scope="session" value="${sessionScope.user}"/>

            <c:set var="activeStatus" scope="session" value="active"/>
            <c:set var="forSubscription" scope="session" value="for_subscription"/>
            <c:set var="toReadingRoom" scope="session" value="to_reading_room"/>
            <c:set var="leftState" scope="session" value="left"/>
            <c:set var="approvedState" scope="session" value="approved"/>
            <c:set var="closedState" scope="session" value="closed"/>
            <c:set var="deniedState" scope="session" value="denied"/>


            <a class="navbar-brand" href="${pageContext.request.contextPath}/home.do">
                <h3>
                    Library
                </h3>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse ml-auto" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/home.do"><fmt:message
                                key="header.home"/><span class="sr-only"></span></a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/load_books.do"><fmt:message
                                key="header.catalogue"/></a>
                    </li>

                    <c:if test="${sessionScope.role.toString().equals(reader)}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/reading_room.do"><fmt:message
                                    key="header.readingRoom"/></a>
                        </li>
                    </c:if>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" data-bs-toggle="dropdown"
                           aria-expanded="false"><fmt:message
                                key="header.changeLanguage"/></a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/change_language.do?newLocalization=ru_RU"><fmt:message
                                    key="language.russian"/></a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/change_language.do?newLocalization=en_EN"><fmt:message
                                    key="language.english"/></a></li>
                        </ul>
                    </li>

                    <c:if test="${role.toString().equals(guest)}">
                        <li class="nav-item">
                            <a class="nav-link header-link offset-1" href="${pageContext.request.contextPath}/register"><fmt:message
                                    key="header.register"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link header-link offset-1"
                               href="${pageContext.request.contextPath}/login"><fmt:message
                                    key="header.login"/></a>
                        </li>
                    </c:if>
                    <c:if test="${!role.toString().equals(guest)}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdownUser" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                    ${sessionScope.user.username}
                                <c:if test="${sessionScope.role.toString().equals(admin)}">
                                    (<fmt:message key="header.adminRole"/>)
                                </c:if>

                                <c:if test="${sessionScope.role.toString().equals(reader)}">
                                    (<fmt:message key="header.readerRole"/>)
                                </c:if>

                                <c:if test="${sessionScope.role.toString().equals(librarian)}">
                                    (<fmt:message key="header.librarianRole"/>)
                                </c:if>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownUser">
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/user_profile.do"><fmt:message
                                        key="header.profile"/></a></li>
                                <li>
                                    <c:if test="${sessionScope.role.toString().equals(admin)}">
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/users.do"><fmt:message
                                        key="header.users"/></a></li>
                                <li>
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/user_reports.do"><fmt:message
                                        key="header.user_reports"/></a></li>
                                <li>
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/load_logging_notes.do">Logging Notes</a></li>
                                <li>
                                    </c:if>
                                    <c:if test="${sessionScope.role.toString().equals(librarian)}">
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/to_librarian_books.do"><fmt:message
                                        key="header.booksManagement"/></a></li>
                                <li>
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/book_requests.do"><fmt:message
                                        key="librarian.bookRequests"/></a></li>
                                <li>
                                    </c:if>
                                    <c:if test="${sessionScope.role.toString().equals(reader)}">
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/my_book_requests.do"><fmt:message
                                        key="header.readerRequests"/></a></li>
                                <li>
                                    </c:if>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/logout.do"><fmt:message
                                        key="header.logout"/></a></li>
                            </ul>
                        </li>
                        <img src="${pageContext.request.contextPath}/load_profile_photo.do?fileName=${sessionScope.user.userDetails.photoPath}"
                             width="64" height="64"
                             class="rounded-circle avatar" alt="" style="margin-top: 20px">
                        <c:if test="${!sessionScope.role.toString().equals(admin)}">
                            <li class="nav-item">
                                <a class="nav-link header-link" href="${pageContext.request.contextPath}/contact">
                                    <fmt:message key="header.contact"/>
                                </a>
                            </li>
                        </c:if>
                    </c:if>
                </ul>
            </div>
        </nav>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
            crossorigin="anonymous"></script>
</header>