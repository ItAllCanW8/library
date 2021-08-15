<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 10.08.2021
  Time: 15:10
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
            <c:if test="${!book.state.value.equals('closed')}">
              <tr class="table-secondary">
                <th scope="row">
                  <a href="load_book_info.do?bookId=${book.book.id}">
                    <div>
                      <img src="load_book_cover.do?fileName=${book.book.img}" alt=""
                           style="max-height: 250px;max-width: 250px">
                    </div>
                    <ctg:out text="${book.book.title}"/>
                  </a>
                </th>
                <th scope="row"><ctg:out text="${book.type}"/></th>
                <th scope="row"><ctg:out text="${book.state}"/></th>
                <th scope="row"><ctg:out text="${book.requestDate}"/></th>
                <th scope="row"><ctg:out text="${book.closingDate}"/></th>
                <th scope="row"><ctg:out text="${book.penaltyAmount}"/></th>
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
                        <c:if test="${book.state.value.equals('approved')}">
                          <li><a class="dropdown-item"
                                 href="view_pdf.do?bookPdf=${book.book.pdf}">
                            <fmt:message key="button.read"/> </a></li>
                          <c:if test="${book.type.value.equals('for_subscription')}">
                            <li><a class="dropdown-item"
                                   href="return_book.do?requestId=${book.id}&bookId=${book.book.id}&bookQuantity=${book.book.availableQuantity}&requestType=${book.type}">
                              <fmt:message key="button.return"/> </a></li>
                          </c:if>
                          <c:if test="${book.type.value.equals('to_reading_room')}">
                            <li><a class="dropdown-item"
                                   href="return_book.do?requestId=${book.id}&bookId=${book.book.id}&bookQuantity=${book.book.availableQuantity}&requestType=${book.type}">
                              <fmt:message key="button.return"/> </a></li>
                          </c:if>
                        </c:if>
                      </ul>
                    </div>
                </th>
              <tr>
            </c:if>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</section>

</body>
</html>
