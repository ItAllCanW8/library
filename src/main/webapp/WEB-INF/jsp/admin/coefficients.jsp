<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 11.08.2021
  Time: 14:12
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
                    <fmt:message key="admin.setCoefficients"/>
                </h4>
                <form method="post" action="set_coefficients.do">
                    <label for="bookIssuingDaysNum"><fmt:message key="admin.bookIssuingDaysNum"/></label>
                    <div>
                        <input type="text" id="bookIssuingDaysNum" name="bookIssuingDaysNum"
<%--                               value="${bookIssuingDaysNum}"--%>
                               placeholder="0-99"
                               pattern="[0-9]{1,2}"/>
                    </div>

                    <label for="readingRoomOpening"><fmt:message key="admin.readingRoomOpening"/></label>
                    <input type="time" id="readingRoomOpening" name="readingRoomOpening">

                    <label for="readingRoomClosing"><fmt:message key="admin.readingRoomClosing"/></label>
                    <input type="time" id="readingRoomClosing" name="readingRoomClosing">

                    <button type="submit"><fmt:message key="button.save"/></button>
                </form>
            </div>
        </div>
    </section>
</div>

</body>
</html>
