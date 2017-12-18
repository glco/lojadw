<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
          "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script src="../js/jquery-3.2.1.min.js"></script>
<title> LojaDw :: Clientes</title>
</head>
<body>
<script type="text/javascript">
	function doAction(id,action){
			$.ajax({
				type:"GET",
				url:"clientes",
				data:{
					  id : id,
					  action : action
					},
				success: function(){
						window.location.reload();
					}
				});
		}
	
	function doFillUpdateForm(id,nome,email,cpf,estado,cidade,bairro,cep,endereco,referencia,telefone_fixo,celular,cartao,bandeira){
		var idInput = document.getElementById("createOrUpdateId").value= id;
		var nomeInput = document.getElementById("formNomeInput").value = nome;
		var emailInput = document.getElementById("formEmailInput").value = email;
		var cpfInput = document.getElementById("formCpfInput").value = cpf;
		var estadoInput = document.getElementById("formEstadoInput").value = estado;
		var cidadeInput = document.getElementById("formCidadeInput").value = cidade;
		var bairroInput = document.getElementById("formBairroInput").value = bairro;
		var cepInput = document.getElementById("formCepInput").value = cep;
		var enderecoInput = document.getElementById("formEnderecoInput").value = endereco;
		var referenciaInput = document.getElementById("formReferenciaInput").value = referencia;
		var telefoneInput = document.getElementById("formTelefoneInput").value = telefone_fixo;
		var celularInput = document.getElementById("formCelularInput").value = celular;
		var cartaoInput = document.getElementById("formCartaoInput").value = cartao;
		var bandeiraInput = document.getElementById("formBandeiraInput").value = bandeira;
		
		var actionInput = document.getElementById("createOrUpdateAction").value = "update";
		}
	
	function submitCreateOrUpdateRequest(){
		var form = document.getElementById("createForm").submit();
		}

</script>
<style>
	#createForm{
		margin-top:20px;
		width:100%;
	}
	#createForm label{
		float:left;
		width:5%;
		margin: 5px;
	}
	#createForm input{
		width:95%;
		float:right;
	}
	#createForm button{
		margin-top:30px;
		width:40%;
		margin:auto;
	}
</style>
	<h2>Checkout: </h2>
 	
 	<div>
		<form id="createForm"  method="post">
			<input id="createOrUpdateId"  type="hidden" name="id" />
			<input id="createOrUpdateAction" type="hidden" name="action" value="create"/>
			<label>Nome:</label><input id="formNomeInput" name="nome" type="text" placeholder="nome..." value="" required/>
			<label>email:</label><input id="formEmailInput" name="email" type="text" placeholder="valor..." value="" required/>
			<label>cpf:</label><input id="formCpfInput" name="cpf" type="text" placeholder="..." value="" required/>
			<label>estado:</label><input id="formEstadoInput" name="estado" type="text" placeholder="..." value="" required/>
			<label>cidade:</label><input id="formCidadeInput" name="cidade" type="text" placeholder="..." value="" required/>
			<label>bairro:</label><input id="formBairroInput" name="bairro" type="text" placeholder="..." value="" required/>
			<label>cep:</label><input id="formCepInput" name="cep" type="text" placeholder="..." value="" required/>
			<label>endereco:</label><input id="formEnderecoInput" name="endereco" type="text" placeholder="..." value="" required/>
			<label>referencia:</label><input id="formReferenciaInput" name="referencia" type="text" placeholder="..." value="" required/>
			<label>telefone_fixo:</label><input id="formTelefoneInput" name="telefone" type="text" placeholder="..." value="" required/>
			<label>celular:</label><input id="formCelularInput" name="celular" type="text" placeholder="..." value="" required/>
			<label>n_cartao:</label><input id="formCataoInput" name="cartao" type="text" placeholder="..." value="" required/>
			<label>bandeira:</label><input id="formBandeiraInput" name="bandeira" type="text" placeholder="..." value="" required/>
			
			<input type="button" onclick="submitCreateOrUpdateRequest();" value="Enviar"/>
		</form>
	</div>
 	
</body>
</html>
