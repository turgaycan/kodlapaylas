<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 13/02/17
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">

<head>
    <%@include file="contents/head.jsp" %>
</head>
<body>

<%@include file="contents/users-content.jsp" %>

<%@include file="contents/footer.jsp" %>
<%@include file="contents/table-footer.jsp" %>

<script>
    $(document).ready(function () {
        $('#dtUsers').DataTable({
            responsive: true
        });
    });
</script>

</body>

</html>
