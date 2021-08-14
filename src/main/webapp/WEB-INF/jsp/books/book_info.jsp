<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 28.07.2021
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>

<body class="sub_page">
<div class="hero_area">
  <%@ include file="../components/header.jsp"%>
  <c:set var="request" scope="request" value="${book}"/>
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
            <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${request.img}" alt="" />
          </div>
          <div class="img-box b2">
            <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${request.authorImg}" alt="" />
          </div>
          <c:if test="${role.toString().equals(librarian)}">
            <label for="coverUpload"><fmt:message key="librarian.uploadBookCover"/> </label>
            <form action="upload_book_cover.do?bookId=${request.id}" method="post" enctype="multipart/form-data"
                  id="coverUpload">
              <input type="file" name="bookCover" class="form-control-file" accept="image/jpeg, image/png"/>
              <input type="submit" class="btn btn-outline-secondary"
                     value="<fmt:message key="button.upload"/>"/>
            </form>

            <label for="authorPhotoUpload"><fmt:message key="librarian.uploadAuthorPhoto"/></label>
            <form action="upload_author_photo.do?bookId=${request.id}" method="post" enctype="multipart/form-data"
                  id="authorPhotoUpload">
              <input type="file" name="bookAuthorPhoto" class="form-control-file" accept="image/jpeg, image/png"/>
              <input type="submit" class="btn btn-outline-secondary" value="<fmt:message key="button.upload"/>"/>
            </form>

            <label for="pdfUpload"><fmt:message key="librarian.uploadBookPDf"/></label>
            <form action="upload_pdf.do?bookId=${request.id}" method="post" enctype="multipart/form-data"
                  id="pdfUpload">
              <input type="file" name="bookPdf" class="form-control-file" accept="application/pdf"/>
              <input type="submit" class="btn btn-outline-secondary" value="<fmt:message key="button.upload"/>"/>
            </form>
          </c:if>

        </div>
      </div>
      <div class="col-md-6">
        <div class="detail-box">
          <div class="heading_container">
            <h3><ctg:out text="${request.title}"/></h3>
            <h4><ctg:out text="${request.authorPseudo}"/></h4>
            <h5>ISBN: <ctg:out text="${request.isbn}"/></h5>
            <h5><fmt:message key="books.genre"/>:<ctg:out text="${request.genre}"/> </h5>
            <h5><fmt:message key="books.availableQuantity"/>:<ctg:out text="${request.availableQuantity}"/> </h5>
            <hr style="width:100%;text-align:left;margin-left:0">
            <p>
              <ctg:out text="${request.shortDescription}"/>
            </p>

            <c:if test="${role.toString().equals(reader)}">
              <div class="dropdown">
                <a class="dropdown-toggle" id="navbarDropdown" data-bs-toggle="dropdown"
                   aria-expanded="false"><fmt:message key="reader.rent"/></a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <li><a class="dropdown-item btn btn-secondary" role="button"
                         href="rent_book.do?bookId=${request.id}&requestType=to_reading_room">
                    <fmt:message key="reader.rentToReadingRoom"/></a></li>
                  <li><a class="dropdown-item btn btn-secondary" role="button"
                         href="rent_book.do?bookId=${request.id}&requestType=for_subscription">
                    <fmt:message key="reader.rentForSubscription"/></a></li>
                </ul>
              </div>
            </c:if>

            <c:if test="${role.toString().equals(librarian)}">
              <button type="button" class="btn btn-secondary" data-bs-toggle="modal"
                      data-bs-target="#editProfileModal">
                <fmt:message key="button.edit"/>
              </button>
              <hr style="width:100%;text-align:left;margin-left:0">
              <c:if test="${!request.pdf.equals('')}">
                <a class="btn btn-secondary" href="view_pdf.do?bookPdf=${request.pdf}" role="button">
                  <fmt:message key="button.read"/>
                </a>
              </c:if>
              <a class="btn btn-secondary" href="delete_book.do?bookId=${request.id}" role="button">
                <fmt:message key="button.delete"/>
              </a>
            </c:if>

            <div class="modal fade" id="editProfileModal" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h4 class="modal-title"><fmt:message key="button.edit"/></h4>
                  </div>

                  <div class="modal-body">
                    <form id="editBookForm" method="POST" action="edit_book.do?bookId=${request.id}">
                      <label for="inputBookTitle"><fmt:message key="books.title"/></label>
                      <div class="form-group mt-1">
                        <input type="text" id="inputBookTitle" name="bookTitle" class="form-control field"
                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required
                               value="${request.title}" pattern="[А-Яа-я\w\p{Blank}]{3,255}"/>
                      </div>

                      <label for="inputBookAuthor"><fmt:message key="books.author"/></label>
                      <div class="form-group mt-1">
                        <input type="text" id="inputBookAuthor" name="bookAuthor" class="form-control field"
                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required
                               value="${request.authorPseudo}" pattern="[А-Яа-яa-zA-Z.\s]{3,255}"/>
                      </div>

                      <label for="inputBookISBN">ISBN-13</label>
                      <div class="form-group mt-1">
                        <input type="text" id="inputBookISBN" name="bookISBN" class="form-control field"
                               placeholder="<fmt:message key="books.isbnPlaceholder"/>" required
                               value="${request.isbn}" pattern="[\d]{13}"/>
                      </div>

                      <label for="inputBookGenre"><fmt:message key="books.genre"/></label>
                      <div class="form-group mt-1">
                        <input type="text" id="inputBookGenre" name="bookGenre" class="form-control field"
                               placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required
                               value="${request.genre}" pattern="[А-Яа-яa-zA-Z]{3,45}"/>
                      </div>

                      <label for="inputBookQuantity"><fmt:message key="books.availableQuantity"/></label>
                      <div class="form-group mt-1">
                        <input type="text" id="inputBookQuantity" name="bookQuantity" class="form-control field"
                               placeholder="<fmt:message key="books.quantityPlaceholder"/>" required
                               value="${request.availableQuantity}" pattern="[\d]{1,4}"/>
                      </div>

                      <label for="inputBookDescription"><fmt:message key="books.shortDescription"/></label>
                      <div class="form-group mt-1">
                            <textarea id="inputBookDescription" name="bookDescription" class="form-control"
                                      rows="5"
                                      placeholder="<fmt:message key="register.inputNamePlaceholder"/>"
                                      required minlength="3" maxlength="1000">${request.shortDescription}</textarea>
                      </div>
                    </form>
                  </div>

                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                      <fmt:message key="button.close"/>
                    </button>
                    <button type="submit" class="btn btn-primary" form="editBookForm">
                      <fmt:message key="button.save"/></button>
                  </div>
                </div>
              </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</section>

</body>

</html>
