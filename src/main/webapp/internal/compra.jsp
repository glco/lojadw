<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
          "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script src="../js/jquery-3.2.1.min.js"></script>
<title> LojaDw :: Compras</title>
</head>
<body>
<script type="text/javascript">
	function doAction(id,action){
			$.ajax({
				type:"GET",
				url:"compra",
				data:{
					  id : id,
					  action : action
					},
				success: function(){
						window.location.reload();
					}
				});
		}
	
	function doFillUpdateForm(id,cliente, produto){
		var idInput = document.getElementById("createOrUpdateId").value= id;
		var clienteSelect = document.getElementById("formClienteSelect").value = cliente;
		var produtoSelect = document.getElementById("formProdutoSelect").value = produto;
		var actionInput = document.getElementById("createOrUpdateAction").value = "update";
		console.log(idInput);
		console.log(clienteSelect);
		console.log(produtoSelect);
		}
	
	function submitCreateOrUpdateRequest(){
		var form = document.getElementById("createAdminForm").submit();
// 		var form = $('#createAdminForm').serialize();
		}
</script>
	<h2>Compras: </h2>
	
	<table>
	<tr>
		<th>
			cliente
		</th>
		<th>produto</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach items="${compraList}" var="compra">
		<tr>
			<td><c:out value="${compra.cliente.nome}"/></td>
			<td><c:out value="${compra.produto.descricao}"/></td>
			<td><a href="#"  onclick="doAction(${compra.id},'delete');"><img alt="delete icon" width="15px" height="30px" src="../images/delete.png"></a></td>
			<td><a href="#" onclick="doFillUpdateForm('${compra.id }','${compra.cliente.id}',${compra.produto.id});"><img alt="Edit icon" width="15px" height="30px" src="../images/edit.png"></a></td>
		</tr>
	</c:forEach>
 	</table>
 	
 	<div>
		<form id="createAdminForm"  method="post">
			<input id="createOrUpdateId"  type="hidden" name="id" />
			<input id="createOrUpdateAction" type="hidden" name="action" value="create"/>
			<label>Cliente:</label>
			<select id="formClienteSelect" name="clienteId">
				<c:forEach items="${clienteList}" var="cliente">
					<option value="${cliente.id}"> <c:out value="${cliente.nome}"></c:out></option>
				</c:forEach>
			</select>
			<label>Produto:</label>
			<select id="formProdutoSelect" name="produtoId">
				<c:forEach items="${produtoList}" var="prod">
					<option value="${prod.id}"> <c:out value="${prod.descricao}"></c:out></option>
				</c:forEach>
			</select>
			<input type="button" onclick="submitCreateOrUpdateRequest();" value="Enviar"/>
		</form>
	</div>
 	
</body>
</html>
