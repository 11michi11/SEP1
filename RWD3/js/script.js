var count=0;
addPoping("#bubble1Id");
addPoping("#bubble2Id");
addPoping("#bubble3Id");

animateBubble("#bubble1Id");
animateBubble("#bubble2Id");
animateBubble("#bubble3Id");

swim("#fish1Id");
swim("#fish2Id");
swim("#fish3Id");

$("#fish4Id").hide();
$("#counter").hide();

$("#fish1Id").attr('dead', 'false');
$("#fish2Id").attr('dead', 'false');
$("#fish3Id").attr('dead', 'false');

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

    $("#fish2Id").stop(true).animate({
        left: x,
        top: y
    }, "fast", function () {
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
    $(IdRef).animate({
        left: x,
        top: y
    }, v, "linear", function () {
        swim(IdRef);
    });
}

$(window).click(function (event) {
    var x, y;
    if ((($(window).width() - event.pageX) < 125 || event.pageX < 125)) {
        if (event.pageX < 125) {
            x = 0;
        } else {
            x = $(window).width() - 250;
        }
    } else {
        x = event.pageX - 125;
    }
    if ((($(window).height() - event.pageY) < 125 || event.pageY < 125)) {
        if (event.pageY < 125) {
            y = 0;
        } else {
            y = $(window).height() - 250;
        }
    } else {
        y = event.pageY - 125;
    }

    $("#fish1Id").stop(true).animate({
        top: y,
        left: x
    }, function () {
        swim("#fish1Id");
    });
});


$("#fish1Id").dblclick(function () {
    $(this).height(400);
    $(this).width(400);
    setTimeout(function () {
        $("#fish1Id").height(170);
        $("#fish1Id").width(200);
    }, 3000);
});



$('html').keydown(function (e) {
    $("#fish4Id").stop(true);
    checkCollisions("oct");
    switch (e.key) {
        case 'ArrowUp':
            if ($("#fish4Id").offset().top > 5) {
                $("#fish4Id").animate({
                    top: '-=100'
                });
            }
            break;
        case 'ArrowDown':
            var bottomPos = $("#fish4Id").offset().top + $("#fish4Id").outerHeight();
            if ($(window).height() - bottomPos > 5) {

                $("#fish4Id").animate({
                    top: '+=100'
                });
            }
            break;
        case 'ArrowRight':
            var rightPos = $("#fish4Id").offset().left + $("#fish4Id").outerWidth();
            if ($(window).width() - rightPos > 5) {
                $("#fish4Id").animate({
                    left: '+=100'
                });
            }
            break;
        case 'ArrowLeft':
            if ($("#fish4Id").offset().left > 5) {

                $("#fish4Id").animate({
                    left: '-=100'
                });
            }
            break;
    }
});

function checkCollisions(name) {
    var octopus = $("#fish4Id");
    var nemo = $("#fish1Id");
    var dory = $("#fish2Id");
    var unicorn = $("#fish3Id");

    checkFish(octopus, nemo);
    checkFish(octopus, dory);
    checkFish(octopus, unicorn);
}

function checkFish(oct, fish) {
    var octTop = oct.offset().top - 30;
    var octBot = oct.offset().top + $("#fish4Id").outerHeight() - 30;
    var octLef = oct.offset().left - 30;
    var octRig = oct.offset().left + $("#fish4Id").outerWidth() - 30;

    var fishTop = fish.offset().top;
    var fishBot = fish.offset().top + fish.outerHeight();
    var fishLef = fish.offset().left;
    var fishRig = fish.offset().left + fish.outerWidth();

    if (octBot > fishTop && octTop < fishBot && octRig > fishLef && octLef < fishRig && fish.attr('dead') !== 'true')
        die(fish);
}

function die(fish) {
    fish.stop();
    fish.attr("dead", 'true');
    var fishName = fish.attr("id").substring(0,5);
    fish.attr("src", 'images/' + fishName + 'dead.png');
    fish.animate({
        borderSpacing: -90
    }, {
        step: function (now, fx) {
            fish.css('transform', 'rotate(' + now + 'deg)');
        },
        duration: 'slow',
        complete: function () {
            fish.animate({
                top: -275
            }, 7000, function(){swimIn(fish)});
        }
    });
    count++;
    document.getElementById("kill").innerHTML=count;
};

function swimIn(fish){
    fish.attr('dead', false);
    var fishName = fish.attr("id").substring(0,5);
    fish.attr("src", 'images/' + fishName + '.png');
    fish.animate({
        borderSpacing: 0
    }, {
        step: function (now, fx) {
            fish.css('transform', 'rotate(' + now + 'deg)');
        },
        duration: 'slow',
    });
    var y, x, r=Math.round(Math.random());
    y=randomY(fish);
    if(r===0)
        x=0-$(fish).width();
    else
        x=$(window).width()+$(fish).width();
    $(fish).offset({
        top: y,
        left: x
    });
    swim(fish);
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

$("#stone").click( function(){
    $("#fish4Id").show();
    $("#counter").show();
});