<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- GRUPOS -->
<c:forEach var="grupo" items="${gruposParticipa }">
    <a class="collection-item modal-trigger" href="#${grupo}">${grupo}</a>
</c:forEach>