<html>

<body>

<h1>Cheng En Ho , Fight world database</h1>

<?php
$title = "Home";
echo "selectAccounts\r\n";
echo "<br>";
?>

    <input type="button" value="Say Hi Accounts!" onclick="location='selectAccounts.php'" />
<?php echo "<br>"; ?>
<?php echo "<br>"; ?><?php echo "<br>"; ?><?php echo "<br>"; ?>




<?php
echo "selectEquipment\r\n";
echo "<br>";
?>
    <input type="button" value="Say Hi Equipment!" onclick="location='selectEquipment.php'" />
<?php echo "<br>"; ?>
<?php echo "<br>"; ?><?php echo "<br>"; ?><?php echo "<br>"; ?>







<?php
echo "updateBalance\r\n";
echo "<br>";
?>

<form action="update.php" method="post">

    AccountID: <input type="text" name="AccountID" /><br><br>

    Balance: <input type="text" name="Balance" /><br><br>



    <input type="submit" />

</form>


<?php echo "<br>"; ?>
<?php echo "<br>"; ?><?php echo "<br>"; ?><?php echo "<br>"; ?>






<?php
echo "deleteAccounts\r\n";
echo "<br>";
?>

<form action="delete.php" method="post">

    AccountID: <input type="text" name="AccountID" /><br><br>



    <input type="submit" />

</form>





<?php echo "<br>"; ?>
<?php echo "<br>"; ?><?php echo "<br>"; ?>








<?php
echo "createAccount\r\n";
echo "<br>";
?>

<form action="create.php" method="post">

    firstName: <input type="text" name="firstName" /><br><br>
    attack: <input type="text" name="attack" /><br><br>
    defence: <input type="text" name="defence" /><br><br>
    citizenship: <input type="text" name="citizenship" /><br><br>
    Balance: <input type="text" name="Balance" /><br><br>




    <input type="submit" />

</form>


<?php echo "<br>"; ?><?php echo "<br>"; ?>




</body>
</html>