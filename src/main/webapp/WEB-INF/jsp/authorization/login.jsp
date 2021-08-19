<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 19.07.2021
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>

<section class="login_section layout_padding">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="detail-box">
                    <h3>
                        <fmt:message key="login.getBooksMsg"/>
                    </h3>
                    <p>
                        <fmt:message key="login.createAccMsg"/>
                    </p>
                    <a href="${pageContext.request.contextPath}/register">
                        <fmt:message key="login.registerNow"/>
                    </a>
                </div>
            </div>
            <div class="col-md-6">
                <div class="login_form">
                    <h5>
                        <fmt:message key="header.login"/>
                    </h5>
                    <form action="${pageContext.request.contextPath}/login.do" method="post">
                        <div>
                            <label for="email">
                                <input type="email" id="email" name="email" value="${email}"
                                       pattern="((\w)|(\w[.-]\w)){1,42}@((\w)|(\w[.-]\w)){1,41}.[a-zA-Zа-яА-Я]{2,4}"
                                       required
                                       placeholder="<fmt:message key="register.inputEmail"/>" />
                            </label>
                        </div>
                        <div>
                            <label for="password">
                                <input type="password" id="password" name="password"
                                       placeholder="<fmt:message key="register.inputPassword"/>"
                                       required pattern="[а-яА-Я\w\s\p{Punct}]{6,255}"/>
                            </label>
                        </div>
                        <button type="submit"><fmt:message key="button.login"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</div>

</body>

</html>
