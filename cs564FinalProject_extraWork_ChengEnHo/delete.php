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




$sql = "DELETE from mappings where AccountId = '$_POST[AccountID]' ";
$sql = "DELETE from Accounts where AccountID = '$_POST[AccountID]' ";
$sql = "DELETE from Users where UserID = '$_POST[AccountID]' ";

if ($conn->query($sql) === TRUE) {
    echo "Record deleted successfully";
} else {
    echo "Error deleting record: " . $conn->error;
}

$conn->close();
?> 