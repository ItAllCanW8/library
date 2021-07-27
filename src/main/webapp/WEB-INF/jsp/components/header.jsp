<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 18.07.2021
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocalization}"/>
<fmt:setBundle basename="locale.content"/>

<!-- header section starts -->
<header class="header_section">
    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg custom_nav-container ">
            <c:set var="role" scope="session" value="${sessionScope.currentRole}"/>
            <c:set var="guest" scope="session" value="GUEST"/>
            <c:set var="admin" scope="session" value="ADMIN"/>
            <c:set var="librarian" scope="session" value="LIBRARIAN"/>
            <c:set var="reader" scope="session" value="READER"/>
            <c:set var="user" scope="session" value="${sessionScope.user}"/>

            <a class="navbar-brand" href="${pageContext.request.contextPath}/home.do">
                <h3>
                    Library
                </h3>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse ml-auto" id="navbarSupportedContent">
                <ul class="navbar-nav  ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/home.do"><fmt:message
                                key="header.home"/><span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/load_books.do"><fmt:message
                                key="header.books"/></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" data-toggle="dropdown"
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
                            <a class="nav-link header-link offset-1" href="${pageContext.request.contextPath}/login"><fmt:message
                                    key="header.login"/></a>
                        </li>
                    </c:if>
                    <c:if test="${!role.toString().equals(guest)}">
                        <li class="nav-item">
                            <a class="nav-link header-link offset-1" href="${pageContext.request.contextPath}/logout.do"><fmt:message
                                    key="header.logout"/></a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>
    </div>

<%--    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</header>