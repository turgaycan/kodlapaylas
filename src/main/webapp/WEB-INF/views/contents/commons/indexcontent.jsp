<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 13/10/15
  Time: 01:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <h2 class="heading">Yeni Fırından Çıktı!</h2>

    <div class="row">
        <div class="col-md-9 col-lg-9">
            <article class="post">
                <div class="post-type post-img"><a href="#"><img
                        src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/post.jpg"
                        class="img-responsive" alt="image post"/></a></div>
                <div class="author-info">
                    <ul class="list-inline">
                        <li>
                            <div class="icon-box"><img
                                    src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/author.png"
                                    class="img-responsive"
                                    alt="image post"/></div>
                            <div class="info">
                                <p>Yazar :</p>
                                <a href="<c:url value='/hakkimda' />">${lastArticle.user.fullname}</a></div>
                        </li>
                        <li>
                            <div class="icon-box"><i class="fa fa-calendar"></i></div>
                            <div class="info">
                                <p>Tarih :</p>
                                <strong> <fmt:formatDate value="${lastArticle.createdate}" type="both"
                                                         pattern="dd MMM, yyyy"/></strong></div>
                        </li>
                        <li>
                            <div class="icon-box"><i class="fa fa-comments-o"></i></div>
                            <div class="info">
                                <p>Yorum</p>
                                <strong>${lastArticle.commentListSize}</strong></div>
                        </li>
                        <li>
                            <div class="icon-box"><i class="fa fa-eye"></i></div>
                            <div class="info">
                                <p>Görüntüleme</p>
                                <strong>${lastArticle.viewNumber}</strong></div>
                        </li>
                        <li>
                            <div class="icon-box"><i class="fa fa-eye"></i></div>
                            <div class="info">
                                <p>İndirme</p>
                                <strong>${lastArticle.downloadNumber}</strong></div>
                        </li>
                    </ul>
                </div>
                <div class="caption">
                    <h3><a title="${lastArticle.title}"
                           href="<c:url value="/${lastArticle.buildUrl()}" />">${lastArticle.title}</a></h3>

                    <p>${fn:substring(lastArticle.content, 0, 300).contains("<code>") ? '' : fn:substring(lastArticle.content, 0, 300)}</p>

                    <div class="post-category"><a title="${fn:toLowerCase(lastArticle.articleType.name)}"
                                                  href="<c:url value="/kategori/${fn:toLowerCase(lastArticle.articleType.name)}" />"><span>&nbsp;</span> ${lastArticle.articleType.name}
                    </a></div>
                    <ul class="list-inline tags">
                        <c:forEach items="${lastArticle.articleTags}" var="tag">
                            <li><a title="${tag}" href="<c:url value="/${tag}" />">${tag}</a></li>
                        </c:forEach>
                    </ul>
                    <a class="btn btn-default btn-lg" title="<c:url value="/${lastArticle.buildUrl()}"/>"
                       href="<c:url value="/${lastArticle.buildUrl()}" />" role="button">Daha Fazla..</a></div>
            </article>
            <div class="clearfix"></div>
        </div>
        <aside class="col-md-3 col-lg-3">
            <div class="row">
                <script type="text/javascript">
                    $.get("/recent-articles-${lastArticle.id}",
                            async = true,
                            function (data, status) {
                                $('#recent-articles').append("" + data);
                            });

                </script>
                <div id="recent-articles"></div>

                <div class="col-sm-6 col-md-12 col-lg-12">
                    <div class="panel panel-default theme-panel">
                        <div class="panel-heading">Categories</div>
                        <div class="panel-body nopadding">
                            <div class="list-group"><a href="#" class="list-group-item">LifeStyle</a> <a href="#"
                                                                                                         class="list-group-item">SmartPhones</a>
                                <a href="#" class="list-group-item">Business</a> <a href="#"
                                                                                    class="list-group-item">Graphic
                                    Design</a> <a href="#" class="list-group-item">Agriculture</a> <a href="#"
                                                                                                      class="list-group-item">Music</a>
                                <a href="#" class="list-group-item">Travel</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </aside>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div id="parallax-1" class="bgParallax" data-speed="15">
                <div class="color-overlay black">
                    <h3><span>Hot News:</span> The Heading Text Size Should Match With the Size Of The Image</h3>
                </div>
            </div>
        </div>
    </div>
    <h2 class="heading">Travel</h2>

    <div class="row">
        <div class="col-md-8 col-lg-8">
            <div class="row">
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
                                        <p>Posted by:</p>
                                        <a href="author.html">Waqas Hussain</a></div>
                                </li>
                                <li>
                                    <div class="info">
                                        <p>Posted on:</p>
                                        <strong>Mar 21, 2015</strong></div>
                                </li>
                            </ul>
                        </div>
                        <div class="caption">
                            <h3 class="md-heading"><a href="#">The Heading Text Size Should Match</a></h3>

                            <p> Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus
                                mus. </p>

                            <div class="post-category"><a href="#"><span>&nbsp;</span> Featured image</a></div>
                            <a class="btn btn-default" href="#" role="button">Read More</a></div>
                    </article>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                    <article class="post">
                        <div class="post-type post-audio">
                            <iframe width="100" height="200"
                                    src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/201003535&amp;auto_play=false&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;visual=true"></iframe>
                        </div>
                        <div class="author-info author-info-2">
                            <ul class="list-inline">
                                <li>
                                    <div class="info">
                                        <p>Posted by:</p>
                                        <a href="author.html">Waqas Hussain</a></div>
                                </li>
                                <li>
                                    <div class="info">
                                        <p>Posted on:</p>
                                        <strong>Mar 21, 2015</strong></div>
                                </li>
                            </ul>
                        </div>
                        <div class="caption">
                            <h3 class="md-heading"><a href="#">The Heading Text</a></h3>

                            <p> Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus
                                mus. </p>

                            <div class="post-category"><a href="#"><span>&nbsp;</span> Audio Post</a></div>
                            <a class="btn btn-default" href="#" role="button">Read More</a></div>
                    </article>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <aside class="col-md-3 col-lg-3">
            <div class="row">
                <div class="col-sm-6 col-md-12 col-lg-12">
                    <div class="panel panel-default theme-panel">
                        <div class="panel-heading">Search Post</div>
                        <div class="panel-body">
                            <form action="#" method="post">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                    </span></div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-12 col-lg-12">
                    <div class="panel panel-default theme-panel">
                        <div class="panel-heading">Archives</div>
                        <div class="panel-body nopadding">
                            <div class="list-group"><a href="#" class="list-group-item">January 2014</a> <a href="#"
                                                                                                            class="list-group-item">February
                                2014</a> <a href="#" class="list-group-item">March 2014</a> <a href="#"
                                                                                               class="list-group-item">April
                                2014</a> <a href="#" class="list-group-item">May 2014</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </aside>
    </div>
    <h2 class="heading">Music</h2>

    <div class="row">
        <div class="col-md-8 col-lg-8">
            <article class="post vt-post">
                <div class="row">
                    <div class="col-xs-12 col-sm-5 col-md-5 col-lg-4">
                        <div class="post-type post-img"><a href="#"><img
                                src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/m1.jpg"
                                class="img-responsive"
                                alt="image post"/></a></div>
                        <div class="author-info author-info-2">
                            <ul class="list-inline">
                                <li>
                                    <div class="info">
                                        <p>Posted on:</p>
                                        <strong>Mar 21, 2015</strong></div>
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
                            <h3 class="md-heading"><a href="#">The Heading Text Size Should Match</a></h3>

                            <p> Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus
                                mus. Donec ullamcorper nulla non metus auctor fringilla. </p>
                            <a class="btn btn-default" href="#" role="button">Read More</a></div>
                    </div>
                </div>
            </article>
            <article class="post vt-post">
                <div class="row">
                    <div class="col-xs-12 col-sm-5 col-md-5 col-lg-4">
                        <div class="post-type post-img"><a href="#"><img
                                src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/m2.jpg"
                                class="img-responsive"
                                alt="image post"/></a></div>
                        <div class="author-info author-info-2">
                            <ul class="list-inline">
                                <li>
                                    <div class="info">
                                        <p>Posted on:</p>
                                        <strong>Mar 21, 2015</strong></div>
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
                            <h3 class="md-heading"><a href="#">The Heading Text Size Should Match</a></h3>

                            <p> Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus
                                mus. Donec ullamcorper nulla non metus auctor fringilla.</p>
                            <a class="btn btn-default" href="#" role="button">Read More</a></div>
                    </div>
                </div>
            </article>
            <div class="pagination-wrap">
                <ul class="pagination">
                    <li><a href="#" aria-label="Previous"> <span aria-hidden="true">Previous</span> </a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">...</a></li>
                    <li class="active"><a href="#" aria-label="Next"> <span aria-hidden="true">Next</span> </a></li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
        <aside class="col-md-3 col-lg-3">
            <div class="row">
                <div class="col-sm-6 col-md-12 col-lg-12">
                    <div class="panel panel-default theme-panel">
                        <div class="panel-heading">Subscribe us</div>
                        <div class="panel-body">
                            <form action="#" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="@ Email">
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-default btn-lg pull-right" type="button">Subscribe
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-12 col-lg-12">
                    <div class="panel panel-default theme-panel">
                        <div class="panel-heading">Tags</div>
                        <div class="panel-body">
                            <ul class="list-inline tags">
                                <li><a href="#">LifeStyle</a></li>
                                <li class="big"><a href="#">Music</a></li>
                                <li><a href="#">SmartPhones</a></li>
                                <li><a href="#">Business</a></li>
                                <li><a href="#">Travel</a></li>
                                <li class="big"><a href="#">Business</a></li>
                                <li class="small"><a href="#">LifeStyle</a></li>
                                <li><a href="#">SmartPhones</a></li>
                                <li><a href="#">Fireworks</a></li>
                                <li class="big"><a href="#">Travel</a></li>
                                <li><a href="#">Fireworks</a></li>
                                <li class="small"><a href="#">Music</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </aside>
    </div>
</div>
