<%-- 
    Document   : RegistroVideo
    Created on : 22-jun-2016, 22:54:39
    Author     : CristianFaune
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css">
        <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10" id="margen-menu">
                    <h2 id="titulos">Bienvenido</h2> 
                    <p id="parrafo"><c:out value="${usuarioSesion.nombres} ${usuarioSesion.apellidos}"/></p>

                    <p  class="text-right"id="parrafo">
                        <a href="">cerrar sesión</a>
                    </p>
                    <br>
                    <div class="centered-pills">
                        <ul class="nav nav-pills">
                            <li role="presentation"><a href="#">Inicio</a></li>
                            <li role="presentation"><a href="<c:url value="RegistroVideoServlet"/>">Registro Video</a></li>
                            <li role="presentation"><a href="#">Consulta</a></li>
                        </ul>
                    </div>
                    <hr>
                    <br>
                </div>
                <div class="col-md-1"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <p class="text-center" id="parrafo">Ingresa los datos para el nuevo archivo</p>
                    <br>
                    <div class="row">
                        <form action="UploadServlet" method="post" enctype="multipart/form-data">
                            <div class="col-md-12" id="borde-imagen">
                                <p id="palanquin-font">Carga tu video:</p>
                                <input class="btn btn-default btn-primary btn-sm" type="file" name="dataFile" size="20" accept="video/*"/>
                                <input class="btn btn-default" type="submit" value="Cargar" />
                            </div>
                        </form>
                    </div>
                    <br>
                    <label>Categoría de video:</label>
                    <form action="<c:url value="RegistroVideoServlet"/>" method="post">
                        <select class="form-control" name="categoria">
                            <option value="0">--seleccione categoría--</option>
                            <c:forEach var="dato" items="${lstCategoria}">
                                <option value="${dato.idCategoria}">
                                    <c:out value="${dato.descripcion}"></c:out>
                                    </option>
                            </c:forEach>
                        </select>
                        <br>
                        <div>
                            <label>Nombre:</label>
                            <input class="form-control" type="text" name="nombre">
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-3">
                                <label>Horas:</label>
                                <input class="form-control" type="number" name="horas" placeholder="00" min="00">
                            </div>
                            <div class="col-md-3">
                                <label>Minutos:</label>
                                <input class="form-control" type="number" name="minutos" placeholder="00" min="00" max="59">
                            </div>
                            <div class="col-md-3">
                                <label>Segundos:</label>
                                <input class="form-control" type="number" name="segundos" placeholder="00" min="" max="59">
                            </div> 
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-8">
                                <label>Fecha Origen:</label>
                                <input class="form-control" type="date" name="fechaOrigen">
                            </div>
                        </div>
                        <br>
                        <label>Resolución de video:</label>
                        <form action="<c:url value="RegistroVideoServlet"/>" method="post">
                            <select class="form-control" name="resolucion">
                                <option value="0">--seleccione resolución--</option>
                                <c:forEach var="dato" items="${lstResolucion}">
                                    <option value="${dato.idResolucion}">
                                        <c:out value="${dato.idResolucion} ${dato.descripcion}"></c:out>
                                        </option>
                                </c:forEach>
                            </select>
                            <br>
                            <label>Descripción:</label>
                            <p id="info">(puedes escribir palabras claves que te ayuden a su posterior búsqueda)</p>
                            <textarea class="form-control" rows="6" name="descripcion"></textarea>
                            <br><br>
                            <input type="hidden" name="nombreVideo" value="${nombreArchivo}">
                            
                            <button type="submit" class="btn btn-primary btn-block">Guardar</button>
                        </form>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </body>
</html>
