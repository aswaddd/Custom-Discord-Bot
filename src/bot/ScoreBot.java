package bot;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import tools.*;

public class ScoreBot extends CommandBot {
    public static final String DEFAULT_FILE = "dictation.csv";
    //TODO: Add your private data member here
    private ArrayList<String[]> scores;
    private UserManagementBot umBot;
    private String userName = "";
    public ScoreBot(UserManagementBot r) {
        //TODO
        umBot = r;
        File inputFile = new File(DEFAULT_FILE);
        scores = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(inputFile); // loading the data into the program
            while (fileReader.hasNextLine()) {
                String temp = fileReader.nextLine();
                String[] tempArray = temp.split(",");
                scores.add(tempArray);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ScoreBot(UserManagementBot r, String filename) {
        //TODO
        umBot = r;
        File inputFile = new File(filename);
        scores = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(inputFile); // loading the data into the program
            while (fileReader.hasNextLine()) {
                String temp = fileReader.nextLine();
                String[] tempArray = temp.split(",");
                scores.add(tempArray);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Which score that the object is listening to.
     */
    @Override
    public String getCommand() {
        //TODO
        return "score";
    }
    /**
     * The short description for this command.
     */
    @Override
    public String getCommandHelp() {
        //TODO
        return "Returns the score of the user";
    }
    /**
     * This method is used to respond to a command.
     */
    @Override
    public String respond(Command command) {
        //TODO
        userName = command.getName();
        String result = "Your scores are: ";
        if (umBot.isRegistered(command.getSenderID())) { // check if user is registered
            try {
                for (int i = 0; i < scores.size(); i++) {
                    if (scores.get(i)[0].equals(umBot.getStudentID(command.getSenderID()))) { // get the ID of the user and their scores
                        double sum = 0;
                        int scoreCount = 0;
                        for (int j = 1; j < scores.get(i).length; j++) {
                            if (!scores.get(i)[j].equals("-")) {
                                sum += Double.parseDouble(scores.get(i)[j]); // sum scores
                                scoreCount++; // track how many scores were not "-"
                                result += scores.get(i)[j] + ", ";
                            } else
                                result += "N/A ,";
                        }
                        double average = sum / (double) scoreCount;
                        result += "and your average score is: " + average;
                        return result;
                    }
                }
            } catch (IDNotFoundException e) {
                throw new RuntimeException(e);
                }
        }
        return "You have not registered yet.";
    }
    /**
     * Print the last user who queried the score bot service to console.
     */
    @Override
    public void actionPerform() {
        //TODO
        System.out.println("The last user who queried the score bot:" + userName);
    }
}
