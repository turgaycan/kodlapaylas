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

                    <h3><a href="#">${article.title}</a></h3>

                    <p>${article.content}</p>

                    <ul class="list-inline img-grid">

                        <li class="col-sm-4 col-md-4 col-lg-4"><a href="#"><img
                                src="http://www.mirchu.net/themes/BlogDesk/assets/images/feature-posts/feature-post6.png"
                                class="img-responsive" alt=""/> </a>
                        </li>

                        <li class="col-sm-4 col-md-4 col-lg-4"><a href="#"><img
                                src="http://www.mirchu.net/themes/BlogDesk/assets/images/feature-posts/feature-post1.png"
                                class="img-responsive" alt=""/> </a>
                        </li>

                        <li class="col-sm-4 col-md-4 col-lg-4"><a href="#"><img
                                src="http://www.mirchu.net/themes/BlogDesk/assets/images/feature-posts/feature-post8.png"
                                class="img-responsive" alt=""/> </a>
                        </li>

                    </ul>

                    <h5>Excepteur sint occaecat cupidatat non proident</h5>

                    <p> Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec
                        ullamcorper nulla non metus auctor fringilla. Duis mollis, est non commodo luctus, nisi erat
                        porttitor ligula, eget lacinia odio sem nec elit. Donec ullamcorper nulla non metus auctor
                        fringilla.</p>

                    <ul class="list-unstyled">

                        <li><i class="fa fa-circle"></i> Lorem ipsum dolor sit amet</li>

                        <li><i class="fa fa-circle"></i> Consectetur adipiscing elit</li>

                        <li><i class="fa fa-circle"></i> Integer molestie lorem at massa</li>

                        <li><i class="fa fa-circle"></i> Facilisis in pretium nisl aliquet</li>

                        <li><i class="fa fa-circle"></i> Eget porttitor lorem</li>

                    </ul>

                    <blockquote>

                        <em>" Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante. "</em>

                    </blockquote>


                </div>

                <div class="line-block">


                    <ul class="list-inline tags">

                        <c:forEach items="${articleTags}" var="tag">
                            <li><a href="<c:url value="/${tag}" />">${tag}</a></li>
                        </c:forEach>
                    </ul>

                </div>

                <div class=" share-this">

                    <div class="row">

                        <div class="col-xs-5 col-sm-4 col-md-5 col-lg-4"><p>Share this post with:</p></div>

                        <div class="col-xs-7 col-sm-8 col-md-7 col-lg-8 nopadding">

                            <ul class="list-inline">


                                <li><a href="#" class="google-plus"><i class="fa fa-google-plus"></i></a></li>

                                <li><a href="#" class="facebook"><i class="fa fa-facebook"></i></a></li>

                                <li><a href="#" class="twitter"><i class="fa fa-twitter"></i></a></li>

                            </ul>

                        </div>

                    </div>

                </div>

                <div class="related-post">

                    <div class="row">

                        <div class="col-md-12"><h4>Related Topics</h4></div>

                        <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">

                            <div class="thumbnail">

                                <a href="#"><img
                                        src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/related-post1.png"
                                        alt="related post"></a>

                                <div class="caption">

                                    <a href="#">Cras sit amet nibh libero, in gravida nulla</a>

                                </div>

                            </div>

                        </div>

                        <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">

                            <div class="thumbnail">

                                <a href="#"><img
                                        src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/related-post2.png"
                                        alt="related post"></a>

                                <div class="caption">

                                    <a href="#">Cras sit amet nibh libero, in gravida nulla</a>

                                </div>

                            </div>

                        </div>

                        <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">

                            <div class="thumbnail">

                                <a href="#"><img
                                        src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/related-post3.png"
                                        alt="related post"></a>

                                <div class="caption">

                                    <a href="#">Cras sit amet nibh libero, in gravida nulla</a>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>

                <div class="comment-count">

                    <h4>32 Comments</h4>

                    <p><a href="#">Sign in</a> to comment or comment as a guest.</p>

                </div>

                <div class="comment-list">

                    <div class="media">

                        <div class="media-left">

                            <a href="#">

                                <img class="media-object"
                                     src="http://www.mirchu.net/themes/BlogDesk/assets/images/comment-thumbnail.png"
                                     alt="placeholder image">

                            </a>

                        </div>

                        <div class="media-body">

                            <p>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                                sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra
                                turpis. </p>

                            <ul class="list-inline">

                                <li><a class="media-heading" href="#">Anonymous</a></li>

                                <li>3 hours ago</li>

                                <li><a class="reply-btn" href="#">Post Reply</a></li>

                            </ul>


                        </div>

                    </div>

                    <div class="media">

                        <div class="media-left">

                            <a href="#">

                                <img class="media-object"
                                     src="http://www.mirchu.net/themes/BlogDesk/assets/images/comment-thumbnail.png"
                                     alt="placeholder image">

                            </a>

                        </div>

                        <div class="media-body">


                            <p>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                                sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra
                                turpis. </p>

                            <ul class="list-inline">

                                <li><a class="media-heading" href="#">Anonymous</a></li>

                                <li>3 hours ago</li>

                                <li><a class="reply-btn" href="#">Post Reply</a></li>

                            </ul>

                            <div class="media nested-first">

                                <div class="media-left">

                                    <a href="#">

                                        <img class="media-object"
                                             src="http://www.mirchu.net/themes/BlogDesk/assets/images/comment-thumbnail.png"
                                             alt="placeholder image">

                                    </a>

                                </div>

                                <div class="media-body">


                                    <p> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                                        sollicitudin commodo.</p>

                                    <ul class="list-inline">

                                        <li><a class="media-heading" href="#">Anonymous</a></li>

                                        <li>3 hours ago</li>

                                    </ul>

                                </div>

                            </div>

                            <div class="media">

                                <div class="media-left">

                                    <a href="#">

                                        <img class="media-object"
                                             src="http://www.mirchu.net/themes/BlogDesk/assets/images/comment-thumbnail.png"
                                             alt="placeholder image">

                                    </a>

                                </div>

                                <div class="media-body">

                                    <p> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                                        sollicitudin commodo.</p>

                                    <ul class="list-inline">

                                        <li><a class="media-heading" href="#">Anonymous</a></li>

                                        <li>3 hours ago</li>

                                    </ul>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>

                <div class="comment-form">

                    <h4>Post A Reply</h4>

                    <form role="form" action="#" method="post" novalidate id="comment-form">

                        <div class="row">

                            <div class="col-md-6 col-lg-5">

                                <div class="form-group">

                                    <input type="text" class="form-control" name="name" autocomplete="off"
                                           placeholder="Full Name:">

                                </div>

                            </div>

                        </div>

                        <div class="row">

                            <div class="col-md-6 col-lg-5">

                                <div class="form-group">

                                    <input type="email" class="form-control" name="email" autocomplete="off"
                                           placeholder="Enter Address:">

                                </div>

                            </div>

                        </div>

                        <div class="row">

                            <div class="col-md-6 col-lg-5">

                                <div class="form-group">

                                    <input type="text" class="form-control" placeholder="Phone Number:">

                                </div>

                            </div>

                        </div>

                        <div class="form-group">

                            <textarea class="form-control" name="message" placeholder="Message:" rows="3"></textarea>

                        </div>

                        <button type="submit" class="btn btn-default">Post Reply</button>

                    </form>

                </div>


            </article>

            <div class="clearfix"></div>

        </div>

        <aside class="col-md-3 col-lg-3">


            <div class="row">

                <%@include file="commons/subscribeus.jsp" %>

                <%@include file="commons/recent-articles.jsp" %>

                <%@include file="commons/categories.jsp" %>

                <%@include file="commons/tags.jsp" %>

                <div class="col-sm-6 col-md-12 col-lg-12">

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