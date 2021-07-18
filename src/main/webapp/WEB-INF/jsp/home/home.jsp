<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 18.07.2021
  Time: 16:29
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
    <jsp:include page="../components/header.jsp"/>
    <!-- slider section -->
    <section class=" slider_section position-relative">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="detail-box">
                                    <div>
                                        <h1>
                                            E D U C A T I O N
                                        </h1>
                                        <a href="">
                                            Read More
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item ">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="detail-box">
                                    <div>
                                        <h1>
                                            E D U C A T I O N
                                        </h1>
                                        <a href="">
                                            Read More
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item ">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="detail-box">
                                    <div>
                                        <h1>
                                            E D U C A T I O N
                                        </h1>
                                        <a href="">
                                            Read More
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- end slider section -->
</div>

<!-- special section -->

<section class="special_section">
    <div class="container">
        <div class="special_container">
            <div class="box b1">
                <div class="img-box">
                    <img src="images/award.png" alt="" />
                </div>
                <div class="detail-box">
                    <h4>
                        BEST <br />
                        INDUSTRY LEADERS
                    </h4>
                    <a href="">
                        Read More
                    </a>
                </div>
            </div>
            <div class="box b2">
                <div class="img-box">
                    <img src="images/study.png" alt="" />
                </div>
                <div class="detail-box">
                    <h4>
                        LEARN <br />
                        COURSES ONLINE
                    </h4>
                    <a href="">
                        Read More
                    </a>
                </div>
            </div>
            <div class="box b3">
                <div class="img-box">
                    <img src="images/books-stack-of-three.png" alt="" />
                </div>
                <div class="detail-box">
                    <h4>
                        BEST <br />
                        LIBRARY & STORE
                    </h4>
                    <a href="">
                        Read More
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- end special section -->

<!-- about section -->
<section class="about_section layout_padding">
    <div class="side_img">
        <img src="images/side-img.png" alt="" />
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="img_container">
                    <div class="img-box b1">
                        <img src="images/a-1.jpg" alt="" />
                    </div>
                    <div class="img-box b2">
                        <img src="images/a-2.jpg" alt="" />
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="detail-box">
                    <div class="heading_container">
                        <h3>
                            About Our College
                        </h3>
                        <p>
                            It is a long established fact that a reader will be distracted
                            by the readable content of a page when looking at its layout.
                            The point of using Lorem Ipsum is that it has a more it
                        </p>
                        <a href="">
                            Read More
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- end about section -->

<!-- course section -->

<section class="course_section layout_padding-bottom">
    <div class="side_img">
        <img src="images/side-img.png" alt="" />
    </div>
    <div class="container">
        <div class="heading_container">
            <h3>
                POPULAR COURSES
            </h3>
            <p>
                It is a long established fact that a reader will be distracted
            </p>
        </div>
        <div class="course_container">
            <div class="course_content">
                <div class="box">
                    <img src="images/c-1.jpg" alt="" />
                    <a href="" class="">
                        <img src="images/link.png" alt="" />
                    </a>
                    <h5>
                        LMS <br />
                        Content
                    </h5>
                </div>
                <div class="box">
                    <img src="images/c-2.jpg" alt="" />
                    <a href="" class="">
                        <img src="images/link.png" alt="" />
                    </a>
                    <h5>
                        From <br />
                        Zero to Hero
                    </h5>
                </div>
            </div>
            <div class="course_content">
                <div class="box">
                    <img src="images/c-3.jpg" alt="" />
                    <a href="" class="">
                        <img src="images/link.png" alt="" />
                    </a>
                    <h5>
                        Learn <br />
                        Python – Interactive
                    </h5>
                </div>
                <div class="box">
                    <img src="images/c-4.jpg" alt="" />
                    <a href="" class="">
                        <img src="images/link.png" alt="" />
                    </a>
                    <h5>
                        Your <br />
                        Complete Guide
                    </h5>
                </div>
                <div class="box">
                    <img src="images/c-5.jpg" alt="" />
                    <a href="" class="">
                        <img src="images/link.png" alt="" />
                    </a>
                    <h5>
                        Photography
                    </h5>
                </div>
            </div>
        </div>
        <div class="btn-box">
            <a href="">
                Read More
            </a>
        </div>
    </div>
