<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 28.07.2021
  Time: 13:47
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

</head>

<body class="sub_page">
<div class="hero_area">
  <%@ include file="../components/header.jsp"%>
  <c:set var="book" scope="request" value="${book}"/>
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
            <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${book.img}" alt="" />
          </div>
          <div class="img-box b2">
            <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${book.authorImg}" alt="" />
          </div>
          <c:if test="${sessionScope.userId.equals(user.id)}">
            <label for="coverUpload">Upload book cover </label>
            <form action="upload_book_cover.do?bookId=${book.id}" method="post" enctype="multipart/form-data" id="coverUpload">
              <input type="file" name="bookCover" class="form-control-file"/>
              <input type="submit" class="btn btn-outline-secondary" value="Upload"/>
            </form>
          </c:if>

          <label for="authorPhotoUpload">Upload author photo</label>
          <c:if test="${sessionScope.userId.equals(user.id)}">
            <form action="upload_author_photo.do?bookId=${book.id}" method="post" enctype="multipart/form-data" id="authorPhotoUpload">
              <input type="file" name="bookAuthorPhoto" class="form-control-file"/>
              <input type="submit" class="btn btn-outline-secondary" value="Upload"/>
            </form>
          </c:if>
        </div>
      </div>
      <div class="col-md-6">
        <div class="detail-box">
          <div class="heading_container">
            <h3>${book.title}</h3>
            <h4>${book.authorPseudo}</h4>
            <h5>ISBN: ${book.isbn}</h5>
            <h5><fmt:message key="books.genre"/>: ${book.genre}</h5>
            <h5><fmt:message key="books.availableQuantity"/>: ${book.availableQuantity}</h5>
            <hr style="width:100%;text-align:left;margin-left:0">
            <p>
              ${book.shortDescription}
            </p>
            <c:if test="${role.toString().equals(reader)}">
              <a href="">Rent</a>
            </c:if>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

</body>

</html>
