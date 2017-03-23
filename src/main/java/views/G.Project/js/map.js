jQuery(document).ready(function () {
    var map;

    var style = [
		{
		stylers: [
			{ saturation: "-30" },
			{ lightness: "10" }
		]
		},{
		featureType: "poi",
		stylers: [
			{ visibility: "on" }
		]
		},{
		featureType: "transit",
		stylers: [
			{ visibility: "on" }
		]
		},{
		featureType: "road",
		stylers: [
			{ lightness: "50" },
			{ visibility: "on" }
		]
		},{
		featureType: "landscape",
		stylers: [
			{ lightness: "50" }
		]
		}
	]

    var options = {
        zoom: 11,
        center:  new google.maps.LatLng(55.958347, -3.187532),
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        mapTypeControl: true,
          mapTypeControlOptions: {
              style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
              position: google.maps.ControlPosition.TOP_CENTER
          },
          zoomControl: true,
          zoomControlOptions: {
              position: google.maps.ControlPosition.RIGHT_BOTTOM
          },
          scaleControl: true,
          streetViewControl: true,
          streetViewControlOptions: {
              position: google.maps.ControlPosition.RIGHT_TOP
          },
          fullscreenControl: true
    };


    
    map = new google.maps.Map($('#map')[0], options);
    map.setOptions({
        styles: style
    });

$(document).ready(function() 
{
    $('#search').click(function()
    {
        var address = $('#location').val();
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({
            "address": address
        }, function(results) {
            map.setCenter(results[0].geometry.location); 
        });
     });
});



});