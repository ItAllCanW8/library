<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 10.08.2021
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body class="sub_page">
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
</div>
<%--    <c:set var="requests" scope="session" value="${bookRequests}"/>--%>

<section class="course_section layout_padding-bottom">
<%--    <div class="side_img">--%>
<%--        <img src="${pageContext.request.contextPath}/images/layout/side-img.png" alt="" />--%>
<%--    </div>--%>
    <div class="container">
        <div class="heading_container">
            <h3>
                <fmt:message key="header.readingRoom"/>
            </h3>
        </div>
        <div class="course_container">
            <div class="course_content">
                <c:forEach var="book" items="${books}">
                    <div class="box">
                        <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${book.img}" alt="" />
                        <h5>${book.title}</h5>
                        <a href="<c:url value="view_pdf.do?bookPdf=${book.pdf}"/>">
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

</body>
</html>
