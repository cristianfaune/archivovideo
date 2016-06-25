<%-- 
    Document   : Menu
    Created on : 22-jun-2016, 22:18:27
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
        <title>Sistema archivo video</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10" id="margen-menu">
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
                    <br><br>
                    <p class="text-center">En este ambiente de trabajo podrás hacer ingreso de <br>
                    nuevos videos, consultar, modificar y eliminar registros.</p>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>
    </body>
</html>
