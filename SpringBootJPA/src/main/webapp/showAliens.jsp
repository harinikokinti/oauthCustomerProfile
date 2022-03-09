<%@ page language="java" contentType= "text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<body>
<h1> Get all Aliens </h1>
<c:if test="${not empty alienList}">
	<c:forEach var="alien" items="${alienList}">
	${alien} <br>
	</c:forEach>
</c:if>	

</body>
