<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 18.07.2021
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>

    <c:set var="books" scope="session" value="${books}"/>
    <!-- slider section -->
    <section class=" slider_section position-relative">
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <ol class="carousel-indicators">
                <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"></li>
                <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"></li>
                <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"></li>
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
                                            <fmt:message key="button.readMore"/>
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
                                            <fmt:message key="button.readMore"/>
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
                                            <fmt:message key="button.readMore"/>
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
                        <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${book.img}" alt="" />
                        <a href="<c:url value="load_book_info.do?bookId=${book.id}"/>">
                            <img src="${pageContext.request.contextPath}/images/layout/link.png" alt="" />
                        </a>
                        <h5>${book.title}</h5>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

</body>
</html>
