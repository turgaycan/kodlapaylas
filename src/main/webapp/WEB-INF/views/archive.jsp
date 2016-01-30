<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 04/10/15
  Time: 00:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">

<head>
  <%@include file="parts/head.jsp" %>
</head>
<body>

<header>
  <%@include file="parts/header.jsp" %>
</header>

<script type="text/javascript">
  $.get("/feature-articles",
          async = true,
          function (data, status) {
            $('#feature-articles').append("" + data);
          });

</script>
<div id="feature-articles"></div>
<section class="innercontent">
  <%@include file="contents/archive-content.jsp" %>
</section>

<footer>
  <%@include file="parts/footer.jsp" %>
</footer>
<!-- Footer -->

<%@include file="parts/kp-js.jsp" %>

</body>

</html>