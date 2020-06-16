<?php
$host = "localhost";
$username = "root";
$password = "";
$namedatabase = "quanlycuahang";

$nameTable = $_GET["name"];
$conn = mysqli_connect("$host", "$username", "$password", "$namedatabase");
mysqli_set_charset($conn, 'utf8');
if($conn){
    $sql = "SELECT COUNT(*) FROM lichsu WHERE idkhach =".$nameTable;
    $arr = array();
    $query = mysqli_query($conn,$sql);

    while($row = mysqli_fetch_assoc($query)){
        $arr[] = $row;
    }
    echo json_encode($arr);
}else{
    echo "nook";
}
?>