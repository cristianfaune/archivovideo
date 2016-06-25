<%-- 
    Document   : Consulta
    Created on : 24-jun-2016, 12:53:01
    Author     : CristianFaune
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="datatables" uri="http://github.com/tduchateau/DataTables-taglib" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <link href="css/jquery.dataTables.css" rel="stylesheet">
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link href="css/jquery.dataTables.TableTools.css" rel="stylesheet">
        <script src="js/jquery.dataTables.TableTools.js"/></script>
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>

    <title>Sistema Archivo Video</title>
</head>
<body>
    <script>
        $(document).ready(function () {
            $('#tabla-videos').dataTable({
                "dom": 'T<"clear">lfrtip',
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json"
                },
                "tableTools": {
                    "sSwfPath": "/swf/copy_csv_xls_pdf.swf"
                },
            });

            $('.modal').on('shown.bs.modal', function (e) {

                var $invoker = $(e.relatedTarget);

                var url = $invoker.val();

                $('#titulo-modal').html('<p>Previsualización de video: <strong>' + url + '</strong></p>');
                $('#enlace').html('<source src="' + url + '" type="video/mp4">');
            });

        });
    </script>
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <h2 id="titulos">Bienvenido</h2> 
            <p id="parrafo"><c:out value="${usuarioSesion.nombres} ${usuarioSesion.apellidos}"/></p>

            <p  class="text-right"id="parrafo">
                <a href="<c:url value="index.jsp"/>">cerrar sesión</a>
            </p>
            <br><br>
            <div class="centered-pills">
                <ul class="nav nav-pills">
                    <li role="presentation"><a href="<c:url value="Menu.jsp"/>">Inicio</a></li>
                    <li role="presentation"><a href="<c:url value="RegistroVideoServlet"/>">Registro Video</a></li>
                    <li role="presentation"><a href="<c:url value="ConsultaServlet"/>">Consulta</a></li>
                </ul>
            </div>
            <hr>
            <h3 class="text-center" id="palanquin-font">Registro de archivo video</h3>
            <c:if test="${not empty mensaje}">
                <div class="alert alert-danger" role="alert">
                    <p class="text-center text-danger"><c:out value="${mensaje['mensajeEliminado']}"/></p>
                </div>
            </c:if>
            <c:if test="${not empty mensajeActualizado}">
                <div class="alert alert-success" role="alert">
                    <p class="text-center text-success"><c:out value="${mensajeActualizado['mensajeExito']}"/></p>
                </div>
            </c:if>
            <br>
            <datatables:table cssClass="table" data="${lstVideos}" htmlTableId="tabla-videos" dataObjectId="row">
                        <datatables:column title="Id Video" sortable="true" headerCssClass="text-center">
                    <strong><a href="${row.video.rutaArchivoVideo}"><p class="text-center"><c:out value="${row.video.idVideo}"></c:out></p></a></strong>
                        </datatables:column>
                        <datatables:column title="Nombre" sortable="true" headerCssClass="text-center"> 
                    <p class="text-center"><c:out value="${row.video.nombre}"></c:out></p>
                </datatables:column>
                <datatables:column title="Duracion" sortable="true" headerCssClass="text-center">
                    <p class="text-center"><c:out value="${row.video.duracion}"></c:out></p>
                </datatables:column>
                <datatables:column title="Fecha Origen" sortable="true" headerCssClass="text-center">
                    <p class="text-center"><fmt:formatDate value="${row.video.fechaOrigen}" pattern="dd/MM/yyyy"/></p>
                </datatables:column>
                <datatables:column title="Descripción" sortable="true" headerCssClass="text-center">
                    <c:out value="${row.video.descripcion}"></c:out>
                </datatables:column>
                <datatables:column title="Acciones" headerCssClass="text-center">
                    <form action="<c:url value="/ModificarServlet"/>" method="get">
                        <input type="hidden" name="idVideo" value="${row.video.idVideo}"/>
                        <input class="btn btn-primary btn-xs" type="submit" value="Modificar"/>
                    </form>
                    <br>
                    <form action="<c:url value="/EliminarServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de eliminar el registro');">
                        <input type="hidden" name="idVideo" value="${row.video.idVideo}"/>
                        <input class="btn btn-danger btn-xs" type="submit" value="Eliminar"/>
                    </form>
                    <br>
                </datatables:column>
            </datatables:table>
        </div>
        <div class="col-md-1">

        </div>
    </div>
</body>
</html>
