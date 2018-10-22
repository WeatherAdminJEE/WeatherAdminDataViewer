<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
  <head>
    <title>WeatherAdminDataViewer</title>
    <%@ include file="/WEB-INF/jspf/templateHeader.jspf" %>
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <%@ include file="/WEB-INF/jspf/templateSidebarMenu.jspf" %>
        <%@ include file="/WEB-INF/jspf/templateTopNavigation.jspf" %>

        <!-- page content -->
        <div class="right_col" role="main">

          <!-- map line -->
          <div class="row">
            <!-- map container -->
            <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel" style="height:620px">
                <div class="x_title">
                  <h2>Localisation des capteurs
                  </h2>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div id="sensorMap" style="height:532px"></div>
                </div>
              </div>
            </div>
          </div>
          <!-- /map line -->

        </div>
        <!-- /page content -->

        <%@ include file="/WEB-INF/jspf/templateFooter.jspf" %>
      </div>
    </div>
    <%@ include file="/WEB-INF/jspf/includeTemplateScripts.jspf" %>
    <script src="http://www.openlayers.org/api/OpenLayers.js"></script>
    <script>
        map = new OpenLayers.Map("sensorMap");
        map.addLayer(new OpenLayers.Layer.OSM());
        map.zoomToMaxExtent();
        map.setCenter(new OpenLayers.LonLat(-1.553621,47.218371) // Centre de la carte
            .transform(
                new OpenLayers.Projection("EPSG:4326"), // Transformation de WGS 1984
                new OpenLayers.Projection("EPSG:900913") // Projection Mercator sphérique
            ), 12 // Zoom level
        );
    </script>
  </body>
</html>
