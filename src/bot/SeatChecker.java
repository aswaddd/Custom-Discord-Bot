package bot;

import tools.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * This bot will check the seat of a quiz or practical test for a student.
 *
 * This message bot will only work in private message.
 * The user should first type "seat" to start the conversation.
 * The bot will then ask for the student ID. The bot is expecting
 * a 8-digit number as the student ID and ignore any other message.
 * After received the 8-digit number in a private message, the bot
 * will check the seat of the student and return the seat number.
 *
 * The bot allows the user to check seat for other students or check
 * the seat even if the user did not register to UserManagementBot before.
 *
 * We will assume the seat will never change during the execution of the
 * program. Any change of seat will require the program to restart.
 */
public class SeatChecker implements MessageListener {
    // @Override
    //TODO: Add your private data member here
    private ArrayList<String[]> seats;
    private boolean seatCalled = false;
    //TODO: Add your methods here
    public SeatChecker() {
        seats = new ArrayList<>();
        File inputFile = new File("seat.csv");
        try {
            Scanner fileReader = new Scanner(inputFile);
            while (fileReader.hasNextLine()) {
                seats.add(fileReader.nextLine().split(","));
                fileReader.nextLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public String onMessageReceived(Message message) {
        if (!message.getIsPrivate() && message.getContent().equals("seat")) {
            return "Sorry I only work in private messages";
        }
        if (message.getContent().equals("seat")) {
            seatCalled = true;
            return "What is your ID?";
        }
        if (seatCalled && message.getContent().length() == 8 && digitsCheck(message.getContent())) {
            seatCalled = false;
            for (int i = 0; i < seats.size(); i++) {
                if (seats.get(i)[0].equals(message.getContent()))
                    return "Your seat is: " + seats.get(i)[1];
            }
            return "Sorry I cannot find your seat";
        } else
            return null;
    }
    // to check if input is all digits
    public boolean digitsCheck(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9')
                return false;
        }
        return true;
    }


}
