<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
          "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script src="../js/jquery-3.2.1.min.js"></script>
<title> LojaDw :: Carrinho</title>
</head>
<body>
<script type="text/javascript">
	function doAction(id,action){
			$.ajax({
				type:"GET",
				url:"produto",
				data:{
					  id : id,
					  action : action
					},
				success: function(){
						window.location.reload();
					}
				});
		}
	
	function doFillUpdateForm(id,valor,nome,categoria,descricao){
		var idInput = document.getElementById("createOrUpdateId").value= id;
		var valorInput = document.getElementById("formValorInput").value = valor;
		var nomeInput = document.getElementById("formNomeInput").value = nome;
		var categoriaSelect = document.getElementById("formCategoriaSelect").value = categoria;
		var descricaoInput = document.getElementById("formDescricaoInput").value = descricao;
		var actionInput = document.getElementById("createOrUpdateAction").value = "update";
		console.log(id);
		console.log(valorInput);
		console.log(nomeInput);
		console.log(descricaoInput);
		console.log(categoria);
		console.log(actionInput);
		}
	
	function submitCreateOrUpdateRequest(){
		var form = document.getElementById("createDescricaoForm").submit();
		}

	function checkout(){
			window.top.location = "checkout";
		}	
	function returnHome(){
			window.top.location = "/lojadw/";
		}
	
</script>
<div style="width:65%;margin:auto">
	<h2>Carrinho: </h2>
	
	<table>
	<tr>
		<th>
			nome
		</th>
		<th>valor</th>
		<th>descricao</th>
		<th>categoria</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach items="${produtoList}" var="prod">
		<tr>
			<td><c:out value="${prod.nome}"/></td>
			<td><c:out value="${prod.valor}"/></td>
			<td><c:out value="${prod.descricao}"/></td>
			<td><c:out value="${prod.categoria.descricao}"/></td>
			<td><a href="#"  onclick="doAction(${prod.id},'delete');"><img alt="remove" width="15px" height="30px" src="../images/delete.png"></a></td>
		</tr>
	</c:forEach>
 	</table>
 	<div>
 		<button style="float:left" onclick="returnHome()"> < Voltar</button>
 	</div>
 	<div>
 		<button style="float:right" onclick="checkout()">Checkout ></button>
 	</div>
 	</div>
</body>
</html>
