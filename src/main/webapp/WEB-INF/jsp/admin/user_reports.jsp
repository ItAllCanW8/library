<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 19.08.2021
  Time: 16:27
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
            ${noReport}
            ${errorDuplicate}
        </wrong-message>

        <div class="heading_container">
            <h3>
                <fmt:message key="admin.userReports"/>
            </h3>
            <hr style="width:100%;text-align:left;margin-left:0">
            <div class="row align-items-start" style="width:100%;margin-bottom: 20px">
                <div class="col-2" style="display: flex;justify-content: center">
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/user_reports.do"
                       role="button">
                        <fmt:message key="button.allReports"/> </a>
                </div>

                <div class="col-2" style="display: flex;justify-content: center">
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="sortByDate"
                                data-bs-toggle="dropdown"
                                aria-expanded="false">
                            <fmt:message key="button.sortByCreationDate"/>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="sortByDate">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/sort_records.do?sortingEntity=user_reports&sortingField=creation_date&sortingOrder=desc">
                                <fmt:message key="button.sortDesc"/> </a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/sort_records.do?sortingEntity=user_reports&sortingField=creation_date&sortingOrder=asc">
                                <fmt:message key="button.sortAsc"/> </a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-2" style="display: flex;justify-content: center">
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="reportStateDropdown"
                                data-bs-toggle="dropdown"
                                aria-expanded="false">
                            <fmt:message key="button.reportState"/>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="reportStateDropdown">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/find_reports_by_state.do?isProcessed=1">
                                <fmt:message key="button.processedReports"/> </a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/find_reports_by_state.do?isProcessed=0">
                                <fmt:message key="button.notProcessedReports"/> </a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="event_container">
            <table id="booksTable" class="table table-dark table-bordered border-secondary">
                <thead>
                <tr>
                    <th scope="col">Subject</th>
                    <th scope="col">User</th>
                    <th scope="col">Role</th>
                    <th scope="col">Is processed?</th>
                    <th scope="col">Creation date</th>
                    <th scope="col"><fmt:message key="button.action"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="report" items="${userReports}">
                    <tr class="table-secondary">
                        <th scope="row"><ctg:out text="${report.subject}"/></th>
                        <th scope="row">
                            <a href="${pageContext.request.contextPath}/load_user_profile.do?userId=${report.user.id}">
                                <ctg:out text="${report.user.username}"/>
                            </a><br>
                            <ctg:out text="${report.user.email}"/>
                        </th>
                        <th scope="row"><ctg:out text="${report.user.role}"/></th>
                        <th scope="row">${report.isProcessed()}</th>
                        <th scope="row">${report.creationDate}</th>
                        <th scope="row"><a href="${pageContext.request.contextPath}/load_user_report.do?reportId=${report.id}">
                            <fmt:message key="button.moreInfo"/>
                        </a> </th>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

</body>

</html>
