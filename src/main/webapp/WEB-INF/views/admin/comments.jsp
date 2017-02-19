<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 05/02/17
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">

<head>
    <%@include file="contents/head.jsp" %>
</head>
<body>

<%@include file="contents/comments-content.jsp" %>

<%@include file="contents/footer.jsp" %>
<%@include file="contents/table-footer.jsp" %>

<script>
    $(document).ready(function () {
        $('#dtComments').DataTable({
            responsive: true
        });
    });
</script>

</body>

</html>