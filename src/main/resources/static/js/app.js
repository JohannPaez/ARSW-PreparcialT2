var app = (function() {

    var map;
    var markers;
    var bounds;  
    var marcadores;  

    var crearMapa = function() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 8
        });
        plotMarkers(marcadores);
    }

    function plotMarkers(m) {
        markers = [];
        bounds = new google.maps.LatLngBounds();

        m.forEach(function (marker) {
            var position = new google.maps.LatLng(marker.latitude, marker.longitude);

            markers.push(
            new google.maps.Marker({
                position: position,
                map: map,
                animation: google.maps.Animation.DROP
            })
            );

            bounds.extend(position);
        });

        map.fitBounds(bounds);
    }

    var tableAndMap = function(error, tableAndMap) {
        if (error != null) {
            return;
        }

        var stringTable = table(tableAndMap);
        $("#idTable").html(stringTable);
        crearMapa(tableAndMap);
    }

    var table = function(dataTable) {
		var tabla = "<center> <table class='table table-bordered table-dark' style = 'width:800px; text-align: center;'" +
						"<thead>" +
							"<tr>" +
								"<th scope='col'> airportId </th>" +
								"<th scope='col'> Name </th>" +
								"<th scope='col'> Code </th>" +
							"</tr>" +
						"</thead>" +
                        "<tbody>";
        marcadores = [];
        dataTable.forEach(function(airport) {			
            var coordenadasJson = {latitude:airport.location.latitude, longitude:airport.location.longitude};
            marcadores.push(coordenadasJson);
            tabla += "<tr>" +
                        "<td>" + airport.airportId + "</td>" +
                        "<td>" + airport.name + "</td>" +
                        "<td>" + airport.code + "</td>" +
                    "</tr>";
        });
        tabla += "</tbody> </table> </center>";
        return tabla;
    };
            
    return {
        mapa: function() {
           
        }, 

        updateTableAirports: function(name) {
        	apiClient.getAirportsByName(name, tableAndMap); 
        }
    }



})();