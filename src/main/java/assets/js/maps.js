$(function() {
  var map = new google.maps.Map(document.getElementById("map_canvas"), {});
  var edinburgh = new google.maps.LatLng(55.958347, -3.187532);

  var googleMapWidth = $("#map_canvas").css('width');
  var googleMapHeight = $("#map_canvas").css('height');

  map.setCenter(edinburgh);

  $('#enter-full-screen').click(function(){
    $("#map_canvas").css("position", 'fixed').
      css('top', 0).
      css('left', 0).
      css("width", '100%').
      css("height", '100%');
    google.maps.event.trigger(map, 'resize');
    map.setCenter(edinburgh);
    return false;
  });

  $('#exit-full-screen').click(function(){
    $("#map_canvas").css("position", 'relative').
      css('top', 0).
      css("width", googleMapWidth).
      css("height", googleMapHeight);
    google.maps.event.trigger(map, 'resize');
    map.setCenter(edinburgh);
    return false;
  });
});