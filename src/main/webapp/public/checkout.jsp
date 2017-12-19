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

	form {
    margin: 0 auto;
    width: 500px;
    padding: 1em;
    border: 1px solid #CCC;
    border-radius: 1em;
	}

	form div + div {
	    margin-top: 1em;
	}

	label {
	    display: inline-block;
	    width: 120px;
	    text-align: right;
	}

	input, textarea {
	    font: sans-serif;
	    font-size: 15px;
	    width: 300px;
	    -moz-box-sizing: border-box;
	    box-sizing: border-box;
	    border: 1px solid #999;
	}

	textarea {
	    vertical-align: top;
	    height: 5em;
	    resize: vertical;
	}

	.button {
	    padding-left: 100px;
		  
	}


</style>
	<h2 align="center">Checkout: </h2>
 	
 	<form id="createForm"  method="post">
 		<input id="createOrUpdateId"  type="hidden" name="id" />
		<input id="createOrUpdateAction" type="hidden" name="action" value="create"/>

		<div>	
			<label>Nome:</label>
			<input id="formNomeInput" name="nome" type="text" placeholder="Nome completo" value="" required/>
		</div>
		<div>	
			<label>E-mail:</label>
			<input id="formEmailInput" name="email" type="email" placeholder="exemplo@exemplo.com" value="" required/>
		</div>
		<div>
			<label>CPF:</label>
			<input id="formCpfInput" name="cpf" type="text" placeholder="" value="" required/>
		</div>
		<div>	
			<label>Estado:</label>
			<input id="formEstadoInput" name="estado" type="text" placeholder="" value="" required/>
		</div>
		<div>	
			<label>Cidade:</label>
			<input id="formCidadeInput" name="cidade" type="text" placeholder="" value="" required/>
		</div>
		<div>	
			<label>Bairro:</label>
			<input id="formBairroInput" name="bairro" type="text" placeholder="" value="" required/>
		</div>
		<div>	
			<label>CEP:</label>
			<input id="formCepInput" name="cep" type="text" placeholder="" value="" required/>
		</div>
		<div>	
			<label>Endereço:</label>
			<input id="formEnderecoInput" name="endereco" type="text" placeholder="" value="" required/>
		</div>
		<div>	
			<label>Referência:</label>
			<input id="formReferenciaInput" name="referencia" type="text" placeholder="" value="" required/>
		</div>
		<div>	
			<label>Telefone fixo:</label>
			<input id="formTelefoneInput" name="telefone" type="tel" placeholder="" value="" required/>
		</div>
		<div>	
			<label>Celular:</label>
			<input id="formCelularInput" name="celular" type="tel" placeholder="" value="" required/>
		</div>
		<div>	
			<label>Nº do cartão:</label>
			<input id="formCataoInput" name="cartao" type="text" placeholder="" value="" onblur="return valid_credit_card()" required/>
		</div>
		<div>	
			<label>Bandeira:</label>
			<select id="bandeiraInput">
				<option value="amex">American Express</option>
				<option value="elo">Elo</option>
				<option value="master">MasterCard</option>
				<option value="visa">Visa</option>
			</select>
		</div>	
		<div class="button">
			<input type="button" onclick="submitCreateOrUpdateRequest();" value="Enviar"/>
		</div>

	</form>
 	
</body>
</html>
