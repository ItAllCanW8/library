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
                    <th scope="col"><fmt:message key="librarian.book"/> id</th>
                    <th scope="col">Type</th>
                    <th scope="col">State</th>
                    <th scope="col">Request date</th>
                    <th scope="col">Processing date</th>
                    <th scope="col">Closing date</th>
                    <th scope="col">Penalty amount</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach var="request" items="${bookRequests}">
                        <tr class="table-secondary">
                            <th scope="row"><a href="<c:url value="load_user_profile.do?userId=${request.user.id}"/>">
                                    ${request.user.id}</a></th>
                            <th scope="row"><div>
                                <a href="<c:url value="load_book_info.do?bookId=${request.book.id}"/>">
                                        ${request.book.id}</a></div></th>
                            <th scope="row">${request.type}</th>
                            <th scope="row">${request.state}</th>
                            <th scope="row">${request.requestDate}</th>
                            <th scope="row">${request.processingDate}</th>
                            <th scope="row">${request.closingDate}</th>
                            <th scope="row">${request.penaltyAmount}</th>
                            <th scope="row">
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
                                               href="change_book_request_state.do?requestId=${request.id}&requestState=approved">
                                            <fmt:message key="button.approve"/> </a></li>
                                        <li><a class="dropdown-item"
                                               href="change_book_request_state.do?requestId=${request.id}&requestState=denied">
                                            <fmt:message key="button.deny"/> </a></li>
                                    </ul>
                                </div>
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