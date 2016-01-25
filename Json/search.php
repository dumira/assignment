<?php
//post json array
$json=$_POST["json"];

//decode json array
$hotel_data = json_decode($json);

$city = $hotel_data->{'city'};

// PHP variable to store the host address
 $db_host  = "localhost";
 // PHP variable to store the username
 $db_uid  = "root";
 // PHP variable to store the password
 $db_pass = "";
 // PHP variable to store the Database name  
 $db_name  = "test"; 
 
 
 // PHP variable to store the result of the PHP function 'mysql_connect()' which establishes the PHP & MySQL connection  
 $db_con = mysql_connect($db_host,$db_uid,$db_pass) or die('could not connect');
 mysql_select_db($db_name);
 
 //sql query
 $sql = "SELECT * FROM hotel WHERE  city = '$city' ORDER BY name";
 $result = mysql_query($sql);
 
 //get all results
 while($row=mysql_fetch_assoc($result))
  $output[]=$row;
//return output
 print(json_encode($output));
 mysql_close();   
?>