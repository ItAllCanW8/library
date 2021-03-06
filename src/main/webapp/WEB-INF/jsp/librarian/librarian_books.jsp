<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 03.08.2021
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<body class="sub_page">
<div class="hero_area">
    <%@ include file="../components/header.jsp" %>
</div>

<section class="event_section layout_padding">
    <div class="container">

        <wrong-message>
            ${errorInputData}
            ${noBooks}
            ${errorBookCreation}
        </wrong-message>

        <div class="heading_container">
            <h3>
                <fmt:message key="header.booksManagement"/>
            </h3>
            <button type="button" class="btn btn-secondary" style="margin-left: 30.7%; width: 40%" data-bs-toggle="modal"
                    data-bs-target="#addBookModal">
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
                            <th scope="row"><a href="${pageContext.request.contextPath}/load_book_info.do?bookId=${book.id}">
                                <ctg:out text="${book.title}"/></a></th>
                            <th scope="row">
                                <div>
                                    <img src="${pageContext.request.contextPath}/load_book_cover.do?fileName=${book.img}" alt=""
                                         style="max-height: 250px;max-width: 250px"></div>
                            </th>
                            <th scope="row"><ctg:out text="${book.authorPseudo}"/></th>
                            <th scope="row"><ctg:out text="${book.genre}"/></th>
                            <th scope="row"><ctg:out text="${book.isbn}"/></th>
                            <th scope="row"><ctg:out text="${book.availableQuantity}"/></th>
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
                    <form id="addBookForm" method="POST" action="${pageContext.request.contextPath}/add_book.do">
                        <label for="inputBookTitle"><fmt:message key="books.title"/></label>
                        <div class="form-group mt-1">
                            <input type="text" id="inputBookTitle" name="bookTitle" class="form-control field"
                                   placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required
                                   value="${title}" pattern="[??-????-??\w\p{Blank}.]{3,255}"/>
                        </div>

                        <label for="inputBookAuthor"><fmt:message key="books.author"/></label>
                        <div class="form-group mt-1">
                            <input type="text" id="inputBookAuthor" name="bookAuthor" class="form-control field"
                                   placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required
                                   value="${bookAuthor}" pattern="[??-????-??a-zA-Z.\s]{3,255}"/>
                        </div>

                        <label for="inputBookISBN">ISBN-13</label>
                        <div class="form-group mt-1">
                            <input type="text" id="inputBookISBN" name="bookISBN" class="form-control field"
                                   placeholder="<fmt:message key="books.isbnPlaceholder"/>" required
                                   value="${bookISBN}" pattern="[\d]{13}"/>
                        </div>

                        <label for="inputBookGenre"><fmt:message key="books.genre"/></label>
                        <div class="form-group mt-1">
                            <input type="text" id="inputBookGenre" name="bookGenre" class="form-control field"
                                   placeholder="<fmt:message key="register.inputNamePlaceholder"/>" required
                                   value="${bookGenre}" pattern="[??-????-??a-zA-Z]{3,45}"/>
                        </div>

                        <label for="inputBookQuantity"><fmt:message key="books.availableQuantity"/></label>
                        <div class="form-group mt-1">
                            <input type="text" id="inputBookQuantity" name="bookQuantity" class="form-control field"
                                   placeholder="<fmt:message key="books.quantityPlaceholder"/>" required
                                   value="${bookQuantity}" pattern="[\d]{1,4}"/>
                        </div>

                        <label for="inputBookDescription"><fmt:message key="books.shortDescription"/></label>
                        <div class="form-group mt-1">
                            <textarea type="text" id="inputBookDescription" name="bookDescription" class="form-control"
                                  rows="5"
                                  placeholder="<fmt:message key="register.inputNamePlaceholder"/>"
                                  required minlength="3" maxlength="1000">${bookDescription}</textarea>
                        </div>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
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

