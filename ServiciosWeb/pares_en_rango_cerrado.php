<?php
$numero=$_REQUEST['numero'];
$array = array();

foreach (range(0, $numero, 1) as $número) {
    if ($número % 2 == 0)
        $array[] = $número;
}

$numeroString=array('numero'=>$array);
echo json_encode($numeroString);
?>