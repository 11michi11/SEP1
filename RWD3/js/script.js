addPoping("#bubble1Id");
addPoping("#bubble2Id");
addPoping("#bubble3Id");

animateBubble("#bubble1Id");
animateBubble("#bubble2Id");
animateBubble("#bubble3Id");

swim("#fish1Id");
swim("#fish2Id");


function animateBubble(bubbleId) {
    initBubble(bubbleId);

    var speed = Math.floor(Math.random() * 6) + 4;

    $(bubbleId).animate({
        top: -100
    }, (speed * 1000), function () {
        animateBubble(bubbleId);
    });
}

function initBubble(bubbleId) {
    $(bubbleId).show();
    var top = $(window).height();
    var left = randomX(bubbleId);
    $(bubbleId).offset({
        top: top,
        left: left
    });
}

function addPoping(bubbleId) {
    $(bubbleId).click(function () {
        $(this).stop(true);
        $(this).fadeOut(function () {
            animateBubble(bubbleId);
        });
    });
}

$("#fish2Id").on("mouseenter", function () {

    var x = randomX("#fish2Id");
    var y = randomY("#fish2Id");

    $("#fish2Id").stop(true).animate({ left: x, top: y }, "fast", function () {
        swim("#fish2Id");
    });
});


function swim(IdRef) {
    var x, y, v, moveX, moveY;
    x = randomX(IdRef);
    y = randomY(IdRef);
    moveX = x - $(IdRef).offset().left;
    moveY = y - $(IdRef).offset().top;

    if (IdRef === "#fish1Id")
        v = Math.sqrt(Math.pow(moveX, 2) + Math.pow(moveY, 2)) * 3;
    else
        v = Math.sqrt(Math.pow(moveX, 2) + Math.pow(moveY, 2)) * 17;
    $(IdRef).animate({ left: x, top: y }, v, "linear", function () { swim(IdRef); });
}

function randomX(IdRef) {
    var x;
    x = Math.floor(Math.random() * ($(window).width() - $(IdRef).width()));
    return x;
}

function randomY(IdRef) {
    var y;
    y = Math.floor(Math.random() * ($(window).height() - $(IdRef).height()));
    return y;
}