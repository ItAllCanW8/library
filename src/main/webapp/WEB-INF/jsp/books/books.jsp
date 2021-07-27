<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 26.07.2021
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocalization}"/>
<fmt:setBundle basename="locale.content"/>

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

<%--works--%>
<%--    <script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>--%>
<%--    <script src="http://cdn.datatables.net/1.10.3/js/jquery.dataTables.min.js"    type="text/javascript"></script>--%>
<%--    <link href="http://cdn.datatables.net/1.10.3/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />--%>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js" type="text/javascript"></script>
    <script src="http://cdn.datatables.net/1.10.3/js/jquery.dataTables.min.js"    type="text/javascript"></script>
    <link href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<%--    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>--%>


    <script>
        $(document).ready( function () {
            $('#example').DataTable();
        } );
    </script>

</head>

<body class="sub_page">

<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
    <c:set var="books" scope="session" value="${books}"/>
</div>

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
            <table id="example" class="table table-dark table-bordered border-secondary">
                <thead>
                <tr>
                    <th scope="col">title</th>
                    <th scope="col">cover</th>
                    <th scope="col">author</th>
                    <th scope="col">genre</th>
                    <th scope="col">isbn</th>
                    <th scope="col">available quantity</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${books}">
                    <tr class="table-secondary">
                        <th scope="row"><a href="<c:url value="to_book_info.do?bookId=${book.id}"/>">
                                ${book.title}</a></th>
                        <th scope="row"><div>
                            <img src="${pageContext.request.contextPath}/images/book-covers/${book.img}" alt=""
                            style="max-height: 250px;max-width: 250px"></div></th>
                        <th scope="row">${book.authorPseudo}</th>
                        <th scope="row">${book.genre}</th>
                        <th scope="row">${book.isbn}</th>
                        <th scope="row">${book.availableQuantity}</th>
                    <tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

</body>
<%--TODO: deal with jquery imports--%>
</html>