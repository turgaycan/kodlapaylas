<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 20/12/15
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<article class="post vt-post">

    <div class="row">

        <div class="col-xs-12 col-sm-5 col-md-5 col-lg-4">

            <div class="post-type post-img">

                <a href="<c:url value="/${article.buildUrl()}" />" title="${article.title}"><img
                        src="<c:url value='/resources/static/img/${fn:toLowerCase(article.buildResizedImageUrl("300_300"))}' />"
                        class="img-responsive"
                        alt="${article.title}"/></a>
            </div>

            <div class="author-info author-info-2">

                <ul class="list-inline">

                    <li>
                        <div class="info">

                            <p>Tarih</p>

                            <strong>
                                <fmt:formatDate value="${article.createdate}" type="both"
                                                pattern="dd MMM, yyyy"/>
                            </strong></div>
                    </li>
                    <li>
                        <div class="info">
                            <p>Görüntüleme</p>

                            <strong>${article.viewNumber}</strong></div>
                    </li>
                </ul>
            </div>
        </div>

        <div class="col-xs-12 col-sm-7 col-md-7 col-lg-8">

            <div class="caption">

                <h3 class="md-heading"><a
                        href="<c:url value="/${article.buildUrl()}" />">${article.title}</a>
                </h3>

                <p> ${fn:substring(article.content, 0, 100).contains("<code>") ? '' : fn:substring(article.content, 0, 100)}</p>

                <a class="btn btn-default" href="<c:url value="/${article.buildUrl()}" />"
                   role="button">Daha Fazlası..</a>

            </div>

        </div>
    </div>
</article>