<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
          "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script src="js/jquery-3.2.1.min.js"></script>
<title> LojaDw </title>
</head>
<body>
<script type="text/javascript">
	
	function loadProduct(id){
		$.ajax({
			type:"GET",
			url:"",
			data:{
				  id : id,
				  action : "load"
				}
			});
	}
	
	function adicionaAoCarrinho(id){
		$.ajax({
			type:"POST",
			url:"",
			data:{
				  id : id,
				  action : "addToCart"
				},
			success: function(){
					window.location.reload();
				}
			});	
		}
	
</script>
<div style="width:100%;float:left">
	<div style="float:left;width:15%">
		<label>
			LojaDW
		</label>
	</div>
	
	<div style="float:right;width:25%;">
		<a href="login.jsp">Login</a>
	</div>
</div>

<div style="width:100%" style="margin-top:20px;float:left">
	<div style="width:65%;margin:auto" >
	<table>
	<tr>
	<c:forEach items="${categoriaList}" var="cat">
		<th style="width:20%"><a href="#" onclick="loadProduct(${cat.id})" ><c:out  value="${cat.descricao}"/></a>
	</c:forEach>
	</tr>
	</table>
	</div>
	<div style="float:right">
		<a href="carrinho">
			<img alt="carrinho" src="images/carrino.jpg">
		</a>
	</div>
	
	<div style="margin:auto;width:65%">
		<form>
			<input type="text" placeholder="" style="width:45%">
			<input type="submit" value="PESQUISAR" style="width:25%"/>
		</form>
	</div>
	<table>
	<div>
		<c:forEach items="${produtoList}" var="prod">
			<tr> <c:out  value="${prod.descricao} - ${prod.valor}"/> <a href="" onlick="adicionaAoCarrinho({prod.id})">Comprar</a></tr>
		</c:forEach>
	</div>
	</table>
</div>

</body>
</html>
