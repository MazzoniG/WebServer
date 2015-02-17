<!DOCTYPE html>
<?php
if($_POST['formSubmit'] == "Submit")
{
	
	$nombre = $_POST['nombre'];
}
?>
<html>
<head>
	<title>My Form</title>
</head>

<body>
	<form action="myform1.php" method="post">
		<p>
			Ingrese su nombre<br>
			<input type="text" name="nombre" maxlength="50" value="<?=$nombre;?>" />
		</p>
		<input type="submit" name="formSubmit" value="Submit" />
	</form>
</body>
</html>