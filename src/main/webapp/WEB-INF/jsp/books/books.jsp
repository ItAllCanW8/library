<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 26.07.2021
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<body class="sub_page">

<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
    <c:set var="books" scope="session" value="${books}"/>
</div>

<section class="event_section layout_padding">
    <div class="container">
        <div class="heading_container">
            <h3>
                <fmt:message key="books.catalogue"/>
            </h3>
        </div>
        <div class="event_container">
            <table id="booksTable" class="table table-dark table-bordered border-secondary">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="books.title"/></th>
                    <th scope="col"><fmt:message key="books.cover"/></th>
                    <th scope="col"><fmt:message key="books.author"/></th>
                    <th scope="col"><fmt:message key="books.genre"/></th>
                    <th scope="col">ISBN</th>
                    <th scope="col"><fmt:message key="books.availableQuantity"/></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach var="book" items="${books}">
                        <tr class="table-secondary">
                            <th scope="row"><a href="<c:url value="load_book_info.do?bookId=${book.id}"/>">
                                    ${book.title}</a></th>
                            <th scope="row"><div>
                                <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${book.img}" alt=""
                                     style="max-height: 250px;max-width: 250px"></div></th>
                            <th scope="row">${book.authorPseudo}</th>
                            <th scope="row">${book.genre}</th>
                            <th scope="row">${book.isbn}</th>
                            <th scope="row">${book.availableQuantity}</th>
                        <tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

</body>

</html>