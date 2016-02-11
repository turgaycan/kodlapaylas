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

        <h2 class="heading">Search...</h2>


        <div class="row">

            <div class="col-md-8 col-lg-8">

                <c:forEach items="${pageArticles}" var="article">
                    <%@include file="commons/horizontal-article.jsp" %>
                </c:forEach>


                <%@include file="commons/paging.jsp" %>

                <div class="clearfix"></div>

            </div>

            <aside class="col-md-4 col-lg-4">


                <div class="row">


                    <%@include file="commons/subscribeus.jsp" %>

                    <script type="text/javascript">
                        $.get("/recent-articles-${pageArticles[0].id}",
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

                    <%@include file="commons/archive.jsp" %>

                </div>


            </aside>

        </div>

    </div>

</section>
