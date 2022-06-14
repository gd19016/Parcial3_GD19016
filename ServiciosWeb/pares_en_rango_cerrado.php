<?php
if (!isset($_REQUEST['numero'])) {
    echo "Debe especificar un número.";
    return;
}

$numero=$_REQUEST['numero'];

if (strval($numero) !== strval(intval($numero))) {
    echo "Debe especificar un número entero.";
    return;
}

if ($numero >= 0)
    echo "Números en el rango:<br />";

if ($numero < 0) {
    echo "El número debe ser positivo.<br />";
    return;
}

foreach (range(0, $numero, 2) as $número) {
    echo $número;
    echo "<br />";
}
?>