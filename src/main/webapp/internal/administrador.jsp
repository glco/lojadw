<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
          "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title> LojaDw :: Administradores</title>
</head>
<body>
<script type="text/javascript">
	function doAction(id,action){
			$.ajax({
				type:"GET",
				url:"administrador",
				data:{
					  id : id,
					  action : action
					}
				});
		}

	function submitCreateOrUpdateRequest(){
		var form = document.getElementById("createAdminForm");
		console.log(form);
		}
</script>
	<h2>Administradores: </h2>
	
	<table>
	<tr>
		<th>
			login
		</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach items="${administradorList}" var="adm">
		<tr>
			<td><c:out value="${adm.login}"/></td>
			<td><a href="#"  onclick="doAction(${adm.id},'delete');"><img alt="delete icon" src="delete.png"></a></td>
			<td><a href="#" onclick="fillForm(${adm.id}, ${adm.login } );"><img alt="Edit icon" src="edit.png"></a></td>
		</tr>
	</c:forEach>
 	</table>
 	
 	<div>
		<form id="createAdminForm" >
			<input id="createOrUpdateId"  type="hidden" name="id" />
			<input id="createOrUpdateAction" type="hidden" name="action" value="create"/>
			<label>Login:</label><input name="login" type="text" placeholder="login..." value=""/>
			<label>Senha:</label><input name="senha" type="password" placeholder="senha" /> 
			<input type="button" onclick="submitCreateOrUpdateRequest();" value="Enviar"/>
		</form>
	</div>
 	
</body>
</html>
