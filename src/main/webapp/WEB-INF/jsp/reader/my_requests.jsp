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

    <wrong-message>
      ${errorInputData}
    </wrong-message>

    <div class="heading_container">
      <h3>
        <fmt:message key="librarian.bookRequests"/>
      </h3>
      <hr style="width:100%;text-align:left;margin-left:0">
    </div>
    <div class="event_container">
      <table id="booksTable" class="table table-dark table-bordered border-secondary">
        <thead>
          <tr>
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
          <c:forEach var="report" items="${bookRequests}">
            <c:if test="${!report.state.value.equals('closed')}">
              <tr class="table-secondary">
                <th scope="row">
                  <a href="${pageContext.request.contextPath}/load_book_info.do?bookId=${report.book.id}">
                    <div>
                      <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${report.book.img}" alt=""
                           style="max-height: 250px;max-width: 250px">
                    </div>
                    <ctg:out text="${report.book.title}"/>
                  </a>
                </th>
                <th scope="row"><ctg:out text="${report.type}"/></th>
                <th scope="row"><ctg:out text="${report.state}"/></th>
                <th scope="row"><ctg:out text="${report.requestDate}"/></th>
                <th scope="row"><ctg:out text="${report.closingDate}"/></th>
                <th scope="row"><ctg:out text="${report.penaltyAmount}"/></th>
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
                        <c:if test="${report.state.value.equals('approved')}">
                          <li><a class="dropdown-item"
                                 href="${pageContext.request.contextPath}/view_pdf.do?bookPdf=${report.book.pdf}">
                            <fmt:message key="button.read"/> </a></li>
                          <c:if test="${report.type.value.equals('for_subscription')}">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/return_book.do?requestId=${report.id}&bookId=${report.book.id}&bookQuantity=${report.book.availableQuantity}&requestType=${report.type}">
                              <fmt:message key="button.return"/> </a></li>
                          </c:if>
                          <c:if test="${report.type.value.equals('to_reading_room')}">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/return_book.do?requestId=${report.id}&bookId=${report.book.id}&bookQuantity=${report.book.availableQuantity}&requestType=${report.type}">
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
