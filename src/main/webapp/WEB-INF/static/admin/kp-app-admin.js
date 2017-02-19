/**
 * Created by tcan on 14/11/15.
 */

function showArticleDetail(articleId) {
    // $.ajax({
    //     type: "GET",
    //     url: "/show/article/" + articleId,
    //     error: function (data) {
    //         if (data.status == 400) {
    //             console.log('Bad Request');
    //         }
    //         if (data.status == 500) {
    //             console.log('Server Error');
    //         }
    //         $('#articleModal').modal('toggle');
    //         $('#articleModalMessage').addClass("").html("Bir yerde hata var gibi duruyor..?")
    //             .hide()
    //             .fadeIn(5000, function () {
    //                 $('#articleModalMessage').html("");
    //             });
    //     },
    //     success: function (data) {
    //         if (_.isNull(data)) {
    //             $('#articleModalMessage').html(data)
    //                 .hide()
    //                 .fadeIn(2000, function () {
    //                     $('#articleModalMessage').append("");
    //                 });
    //         } else {
    //             $('#createdate').val(data.createdate);
    //             document.getElementById('articleModalTitle').value = data.title;
    //             $('#articleModal').modal('toggle');
    //
    //         }
    //     }
    // });
}