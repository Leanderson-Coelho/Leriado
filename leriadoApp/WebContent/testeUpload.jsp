<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="testeUpload?upload=2" method="post" enctype="multipart/form-data" id="form1">
		<input type="file" name="files" multiple><br>
		<input type="text" name="titulo"><br>
		<input type="text" name="conteudo"><br>
		<input type="url" name="link"><br>
		<input type="submit" value="==>">
	</form>
</body>
</html>