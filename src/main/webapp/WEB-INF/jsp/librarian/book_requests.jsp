<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 09.08.2021
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body class="sub_page">

<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
</div>

<section class="event_section layout_padding">
    <div class="container">
        <div class="heading_container">
            <h3>
                <fmt:message key="librarian.bookRequests"/>
            </h3>
        </div>
        <div class="event_container">
            <table id="booksTable" class="table table-dark table-bordered border-secondary">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="librarian.reader"/> id</th>
                    <th scope="col"><fmt:message key="librarian.book"/></th>
                    <th scope="col">Type</th>
                    <th scope="col">State</th>
                    <th scope="col">Request date</th>
                    <th scope="col">Closing date</th>
                    <th scope="col">Penalty amount</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach var="book" items="${bookRequests}">
                        <tr class="table-secondary">
                            <th scope="row"><a href="<c:url value="load_user_profile.do?userId=${book.user.id}"/>">
                                    ${book.user.id}</a></th>
                            <th scope="row">
                                <a href="<c:url value="load_book_info.do?bookId=${book.book.id}"/>">
                                    <div>
                                        <img src="${pageContext.request.contextPath}/load_book_cover.do?bookId=${book.book.id}" alt=""
                                             style="max-height: 250px;max-width: 250px">
                                    </div>
                                </a>
                            </th>
                            <th scope="row">${book.type}</th>
                            <th scope="row">${book.state}</th>
                            <th scope="row">${book.requestDate}</th>
                            <th scope="row">${book.closingDate}</th>
                            <th scope="row">${book.penaltyAmount}</th>
                            <th scope="row">
                                <c:if test="${book.state.value.equals('left')}">
                                    <div class="dropdown col-8 mb-4">
                                        <button class="btn btn-outline-secondary dropdown-toggle button mt-3" type="button"
                                                id="actionDropDown"
                                                data-bs-toggle="dropdown"
                                                aria-expanded="false">
                                            <fmt:message key="button.action"/>
                                        </button>
                                        <ul class="dropdown-menu dropdown-menu-dark" style="width: 100%"
                                            aria-labelledby="actionDropDown">
                                            <li><a class="dropdown-item"
                                                   href="change_book_request_state.do?requestId=${book.id}&requestState=approved">
                                                <fmt:message key="button.approve"/> </a></li>
                                            <li><a class="dropdown-item"
                                                   href="change_book_request_state.do?requestId=${book.id}&requestState=denied">
                                                <fmt:message key="button.deny"/> </a></li>
                                        </ul>
                                    </div>
                                </c:if>
                            </th>
                        <tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

</body>

</html>
