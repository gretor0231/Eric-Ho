import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class DBserverce {
    String url = "jdbc:mysql://chester.cs.unm.edu:3306/ericho";
    String user = "ericho";
    String password = "rvWERE2y";

    private Connection connect() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            connection = null;
        }
        return connection;
    }

    //create(add account)

    int AddAccount(String firstName, Double attack, Double defence, String citizenship, Double balance) {
        int userId = -1;
        int accountId = -1;
        Connection connection = connect();

        try {
            connection.setAutoCommit(false);
            //AddUser
            String addUserSql = "insert into Users(firstName, attack, defence, citizenship) values(?,?,?,?)";
            PreparedStatement addUser = connection.prepareStatement(addUserSql, Statement.RETURN_GENERATED_KEYS);
            addUser.setString(1, firstName);
            addUser.setDouble(2, attack);
            addUser.setDouble(3, defence);
            addUser.setString(4, citizenship);
            addUser.executeUpdate();

            ResultSet addUserResults = addUser.getGeneratedKeys();
            if (addUserResults.next()) {
                userId = addUserResults.getInt(1);
            }


            //AddAccount
            String addAccountSql = "insert into Accounts(Balance) values(?)";
            PreparedStatement addAccount = connection.prepareStatement(addAccountSql, Statement.RETURN_GENERATED_KEYS);
            addAccount.setDouble(1, balance);
            addAccount.executeUpdate();

            ResultSet addAccountResults = addAccount.getGeneratedKeys();
            if (addAccountResults.next()) {
                accountId = addAccountResults.getInt(1);
            }
            if (userId > 0 && accountId > 0) {
                String linkAccountSql = "insert into mappings(UserId, AccountId) values (?,?)";
                PreparedStatement linkAccount = connection.prepareStatement(linkAccountSql);
                linkAccount.setInt(1, userId);
                linkAccount.setInt(2, accountId);
                linkAccount.executeUpdate();
                connection.commit();

            } else {
                connection.rollback();
            }
            connection.close();

        } catch (SQLException ex) {
            System.err.println("An error !!!!!!!!!!!!!!! has occure!!!!!!!!!!!!!!!!!!!!!" + ex.getMessage());

        }
        return accountId;

    }

    //read Account
    Fitherinfo GetAccount(int accountId) {
        Fitherinfo fither = null;
        Connection connection = connect();
        try {
            String findUserSql = "select FirstName, attack, defence, citizenship, Balance "
                    + "from Users a join mappings b on a.UserID = b.UserId "
                    + "join Accounts c on c.AccountID = b.AccountId "
                    + "where c.AccountID = ? ";
            PreparedStatement findUser = connection.prepareStatement(findUserSql);
            findUser.setInt(1, accountId);
            ResultSet findUserResults = findUser.executeQuery();
            if (findUserResults.next()) {
                String firstName = findUserResults.getString("firstName");
                double attack = findUserResults.getDouble("attack");
                double defence = findUserResults.getDouble("defence");
                String citizenship = findUserResults.getString("citizenship");
                double balance = findUserResults.getDouble("Balance");

                fither = new Fitherinfo(firstName, attack, defence, citizenship, balance);

            } else {
                System.err.println("Nooooooooooooooo~!!! User account was not found for ID !!!!!!" + accountId);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return fither;
    }


//    update Account

    boolean UpdateAccount(int accountId, double balance) {
        boolean success = false;
        Connection connection = connect();
        try {
            String updateSql;
            updateSql = "UPDATE Accounts SET Balance = ? WHERE AccountID = ?";
            PreparedStatement updateBalance = connection.prepareStatement(updateSql);
            updateBalance.setDouble(1, balance);
            updateBalance.setInt(2, accountId);
            updateBalance.executeUpdate();

            success = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBserverce.class.getName()).log(Level.SEVERE, null, ex);

        }
        return success;
    }


//    delete Account

    boolean DeleteAccount(int accountId) {
        boolean success = false;
        Connection connection = connect();
        try {
            String deleteSql = "DELETE "
                    + "from mappings "
                    + "where AccountId = ? ";
            PreparedStatement deleteBalance = connection.prepareStatement(deleteSql);
            deleteBalance.setInt(1, accountId);
            deleteBalance.executeUpdate();

            String deleteSql1 = "DELETE "
                    + "from Accounts "
                    + "where AccountID = ? ";
            PreparedStatement deleteBalance1 = connection.prepareStatement(deleteSql1);
            deleteBalance1.setInt(1, accountId);
            deleteBalance1.executeUpdate();

            String deleteSql2 = "DELETE "
                    + "from Users "
                    + "where UserID = ? ";
            PreparedStatement deleteBalance2 = connection.prepareStatement(deleteSql2);
            deleteBalance2.setInt(1, accountId);
            deleteBalance2.executeUpdate();


            success = true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return success;
    }


    //read All accounts information by using ArrayList.

    boolean GetAllAccounts() {
        ArrayList<Fitherinfo> Fithers = new ArrayList<>();
        Connection connection = connect();
        boolean success = false;
        try {
            try ( PreparedStatement findUser = connection.prepareStatement(
                    "select FirstName, attack, defence, citizenship, Balance "
                            + "from Users a join mappings b on a.UserID = b.UserId "
                            + "join Accounts c on c.AccountID = b.AccountId "
                            ) ) {
                ResultSet findUserResults = findUser.executeQuery();
                while (findUserResults.next()) {
                    String firstName = findUserResults.getString("firstName");
                    double attack = findUserResults.getDouble("attack");
                    double defence = findUserResults.getDouble("defence");
                    String citizenship = findUserResults.getString("citizenship");
                    double balance = findUserResults.getDouble("Balance");

                    Fitherinfo fither = new Fitherinfo(firstName, attack, defence, citizenship, balance);
                    Fithers.add(fither);
                }
                Iterator<Fitherinfo> itr = Fithers.iterator();
                while(itr.hasNext()){
                    System.out.println(itr.next());
                }

                success = true;

            }
        } catch (SQLException ex) {
            System.err.println("An error has occured!!FFFFFFFFFK" + ex.getMessage());
        }

        return success;

    }




    //read Equipment
    Equipment GetEquipment(int equipmentId) {
        Equipment equipment = null;
        Connection connection = connect();
        try {
            String findEquSql = "select Name, Attack, Defence, Price "
                    + "from EquipmentTable "
                    + "where EquipmentID = ? ";
            PreparedStatement findEqu = connection.prepareStatement(findEquSql);
            findEqu.setInt(1, equipmentId);
            ResultSet findEquResults = findEqu.executeQuery();
            if (findEquResults.next()) {
                String Name = findEquResults.getString("Name");
                double attack = findEquResults.getDouble("Attack");
                double defence = findEquResults.getDouble("Defence");
                double price = findEquResults.getDouble("Price");

                equipment = new Equipment(Name, attack, defence, price);

            } else {
                System.err.println("Nooooooooooooooo~!!! equipment was not found for ID !!!!!!" + equipmentId);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return equipment;
    }

    //read All equiments information by using ArrayList.

    boolean GetAllEquiments() {
        ArrayList<Equipment> Equipments = new ArrayList<>();
        Connection connection = connect();
        boolean success = false;
        try {
            try ( PreparedStatement findEqu = connection.prepareStatement(
                    "select Name, Attack, Defence, Price "
                            + "from EquipmentTable "
            ) ) {
                ResultSet findEquResults = findEqu.executeQuery();
                while (findEquResults.next()) {

                    String Name = findEquResults.getString("Name");
                    double attack = findEquResults.getDouble("Attack");
                    double defence = findEquResults.getDouble("Defence");
                    double price = findEquResults.getDouble("Price");

                    Equipment equipment = new Equipment(Name, attack, defence, price);
                    Equipments.add(equipment);
                }
                Iterator<Equipment> itr = Equipments.iterator();
                while(itr.hasNext()){
                    System.out.println(itr.next());
                }

                success = true;

            }
        } catch (SQLException ex) {
            System.err.println("An error has occured!!FFFFFFFFFK" + ex.getMessage());
        }

        return success;

    }




    //read Location
    Location GetLocation(int locationId) {
        Location location = null;
        Connection connection = connect();
        try {
            String findLocSql = "select Country "
                    + "from LocationTable "
                    + "where LocationID = ? ";
            PreparedStatement findLoc = connection.prepareStatement(findLocSql);
            findLoc.setInt(1, locationId);
            ResultSet findLocResults = findLoc.executeQuery();
            if (findLocResults.next()) {
                String Country = findLocResults.getString("Country");

                location = new Location(Country);

            } else {
                System.err.println("Nooooooooooooooo~!!! Location was not found for ID !!!!!!" + locationId);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return location;
    }


    //read Weather
    Weather GetWeather(int weatherId) {
        Weather weather = null;
        Connection connection = connect();
        try {
            String findWeaSql = "select Weather "
                    + "from WeatherTable "
                    + "where WeatherID = ? ";
            PreparedStatement findWea = connection.prepareStatement(findWeaSql);
            findWea.setInt(1, weatherId);
            ResultSet findWeaResults = findWea.executeQuery();
            if (findWeaResults.next()) {
                Double Weather = findWeaResults.getDouble("Weather");

                weather = new Weather(Weather);

            } else {
                System.err.println("Nooooooooooooooo~!!! Weather was not found for ID !!!!!!" + weatherId);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return weather;
    }

}