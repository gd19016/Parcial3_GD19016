<?php
$juego=$_REQUEST['juego'];

$servidor="localhost";
$usuario="id19079472_gd19016";
$baseDatos="id19079472_gd19016pdm115";
$password="6?@2VD<>ffd4jcg+";
$conexion=mysql_connect($servidor,$usuario,$password) or die ("Problemas en la conexion");

mysql_select_db($baseDatos,$conexion) or  die("Problemas en la selección de la base de datos");

$registros=mysql_query("SELECT juego, AVG(participantes) AS PROMEDIO FROM partida WHERE juego=".$juego." group by juego", $conexion) or die("Problemas en el select:".mysql_error());
$filas=array();

while ($reg=mysql_fetch_assoc($registros))
{
 $filas[]=$reg;
}

echo json_encode($filas);
mysql_close($conexion);
?>