package bot;

import tools.Command;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class JokesBot extends CommandBot {

    public static final String JOKES = "jokes.csv";
    private ArrayList<String> jokes;
    // constructor
    public JokesBot() {
        jokes = new ArrayList<>();
        File inputFile = new File(JOKES);
        try {
            Scanner fileReader = new Scanner(inputFile);
            while (fileReader.hasNextLine()) {
                jokes.add(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void actionPerform() {
        // not needed
    }

    @Override
    protected String respond(Command command) {
        return jokes.get(ThreadLocalRandom.current().nextInt(0, jokes.size()));
    }

    @Override
    protected String getCommand() {
        return "jokes";
    }

    @Override
    protected String getCommandHelp() {
        return "this bot returns jokes";
    }
}
