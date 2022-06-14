<?php
$juego=$_REQUEST['juego'];
$participantes=$_REQUEST['participantes'];
$ganador=$_REQUEST['ganador'];

$servidor="localhost";
$usuario="id19079472_gd19016";
$baseDatos="id19079472_gd19016pdm115";
$password="6?@2VD<>ffd4jcg+";
$respuesta=array('resultado'=>0);

json_encode($respuesta);
$conexion=mysql_connect($servidor,$usuario,$password) or die ("Problemas en la conexion");

mysql_select_db($baseDatos,$conexion) or  die("Problemas en la seleccion de la base de datos");
$query = "INSERT INTO partida (juego, participantes, ganador) VALUES(".$juego.",".$participantes.",'".$ganador."');";
//echo($query);
$resultado = mysql_query($query) or die(mysql_error());

//Si la respuesta es correcta enviamos 1 y sino enviamos 0
if(mysql_affected_rows() == 1)
   $respuesta=array('resultado'=>1);
   
echo json_encode($respuesta);
mysql_close($conexion);
?>