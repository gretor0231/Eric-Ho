<html>

<body>

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
?>



<?php
/** @var TYPE_NAME $sql */
$sql = "UPDATE Accounts SET Balance = '$_POST[Balance]' WHERE AccountID = '$_POST[AccountID]' ";




if ($conn->query($sql) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();
?>



</body>

</html>
