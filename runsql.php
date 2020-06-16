<?php
$host = "localhost";
$username = "root";
$password = "";
$namedatabase = "quanlycuahang";

$sql = $_POST["sql"];
$conn = mysqli_connect("$host", "$username", "$password", "$namedatabase");
mysqli_set_charset($conn, 'utf8');
if($conn){
    if(mysqli_query($conn,$sql)){
        echo "ok";
    }else{
        echo "nook";
    }

}else{
    echo "nooks";
}
?>