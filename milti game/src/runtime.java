import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class runtime  {
    public static ArrayList<player> pList = new ArrayList<player>();


    public static void main (String [] args){
        Scanner input = new Scanner(System.in);
        intitalData data = recalData("file");
        if (data == null) {data = new intitalData();}



        System.out.println(data.getNames().toString());
        for (int p = 0; p < data.getSize(); p++) {
            pList.add(recalPlayerData(data.getName(p)));
        }

        control(input, data);

        for (player p : pList ){
            savePlayerData(p);
        }
        saveData(data);
    }

    public static void savePlayerData (player object){
        try {
            FileOutputStream file = new FileOutputStream(object.getName()); // making data readable by computer / organized
            ObjectOutputStream out = new ObjectOutputStream(file); // setting it up to be out put

            out.writeObject(object); // writing it to file

            out.close();
            file.close();



        } catch (IOException x){
            System.out.println("IOException is caught");
        }
    }
    public static void saveData (intitalData object){
        try {
            FileOutputStream file = new FileOutputStream("file"); // making data readable by computer / organized
            ObjectOutputStream out = new ObjectOutputStream(file); // setting it up to be out put

            out.writeObject(object); // writing it to file

            out.close();
            file.close();

        } catch (IOException x){
            System.out.println("IOException is caught");
        }
    }
    public static player recalPlayerData(String filename) {
        player Player = null; // used for test to see if any data was actually recovered and to save to

        try
        {
            File f = new File(filename);
            if(f.exists() ) {
                System.out.println("Player " + filename +  " does exist");
                // Reading the object from a file
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                Player = (player) in.readObject();

                in.close();
                file.close();


            } else {
                System.out.println("Player " + filename +  " doesnt exist");
            }
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }


           return Player;

    }
    public static intitalData recalData(String filename) {
        intitalData data = null; // used for test to see if any data was actually recovered and to save to

        try
        {
            File f = new File(filename);
            if(f.exists() ) {
                System.out.println("data " + filename +  " does exist");
                // Reading the object from a file
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                data = (intitalData) in.readObject();

                in.close();
                file.close();


            } else {
                System.out.println("Data " + filename +  " doesnt exist");
            }
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }


        return data;

    }
    public static void control(Scanner in , intitalData data) {
        int n = 0;
        while (n != 999) {
            System.out.println(" what would you like to do " + "\n" + "1: one player game \n 2: Add player\n 3: remove player\n 999: quit");
            n = in.nextInt();
            switch(n) {
                case 1:game(in , data);
                break;
                case 2: System.out.println(newPlayer(in, data));
                break;
                case 3: System.out.println(removePlayer(in,data));
                break;
            }

        }
    }
    public static String newPlayer(Scanner in, intitalData data){
        in.nextLine();
        System.out.println("input new player name");
        String name = in.nextLine();
        pList.add(new player(name));// array list already public so it saves
        data.newName(name); // calls method to change so also saves
        return name + " added to player list";

    }
    public static String removePlayer ( Scanner in, intitalData data){
        for (int i = 1; i < data.getSize()+1; i ++){
            System.out.println(i+ ": "+ data.getName(i-1));
        }
        System.out.println("which person would you like to remove or 999 to quit");
        int r =in.nextInt();
        if (r == 999) {
            return " no names where removed";
        }else {
            System.out.println(pList.get(r).getName()+ " was removed from list");
            pList.remove(r);data.remove(r);
            return"";

            }
    }
    public static void game(Scanner in, intitalData data){
        System.out.println("1: one player game \n 2: two player game \n or 999 to cancel");
        int n = in.nextInt();
        if (n == 1 ){
            singleGames(in);
        } else if(n == 2){
            duoGame(in);
        }
    }
    public static void singleGames(Scanner in ){
        System.out.println("which game would you like to play \n 1: safe cracker " );
        int n = in.nextInt();
        switch ( n ) {
            case 1: safeCracker(in, choosePlayer(in));
                break;

        }
    }

    public static void duoGame(Scanner in) {

    }
    public static player choosePlayer(Scanner in){
        System.out.println("who is playing the game");
        for (int i = 1; i < pList.size()+1; i ++){
            System.out.println(i+ ": "+ pList.get(i-1).getName());
        }
        return pList.get(in.nextInt()-1);
    }

    public static void safeCracker(Scanner in, player p){
p.setWins(p.getWins()+1);
System.out.println(p.getName()+ " wins =" + p.getWins());
    }
}

