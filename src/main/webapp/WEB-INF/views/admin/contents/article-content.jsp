<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 08/02/17
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="<spring:url value="/resources/static/admin/admin-style.css" />" type="text/css"/>

<div id="wrapper">
    <%@include file="navigation.jsp" %>
    <div id="page-wrapper">
        <a href="<c:url value="/edit/article/${article.id}"/>" class="btn btn-outline btn-primary btn-lg btn-block">Düzenle</a>
        <a href="<c:url value="/${article.buildUrl()}" />" target="_blank" class="btn btn-success" title="${article.title}">Canlı görünüm!</a>
        <div class="container">

            <h2 class="heading">${article.title}</h2>
            <div class="row">

                <div class="col-md-9 col-lg-9">

                    <article class="post">

                        <div class="post-type post-img">

                            <a href="#"><img
                                    src="<c:url value='/resources/static/img/${article.buildResizedImageUrl("300_300")}' />"
                                    class="img-responsive" style="height: 400px !important;"
                                    alt="${fn:toLowerCase(article.categoryName)}"/></a>

                        </div>

                        <div class="author-info">

                            <ul class="list-inline">
                                <li>
                                    <div class="icon-box" style="width: auto"><img
                                            src="<c:url value='/resources/static/img/tcan_min.png' />"
                                            class="img-responsive" style="width: 100px !important;"
                                            alt="Turgay Can"/></div>
                                    <div class="info">
                                        <p>Yazar</p>
                                        <a href="<c:url value='/hakkimda'/>"> ${article.user.fullname}</a>
                                    </div>
                                </li>
                                <li>
                                    <div class="icon-box"><i class="fa fa-calendar"></i></div>

                                    <div class="info">

                                        <p>Tarih</p>

                                        <strong>
                                            <fmt:formatDate value="${article.createdate}" type="both"
                                                            pattern="dd MMM, yyyy"/>
                                        </strong>
                                    </div>

                                </li>

                                <li>

                                    <div class="icon-box"><i class="fa fa-comments-o"></i></div>

                                    <div class="info">

                                        <p>Yorum</p>

                                        <a href="#comment-list"><strong>${commentsCount}</strong></a></div>

                                </li>

                                <li>
                                    <div class="icon-box"><i class="fa fa-eye"></i></div>

                                    <div class="info">

                                        <p>Görüntüleme</p>

                                        <strong>${article.viewNumber}</strong></div>

                                </li>

                                <li>

                                    <div class="icon-box"><i class="fa fa-eye"></i></div>

                                    <div class="info">

                                        <p>İndirme</p>

                                        <strong>${article.downloadNumber}</strong></div>

                                </li>

                            </ul>

                        </div>

                        <div class="caption">

                            <h3>${article.title}</h3>

                            <p>${article.content}</p>

                        </div>

                        <div class="line-block">


                            <ul class="list-inline tags">

                                <c:forEach items="${article.articleTags}" var="tag">
                                    <li><a href="<c:url value="/${tag}" />">${tag}</a></li>
                                </c:forEach>
                            </ul>

                        </div>


                    </article>

                    <div class="clearfix"></div>

                </div>
            </div>
        </div>

    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

