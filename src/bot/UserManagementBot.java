package bot;


import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import tools.*;

public class UserManagementBot extends CommandBot {
    //Add your data member here
    protected ArrayList<User> users;
    private File inputFile;
    //Constructor
    public UserManagementBot(String filename) {
        //TODO
        users = new ArrayList<>();
        inputFile = new File(filename);
        addOption("regcode", "registration code", true);

        try {
            Scanner fileReader = new Scanner(inputFile); // load the users file into the program
            while (fileReader.hasNextLine()) {
                String[] tokens = fileReader.nextLine().split(",");
                if (tokens.length == 3) {
                    users.add(new User(tokens[0], tokens[1], tokens[2]));
                }
                else if (tokens.length == 5) {
                    users.add(new User(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Written for you. No need to change it
     */
    @Override
    public String getCommand() {
        return "registration";
    }

    /**
     * Written for you. No need to change it
     */
    @Override
    public String getCommandHelp() {
        return "Registers the user";
    }


    /**
     * to check if a user has been registered
     */
    boolean isRegistered(String id) {
        //TODO
        for (int i = 0; i < users.size(); i++) { // cycle through the users to find the match who matches
            if (users.get(i).getID() != null && users.get(i).getID().equals(id)) // if ID exists and matches with the user sending command
                return true;
        }
        return false;
    }


    /**
     * To respond to the command '/registration'.
     *
     * If the user has already registered, return "You are already registered!"
     * If the registration code is correct, register the user and return "You are registered!".
     * If the registration code is incorrect, return "Invalid registration code!"
     *
     * To avoid data lost, remember to save the data to the file after each user's registration.
     */
    @Override
    public String respond(Command command) {
        //TODO
        if (isRegistered(command.getId())) // if the discord id is registered, no matter what the user sends, this will be returned
            return "You are already registered";

        for (int i = 0; i < users.size(); i++) { // if content matches the regCode, the user is registered
            if (!users.get(i).getRegistrationCode().equals("-") && users.get(i).getRegistrationCode().equals(command.getOption(0).getValue())) {
                users.get(i).setRegistrationCode("-");
                users.get(i).setID(command.getId());
                users.get(i).setUsername(command.getName());
                updateFile();
                return "You are registered!";
            }
        }
        return "Invalid registration code!"; // any other case will return this
    }
// write back to file to prevent data loss
    private void updateFile() {
        try {
            PrintWriter fileWriter = new PrintWriter(inputFile);
            for (int i = 0; i < users.size(); i++) {
                fileWriter.print(users.get(i).toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * return the student ID of the user with the given discord ID
     *
     * throws an IDNotFoundException if the discord ID cannot be found
     */
    public String getStudentID(String id) throws IDNotFoundException {
        //TODO
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID() != null && users.get(i).getID().equals(id))
                return users.get(i).getStudentID();
        }
        throw new IDNotFoundException("Discord ID cannot be found.");
    }

    /**
     * return the user object with the given discord ID
     *
     * throws an IDNotFoundException if the discord ID cannot be found
     */
    public User getStudent(String id) throws IDNotFoundException {
        //TODO
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID() != null && users.get(i).getID().equals(id))
                return users.get(i);
        }
        throw new IDNotFoundException("Discord ID cannot be found.");
    }


    /**
     * Output how many number of users have registered.
     */
    @Override
    public void actionPerform() {
        int registeredUserCount = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRegistrationCode().equals("-"))
                registeredUserCount++;
        }
        System.out.println("The number of registered users: " + registeredUserCount);
    }
}
