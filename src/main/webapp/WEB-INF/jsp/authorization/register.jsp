<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 20.07.2021
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<body>
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>

    <section class="login_section layout_padding">
        <div class="container">
            <div class="login_form">
                <h4>
                    <fmt:message key="register.title"/>
                </h4>
                <form method="post" action="${pageContext.request.contextPath}/register.do">
                    <label for="username"><fmt:message key="register.inputUsername"/></label>
                    <div>
                        <input type="text" id="username" name="username" value="${username}"
                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>"
                               pattern="[a-zA-Zа-яА-Я\w]{3,255}" required/>
                    </div>
                    <label for="email"><fmt:message key="register.inputEmail"/></label>
                    <div>
                        <input type="email" id="email" name="email" value="${email}"
                               placeholder="<fmt:message key="register.inputEmailPlaceholder"/>"
                               pattern="((\w)|(\w[.-]\w)){1,42}@((\w)|(\w[.-]\w)){1,41}.[a-zA-Zа-яА-Я]{2,4}"
                               required/>
                    </div>
                    <label for="name"><fmt:message key="register.inputName"/></label>
                    <div>
                        <input type="text" id="name" name="name" value="${name}"
                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>"
                               pattern="[a-zA-Zа-яА-Я]{3,255}"
                               required/>
                    </div>
                    <label for="surname"><fmt:message key="register.inputSurname"/></label>
                    <div>
                        <input type="text" id="surname" name="surname" value="${surname}"
                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>"
                               pattern="[a-zA-Zа-яА-Я]{3,255}"
                               required/>
                    </div>
                    <label for="dateOfBirth"><fmt:message key="register.inputDateOfBirth"/></label>
                    <div>
                        <input type="date" id="dateOfBirth" name="dateOfBirth" placeholder="17.01.1989"
                               value="${dateOfBirth}"
                               required/>
                    </div>
                    <label for="phoneNumber"><fmt:message key="register.inputPhoneNumber"/></label>
                    <div>
                        <input type="text" id="phoneNumber" name="phoneNumber" value="${phoneNumber}"
                               placeholder="<fmt:message key="register.inputPhoneNumberPlaceholder"/>"
                               pattern="(\+?(((\d+-\d+)+)|(\d{2,20})|((\d+\s\d+)+)))|(\(\+?\d+\)[-\s]?(((\d+-\d+)+)|(\d+)|((\d+\s\d+)+)))"
                               required/>
                    </div>
                    <label for="password"><fmt:message key="register.inputPassword"/></label>
                    <div>
                        <input type="password" id="password" name="password" value="${password}"
                               placeholder="<fmt:message key="register.inputPasswordPlaceholder"/>"
                               pattern="[А-Яа-я\w\s\p{Punct}]{6,255}"
                               required/>
                    </div>
                    <label for="repeatedPassword"><fmt:message key="register.inputRepeatedPassword"/></label>
                    <div>
                        <input type="password" id="repeatedPassword" name="repeatedPassword" value="${repeatedPassword}"
                               placeholder="<fmt:message key="register.inputPasswordPlaceholder"/>"
                               pattern="[А-Яа-я\w\s\p{Punct}]{6,255}"
                               required/>
                    </div>

                    <div class="mt-3">
                        <wrong-message>
                            ${errorInputData}
                        </wrong-message>
                    </div>

                    <button type="submit"><fmt:message key="button.completeRegister"/></button>
                </form>
            </div>
        </div>
    </section>
</div>

</body>

</html>