</section>

<!-- end course section -->

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

<!-- event section -->
<section class="event_section layout_padding">
    <div class="container">
        <div class="heading_container">
            <h3>
                Events
            </h3>
            <p>
                Upcoming Education Events to feed your brain.
            </p>
        </div>
        <div class="event_container">
            <div class="box">
                <div class="img-box">
                    <img src="images/event-img.jpg" alt="" />
                </div>
                <div class="detail-box">
                    <h4>
                        Education Events 2021
                    </h4>
                    <h6>
                        8:00 AM - 5:00 PM VENICE, ITALY
                    </h6>
                </div>
                <div class="date-box">
                    <h3>
              <span>
                15
              </span>
                        March
                    </h3>
                </div>
            </div>
            <div class="box">
                <div class="img-box">
                    <img src="images/event-img.jpg" alt="" />
                </div>
                <div class="detail-box">
                    <h4>
                        Education Events 2021
                    </h4>
                    <h6>
                        8:00 AM - 5:00 PM VENICE, ITALY
                    </h6>
                </div>
                <div class="date-box">
                    <h3>
              <span>
                15
              </span>
                        February
                    </h3>
                </div>
            </div>
        </div>
        <div class="btn-box">
            <a href="">
                Read More
            </a>
        </div>
    </div>
</section>

<!-- end event section -->

<!-- client section -->

<section class="client_section layout_padding-bottom">
    <div class="container">
        <div class="heading_container">
            <h3>
                What People Say
            </h3>
            <p>
                It is a long established fact that a reader will be distracted
            </p>
        </div>
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="box">
                        <div class="img-box">
                            <img src="images/client.png" alt="" />
                        </div>
                        <div class="detail-box">
                            <h5>
                                Distracted by
                            </h5>
                            <p>
                                It is a long established fact that a reader will be
                                distracted by the readable content of a page when looking at
                                its layout. The point of using Lorem Ipsum is that it has a
                                more-or-less normal distribution of letters
                            </p>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="box">
                        <div class="img-box">
                            <img src="images/client.png" alt="" />
                        </div>
                        <div class="detail-box">
                            <h5>
                                Distracted by
                            </h5>
                            <p>
                                It is a long established fact that a reader will be
                                distracted by the readable content of a page when looking at
                                its layout. The point of using Lorem Ipsum is that it has a
                                more-or-less normal distribution of letters
                            </p>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="box">
                        <div class="img-box">
                            <img src="images/client.png" alt="" />
                        </div>
                        <div class="detail-box">
                            <h5>
                                Distracted by
                            </h5>
                            <p>
                                It is a long established fact that a reader will be
                                distracted by the readable content of a page when looking at
                                its layout. The point of using Lorem Ipsum is that it has a
                                more-or-less normal distribution of letters
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="btn-box">
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</section>

<!-- end client section -->

<!-- contact section -->

<section class="contact_section ">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="detail-box">
                    <div class="heading_container">
                        <h3>
                            Contact Us
                        </h3>
                        <p>
                            It is a long established fact that a reader will be distracted
                            by the readable
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="contact-form">
                    <h5>
                        Get In Touch
                    </h5>
                    <form action="">
                        <div>
                            <input type="text" placeholder="Full Name " />
                        </div>
                        <div>
                            <input type="text" placeholder="Phone Number" />
                        </div>
                        <div>
                            <input type="email" placeholder="Email Address" />
                        </div>
                        <div>
                            <input type="text" placeholder="Message" class="input_message" />
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn_on-hover">
                                Send
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- end contact section -->

<jsp:include page="../components/footer.jsp"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

</body>
</html>
