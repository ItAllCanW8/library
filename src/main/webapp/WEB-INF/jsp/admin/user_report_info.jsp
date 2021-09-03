<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 20.08.2021
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body class="sub_page">

<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
    <c:set var="report" scope="request" value="${userReport}"/>
</div>

<div class="container">

    <wrong-message>
        ${errorInputData}
        ${noReport}
        ${errorDuplicate}
    </wrong-message>

    <div class="card text-dark bg-light offset-3" style="margin-top: 1%;max-width: 50%; margin-bottom: 2%;">
        <div class="card-header">
            <h3>
                <fmt:message key="report.info.title"/>
                <c:if test="${report.isProcessed()}">
                    <fmt:message key="report.info.processed"/>
                </c:if>
            </h3>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="report.info.subject"/></h4>
            <p class="card-text"><ctg:out text="${report.subject}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="report.info.message"/></h4>
            <p class="card-text"><ctg:out text="${report.message}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="report.info.creationDate"/></h4>
            <p class="card-text"><ctg:out text="${report.creationDate}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="report.info.email"/></h4>
            <p class="card-text"><ctg:out text="${report.user.email}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="report.info.role"/></h4>
            <p class="card-text"><ctg:out text="${report.user.role}"/></p>
        </div>

        <c:if test="${report.response != null}">
            <div class="card-body">
                <h4 class="card-title"><fmt:message key="report.info.response"/></h4>
                <p class="card-text"><ctg:out text="${report.response}"/></p>
            </div>
        </c:if>

        <div class="col-6 offset-3 mt-3" style="display: flex;justify-content: center;">
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
                           href="${pageContext.request.contextPath}/load_user_profile.do?userId=${report.user.id}"><fmt:message
                            key="report.info.userProfile"/> </a></li>
<%--                    <c:if test="${report.response == null}">--%>
                        <li>
                            <button type="button" class="btn btn-secondary dropdown-item"
                                    data-bs-toggle="modal" data-bs-target="#createReportResponseModal">
                                <fmt:message key="report.info.createResponse"/>
                            </button>
                        </li>
<%--                    </c:if>--%>
                </ul>
            </div>
        </div>

        <div class="modal fade" id="createReportResponseModal" data-bs-backdrop="static"
             data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title"><fmt:message
                                key="report.info.createResponse"/></h4>
                    </div>

                    <div class="modal-body">
                        <form id="createResponseForm" method="POST"
                              action="${pageContext.request.contextPath}/create_report_response.do">
                            <input type="hidden" name="reportId" value="${report.id}">
                            <label for="inputResponse"><fmt:message
                                    key="report.info.inputResponse"/> </label>
                            <div class="form-group mt-1">
                                    <textarea class="form-control" rows="5" id="inputResponse"
                                              name="response" placeholder="<fmt:message
                                             key="report.info.inputResponsePlaceholder"/>"
                                              required minlength="3" maxlength="1000">${response}</textarea>
                            </div>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-bs-dismiss="modal">
                            <fmt:message key="button.close"/></button>
                        <button class="btn btn-primary" type="submit" form="createResponseForm">
                            <fmt:message key="button.save"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>