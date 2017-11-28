$(window).click(function (event) {
    console.log(event.pageX +  " " + event.pageY);
});

$("#fish1Id").dblclick(function() {
    $(this).height(400);
    $(this).width(400);
    setTimeout(function(){
        $("#fish1Id").height(250);
        $("#fish1Id").width(250);
    }, 2000);
});
