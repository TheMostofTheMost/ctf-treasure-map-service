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
    mainAjaxFunc("/auth-page", "GET").then(function (result) {
        $('html').html(result);
    });
});

$('.octagon-block').on("click", async function () {

    $('.octagon-background-container').css("background-color", "#00a5ff");
    $(this).parent().css("background-color", "#00ff9e");

    let locationData = await mainAjaxFunc("/location/data/" + getActiveLocation(),
        "GET");
    $('.search-results').html(locationData)
});


$(document).on('click', '.auth-btn', async function (e) {
    let userObject = {};
    userObject.username = $('#username').val();
    userObject.password = $('#password').val();
    mainAjaxFunc("/login", "POST", JSON.stringify(userObject),
        {'Content-Type': 'application/json'}).then(function (result) {
        $('html').empty().html(result);
    });
});
$(document).on('click', '.add-container-btn', async function (e) {
    let locationName = getActiveLocation();
    let treasure = $('.add-container-input').val();
    let data = {
        treasure: treasure,
        locationName: locationName
    };
    if (locationName === "") {
        alert("Выберите локацию!")
    } else {
        await mainAjaxFunc("/container/save", "POST", data, {'Content-Type': 'application/json'});

    }
});


function getActiveLocation() {
    let tags = $('.octagon-background-container');
    let activeLocationName = "";
    $.each(tags, function (index, value) {
        if ($(value).css('background-color') === 'rgb(0, 255, 158)') {
            activeLocationName = $(value).attr("name");
        }
    })
    return activeLocationName;
}


