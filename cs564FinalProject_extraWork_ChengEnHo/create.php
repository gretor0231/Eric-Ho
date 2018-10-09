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




$sql = "insert into Users(firstName, attack, defence, citizenship) values('$_POST[firstname]','$_POST[attack]','$_POST[defence]','$_POST[citizenship]')";
$sql = "insert into Accounts(Balance) values('$_POST[Balance]')";

if ($conn->query($sql) === TRUE) {
    echo "Record created successfully";
} else {
    echo "Error creating record: " . $conn->error;
}

$conn->close();
?>