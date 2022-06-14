<?php
$numero=$_REQUEST['numero'];
$array = array();

foreach (range(0, $numero, 2) as $número) {
    $array[] = $número;
}

$numeroString=array('numero'=>$array);
echo json_encode($numeroString);
?>