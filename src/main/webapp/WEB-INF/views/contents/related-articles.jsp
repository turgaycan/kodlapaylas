<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 05/10/15
  Time: 00:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="related-post">
    <div class="row">
        <div class="col-md-12"><h4>İlginizi Çekebilecek Diğer Yazılar..</h4></div>
        <c:forEach items="${relatedArticles}" var="relatedArticle">
            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                <div class="thumbnail">
                    <a href="#"><img
                            src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/related-post1.png"
                            alt="related post"></a>

                    <div class="caption">
                        <a href="<c:url value="${relatedArticle.buildUrl()}" />">${relatedArticle.title}</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
