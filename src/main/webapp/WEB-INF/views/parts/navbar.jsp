<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar main-menu navbar-default navbar-fixed-top" role="navigation">

    <div class="container">

        <div class="navbar-header">

            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">

                <span class="sr-only">Toggle navigation</span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

            </button>

            <a class="navbar-brand" href="<c:url value="/" /> " title="Kodlapaylas.com">
                <img src="<c:url value='/resources/static/img/kodlapaylas-white-logo.jpg' /> " alt="logo"/>
            </a></div>

        <div class="navbar-collapse collapse pull-left">

            <ul class="nav navbar-nav menu" id="menu">

                <li><a title="Anasayfa" href="<c:url value="/" />" role="button"
                       aria-expanded="false">Anasayfa</a>
                </li>

                <li class="dropdown mega-dropdown">
                    <a title="Kategoriler" href="#" class="dropdown-toggle" data-toggle="dropdown">Kategoriler<span
                            class="caret"></span></a>

                    <script type="text/javascript">
                        $.get("/all-categories",
                                async = true,
                                function (data, status) {
                                    $('#all-categories').append("" + data);
                                });

                    </script>
                    <div id="all-categories"></div>

                </li>


                <li class="dropdown">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Olurda..
                        <span class="caret"></span></a>

                    <ul class="dropdown-menu" role="menu">

                        <li><a href="/giris-yap">Giriş Yapmak İstersen..</a></li>

                        <li><a href="/kayit-ol">Kayıt Olmak İstersen..</a></li>

                        <li class="divider"></li>

                        <li><a href="/ara">Arama Yapmak İstersen..</a></li>

                    </ul>

                </li>

                <li><a href="/hata">Hata :)</a></li>

                <li><a href="/hakkimda">Hakkımda</a></li>

                <li><a href="/iletisim">İletişim</a></li>

            </ul>

        </div>

        <ul class="nav navbar-nav navbar-right menu social-icons">

            <li class="dropdown mega-dropdown search">

                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i
                        class="fa fa-search"></i></a>

                <div class="dropdown-menu">

                    <div class="container">

                        <div class="row">

                            <div class="col-md-6 pull-right">

                                <div class="mega-dropdown-menu">

                                    <form class="search-form" action="search.php">

                                        <div class="form-group">

                                            <div class="col-lg-12">

                                                <div class="input-group">

                                                    <input type="text" class="form-control"
                                                           placeholder="Search for...">

                                                    <span class="input-group-btn">

                                                        <button class="btn btn-default" type="submit"><i
                                                                class="fa fa-search"></i></button>

                                                      </span>

                                                </div>
                                                <!-- /input-group -->

                                            </div>

                                        </div>

                                    </form>

                                </div>

                            </div>

                        </div>


                    </div>

                </div>

            </li>

            <li><a href="https://www.facebook.com/Kodlapaylas" target="_blank"><i class="fa fa-facebook"></i></a></li>

            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>

            <li><a href="https://www.twitter.com/Kodlapaylas" target="_blank"><i class="fa fa-twitter"></i></a></li>


            <c:choose>
                <c:when test="${userLoggedIn}">
                    <li>
                        <sec:authorize ifAnyGranted="ADMIN">
                            <a href="<c:url value="/admin" /> ">
                                Hoşgeldin <strong>${loggerUsername}</strong>
                                <i class="fa fa-user"></i>
                            </a>
                        </sec:authorize>
                        <sec:authorize ifAnyGranted="USER">
                            <a href="<c:url value="/user" /> ">
                                Hoşgeldin <strong>${loggerUsername}</strong>
                                <i class="fa fa-user"></i>
                            </a>
                        </sec:authorize>
                    </li>
                    <li>
                        <a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out"></span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="<c:url value="/giris-yap" /> ">
                            <strong>Misafir üye</strong>
                            <i class="fa fa-user"></i>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>

        <!--/.nav-collapse -->

    </div>

</nav>