$("#fish2Id").on("mouseenter", function() {
    var possibleWidth = $(window).width()-$("#fish2Id").width();
    var possibleHieght = $(window).height()-$("#fish2Id").height();
    
    var x = Math.floor(Math.random()*possibleWidth);
    var y = Math.floor(Math.random()*possibleHieght);
    
    $("#fish2Id").animate({left: x, top: y}, "fast");
})