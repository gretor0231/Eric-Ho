/******************
 CS564 Database Final Project : FIGHT WORLD!
 2018/5/1
 Author: Cheng En Ho.

***************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        while(true) {

            try {
                System.out.println("Welcome to our FIGHT WORLD!!");
                System.out.println("Please select a function in our Fight system!");
                System.out.println("1. Create a Fighter account");
                System.out.println("2. Update Fighter account money");
                System.out.println("3. Read a Fighter account information");
                System.out.println("4. Delete a Fighter account");
                System.out.println("5. Read all Fighters account");
                System.out.println("6. Read all Equipments information");
                System.out.println("7. Two players Fight!");
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                System.out.println("please enter the number 1-7");
                String temp = br.readLine();
                System.out.println(temp);

                if (temp.equals("1")) {
                    System.out.println("Yes! Thank you for create a Fighter, but we do not have any create reward!");
                    DBserverce addAcount = new DBserverce();

                    System.out.println("Please enter the Fighter's first name");
                    String firstname =br.readLine();
                    System.out.println(firstname);

                    System.out.println("Please enter the Fighter's Attack value");
                    String attack =br.readLine();
                    System.out.println(attack);
                    double attacknumber = Double.parseDouble(attack);

                    System.out.println("Please enter the Fighter's Defence value");
                    String defence =br.readLine();
                    System.out.println(defence);
                    double defencenumber = Double.parseDouble(defence);

                    System.out.println("Please enter the Fighter's citizenship");
                    String citizenship =br.readLine();
                    System.out.println(citizenship);

                    System.out.println("Please enter Fighter's initial deposit");
                    String balance =br.readLine();
                    System.out.println(balance);
                    double number = Double.parseDouble(balance);

                    int accountID;
                    accountID = addAcount.AddAccount(firstname,attacknumber,defencenumber,citizenship,number);
                    System.out.println("Thank you for create a Fighter, your Fighter ID is "+ accountID);

                } else if (temp.equals("2")) {
                    System.out.println("Yes, Please enter your Account ID!");
                    String accountID =br.readLine();
                    System.out.println(accountID);
                    int ID = Integer.parseInt(accountID);

                    System.out.println("Yes, Please enter your balance!");
                    String balance =br.readLine();
                    System.out.println(balance);
                    double number = Double.parseDouble(balance);
                    DBserverce updateAcount = new DBserverce();
                    boolean temp1;
                    temp1 = updateAcount.UpdateAccount(ID,number);
                    System.out.println("UPDATE amount success " + temp1);

                } else if (temp.equals("3")) {
                    System.out.println("Yes, we are trying to read the User data!!");

                    System.out.println("Yes, Please enter your account ID!");
                    String accountID =br.readLine();
                    System.out.println(accountID);
                    int ID = Integer.parseInt(accountID);

                    DBserverce getAcount = new DBserverce();
                    Fitherinfo fitherinfo;
                    fitherinfo = getAcount.GetAccount(ID);

                    System.out.println(fitherinfo.toString());



                } else if (temp.equals("4")) {
                    System.out.println("Yes, We are deleting your account!!! Please enter your account ID!!");
                    String accountID =br.readLine();
                    System.out.println(accountID);
                    int deleteID = Integer.parseInt(accountID);
                    DBserverce deleteAcount = new DBserverce();
                    boolean temp2;
                    temp2 = deleteAcount.DeleteAccount(deleteID);
                    System.out.println("Delete account success " + temp2);


                }else if (temp.equals("5")) {
                    System.out.println("Yes, we are trying to read all Users data!!");

                    DBserverce AllAccounts = new DBserverce();
                    boolean total;
                    total = AllAccounts.GetAllAccounts();

                    System.out.println(total);


                }else if (temp.equals("6")) {
                    System.out.println("Yes, we are trying to read all Equipments data!!");

                    DBserverce AllEquipments = new DBserverce();
                    boolean total;
                    total = AllEquipments.GetAllEquiments();

                    System.out.println(total);



                }else if (temp.equals("7")) {

                    System.out.println("yes, two player flight!!!");


//                    somthing get from Users

                    System.out.println("Please enter player 1 User ID");
                    String accountID =br.readLine();
                    System.out.println(accountID);
                    int ID1 = Integer.parseInt(accountID);

                    DBserverce getAcount = new DBserverce();
                    Fitherinfo fitherinfo;
                    fitherinfo = getAcount.GetAccount(ID1);

                    System.out.println(fitherinfo.toString());

                    double attack1, dffence1,balance1;
                    String Name1, citizenship1;
                    attack1 = fitherinfo.getAttack();
                    dffence1 = fitherinfo.getDefence();
                    Name1 = fitherinfo.getFirstName();
                    citizenship1 = fitherinfo.getcitizenship();
                    balance1 = fitherinfo.getBalance();

//                    somthing get from Users

                    System.out.println("Please enter player 2 User ID");
                    String accountID2 =br.readLine();
                    System.out.println(accountID2);
                    int ID2 = Integer.parseInt(accountID2);

                    fitherinfo = getAcount.GetAccount(ID2);

                    System.out.println(fitherinfo.toString());

                    double attack2, dffence2, balance2;
                    String Name2,citizenship2;
                    Name2 = fitherinfo.getFirstName();
                    attack2 = fitherinfo.getAttack();
                    dffence2 = fitherinfo.getDefence();
                    citizenship2 = fitherinfo.getcitizenship();
                    balance2 = fitherinfo.getBalance();

//                    something get from Users



                    System.out.println("Notice: Total Attack = player.attack + Equipment.attack");
                    System.out.println("Notice: Total Defence = player.defence + Equipment.defence");

                    while(true) {
                        System.out.println("player 1, do you want to buy Equipment?");
                        System.out.println("You have balance: " + balance1);

                        System.out.println("1.Yes");
                        System.out.println("2.NO");

                        String test = br.readLine();
                        int number = Integer.parseInt(test);
                        if (number == 1) {
                            System.out.println("player 1, Please enter EquipmentID");
                            String test1 = br.readLine();
                            int EquID = Integer.parseInt(test1);
                            DBserverce getEquipment = new DBserverce();
                            Equipment equipment;

                            equipment = getEquipment.GetEquipment(EquID);
                            System.out.println(equipment.toString());

                            if (balance1 < equipment.getPrice()){
                                System.out.println("You do not have enough money!!");
                            }else {
                                System.out.println("Player1: Attack: "+ attack1 +" + Equipment' ATTACK "+ equipment.getAttack());
                                System.out.println("Player1: Defence: "+ dffence1 +" + Equipment' DEFENCE " + equipment.getDefence());
                                attack1 = attack1 + equipment.getAttack();
                                dffence1 = dffence1 + equipment.getDefence();
                                break;
                            }

                        }else {
                            break;
                        }

                    }
//                    somthing happend to player1




                    System.out.println("Notice: Total Attack = player.attack + Equipment.attack");
                    System.out.println("Notice: Total Defence = player.defence + Equipment.defence");


                    while(true) {
                        System.out.println("player 2, do you want to buy Equipment?");
                        System.out.println("You have balance: " + balance2);

                        System.out.println("1.Yes");
                        System.out.println("2.NO");

                        String test = br.readLine();
                        int number = Integer.parseInt(test);
                        if (number == 1) {
                            System.out.println("player 2, Please enter EquipmentID");
                            String test1 = br.readLine();
                            int EquID = Integer.parseInt(test1);
                            DBserverce getEquipment = new DBserverce();
                            Equipment equipment2;

                            equipment2 = getEquipment.GetEquipment(EquID);
                            System.out.println(equipment2.toString());

                            if (balance2 < equipment2.getPrice()){
                                System.out.println("You do not have enough money!!");
                            }else {
                                System.out.println("Player2: Attack: "+ attack2 +" + Equipment' ATTACK "+ equipment2.getAttack());
                                System.out.println("Player2: Defence: "+ dffence2 +" + Equipment' DEFENCE " + equipment2.getDefence());
                                attack2 = attack2 + equipment2.getAttack();
                                dffence2 = dffence2 + equipment2.getDefence();
                                break;
                            }

                        }else {
                            break;
                        }

                    }

//                    something happend to player2






                    System.out.println("Notice: If location = player's citizenship, player's attack and defence +100 ");
                    System.out.println("please choice the Fighting location ID");
                    String test3 = br.readLine();
                    System.out.println(test3);
                    int locationID = Integer.parseInt(test3);
                    DBserverce getCountry = new DBserverce();
                    Location location;

                    location = getCountry.GetLocation(locationID);
                    System.out.println(location.toString());
                    if(citizenship1.equals( location.getCountry())){
                        System.out.println("Player1: "+ Name1 + " get country "+ location.getCountry() + " Power!!!");
                        System.out.println("Player1: Attack: "+ attack1 +" + 100 !!!");
                        System.out.println("Player1: Defence: "+ dffence1 +" + 100 !!!");
                        attack1 = attack1 + 100;
                        dffence1 = dffence1 + 100;
                    }

                    if(citizenship2.equals( location.getCountry())){
                        System.out.println("Player2: "+ Name2 + " get country "+ location.getCountry() + " Power!!!");
                        System.out.println("Player2: Attack: "+ attack2 +" + 100 !!!");
                        System.out.println("Player2: Defence: "+ dffence2 +" + 100 !!!");
                        attack2 = attack2 + 100;
                        dffence2 = dffence2 + 100;
                    }

//                    something happend to location.



                    System.out.println("Notice: If weather < 45 degree, Attack  *50%   ");
                    System.out.println("Notice: If weather > 90 degree, defence  *50%   ");
                    System.out.println("please type weather ID");
                    String test4 = br.readLine();
                    int weatherID = Integer.parseInt(test4);
                    DBserverce getDegree = new DBserverce();
                    Weather weather;

                    weather = getDegree.GetWeather(weatherID);
                    System.out.println(weather.toString());
                    if(weather.getdegree() < 45){
                        System.out.println("Attack * 50%");
                        attack1 *= 0.5;
                        System.out.println("Attack1 = " + attack1);
                        attack2 *= 0.5;
                        System.out.println("Attack2 = " + attack2);

                    }else if(weather.getdegree() > 90){
                        System.out.println("Defence * 50%");
                        dffence1 *= 0.5;
                        System.out.println("Defence1 = " + dffence1 );
                        dffence2 *= 0.5;
                        System.out.println("Defence2 = "+ dffence2 );
                    }
                    else {

                        System.out.println("Nothing Happened!!");
                    }





//                    something happend to weather






                    TimeUnit.SECONDS.sleep(1);

                    System.out.println("start to Fight!!");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Name1 + " : attack1 is " + attack1);
                    System.out.println(Name1 + " : defence1 is " + dffence1);
                    System.out.println(Name2 + " : attack2 is " + attack2);
                    System.out.println(Name2 + " : defence2 is " + dffence2);

                    System.out.println("The dffence2: " + dffence2 + " - attack1: " + attack1 + " = " + (dffence2-attack1));
                    System.out.println("The dffence1: " + dffence1 + " - attack2: " + attack2 + " = " + (dffence1-attack2));


                    if((dffence2-attack1)>(dffence1-attack2)){
                        System.out.println("The winner is~~~Player 2 : " + Name2 + "  !!!!!!!!!!");
                        System.out.println(citizenship2 + "     Win!!!!");
                    }else {

                        System.out.println("The winner is~~~Player 1 : " + Name1 + "  !!!!!!!!!!!");
                        System.out.println(citizenship1 + "     Win!!!!");
                    }
                    System.out.println("Fight End.");
                }else {
                    System.out.println("please retype the number!!");

                }


            } catch (IOException ioe) {
                System.out.println("IO exception raised!");
            }

            TimeUnit.SECONDS.sleep(2);

        }

    }


}
