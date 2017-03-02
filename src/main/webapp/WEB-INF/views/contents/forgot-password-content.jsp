<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 25/02/17
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section class="innercontent">

    <div class="container">

        <div class="row">

            <div class="col-md-12">

                <h2 class="heading">SignIn</h2>

                <div class="row">

                    <div class="col-md-6 col-md-offset-3">

                        <div class="panel panel-default x-panel">

                            <div class="panel-body">

                                <form class="entry-form" method="post" action="${contextPath}/sifremi-unuttum">
                                    <div class="form-group">

                                        <input type="text" autocomplete="off" name="username" id="username" tabindex="1"
                                               class="form-control" placeholder="Kullanıcı Ad yada Email" value="">

                                    </div>

                                    <div class="form-group">
                                        <input type="submit" id="login" tabindex="4" class="btn btn-default btn-lg"
                                               value="Gönder">
                                    </div>

                                </form>

                                <p class="text-center">
                                    <strong>
                                        <div class="alert alert-<c:out value="${success ? 'success' : 'danger'}" /> alert-dismissable">
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                                                ×
                                            </button>
                                            <c:out value="${true == success ? errors : message}"/>
                                            </a>
                                        </div>
                                    </strong>
                                </p>
                            </div>

                        </div>
                    </div>


                </div>

            </div>

        </div>

    </div>

</section>
