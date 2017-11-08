<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
          "http://www.w3.org/TR/html4/loose.dtd">

<head>
<title>:: Loja DW ::</title>
</head>
<body>
	<div>
		<h2 style="float:left;width:75%;" >Bem vindo ${sessionScope.UserName}!</h2>
		<a style="float:right;width:25%;"  href="#" >Logout</a>
	</div>
	<div style="width:30%;float:left;">
	<label>Clique em uma categoria abaixo para a gerenciar: </label>
	<ul>
		<li><a href="administrador" >Administradores</a></li>
		<li>Produtos</li>
		<li>Categorias</li>
		<li>Compras</li>
		<li>Clientes</li>
		
	</ul>
	</div>
</body>
</html>
