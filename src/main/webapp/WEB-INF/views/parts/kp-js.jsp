<a href="#" class="back-to-top"><i class="fa fa-angle-up"></i></a>

<!-- Bootstrap core JavaScript

================================================== -->

<!-- Placed at the end of the document so the pages load faster -->
<spring:url value="/webjars/jquery/1.11.2/jquery.min.js" var="jQuery"/>
<script src="${jQuery}"></script>

<!-- bootstrap -->
<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js" var="bootStrapJs"/>
<script src="${bootStrapJs}"></script>

<spring:url value="/webjars/modernizr/2.8.3/modernizr.min.js" var="modernizrJs"/>
<script src="${modernizrJs}"></script>

<!-- owl-carousel -->
<spring:url value="/webjars/OwlCarousel/1.3.2/owl.carousel.min.js" var="owlCarouselJs"/>
<script src="${owlCarouselJs}"></script>

<!-- masonry -->
<spring:url value="/webjars/masonry/3.3.2/masonry.pkgd.min.js" var="masonryJs"/>
<script src="${masonryJs}"></script>


<!-- Form Validation -->
<spring:url value="/webjars/jquery-validation/1.11.1/jquery.validate.js" var="jQueryValidationJs"/>
<script src="${jQueryValidationJs}"></script>

<!-- underscore util-->
<spring:url value="/webjars/underscorejs/1.8.3/underscore-min.js" var="underscoreJs"/>
<script src="${underscoreJs}"></script>
<!-- custom -->

<script src="<spring:url value="/resources/static/js/kp-app.js" />"></script>