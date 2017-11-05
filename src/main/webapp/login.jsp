<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8" 
		pageEncoding="UTF-8" session="false"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login :: LojaDW</title>
</head>
<body>
<form action="doLogin" method="post">
<label>Login: </label> <input type="text" name="login"></input> <br/>
<label>Senha: </label><input type="password" name="senha"></input><br/>
<input type="submit" value="Login"></input>
</form>
</body>
</html>
</jsp:root>