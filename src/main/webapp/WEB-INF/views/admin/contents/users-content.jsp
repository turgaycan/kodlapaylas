<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 14/02/17
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="wrapper">
    <%@include file="navigation.jsp" %>
    <div id="page-wrapper">
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Kullanıcılar
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <table width="100%" class="table table-striped table-bordered table-hover" id="dtUsers">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Email</th>
                                <th>Kullanıcı Ad</th>
                                <th>Statü</th>
                                <th>Tarih</th>
                                <th>Rol</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr class="odd gradeX">
                                    <td>
                                        <a href="<c:url value="/show/user/${user.id}" />"
                                           class="btn btn-primary btn-lg">${user.id}
                                        </a>
                                    </td>
                                    <td>${user.email}</td>
                                    <td>${user.username}</td>
                                    <td>${user.userStatus}</td>
                                    <td>${user.createdate}</td>
                                    <td>${user.role}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
