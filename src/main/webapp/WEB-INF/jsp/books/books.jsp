<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 26.07.2021
  Time: 14:50
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

<body class="sub_page">
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
    <c:set var="books" scope="session" value="${books}"/>

<!-- event section -->
<section class="event_section layout_padding">
    <div class="container">
        <div class="heading_container">
            <h3>
                Events
            </h3>
            <p>
                Upcoming Education Events to feed your brain.
            </p>
        </div>
        <div class="event_container">
            <div class="box">
                <div class="img-box">
                    <img src="${pageContext.request.contextPath}/images/layout/event-img.jpg" alt="" />
                </div>
                <div class="detail-box">
                    <h4>
                        Education Events 2021
                    </h4>
                    <h6>
                        8:00 AM - 5:00 PM VENICE, ITALY
                    </h6>
                </div>
                <div class="date-box">
                    <h3>
              <span>
                15
              </span>
                        March
                    </h3>
                </div>
            </div>
            <div class="box">
                <div class="img-box">
                    <img src="${pageContext.request.contextPath}/images/layout/event-img.jpg" alt="" />
                </div>
                <div class="detail-box">
                    <h4>
                        Education Events 2021
                    </h4>
                    <h6>
                        8:00 AM - 5:00 PM VENICE, ITALY
                    </h6>
                </div>
                <div class="date-box">
                    <h3>
              <span>
                15
              </span>
                        February
                    </h3>
                </div>
            </div>
        </div>
        <div class="btn-box">
            <a href="">
                Read More
            </a>
        </div>
    </div>
</section>

<!-- end event section -->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

</body>

</html>

<%--<html>--%>
<%--<head>--%>
<%--    <!-- Basic -->--%>
<%--    <meta charset="utf-8"/>--%>
<%--    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>--%>
<%--    <!-- Mobile Metas -->--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>--%>
<%--    <!-- Site Metas -->--%>
<%--    <meta name="keywords" content=""/>--%>
<%--    <meta name="description" content=""/>--%>
<%--    <meta name="author" content=""/>--%>

<%--    <title>Books</title>--%>

<%--    <!-- bootstrap core css -->--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>--%>
<%--    <!-- fonts style -->--%>
<%--    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Poppins:400,600,700&display=swap"--%>
<%--          rel="stylesheet"/>--%>
<%--    <!-- Custom styles for this template -->--%>
<%--    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>--%>
<%--    <!-- responsive style -->--%>
<%--    <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet"/>--%>
<%--</head>--%>

<%--<body class="sub_page">--%>
<%--<div class="hero_area">--%>
<%--    <%@ include file="../components/header.jsp" %>--%>
<%--    <c:set var="books" scope="session" value="${books}"/>--%>

<%--    <!-- event section -->--%>
<%--    <section class="event_section layout_padding">--%>
<%--        <div class="container">--%>
<%--            <div class="heading_container">--%>
<%--                <h3>--%>
<%--                    Events--%>
<%--                </h3>--%>
<%--                <p>--%>
<%--                    Upcoming Education Events to feed your brain.--%>
<%--                </p>--%>
<%--            </div>--%>
<%--            <div class="event_container">--%>
<%--                <c:forEach var="book" items="${books}">--%>
<%--                    <div class="box">--%>
<%--                        <div class="box">--%>
<%--                            <div class="img-box">--%>
<%--                                <img src="${pageContext.request.contextPath}/images/layout/event-img.jpg" alt=""/>--%>
<%--                            </div>--%>
<%--                            <div class="detail-box">--%>
<%--                                <h4>--%>
<%--                                    Education Events 2021--%>
<%--                                </h4>--%>
<%--                                <h6>--%>
<%--                                    8:00 AM - 5:00 PM VENICE, ITALY--%>
<%--                                </h6>--%>
<%--                            </div>--%>
<%--                            <div class="date-box">--%>
<%--                                <h3>--%>
<%--                            <span>--%>
<%--                                15--%>
<%--                            </span>--%>
<%--                                    March--%>
<%--                                </h3>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <img src="${pageContext.request.contextPath}/images/book-covers/${book.img}" alt=""/>--%>
<%--                        <a href="" class="">--%>
<%--                            <img src="${pageContext.request.contextPath}/images/layout/link.png" alt=""/>--%>
<%--                        </a>--%>
<%--                        <h5>--%>
<%--                            ${book.title}--%>
<%--                        </h5>--%>
<%--                    </div>--%>
<%--                </c:forEach>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </section>--%>

<%--    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>--%>
<%--    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>
