<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 10.08.2021
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
</div>
    <c:set var="requests" scope="session" value="${requests}"/>

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
                <c:forEach var="request" items="${requests}">
                    <div class="box">
                        <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${request.book.img}" alt="" />
                        <a href="<c:url value="load_book_info.do?bookId=${request.id}"/>">
                            <h5>${request.title}</h5>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

</body>
</html>
