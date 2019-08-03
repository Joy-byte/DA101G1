<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Login</title>
<style>
	body{
		background-color: #00FFFF;
	}
</style>
</head>
<body>



<form action="Member.do" method="post">
	<div>
		帳號:<input type="text" name="MemId"><br>
		密碼:<input type="password" name="MemPsw">
		<input type="submit" value="登入" id="reqlogin">
	</div>
</form>


</body>
</html>