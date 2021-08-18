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
            <hr style="width:100%;text-align:left;margin-left:0">
            <div class="row align-items-start" style="width:100%;">
                <div class="col-2" style="display: flex;justify-content: center">
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/book_requests.do"
                       role="button">
                        <fmt:message key="button.allRequests"/> </a>
                </div>

                <div class="col-2" style="display: flex;justify-content: center">
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="sortByDate"
                                data-bs-toggle="dropdown"
                                aria-expanded="false">
                            <fmt:message key="button.sortByDate"/>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="sortByDate">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/sort_records.do?sortingObject=book_requests&sortingField=request_date&sortingOrder=desc">
                                <fmt:message key="button.sortDesc"/> </a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/sort_records.do?sortingObject=book_requests&sortingField=request_date&sortingOrder=asc">
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
                    <th scope="col"><fmt:message key="librarian.reader"/></th>
                    <th scope="col"><fmt:message key="librarian.book"/></th>
                    <th scope="col"><fmt:message key="request.type"/></th>
                    <th scope="col"><fmt:message key="request.state"/></th>
                    <th scope="col"><fmt:message key="request.date"/></th>
                    <th scope="col"><fmt:message key="request.closingDate"/></th>
                    <th scope="col"><fmt:message key="request.penalty"/></th>
                    <th scope="col"><fmt:message key="button.action"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="request" items="${bookRequests}">
                    <tr class="table-secondary">
                        <th scope="row">
                            <a href="load_user_profile.do?userId=${request.user.id}">
                                <div>
                                    <img src="load_profile_photo.do?fileName=${request.user.userDetails.photoPath}" alt=""
                                         style="max-height: 250px;max-width: 250px">
                                </div>
                                <ctg:out text="${request.user.username}"/>
                            </a>
                        </th>
                        <th scope="row">
                            <a href="<c:url value="load_book_info.do?bookId=${request.book.id}"/>">
                                <div>
                                    <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${request.book.img}"
                                         alt=""
                                         style="max-height: 250px;max-width: 250px">
                                </div>
                                <ctg:out text="${request.book.title}"/>
                            </a>
                        </th>
                        <th scope="row">
                            <a href="${pageContext.request.contextPath}/find_book_requests_by_type.do?requestType=${request.type}">
                                <ctg:out text="${request.type}"/>
                            </a>
                        </th>
                        <th scope="row">
                            <a href="${pageContext.request.contextPath}/find_book_requests_by_state.do?requestState=${request.state}">
                                <ctg:out text="${request.state}"/>
                            </a>
                        </th>
                        <th scope="row"><ctg:out text="${request.requestDate}"/></th>
                        <th scope="row"><ctg:out text="${request.closingDate}"/></th>
                        <th scope="row"><ctg:out text="${request.penaltyAmount}"/></th>
                        <th scope="row">
                            <c:if test="${request.type.value.equals('for_subscription')}">
                                <div class="dropdown col-8 mb-4">
                                    <button class="btn btn-outline-secondary dropdown-toggle button mt-3" type="button"
                                            id="actionDropDown"
                                            data-bs-toggle="dropdown"
                                            aria-expanded="false">
                                        <fmt:message key="button.action"/>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-dark" style="width: 100%"
                                        aria-labelledby="actionDropDown">
                                        <c:if test="${request.state.value.equals('left')}">

                                            <li><a class="dropdown-item"
                                                   href="change_book_request_state.do?requestId=${request.id}&bookId=${request.book.id}&requestState=approved">
                                                <fmt:message key="button.approve"/> </a></li>
                                            <li><a class="dropdown-item"
                                                   href="change_book_request_state.do?requestId=${request.id}&bookId=${request.book.id}&requestState=denied">
                                                <fmt:message key="button.deny"/> </a></li>

                                        </c:if>
                                        <c:if test="${request.state.value.equals('closed') || request.state.value.equals('denied')}">
                                            <li><a class="dropdown-item"
                                                   href="delete_book_request.do?requestId=${request.id}">
                                                <fmt:message key="button.delete"/> </a></li>
                                        </c:if>
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
