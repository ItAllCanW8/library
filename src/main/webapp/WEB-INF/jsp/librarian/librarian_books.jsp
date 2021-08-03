<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 03.08.2021
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <!-- Basic -->
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <!-- Site Metas -->
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <title>Joson</title>

    <!-- bootstrap core css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <!-- fonts style -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Poppins:400,600,700&display=swap"
          rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>
    <!-- responsive style -->
    <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet"/>
</head>

<body class="sub_page">
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
    <%--    <c:set var="book" scope="request" value="${book}"/>--%>
</div>

<%--<section class="about_section layout_padding">--%>
<%--    <div class="side_img">--%>
<%--        <img src="${pageContext.request.contextPath}/images/layout/side-img.png" alt="" />--%>
<%--    </div>--%>
<%--    <div class="container">--%>
<%--        <div class="row">--%>
<%--            <div class="col-md-6">--%>
<%--                <div class="img_container">--%>
<%--&lt;%&ndash;                    <div class="img-box b1">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <label for="uploadBookCover">Upload book cover</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <form action="upload_photo.do" method="post" enctype="multipart/form-data">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <input type="file" name="file" id="uploadBookCover" class="form-control-file"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <input type="submit" class="btn btn-outline-secondary" value="Upload"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </form>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <div class="img-box b2">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <label for="uploadAuthorsPhoto">Upload author's photo</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <form action="upload_photo.do" method="post" enctype="multipart/form-data">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <input type="file" name="file" id="uploadAuthorsPhoto" class="form-control-file"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <input type="submit" class="btn btn-outline-secondary" value="Upload"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </form>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <div class="col-md-6">--%>
<%--                <div class="detail-box">--%>
<%--                    <div class="heading_container">--%>
<%--&lt;%&ndash;                        <label for="inputBookTitle"><fmt:message key="books.title"/></label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="text" id="inputBookTitle" name="bookTitle"&ndash;%&gt;--%>
<%--&lt;%&ndash;                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>&ndash;%&gt;--%>

<%--&lt;%&ndash;                        <label for="inputBookAuthor"><fmt:message key="books.author"/></label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="text" id="inputBookAuthor" name="bookAuthor"&ndash;%&gt;--%>
<%--&lt;%&ndash;                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>&ndash;%&gt;--%>

<%--&lt;%&ndash;                        <label for="inputBookISBN">ISBN</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="text" id="inputBookISBN" name="bookISBN"&ndash;%&gt;--%>
<%--&lt;%&ndash;                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>&ndash;%&gt;--%>

<%--&lt;%&ndash;                        <label for="inputBookGenre"><fmt:message key="books.genre"/></label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="text" id="inputBookGenre" name="bookGenre"&ndash;%&gt;--%>
<%--&lt;%&ndash;                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>&ndash;%&gt;--%>

<%--&lt;%&ndash;                        <label for="inputBookQuantity"><fmt:message key="books.availableQuantity"/></label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="text" id="inputBookQuantity" name="bookQuantity"&ndash;%&gt;--%>
<%--&lt;%&ndash;                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>&ndash;%&gt;--%>

<%--&lt;%&ndash;                        <label for="inputBookDescription"><fmt:message key="books.shortDescription"/></label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="text" id="inputBookDescription" name="bookDescription"&ndash;%&gt;--%>
<%--&lt;%&ndash;                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>&ndash;%&gt;--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</section>--%>

<section class="event_section layout_padding">
    <div class="container">
        <div class="heading_container">
            <button type="button" class="btn btn-secondary" style="margin-left: 30.7%; width: 40%" data-toggle="modal"
                    data-target="#addBookModal">
                <fmt:message key="button.addBook"/>
            </button>
            <hr style="width:100%;text-align:left;margin-left:0">
        </div>
        <div class="event_container">
            <table id="booksTable" class="table table-dark table-bordered border-secondary">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="books.title"/></th>
                    <th scope="col"><fmt:message key="books.cover"/></th>
                    <th scope="col"><fmt:message key="books.author"/></th>
                    <th scope="col"><fmt:message key="books.genre"/></th>
                    <th scope="col">ISBN</th>
                    <th scope="col"><fmt:message key="books.availableQuantity"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="book" items="${books}">
                <tr class="table-secondary">
                    <th scope="row"><a href="<c:url value="load_book_info.do?bookId=${book.id}"/>">
                            ${book.title}</a></th>
                    <th scope="row">
                        <div>
                            <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${book.img}" alt=""
                                 style="max-height: 250px;max-width: 250px"></div>
                    </th>
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

    <div class="modal fade" id="addBookModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message key="button.addBook"/></h4>
                </div>

                <div class="modal-body">
                    <form id="addBookForm" method="POST" action="add_book.do">
                        <label for="inputBookTitle"><fmt:message key="books.title"/></label>
                        <div class="form-group mt-1">
                            <input type="text" id="inputBookTitle" name="bookTitle" class="form-control field"
                                   placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>
                        </div>

                        <label for="inputBookAuthor"><fmt:message key="books.author"/></label>
                        <div class="form-group mt-1">
                            <input type="text" id="inputBookAuthor" name="bookAuthor" class="form-control field"
                                   placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>
                        </div>

                        <label for="inputBookISBN">ISBN</label>
                        <div class="form-group mt-1">
                            <input type="text" id="inputBookISBN" name="bookISBN" class="form-control field"
                                   placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>
                        </div>

                        <label for="inputBookGenre"><fmt:message key="books.genre"/></label>
                        <div class="form-group mt-1">
                            <input type="text" id="inputBookGenre" name="bookGenre" class="form-control field"
                                   placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>
                        </div>

                        <label for="inputBookQuantity"><fmt:message key="books.availableQuantity"/></label>
                        <div class="form-group mt-1">
                            <input type="text" id="inputBookQuantity" name="bookQuantity" class="form-control field"
                                   placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>
                        </div>

                        <label for="inputBookDescription"><fmt:message key="books.shortDescription"/></label>
                        <div class="form-group mt-1">
                            <input type="text" id="inputBookDescription" name="bookDescription" class="form-control field"
                                   placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required/>
                        </div>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <fmt:message key="button.close"/>
                    </button>
                    <button type="submit" class="btn btn-primary" form="addBookForm">
                        <fmt:message key="button.save"/></button>
                </div>
            </div>
        </div>
    </div>
</section>

</body>

</html>

