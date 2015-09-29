<%--
  Created by IntelliJ IDEA.
  User: turgaycan
  Date: 9/28/15
  Time: 12:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-6 col-md-12 col-lg-12">

    <div class="panel panel-default theme-panel">

        <div class="panel-heading">Recent Articles</div>

        <div class="panel-body nopadding">

            <div class="media">
                <!--loop -->
                <c:forEach items="${recentArticles}" var="each">
                    <div class="media-left">

                        <a href="<c:url value="${each.url}-${each.id}" />">
                            <img src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/rp1.jpg"
                                 alt="author"/>
                        </a>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading"><a href="<c:url value="${each.url}-${each.id}" />">${each.title}</a>
                        </h4>

                        <p>
                            <a href="<c:url value="${fn:toLowerCase(each.articleType.name)}" />">${each.articleType.name}</a> &bull; ${each.createdate}
                        </p>
                    </div>
                </c:forEach>
            </div>

        </div>

    </div>

</div>