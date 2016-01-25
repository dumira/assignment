<?php

//post the json array
$json=$_POST["json"];

//decode the json array
$hotel_data = json_decode($json);

$name = $hotel_data->{'name'};
$address = $hotel_data->{'address'};
$city = $hotel_data->{'city'};
$lat = $hotel_data->{'lat'};
$lon = $hotel_data->{'lon'};

// PHP variable to store the host address
 $db_host  = "localhost";
 // PHP variable to store the username
 $db_uid  = "root";
 // PHP variable to store the password
 $db_pass = "";
 // PHP variable to store the Database name  
 $db_name  = "test";
 
 
 // Create connection
$conn = new mysqli($db_host, $db_uid, $db_pass, $db_name);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

//sql query
$sql = "INSERT INTO hotel (name, address, city, latitude, longitude)
VALUES ('$name', '$address', '$city', '$lat','$lon')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();

?>