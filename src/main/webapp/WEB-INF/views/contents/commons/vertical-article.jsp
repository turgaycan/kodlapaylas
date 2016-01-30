<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 20/12/15
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
    <article class="post">
        <div class="post-type post-img"><a href="#"><img
                src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/post-1.jpg"
                class="img-responsive"
                alt="image post"/></a></div>
        <div class="author-info author-info-2">
            <ul class="list-inline">
                <li>
                    <div class="info">
                        <p>Yazar:</p>
                        <a href="<c:url value='/hakkimda' />">${vArticle.user.fullname}</a>
                    </div>
                </li>
                <li>
                    <div class="info">
                        <p>Tarih:</p>
                        <strong><fmt:formatDate value="${vArticle.createdate}"
                                                type="both"
                                                pattern="dd MMM, yyyy"/></strong></div>
                </li>
            </ul>
        </div>
        <div class="caption">
            <h3 class="md-heading"><a
                    href="<c:url value="/${vArticle.buildUrl()}" />"
                    title="${vArticle.title}">${vArticle.title}</a>
            </h3>

            <p>${fn:substring(vArticle.content, 0, 300).contains("<code>") ? '' : fn:substring(vArticle.content, 0, 300)}</p>

            <div class="post-category"><a
                    title="${fn:toLowerCase(vArticle.articleType.name)}"
                    href="<c:url value="/kategori/${fn:toLowerCase(vArticle.articleType.name)}" />"><span>&nbsp;</span> ${vArticle.articleType.name}
            </a></div>

            <a class="btn btn-default"
               title="<c:url value="/${vArticle.buildUrl()}"/>"
               href="<c:url value="/${vArticle.buildUrl()}" />" role="button">Daha
                Fazla..</a></div>
    </article>
</div>
