<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 13/10/15
  Time: 01:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    $.get("/feature-articles",
            async = false,
            function (data, status) {
                $('#feature-articles').append("" + data);
            });

</script>
<div id="feature-articles"></div>
<section class="content">
    <%@include file="commons/indexcontent.jsp" %>
</section>
<%@include file="commons/sponsor.jsp" %>
