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
    <c:set var="books" scope="request" value="${books}"/>
</div>

<section class="event_section layout_padding">
    <div class="container">
        <div class="heading_container">
            <h3>
                <fmt:message key="books.catalogue"/>
            </h3>
            <hr style="width:100%;text-align:left;margin-left:0">
            <div class="row align-items-start" style="width:100%;">
                <div class="col-2 offset-2" style="display: flex;justify-content: center">
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/load_books.do" role="button"><fmt:message
                            key="button.allBooks"/> </a>
                </div>
                <div class="col-3">
                    <form name="findBooksForm" method="GET" action="${pageContext.request.contextPath}/find_books_by_keyword.do">
                        <div class="d-flex">
                            <input class="form-control me-2" type="search"
                                   placeholder="<fmt:message key="search.keyword"/>"
                                   aria-label="Search"
                                   value="${keyword}" name="keyword">
                            <button class="btn btn-success" type="submit"><fmt:message
                                    key="button.search"/></button>
                        </div>
                    </form>
                </div>

                <div class="col-2" style="display: flex;justify-content: center">
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="sortByQuantity"
                                data-bs-toggle="dropdown"
                                aria-expanded="false">
                            <fmt:message key="button.sortByQuantity"/>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="sortByQuantity">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/sort_records.do?sortingObject=books&sortingField=available_quantity&sortingOrder=desc">
                                <fmt:message key="button.sortDesc"/>
                            </a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/sort_records.do?sortingObject=books&sortingField=available_quantity&sortingOrder=asc">
                                <fmt:message key="button.sortAsc"/> </a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="event_container">
            <table id="booksTable" class="table table-dark table-bordered border-secondary">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="librarian.book"/></th>
                    <th scope="col"><fmt:message key="books.author"/></th>
                    <th scope="col"><fmt:message key="books.genre"/></th>
                    <th scope="col">ISBN</th>
                    <th scope="col"><fmt:message key="books.availableQuantity"/></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach var="request" items="${books}">
                        <tr class="table-secondary">
                            <th scope="row">
                                <a href="${pageContext.request.contextPath}/load_book_info.do?bookId=${request.id}">
                                    <div>
                                        <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${request.img}"
                                             alt="" style="max-height: 250px;max-width: 250px">
                                    </div>
                                    <ctg:out text="${request.title}"/>
                                </a>
                            </th>
                            <th scope="row">
                                <div>
                                    <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${request.authorImg}"
                                         alt="" style="max-height: 250px;max-width: 250px">
                                </div>
                                <a href="${pageContext.request.contextPath}/find_books_by_author.do?bookAuthor=${request.authorPseudo}">
                                    <ctg:out text="${request.authorPseudo}"/>
                                </a>
                            </th>
                            <th scope="row">
                                <a href="${pageContext.request.contextPath}/find_books_by_genre.do?bookGenre=${request.genre}">
                                    <ctg:out text="${request.genre}"/>
                                </a>
                            </th>
                            <th scope="row"><ctg:out text="${request.isbn}"/></th>
                            <th scope="row"><ctg:out text="${request.availableQuantity}"/></th>
                        <tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

</body>

</html>