$(document).ready(function () {
    $(function () {
        var duration = 1000;
        setTimeout(function () {
            $('.alertMessage').fadeOut("slow");
            }, duration);
    });
});

function loadSearchPage() {
    var query = $(".search").val();
    $("#searchButton").attr("href","/searchBook?query="+query);
}

function loadUpdatePage(ele) {
    var bookId = $(ele).attr("id");
    $(ele).attr("href","/update?bookId="+bookId);
}

function loadDeletePage(ele) {
    var bookId = $(ele).attr("id");
    $(ele).attr("href","/delete?bookId="+bookId);
}

function deleteBook(ele) {
    var bookId = $(ele).attr("id");
    $(ele).attr("href","/deleteBook?bookId="+bookId);
}