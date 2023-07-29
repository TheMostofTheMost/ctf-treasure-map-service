async function mainAjaxFunc(path, method, data, headers) {
    return $.ajax({
        url: "http://localhost:8080" + path,
        method: method,
        data: JSON.stringify(data),
        headers: headers
    });
}

$('.sign-in-btn').on("click", function () {
    console.log("Test")
    mainAjaxFunc("/authentication", "GET").then(function (result) {
        $('.middle-container').empty().html(result);
    });
});


$('.sign-up-btn').on("click", function () {
    mainAjaxFunc("/registration", "GET").then(function (result) {
        $('.middle-container').empty().html(result);
    });
});


$('.octagon-block').on("click", function () {

    $('.octagon-background-container').css("background-color", "#00a5ff");
    $(this).parent().css("background-color", "#00ff9e");
});