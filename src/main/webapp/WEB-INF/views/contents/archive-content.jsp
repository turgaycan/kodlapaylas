<%--
  Created by IntelliJ IDEA.
  User: turgaycan
  Date: 9/29/15
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${!pageArticles.isEmpty()}">
        <section class="innercontent">

            <div class="container">

                <h2 class="heading">Search...</h2>

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
                                <c:forEach var="article" items="${pageArticles}">
                                    <%@include file="commons/horizontal-article.jsp" %>
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

                            <script type="text/javascript">
                                $.get("/recent-articles-${article.id}",
                                        async = true,
                                        function (data, status) {
                                            $('#recent-articles').append("" + data);
                                        });

                            </script>
                            <div id="recent-articles"></div>

                            <script type="text/javascript">
                                $.get("/root-categories",
                                        async = true,
                                        function (data, status) {
                                            $('#root-categories').append("" + data);
                                        });

                            </script>
                            <div id="root-categories"></div>

                            <%@include file="commons/tags.jsp" %>

                            <%@include file="commons/months.jsp" %>

                        </div>


                    </aside>

                </div>
            </div>

        </section>
    </c:when>
    <c:otherwise>

        <jsp:include page="error/error-content.jsp">
            <jsp:param name="error" value="Henüz bu yıl içinde yazılmış bir yazı bulunamadı!"/>
            <jsp:param name="errorType" value="499"/>
        </jsp:include>

    </c:otherwise>
</c:choose>


