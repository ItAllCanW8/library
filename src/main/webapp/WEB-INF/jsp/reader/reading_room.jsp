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

<section class="course_section layout_padding-bottom">
    <div class="container">

        <wrong-message>
            ${noBookRequests}
        </wrong-message>

        <div class="heading_container">
            <h3>
                <fmt:message key="header.readingRoom"/>
            </h3>
            <h4><fmt:message key="admin.readingRoomOpening"/>: ${readingRoomOpening}</h4>
            <h4><fmt:message key="admin.readingRoomClosing"/>: ${readingRoomClosing}</h4>
            <hr style="width:100%;text-align:left;margin-left:0">
        </div>
        <div class="course_container">
            <div class="course_content">
                <c:if test="${bookRequests == null}">
                    <a href="${pageContext.request.contextPath}/load_books.do"><fmt:message key="books.catalogue"/> </a>
                </c:if>
                <c:forEach var="request" items="${bookRequests}">
                    <div class="box">
                        <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${request.book.img}" alt="" />
                        <h5><ctg:out text="${request.book.title}"/></h5>
                        <a href="${pageContext.request.contextPath}/view_pdf.do?bookPdf=${request.book.pdf}" style="color:white"><fmt:message key="button.read"/> </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

</body>
</html>
