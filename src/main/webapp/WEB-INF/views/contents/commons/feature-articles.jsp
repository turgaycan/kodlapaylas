<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 13/10/15
  Time: 01:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<section id="feature-posts" class="section">
    <div class="feature-posts-grid owl-carousel owl-theme" id="feature-posts-grid">
        <c:forEach items="${featureArticles}" var="article">
            <article class="col-md-12 col-sm-12 nopadding item"><a
                    href="<c:url value="${article.url}-${article.id}" />"
                    class="thumbnail"> <img
                    src="<c:url value='/resources/static/img/${fn:toLowerCase(article.mainImageUrl)}' />"
                    alt="${article.title}"> </a>

                <div class="feature-text">
                    <h3><a href="<c:url value="${article.url}-${article.id}" />"
                           title="${article.title}">${article.title}</a></h3>

                    <p>
                        <a title="${article.categoryName}"
                           href="<c:url value="/kategori/${fn:toLowerCase(article.categoryName)}" />">
                                ${article.categoryName}
                        </a></p>
                </div>
            </article>
        </c:forEach>
    </div>
</section>