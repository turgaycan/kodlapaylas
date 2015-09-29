<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">

    <div class="row">

        <div class="col-md-12">

            <h2 class="heading">SignUp</h2>

            <div class="row">

                <div class="col-md-6 col-md-offset-3">

                    <div class="panel panel-default x-panel">

                        <div class="panel-body">

                            <form class="entry-form" action="<c:url value='/uye/kayit-ol' />" method="post" role="form">

                                <div class="form-group">

                                    <input type="text" name="username" id="username" tabindex="1"
                                           class="form-control" placeholder="Username" value="">

                                </div>

                                <div class="form-group">

                                    <input type="email" name="email" id="email" tabindex="1" class="form-control"
                                           placeholder="Email Address" value="">

                                </div>

                                <div class="form-group">

                                    <input type="password" name="password" id="password" tabindex="2"
                                           class="form-control" placeholder="Password">

                                </div>

                                <div class="form-group">

                                    <input type="password" name="passwordRepeated" id="passwordRepeated"
                                           tabindex="2" class="form-control" placeholder="Confirm Password">

                                </div>

                                <div class="form-group">

                                    <input type="submit" name="register-submit" id="register-submit" tabindex="4"
                                           class="btn btn-default btn-lg" value="Register">


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