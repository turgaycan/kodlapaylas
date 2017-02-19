<%--
  Created by IntelliJ IDEA.
  User: tcan
  Date: 06/10/15
  Time: 00:44
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

                                <form class="entry-form" method="post" action="${contextPath}/login">
                                    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                                        <div class="alert-danger">
                                            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                                        </div>
                                    </c:if>
                                    <div class="form-group">

                                        <input type="text" autocomplete="off" name="username" id="username" tabindex="1"
                                               class="form-control" placeholder="Username" value="">

                                    </div>

                                    <div class="form-group">

                                        <input type="password" name="password" id="password" tabindex="2"
                                               class="form-control" placeholder="Password">

                                    </div>

                                    <div class="form-group">

                                        <div class="row">

                                            <div class="col-md-6">

                                                <input type="checkbox" tabindex="3" class="" name="remember"
                                                       id="remember">

                                                <label for="remember">Hatırla</label>

                                            </div>

                                            <div class="col-md-6">

                                                <a href="" tabindex="5" class="forgot-password pull-right">Şifremi
                                                    unuttum?</a>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <input type="submit" id="login" tabindex="4" class="btn btn-default btn-lg"
                                               value="Giriş Yap">
                                    </div>

                                </form>
                                <div class="col-md-6">
                                    <a href="${contextPath}/kayit-ol" tabindex="5"
                                       class="forgot-password pull-right">Kayıt Ol</a>
                                </div>
                                <p class="text-center"><strong>Signin with</strong></p>

                                <ul class="list-inline use-to">

                                    <li><a href="#" class="btn btn-lg fb">FaceBook</a></li>

                                    <li><a href="#" class="btn btn-lg twt">Twitter</a></li>

                                    <li><a href="#" class="btn btn-lg gplus">Google+</a></li>

                                </ul>

                            </div>

                        </div>
                    </div>


                </div>

            </div>

        </div>

    </div>

</section>
