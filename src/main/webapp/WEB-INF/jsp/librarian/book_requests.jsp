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
<%--                        <c:if test="${!request.type.value.equals('to_reading_room')}">--%>
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
                                            <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${request.book.img}" alt=""
                                                 style="max-height: 250px;max-width: 250px">
                                        </div>
                                        <ctg:out text="${request.book.title}"/>
                                    </a>
                                </th>
                                <th scope="row"><ctg:out text="${request.type}"/></th>
                                <th scope="row"><ctg:out text="${request.state}"/></th>
                                <th scope="row"><ctg:out text="${request.requestDate}"/></th>
                                <th scope="row"><ctg:out text="${request.closingDate}"/></th>
                                <th scope="row"><ctg:out text="${request.penaltyAmount}"/></th>
                                <th scope="row">
                                    <c:if test="${request.state.value.equals('left')}">
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
                                    </c:if>
                                </th>
                            <tr>
<%--                        </c:if>--%>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

</body>

</html>
