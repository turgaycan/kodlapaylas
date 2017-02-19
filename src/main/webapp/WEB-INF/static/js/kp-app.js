jQuery(function ($) {
    "use strict";
////////////////////////////////////////////////////////
///////////////preloader ///////////////////////////
////////////////////////////////////////////////////////

    $(window).load(function () { // makes sure the whole site is loaded
        $('#status').fadeOut(); // will first fade out the loading animation
        $('#preloader').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website.
        $('body').delay(350).css({'overflow': 'visible'});
    });

////////////////////////////////////////////////////////
///////////////dropdown hover//////////////
////////////////////////////////////////////////////////
    $(".menu .dropdown").hover(
        function () {
            $('.dropdown-menu', this).stop().fadeIn("fast");
        },
        function () {
            $('.dropdown-menu', this).stop().fadeOut("fast");
        });

////////////////////////////////////////////////////////
///////////////back to top ///////////////////////////
////////////////////////////////////////////////////////


    var offset = 220;
    var duration = 500;
    jQuery(window).scroll(function () {
        if (jQuery(this).scrollTop() > offset) {
            jQuery('.back-to-top').fadeIn(duration);
        } else {
            jQuery('.back-to-top').fadeOut(duration);
        }
    });
    jQuery('.back-to-top').click(function (event) {
        event.preventDefault();
        jQuery('html, body').animate({scrollTop: 0}, duration);
        return false;
    });

////////////////////////////////////////////////////////
///////////////Parallax effects/////////////////////////
////////////////////////////////////////////////////////


    $('div.bgParallax').each(function () {
        var $obj = $(this);

        $(window).scroll(function () {
            var yPos = -($(window).scrollTop() / $obj.data('speed'));

            var bgpos = '50% ' + yPos + 'px';

            $obj.css('background-position', bgpos);

        });
    });
////////////////////////////////////////////////////////
///////////////gallery owl-carousel ///////////////////////////
////////////////////////////////////////////////////////
    $("#feature-posts-grid").owlCarousel({
        items: 5,
        itemsDesktop: [1366, 4],
        itemsDesktopSmall: [1024, 3],
        pagination: false,
        navigation: true,
        lazyLoad: true
    });

    jQuery("#sponsor-carousel").owlCarousel({

        autoPlay: 3000, //Set AutoPlay to 3 seconds
        pagination: false,
        items: 3,
        itemsDesktop: [1199, 4],
        itemsDesktopSmall: [979, 3],
        navigation: true,
        navigationText: [
            "<i class='fa fa-angle-left'></i>",
            "<i class='fa fa-angle-right'></i>"
        ]
    });

////////////////////////////////////////////////////////
///////////////masonry ///////////////////////////
////////////////////////////////////////////////////////

    jQuery('.masonry-container').masonry({
        itemSelector: '.masonry-item'
        // columnWidth: 152
    });

////////////////////////////////////////////////////////
///////////////contact form ///////////////////////////
////////////////////////////////////////////////////////
    $("#comment-form").validate({
        ignore: ":hidden",
        rules: {
            fullname: {
                minlength: 2,
                maxlength: 30,
                required: true
            },
            email: {
                minlength: 2,
                maxlength: 50,
                required: true
            },
            message: {
                minlength: 2,
                maxlength: 1000,
                required: true
            }
        }
    });

    $("#contact-form").validate({
        ignore: ":hidden",
        rules: {
            yourname: {
                minlength: 2,
                maxlength: 30,
                required: true
            },
            email: {
                minlength: 2,
                required: true
            },

            message: {
                minlength: 3,
                maxlength: 300,
                required: true
            }
        },
        submitHandler: function (form) {
            $.ajax({
                type: "POST",
                url: "/iletisim/yeni",
                data: $("#contact-form").serialize(),
                error: function () {
                    console.log('Some thing went wrong! :D');
                },
                success: function (data) {
                    alert(data);
                    if (data == 'fail') {
                        $('#errormessage').html("<label for='captcha_code' class='error'>Security Code was incorrect.</label>");
                    } else {
                        $('.reg-form').html("<div id='message'></div>");
                        $('#message').html("<h2> Thanks for Submitting your Message!</h2>")
                            .hide()
                            .fadeIn(1500, function (data) {
                                $('#message').append("");
                            });
                    }
                }
            });
            return false; // required to block normal submit since you used ajax
        }
    });

    $("#subscribe-form").validate({
        ignore: ":hidden",
        rules: {
            email: {
                minlength: 2,
                maxlength: 50,
                required: true
            }
        }
    });

    $('#subscribe').click(function () {
        var subscriberEmail = $('#subscriberEmail').val();
        if (_.isEmpty(subscriberEmail)) {
            $('#subscriberMessage').html("Email alanını boş bırakmayınız..")
                .hide()
                .fadeIn(2000, function () {
                    $('#subscriberMessage').append("");
                });
            return;
        }
        if (isNotValidEmail(subscriberEmail)) {
            $('#subscriberMessage').html("Email formatı hatalı..")
                .hide()
                .fadeIn(2000, function () {
                    $('#subscriberMessage').append("");
                });
            return;
        }
        var subscriberModel = {
            email: subscriberEmail
        }
        $.ajax({
            type: "POST",
            url: "/abone/ol",
            data: subscriberModel,
            error: function (data) {
                if (data.status == 400) {
                    console.log('Bad Request');
                }
                if (data.status == 500) {
                    console.log('Server Error');
                }
                $('#subscriberMessage').addClass("").html("Bir yerde hata var gibi duruyor..?")
                    .hide()
                    .fadeIn(5000, function () {
                        $('#subscriberMessage').html("");
                    });
            },
            success: function (data) {
                if (_.isEqual(data, true)) {
                    $('#subscriberMessage').html("Teşekkürler..")
                        .hide()
                        .fadeIn(2000, function () {
                            $('#message').append("");
                        });
                    $('#subscriberEmail').val("");
                } else {
                    $('#subscriberMessage').html(data)
                        .hide()
                        .fadeIn(2000, function () {
                            $('#subscriberMessage').append("");
                        });
                }
            }
        });
    });

    //!!Comment Reply!!//
    $("#comment-reply-form").validate({
        ignore: ":hidden",
        rules: {
            commentFullname: {
                minlength: 2,
                maxlength: 50,
                required: true
            },
            commentEmail: {
                minlength: 2,
                maxlength: 50,
                required: true
            },
            commentMessage: {
                minlength: 8,
                maxlength: 1000,
                required: true
            }

        }
    });
    $('#replyComment').click(function () {
        var commentEmail = $('#commentEmail').val();
        if (_.isEmpty(commentEmail)) {
            $('#replyCommentErrorMessage').html("Email alanını boş bırakmayınız..")
                .hide()
                .fadeIn(2000, function () {
                    $('#replyCommentErrorMessage').append("");
                });
            return;
        }
        if (isNotValidEmail(commentEmail)) {
            $('#replyCommentErrorMessage').html("Email formatı hatalı..")
                .hide()
                .fadeIn(2000, function () {
                    $('#replyCommentErrorMessage').append("");
                });
            return;
        }
        var commentId = $('#commentId').val();
        var commentFullname = $('#commentFullname').val();
        if (_.isEmpty(commentFullname)) {
            $('#replyCommentErrorMessage').html("Ad Soyad alanını boş bırakmayınız..")
                .hide()
                .fadeIn(2000, function () {
                    $('#replyCommentErrorMessage').append("");
                });
            return;
        }
        var commentMessage = $('#commentMessage').val();
        if (_.isEmpty(commentMessage) || (commentMessage.length < 10 && commentMessage.length > 1000)) {
            $('#replyCommentErrorMessage').html("Mesajınız en az 10, en çok 1000 karakter olmalıdır..")
                .hide()
                .fadeIn(2000, function () {
                    $('#replyCommentErrorMessage').append("");
                });
            return;
        }
        var replyCommentModel = {
            email: commentEmail,
            commentId: commentId,
            fullname: commentFullname,
            message: commentMessage
        }
        $.ajax({
            type: "POST",
            url: "/yorum/tekrar-yeni",
            data: replyCommentModel,
            error: function (data) {
                if (data.status == 400) {
                    console.log('Bad Request');
                }
                if (data.status == 500) {
                    console.log('Server Error');
                }
                $('#replyCommentErrorMessage').addClass("").html("Bir yerde hata var gibi duruyor..?")
                    .hide()
                    .fadeIn(5000, function () {
                        $('#replyCommentErrorMessage').html("");
                    });
            },
            success: function (data) {
                if (_.isNull(data.errorResponse)) {
                    $('#replyCommentErrorMessage').html("Teşekkürler..")
                        .hide()
                        .fadeIn(2000, function () {
                            $('#message').append("");
                        });
                    $('#commentEmail').val("");
                    $('#commentFullname').val("");
                    $('#commentMessage').val("");
                    $('#kpModal').modal('hide');
                    location.reload();
                } else {
                    $('#replyCommentErrorMessage').html(data)
                        .hide()
                        .fadeIn(2000, function () {
                            $('#replyCommentErrorMessage').append("");
                        });
                }
            }
        });
    });
/// end js
});

function isNotValidEmail(email) {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return !re.test(email);
}


function showKpModal(commentId) {
    $(document).ready(function () {
        $('#kpModal').modal('toggle');
        $('#commentId').val(commentId);
    });
}

//Uyarıları jquery.growl ile yap!
