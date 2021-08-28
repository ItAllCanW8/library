<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 19.08.2021
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<body >
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>

    <c:if test="${sessionScope.role.toString().equals(guest)}">
        <jsp:forward page="/login"/>
    </c:if>

    <section class="login_section layout_padding">
        <div class="container">

            <c:if test="${successMessage}">
                <success-message>
                    <fmt:message key="successMessage"/>
                </success-message>
            </c:if>

            <div class="row">
                <div class="col-md-6">
                    <div class="detail-box">
                        <h3>
                            <fmt:message key="header.contact"/>
                        </h3>
                        <p>
                            <fmt:message key="report.subjectPlaceholder"/>
                        </p>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="login_form">
                        <h5>
                            <fmt:message key="header.contact"/>
                        </h5>
                        <form action="${pageContext.request.contextPath}/send_report.do" method="post">
                            <label for="inputReportSubject"><fmt:message key="report.inputSubject"/></label>
                            <input type="text" id="inputReportSubject" name="reportSubject" value="${reportSubject}"
                                   pattern="[А-Яа-я\w\s\p{Punct}]{1,255}"
                                   required
                                   placeholder="<fmt:message key="report.subjectPlaceholder"/>" />

                            <label for="inputContactMessage"><fmt:message key="report.inputMessage"/></label>
                            <div class="form-group mt-1">
                            <textarea type="text" id="inputContactMessage" name="reportMessage" class="form-control"
                                      rows="5"
                                      placeholder="<fmt:message key="report.messagePlaceholder"/>"
                                      required minlength="3" maxlength="1000">${reportMessage}</textarea>
                            </div>

                            <button type="submit"><fmt:message key="button.sent"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

</body>
</html>
