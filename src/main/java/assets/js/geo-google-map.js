/*

 (function() {

 var map = {

 makeMap: function() {

 var loc = this.location.split(","),
 pos = new google.maps.LatLng(loc[0], loc[1]);

 var mapOptions = {
 zoom: 14,
 center: pos,
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

 this.mapObj = new google.maps.Map(document.querySelector("#map"), mapOptions);
 this.destination = pos;

 var marker = new google.maps.Marker({
 map: this.mapObj,
 position: pos,
 animation: google.maps.Animation.BOUNCE,
 icon: this.options.mapMarker
 });

 },

 handleRoute: function(result, status) {

 if(status != google.maps.DirectionsStatus.OK || !result.routes[0]) {
 alert("You have entered wrong data!");
 return false;
 }

 this.pathRender.setDirections(result);
 this.fromInput.value = result.routes[0].legs[0].start_address;
 },

 prepareRoute: function(coords) {

 var renderOptions = {
 map: this.mapObj,
 polylineOptions: {
 strokeColor: "#ff0000",
 strokeWeight: 4,
 strokeOpacity: 0.8
 },
 suppressMarkers: true
 }

 this.pathRender.setOptions(renderOptions);

 var pathData = {
 origin: coords ? coords : this.fromInput.value,
 destination: this.destination,
 travelMode: google.maps.DirectionsTravelMode.DRIVING
 }

 this.path.route(pathData, this.handleRoute.bind(this));

 },

 getGeoData: function() {

 navigator.geolocation.getCurrentPosition(
 function(position) {
 this.prepareRoute(position.coords.latitude + "," + position.coords.longitude);
 }.bind(this),
 function(errorObj) {
 alert("An error has occurred! Refresh the page and try again.");
 },
 {
 enableHighAccuracy: true
 }
 );

 },

 checkGeoSupport: function() {

 if(navigator.geolocation) {
 var findPositionButton = document.querySelector("#findPosition");

 findPositionButton.classList.remove("hidden");

 findPositionButton.onclick = function(e) {
 e.preventDefault();

 this.getGeoData();
 }.bind(this);
 }

 },



 init: function(options) {

 if(!options.location) return;

 try { google.maps.event.addDomListener(window, "load", this.makeMap.bind(this)); } catch(e) { return; };

 this.options = options;
 this.location = this.options.location;
 this.form = document.querySelector("#mapForm");

 this.fromInput = document.querySelector("#from");
 this.path = new google.maps.DirectionsService(),
 this.pathRender = new google.maps.DirectionsRenderer();

 this.form.onsubmit = function(e) {
 e.preventDefault();

 this.prepareRoute();
 }.bind(this);

 this.checkGeoSupport();

 }

 }


 map.init({
 location: "55.958347, -3.187532",
 mapMarker: "img/map_marker.png"
 });

 })

 */


var markers = [];
var infoWindowContent = [];

function initMap() {
    var geometry, location, lat, lng;
    $.getJSON("http://localhost:8080/map/json-resources")
        .done(function (data) {
            $.each(data, function (key, val) {
                $.each(val, function (a, b) {
                    console.log(b.lat);
                    console.log(b.lng);
                    var position = new google.maps.LatLng(b.lat, b.lng);
                    markers.push(position);


                    // Info Window Content
                    infoWindowContent.push(
                        ['<div class="info_content">' +
                        '<h3>' + b.resourceTitle + ' - ' + b.companyName + '</h3>' +
                        '<p>' + b.description + '</p>' +
                        '<p>' + b.address + '</p>'
                        + '</div>'
                        ]
                    )



                });

                console.log(infoWindowContent);
            });
            initMaps();

        })
        .fail(function (jqxhr, textStatus, error) {
            var err = textStatus + ", " + error;
            console.log("Request Failed: " + err);
        });
}

function initMaps() {
    var map;
    var bounds = new google.maps.LatLngBounds();
    var mapCenter = new google.maps.LatLng(55.9332035, -3.2140611);
    var mapOptions = {
        mapTypeId: 'roadmap',
        zoom: 13,
        center: mapCenter
    };

    console.log("init map marker length " + markers.length);
    console.log("1");
    // Display a map on the page
    map = new google.maps.Map(document.getElementById('map'), mapOptions);
    map.setTilt(45);
    console.log("2");

    // Display multiple markers on a map
    var infoWindow = new google.maps.InfoWindow(), marker, i;
    console.log("3");
    // Loop through our array of markers & place each one on the map
    for (i = 0; i < markers.length; i++) {
        console.log("4");
        bounds.extend(markers[i]);
        marker = new google.maps.Marker({
            position: markers[i],
            map: map
        });

        // Allow each marker to have an info window
        google.maps.event.addListener(marker, 'click', (function (marker, i) {
            return function () {
                infoWindow.setContent(infoWindowContent[i][0]);
                infoWindow.open(map, marker);
            }
        })(marker, i));

        // Automatically center the map fitting all markers on the screen
        map.fitBounds(bounds);
    }

    // Override our map zoom level once our fitBounds function runs (Make sure it only runs once)
    var boundsListener = google.maps.event.addListener((map), 'bounds_changed', function (event) {
        this.setZoom(14);
        google.maps.event.removeListener(boundsListener);
    });

}


