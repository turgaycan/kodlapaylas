<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">

    <h2 class="heading">${article.title}</h2>

    <div class="row">

        <div class="col-md-9 col-lg-9">

            <article class="post">

                <div class="post-type post-img">

                    <a href="#"><img src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/post-2.jpg"
                                     class="img-responsive" alt="image post"/></a>

                </div>

                <div class="author-info">

                    <ul class="list-inline">

                        <li>

                            <div class="icon-box"><img
                                    src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/author.png"
                                    class="img-responsive"
                                    alt="image post"/></div>

                            <div class="info">

                                <p>Yazar</p>

                                <a href="author.html">${article.user.fullname}</a>

                            </div>

                        </li>

                        <li>

                            <div class="icon-box"><i class="fa fa-calendar"></i></div>

                            <div class="info">

                                <p>Tarih</p>

                                <strong>
                                    <fmt:formatDate value="${article.createdate}" type="both" pattern="dd MMM, yyyy"/>
                                </strong>
                            </div>

                        </li>


                        <li>

                            <div class="icon-box"><i class="fa fa-comments-o"></i></div>

                            <div class="info">

                                <p>Yorum</p>

                                <strong>127</strong></div>

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

                        <c:forEach items="${articleTags}" var="tag">
                            <li><a href="<c:url value="/${tag}" />">${tag}</a></li>
                        </c:forEach>
                    </ul>

                </div>

                <%@include file="social.jsp" %>

                <%@include file="related-articles.jsp" %>

                <div class="comment-count">

                    <h4>${commentBaseModel.commentSize} Yorum..</h4>
                    <c:if test="${userLoggedIn eq 'false'}">
                        <p>Yorum yapmak için<a href="<c:url value='/login' /> ">
                            "Giriş yapın"</a>
                            yada "Misafir üye" olarak yorum yapabilirsiniz.
                        </p>
                    </c:if>
                </div>

                <%@include file="comments.jsp" %>

                <%@include file="comment.jsp" %>


            </article>

            <div class="clearfix"></div>

        </div>

        <aside class="col-md-3 col-lg-3">


            <div class="row">

                <%@include file="commons/subscribeus.jsp" %>

                <script type="text/javascript">
                    $.get("/recent-articles-${article.id}",
                            async = true,
                            function (data, status) {
                                $('#recent-articles').append("" + data);
                            });

                </script>
                <div id="recent-articles"></div>

                <%--<%@include file="commons/recent-articles.jsp" %>--%>

                <script type="text/javascript">
                    $.get("/root-categories",
                            async = true,
                            function (data, status) {
                                $('#root-categories').append("" + data);
                            });

                </script>
                <div id="root-categories"></div>


            <%--<%@include file="commons/root-categories.jsp" %>--%>

                <%@include file="commons/tags.jsp" %>

                <div class=" col-sm-6 col-md-12 col-lg-12">

                    <div class="panel panel-default theme-panel">

                        <div class="panel-heading">
                            <a href="<c:url value="/arsiv"/> ">Arşiv</a>
                        </div>

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