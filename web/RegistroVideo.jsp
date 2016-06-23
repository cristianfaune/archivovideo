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
                    <br><br>
                    <div class="centered-pills">
                        <ul class="nav nav-pills">
                            <li role="presentation"><a href="#">Inicio</a></li>
                            <li role="presentation"><a href="<c:url value="RegistroVideoServlet"/>">Registro Video</a></li>
                            <li role="presentation"><a href="#">Consulta</a></li>
                        </ul>
                    </div>
                    <hr>
                    <br><br>
                </div>
                <div class="col-md-1"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <p class="text-center" id="parrafo">Ingresa los datos para el nuevo archivo</p>
                    <form action="<c:url value="RegistroVideoServlet"/>" method="post">
                        <select class="form-control">
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
                        <div>
                            <label>Duracion:</label>
                            <time class="form-control" datetime="HH:MM[:SS[.mmm]]" name="duracion"></time>
                        </div>
                    </form>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </body>
</html>
