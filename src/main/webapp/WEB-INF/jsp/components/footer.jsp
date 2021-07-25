<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 18.07.2021
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />

<!-- footer section -->
<footer class="container-fluid footer_section">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <ul class="list-inline text-center">
                <li class="list-inline-item">
                    <a href="http://vk.com/itallcanw8">
                            <span class="fa-stack fa-lg">
                                <i class="fas fa-circle fa-stack-2x"></i>
                                <i class="fab fa-vk fa-stack-1x fa-inverse"></i>
                            </span>
                    </a>
                </li>
                <li class="list-inline-item">
                    <a href="https://www.linkedin.com/in/itallcanw8/">
                            <span class="fa-stack fa-lg">
                                <i class="fas fa-circle fa-stack-2x"></i>
                                <i class="fab fa-linkedin fa-stack-1x fa-inverse"></i>
                            </span>
                    </a>
                </li>
                <li class="list-inline-item">
                    <a href="https://github.com/ItAllCanW8">
                            <span class="fa-stack fa-lg">
                                <i class="fas fa-circle fa-stack-2x"></i>
                                <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                            </span>
                    </a>
                </li>
            </ul>
            <h5 class="text-center m-2">About Creator</h5>
            <p>
                <h5 style="color:white">Copyright &copy; Library <fmt:formatDate value="${date}" pattern="yyyy" />  </h5>
            </p>

        </div>
    </div>
</footer>