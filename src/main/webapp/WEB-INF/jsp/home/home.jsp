<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 18.07.2021
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocalization}"/>
<fmt:setBundle basename="locale.content"/>

<html>
<head>
    <!-- Basic -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <!-- Site Metas -->
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>Joson</title>

    <!-- bootstrap core css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <!-- fonts style -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Poppins:400,600,700&display=swap" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
    <!-- responsive style -->
    <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet" />


</head>

<body>
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>

    <c:set var="books" scope="session" value="${books}"/>
    <!-- slider section -->
    <section class=" slider_section position-relative">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="detail-box">
                                    <div>
                                        <h1>
                                            <fmt:message key="carousel.library"/>
                                        </h1>
                                        <a href="">
                                            <fmt:message key="read.more"/>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item ">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="detail-box">
                                    <div>
                                        <h1>
                                            <fmt:message key="carousel.education"/>
                                        </h1>
                                        <a href="">
                                            <fmt:message key="read.more"/>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item ">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="detail-box">
                                    <div>
                                        <h1>
                                            <fmt:message key="carousel.leisure"/>
                                        </h1>
                                        <a href="">
                                            <fmt:message key="read.more"/>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- end slider section -->
</div>

<section class="course_section layout_padding-bottom">
    <div class="side_img">
        <img src="${pageContext.request.contextPath}/images/layout/side-img.png" alt="" />
    </div>
    <div class="container">
        <div class="heading_container">
            <h3>
                <fmt:message key="books.popular"/>
            </h3>
        </div>
        <div class="course_container">
            <div class="course_content">
                <c:forEach var="book" items="${books}">
                    <div class="box">
                        <img src="${pageContext.request.contextPath}/images/book-covers/${book.img}" alt="" />
                        <a href="" class="">
                            <img src="${pageContext.request.contextPath}/images/layout/link.png" alt="" />
                        </a>
                        <h5>
                                ${book.title}
                        </h5>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

</body>
</html>
