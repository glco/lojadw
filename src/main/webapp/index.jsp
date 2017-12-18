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
		console.log(id);
		var form = document.getElementById("loadProd");
		var input = document.getElementById("catId");
		input.value = id;
		form.submit();		
	}
	
	function adicionaAoCarrinho(id){
		console.log(id);
		
		var input = document.getElementById("prodId").value = id;
		var form = document.getElementById("addToCart").submit();	
		}
	
</script>
<div style="width:100%;float:left">
	<div style="float:left;width:15%">
		<label>
			LojaDW
		</label>
	</div>
	
	<div style="float:right;width:25%;">
		<div style="float:left;width:50%;">
			<a href="carrinho">
				<img style="width:25px;height:25px" alt="carrinho" src="images/carrinho.png">
			</a>
		</div>
		<div style="width:50%;float:right;">
			<a href="login.jsp">Login</a>
		</div>		
		</div>
</div>

<div style="width:100%" style="margin-top:20px;float:left">
	<div style="width:65%;margin:auto" >
	<table>
	<tr>
	<c:forEach items="${categoriaList}" var="cat">
		<th style="width:20%"><a href="#" onclick="loadProduct(${cat.id})" ><c:out  value="${cat.descricao}"/></a></th>
	</c:forEach>
		<th style="width:20%"> <a href="#" onclick="loadProduct(0)">Limpar</a></th>
	</tr>
	</table>
	</div>
	
	
	<div style="margin:auto;width:65%">
		<form>
			<input type="text" placeholder="" style="width:45%">
			<input type="submit" value="PESQUISAR" style="width:25%"/>
		</form>
	</div>
	<div>
		<table>
			<tr>
				<th>
					Descricao
				</th>
				<th>
					Valor
				</th>
				<th>
					Comprar
				</th>
			</tr>
			<c:forEach items="${produtoList}" var="prod">
				<tr>
					<td>
						<c:out  value="${prod.descricao}"/> 
					</td> 
					<td>
						<c:out  value="${prod.valor}"/>
					</td>
					<td>
						<a href="#" onclick="adicionaAoCarrinho(${prod.id})">Comprar</a>
					</td>
				</tr>
			</c:forEach> 
		</table>
	</div>
</div>
<form id="loadProd" method="get">
	<input id="catId" name="catId" type="hidden" value=""/>
</form>
<form id="addToCart" method="post">
	<input id="prodId" type="hidden" name="produtoId" value=""/>
</form>
</body>
</html>
