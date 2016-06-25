<%-- 
    Document   : index
    Created on : 11-nov-2015, 8:31:58
    Author     : CristianFaune
--%>

<% 

    session.invalidate();

%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css">
        <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
        <title>Login - Sistema archivo video</title>
    </head>
    <body onload="javascript:history.go(1)" id="page-top" class="index">
        <div class="container" id="margen-login">
            <h4 align="center" id="titulo-index">Sistema de administración de archivo audiovisual</h4>
            <div class="row">
                <div class="col-md-1">
                    <div id="centrar-logo">
                        <img id="img" src="img/play-icon.png" alt="logo" width="80"/>
                    </div>
                </div>
                <div class="col-md-10 col-md-offset-3">
                    <form class="form-inline" action="<c:url value="/ValidarIngresoServlet"/>" method="post">
                        <c:if test="${empty mapMensajeEmail}">
                            <div class="form-group">
                                <label class="control-label" for="inputSuccess1">Email:</label>
                                <input type="email" class="form-control" id="inputSuccess1" 
                                       name="email" aria-describedby="helpBlock2" placeholder="Ingrese email" 
                                       autofocus="true" value="<c:out value="${param.email}"/>">
                            </div>
                        </c:if>
                        <c:if test="${not empty mapMensajeEmail}">
                            <div class="form-group has-error">
                                <label class="control-label" for="inputSuccess1">Email:</label>
                                <input type="email" class="form-control" 
                                       id="inputError1" name="email" placeholder="Ingrese su email" autofocus="true"
                                       value="<c:out value="${param.email}"/>">
                            </div>
                        </c:if>
                        <c:if test="${empty mapMensajePass}">
                            <div class="form-group">
                                <label class="control-label" for="inputSuccess2">Password:</label>
                                <input type="password" class="form-control" id="inputSuccess1" 
                                       name="password" aria-describedby="helpBlock2" placeholder="Ingrese su Password"
                                       value="<c:out value="${param.password}"/>">
                            </div>
                        </c:if>
                        <c:if test="${not empty mapMensajePass}">
                            <div class="form-group has-error">
                                <label class="control-label" for="inputSuccess2">Password:</label>
                                <input type="password" class="form-control" id="inputError1" name="password" placeholder="Ingrese su Password"
                                       value="<c:out value="${param.password}"/>"
                            </div>
                        </c:if>
                        <button type="submit" class="btn btn-default">Iniciar Sesión</button>
                    </form>
                </div>
                <div class="col-md-1">
                </div>
            </div>
        </div>
    </body>
</html>
