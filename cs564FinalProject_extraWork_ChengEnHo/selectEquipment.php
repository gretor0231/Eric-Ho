<?php
$servername = "chester.cs.unm.edu";
$username = "ericho";
$password = "rvWERE2y";
$dbname = "ericho";

// Create connection
$conn = new mysqli($servername, $username, $password ,$dbname );
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

echo 'Connected to the database.'."\r\n";
echo "<br>";


$sql = "SELECT Name, Attack, Defence, Price FROM EquipmentTable ";



$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "Name: " . $row["Name"]. " Attack: " . $row["Attack"]. " Defence " . $row["Defence"]. " Price : ".$row["Price"]."\r\n";
        echo "<br>";
    }
} else {
    echo "0 results";
}
$conn->close();
?> 