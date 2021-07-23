<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 20.07.2021
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Basic -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <!-- Site Metas -->
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>Joson</title>

    <!-- bootstrap core css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <!-- fonts style -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Poppins:400,600,700&display=swap" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
    <!-- responsive style -->
    <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet" />
</head>

<body>
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
    <!-- login section -->

    <section class="login_section layout_padding">
        <div class="container">
            <div class="login_form">
                <h4>
                    Register
                </h4>
                <form method="post" action="register.do">
                    <label for="username">Username*</label>
                    <div>
                        <input type="text" id="username" placeholder="username" required/>
                    </div>
                    <label for="email">Email*</label>
                    <div>
                        <input type="email" id="email" placeholder="email" required/>
                    </div>
                    <label for="name">Name*</label>
                    <div>
                        <input type="text" id="name" placeholder="name" required/>
                    </div>
                    <label for="surname">Surname*</label>
                    <div>
                        <input type="text" id="surname" placeholder="surname" required/>
                    </div>
                    <label for="dateOfBirth">Date of birth*</label>
                    <div>
                        <input type="date" id="dateOfBirth" placeholder="date of birth" required/>
                    </div>
                    <label for="phoneNumber">Phone number*</label>
                    <div>
                        <input type="text" id="phoneNumber" placeholder="phone number" required/>
                    </div>
                    <label for="password">Password*</label>
                    <div>
                        <input type="password" id="password" placeholder="password" required/>
                    </div>
                    <label for="repeatedPassword">Repeat Password*</label>
                    <div>
                        <input type="password" id="repeatedPassword" placeholder="repeat password" required/>
                    </div>
                    <button type="submit">Login</button>
                </form>
            </div>
        </div>
    </section>

    <!-- end login section -->

    <%--<jsp:include page="../components/footer.jsp"/>--%>
    <%@ include file="../components/footer.jsp" %>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

</body>
</html>

