<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 18.07.2021
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
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
                        <a class="nav-link" href="${pageContext.request.contextPath}/home">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="about.html"> About </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="course.html"> Courses </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="event.html"> Events </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</header>
<!-- end header section -->