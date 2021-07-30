<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 28.07.2021
  Time: 15:56
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

<body class="sub_page">
<div class="hero_area">
    <%@ include file="../components/header.jsp"%>
<%--    <c:set var="user" scope="request" value="${user}"/>--%>
</div>

<!-- about section -->
<section class="about_section layout_padding">
    <div class="side_img">
        <img src="${pageContext.request.contextPath}/images/layout/side-img.png" alt="" />
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="img_container">
                    <div class="img-box b1">
                        <img src="${pageContext.request.contextPath}/load_image.do?fileName=${sessionScope.user.userDetails.photoPath}" alt="" />
                    </div>
                    <form action="upload_photo.do" method="post" enctype="multipart/form-data">
                        <input type="file" name="file" class="form-control-file"/>
                        <input type="submit" class="btn btn-outline-secondary" value="Upload"/>
                    </form>
                </div>
            </div>
            <div class="col-md-6">
                <div class="detail-box">
                    <div class="heading_container">
                        <h3>${user.username}</h3>
                        <h4>${user.email}</h4>
                        <hr style="width:100%;text-align:left;margin-left:0">
                        <h5>Name: ${user.userDetails.name}</h5>
                        <h5>Surname: ${user.userDetails.surname}</h5>
                        <h5>Date of birth: ${user.userDetails.dateOfBirth}</h5>
                        <h5>Phone number: ${user.userDetails.phoneNumber}</h5>
                        <a href="">Edit</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- end about section -->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

</body>

</html>

