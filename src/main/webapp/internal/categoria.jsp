<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
          "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title> LojaDw :: Categorias</title>
</head>
<body>
<script type="text/javascript">
	function doAction(id,action){
			$.ajax({
				type:"GET",
				url:"categoria",
				data:{
					  id : id,
					  action : action
					},
				success: function(){
						window.location.reload();
					}
				});
		}
	
	function doFillUpdateForm(id,descricao){
		var idInput = document.getElementById("createOrUpdateId").value= id;
		var descricaoInput = document.getElementById("createOrUpdateDescricao").value = descricao;
		var actionInput = document.getElementById("createOrUpdateAction").value = "update";
		console.log(idInput);
		console.log(descricaoInput);
		console.log(actionInput);
		}
	
	function submitCreateOrUpdateRequest(){
		var form = document.getElementById("createDescricaoForm").submit();
		}
	
</script>
	<h2>Categorias: </h2>
	
	<table>
	<tr>
		<th>
			login
		</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach items="${categoriaList}" var="cat">
		<tr>
			<td><c:out value="${cat.descricao}"/></td>
			<td><a href="#"  onclick="doAction(${cat.id},'delete');"><img alt="delete icon" width="15px" height="30px" src="../images/delete.png"></a></td>
			<td><a href="#" onclick="doFillUpdateForm('${cat.id }','${cat.descricao}');"><img alt="Edit icon" width="15px" height="30px" src="../images/edit.png"></a></td>
		</tr>
	</c:forEach>
 	</table>
 	
 	<div>
		<form id="createDescricaoForm"  method="post">
			<input id="createOrUpdateId"  type="hidden" name="id" />
			<input id="createOrUpdateAction" type="hidden" name="action" value="create"/>
			<label>Descricao:</label><input id="createOrUpdateDescricao" name="descricao" type="text" placeholder="descricao" value=""/>
			<input type="button" onclick="submitCreateOrUpdateRequest();" value="Enviar"/>
		</form>
	</div>
 	
</body>
</html>
