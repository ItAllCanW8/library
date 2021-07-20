<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 19.07.2021
  Time: 12:52
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
        <div class="row">
            <div class="col-md-6">
                <div class="detail-box">
                    <h3>
                        GET ONLINE COURSES FOR FREE
                    </h3>
                    <p>
                        Create your free account now and get immediate access to 100s of
                        online courses
                    </p>
                    <a href="">
                        REGISTER NOW
                    </a>
                </div>
            </div>
            <div class="col-md-6">
                <div class="login_form">
                    <h5>
                        Login Now
                    </h5>
                    <form action="">
                        <div>
                            <input type="email" placeholder="Email " />
                        </div>
                        <div>
                            <input type="password" placeholder="Password" />
                        </div>
                        <button type="submit">Login</button>
                    </form>
                </div>
            </div>
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