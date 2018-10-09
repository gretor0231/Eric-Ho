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


$sql = "select FirstName, attack, defence, citizenship, Balance 
     from Users a join mappings b on a.UserID = b.UserId 
     join Accounts c on c.AccountID = b.AccountId ";



$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "FirstName: " . $row["FirstName"]. " Attack: " . $row["attack"]. " Defence " . $row["defence"].  " citizenship " . $row["citizenship"]." Balance : ".$row["Balance"]."\r\n";
        echo "<br>";
    }
} else {
    echo "0 results";
}
$conn->close();
?> 