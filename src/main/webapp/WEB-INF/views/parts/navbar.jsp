<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--<script type="text/javascript">--%>
    <%--$(document).ready(function(){--%>
        <%--//Bilgi kısmına ilk değerimizi gönderiyoruz--%>
        <%--$("#img_info span").html("Buraya img bilgisi gelecek...");--%>
        <%--//Listemizin genişlik değerini buluyoruz--%>
        <%--//(64px icon width+10px padding değeri=74px*6 (6 adet li etiketi var))--%>
        <%--var width=($(".img_list li").length)*74;--%>
        <%--//3 adet icon kalınca duracağımız göre--%>
        <%--//son 3 icon soldan uzaklık değeri (left)--%>
        <%--var last_trio=222-width;--%>
        <%--//li etketi içindeki  a link üzerine olay tetikleyicimiz--%>
        <%--$(".img_list li a").on({--%>
            <%--mouseenter:function(){ //Alana girince--%>
                <%--//Gölge sınıfımız ekleniyor(CSS3 özellik içerir: IE9 öncesi gölge eklenmez)--%>
                <%--$(this).find("img").addClass("shadow_box");--%>
                <%--//Resimlerin üzerine gelince 1.1 katı büyütüyoruz--%>
                <%--var img_width=parseInt(($(this).find("img").attr("width"))*1.1);--%>
                <%--var img_height=parseInt(($(this).find("img").attr("height"))*1.1);--%>
                <%--//Resimlerin büyütülmüş boyutlarını giriyoruz--%>
                <%--$(this).find("img").attr({--%>
                    <%--'width':img_width,--%>
                    <%--'height':img_height--%>
                <%--});--%>
                <%--//Bilgi alanına, resimlerin içindeki--%>
                <%--//data-info etiketindeki bilgiyi gönderiyoruz--%>
                <%--$("#img_info span").html($(this).find("img").data("info"));--%>
            <%--},--%>
            <%--mouseleave:function(){ //Alandan çıkınca--%>
                <%--//Gölge sınıfını (CSS3 özellik içerir) kaldırıyoruz--%>
                <%--$(this).find("img").removeClass("shadow_box");--%>
                <%--//Resimleri orjinal boyutlarına getiriyoruz--%>
                <%--$(this).find("img").attr({--%>
                    <%--'width':64,--%>
                    <%--'height':64--%>
                <%--});--%>
                <%--//Bilgi kısmına ilk değerimizi gönderiyoruz--%>
                <%--$("#img_info span").html("Buraya img bilgisi gelecek...");--%>
            <%--}--%>
        <%--});--%>
        <%--//Sonraki butonuna tıklanınca--%>
        <%--$("#next_but").click(function(){--%>
            <%--//Önceki butonunun resmini aktif resim ile değiştiriyoruz--%>
            <%--$("#prev_but").children("img").attr('src','left_active.png');--%>
            <%--//Listenin soldan uzaklığını belirliyoruz--%>
            <%--var list_pos=($(".img_list")).position();--%>
            <%--var list_left=list_pos.left;--%>
            <%--//Eğer liste son 3 resme gelmediyse sola doğru kaydırıyoruz--%>
            <%--if(list_left>last_trio){--%>
                <%--$(".img_list").animate({--%>
                    <%--left:"-=75"--%>
                <%--});--%>
            <%--}else{--%>
                <%--//Son 3 resim görüntüleniyorsa, yani sona gelindiyse--%>
                <%--//Pasif buton resmiyle değiştiriyoruz--%>
                <%--$(this).children("img").attr("src","right_passive.png");--%>
            <%--}--%>
        <%--});--%>
        <%--//Önceki butonuna tıklanınca--%>
        <%--$("#prev_but").click(function(){--%>
            <%--//Sonraki butonunun resmini aktif resim ile değiştiriyoruz--%>
            <%--$("#next_but").children("img").attr("src","right_active.png");--%>
            <%--//Listenin soldan uzaklığını belirliyoruz--%>
            <%--var list_pos=($(".img_list")).position();--%>
            <%--var list_left=list_pos.left;--%>
            <%--//Eğer liste ilk 3 resme gelmediyse sağa doğru kaydırıyoruz--%>
            <%--if(list_left<0){--%>
                <%--$(".img_list").animate({--%>
                    <%--left:"+=75"--%>
                <%--});--%>
            <%--}else{--%>
                <%--//Son 3 resim görüntüleniyorsa, yani başa gelindiyse--%>
                <%--//Pasif buton resmiyle değiştiriyoruz--%>
                <%--$(this).children("img").attr("src","left_passive.png");--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>

<nav class="navbar main-menu navbar-default navbar-fixed-top" role="navigation">

    <div class="container">

        <div class="navbar-header">

            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">

                <span class="sr-only">Toggle navigation</span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

            </button>

            <a class="navbar-brand" href="#" title="logo">
                <img src="http://www.mirchu.net/themes/BlogDesk/assets/images/logo.png" alt="logo"/>
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

                <li class="dropdown mega-dropdown">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mega Menu 2<span
                            class="caret"></span></a>

                    <div class="dropdown-menu">

                        <div class="container">

                            <div class="mega-dropdown-menu">

                                <div class="col-md-3 col-xs-12 col-sm-6 sub-menu">

                                    <h3>Categories</h3>

                                    <ul class="list-unstyled">

                                        <li><a href="#">Photography </a></li>

                                        <li><a href="#">Travel </a></li>

                                        <li><a href="#">Music </a></li>

                                        <li><a href="#">LifeStyle </a></li>

                                        <li><a href="#">Apps </a></li>

                                        <li><a href="#">Business </a></li>

                                    </ul>

                                </div>

                                <div class="col-md-3 col-xs-12 col-sm-6 sub-menu">

                                    <h3>Social Menu</h3>

                                    <ul class="list-unstyled">

                                        <li><a href="#"><i class="fa fa-facebook-square"></i> Facebook </a></li>

                                        <li><a href="#"><i class="fa fa-google-plus-square"></i> Google Plus </a>
                                        </li>

                                        <li><a href="#"><i class="fa fa-twitter-square"></i> Twitter </a></li>

                                        <li><a href="#"><i class="fa fa-pinterest-square"></i> Pinterest </a></li>

                                        <li><a href="#"><i class="fa fa-linkedin-square"></i> Linkedin </a></li>

                                        <li><a href="#"><i class="fa fa-tumblr-square"></i> Tumblr </a></li>

                                    </ul>

                                </div>

                                <div class="col-md-3 col-sm-6 sub-menu hidden-xs">

                                    <h3>Recent Post Menu</h3>

                                    <ul class="list-unstyled">

                                        <li><a href="single.php">Should Be A Large Heading </a></li>

                                        <li><a href="single.php">Match With the Size </a></li>

                                        <li><a href="single.php">The Heading Text Size</a></li>

                                        <li><a href="single.php">Lorem ipsum dolor sit </a></li>

                                        <li><a href="single.php">Should Be A Large Heading </a></li>

                                        <li><a href="single.php">Match With the Image </a></li>

                                    </ul>

                                </div>

                                <div class="col-md-3 col-sm-6 sub-menu  hidden-xs">

                                    <h3>About Us</h3>

                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet
                                        lectus congue mi viverra congue in sed leo.</p>

                                    <br/>

                                    <ul class="list-inline store-icon">

                                        <li><a href=""><i class="fa fa-android"></i> Google Play</a></li>

                                        <li><a href=""><i class="fa fa-apple"></i> Apple Store</a></li>

                                    </ul>

                                </div>

                            </div>

                        </div>

                    </div>

                </li>

                <li class="dropdown">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Features
                        <span class="caret"></span></a>

                    <ul class="dropdown-menu" role="menu">

                        <li><a href="masonry-blog.php">Masonry Blog</a></li>

                        <li><a href="classic-blog.php">Classic Blog</a></li>

                        <li class="divider"></li>

                        <li><a href="single.php">Single Post</a></li>

                        <li><a href="video-blog.php">Video Post</a></li>

                        <li><a href="audio-blog.php">Audio Post</a></li>

                        <li class="divider"></li>

                        <li><a href="signin.php">SignIn Page</a></li>

                        <li><a href="signup.php">SignUp Page</a></li>

                        <li class="divider"></li>

                        <li><a href="search.php">Search Page</a></li>

                    </ul>

                </li>


                <li><a href="404.php">404 Page</a></li>

                <li><a href="about.php">About Us</a></li>

                <li><a href="contact.php">Contact</a></li>

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

                                <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>

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

            <li><a href="#"><i class="fa fa-facebook"></i></a></li>

            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>

            <li><a href="#"><i class="fa fa-twitter"></i></a></li>

            <li><a href="signin.jsp"><i class="fa fa-user"></i></a></li>

        </ul>

        <!--/.nav-collapse -->

    </div>

</nav>