<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">

    <div class="row">

        <div class="col-md-12">

            <h2 class="heading">SignUp</h2>

            <div class="row">

                <div class="col-md-6 col-md-offset-3">

                    <div class="panel panel-default x-panel">

                        <div class="panel-body">

                            <form class="entry-form" action="<c:url value='/uye/kayit-ol' />" method="post" role="userModel">

                                <div class="form-group">

                                    <input type="text" name="fullname" id="fullname" tabindex="1" required="true"
                                           class="form-control" placeholder="Ad Soyad" value="">

                                </div>

                                <div class="form-group">

                                    <input type="email" name="email" id="email" tabindex="1" class="form-control" required="true"
                                           placeholder="Email Adresi" value="">

                                </div>

                                <div class="form-group">

                                    <input type="password" name="password" id="password" tabindex="2" required="true"
                                           class="form-control" placeholder="Şifre">

                                </div>

                                <div class="form-group">

                                    <input type="password" name="passwordRepeated" id="passwordRepeated" required="true"
                                           tabindex="2" class="form-control" placeholder="Şifre Onayla">

                                </div>

                                <div class="form-group">

                                    <input type="submit" name="register-submit" id="register-submit" tabindex="4"
                                           class="btn btn-default btn-lg" value="Kaydet">


                                </div>

                            </form>
                            <div id="errors"> ${errors}</div>

                        </div>

                    </div>


                </div>


            </div>

        </div>

    </div>

</div>