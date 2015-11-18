<%--
  Created by IntelliJ IDEA.
  User: turgaycan
  Date: 9/28/15
  Time: 12:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-6 col-md-12 col-lg-12">

    <div class="panel panel-default theme-panel">

        <div class="panel-heading">Son Yazılar..</div>

        <div class="panel-body nopadding">

            <c:forEach items="${recentArticles}" var="each">
                <div class="media">
                    <!--loop -->
                    <div class="media-left">
                        <a href="<c:url value="/${each.buildUrl()}" />">
                            <img src="http://www.mirchu.net/themes/BlogDesk/assets/images/post/rp1.jpg"
                                 alt="author"/>
                        </a>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading"><a href="<c:url value="/${each.buildUrl()}" />">${each.title}</a>
                        </h4>

                        <p>
                            <a href="<c:url value="/kategori/${fn:toLowerCase(each.articleType.name)}" />">${each.articleType.name}</a> &bull;
                            <fmt:formatDate value="${each.createdate}" type="both" timeZone="tr"
                                            pattern="dd MMM, yyyy"/>
                        </p>
                    </div>
                </div>
            </c:forEach>

        </div>

    </div>

</div>