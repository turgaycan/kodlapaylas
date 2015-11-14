<%--
  Created by IntelliJ IDEA.
  User: turgaycan
  Date: 9/29/15
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="innercontent">

    <div class="container">
        <c:if test="${empty years}">
            <h2 class="heading">Search...</h2>
        </c:if>
        <div class="row">
            <div class="col-md-8 col-lg-8">
                <c:choose>
                    <c:when test="${not empty years}">
                        <c:forEach items="${years}" var="itemYear">
                            <ul class="list-unstyled">
                                <li><i class="fa fa-circle"></i>
                                    <a href="<c:url value="/arsiv/${itemYear}" />">Arşiv ${itemYear}</a></li>
                            </ul>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${pageArticles}" var="article">

                            <article class="post vt-post">

                                <div class="row">

                                    <div class="col-xs-12 col-sm-5 col-md-5 col-lg-4">

                                        <div class="post-type post-img">

                                            <a href="#"><img
                                                    src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/m2.jpg"
                                                    class="img-responsive"
                                                    alt="image post"/></a>
                                        </div>

                                        <div class="author-info author-info-2">

                                            <ul class="list-inline">

                                                <li>
                                                    <div class="info">

                                                        <p>Posted on:</p>

                                                        <strong>
                                                            <fmt:formatDate value="${article.createdate}" type="both"
                                                                            pattern="dd MMM, yyyy"/>
                                                        </strong></div>
                                                </li>
                                                <li>
                                                    <div class="info">
                                                        <p>Comments:</p>

                                                        <strong>127</strong></div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-8">

                                        <div class="caption">

                                            <h3 class="md-heading"><a
                                                    href="<c:url value="/${article.url}-${article.id}" />">${article.title}</a>
                                            </h3>

                                            <p> ${fn:substring(article.content, 0, 100).contains("<code>") ? '' : fn:substring(article.content, 0, 100)}</p>

                                            <a class="btn btn-default"
                                               href="<c:url value="/${article.url}-${article.id}" />"
                                               role="button">Daha Fazlası..</a>

                                        </div>

                                    </div>
                                </div>
                            </article>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <c:if test="${empty years}">
                    <%@include file="commons/paging.jsp" %>
                </c:if>
                <div class="clearfix"></div>
            </div>

            <aside class="col-md-4 col-lg-4">


                <div class="row">


                    <%@include file="commons/subscribeus.jsp" %>

                    <%@include file="commons/recent-articles.jsp" %>

                    <%@include file="commons/root-categories.jsp" %>

                    <%@include file="commons/tags.jsp" %>

                    <div class="col-sm-12 col-md-12 col-lg-12">

                        <div class="panel panel-default theme-panel">

                            <div class="panel-heading">Archives</div>

                            <div class="panel-body nopadding">

                                <div class="list-group">

                                    <a href="#" class="list-group-item">January 2014</a>

                                    <a href="#" class="list-group-item">February 2014</a>

                                    <a href="#" class="list-group-item">March 2014</a>

                                    <a href="#" class="list-group-item">April 2014</a>

                                    <a href="#" class="list-group-item">May 2014</a>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>


            </aside>

        </div>

    </div>

</section>

