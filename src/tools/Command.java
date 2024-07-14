package tools;


import java.util.List;
import java.util.ArrayList;


public class Command extends Message {
    //TODO - add data members and methods
    ArrayList<TextPair> options = new ArrayList<>();
    public Command(String globalName, String id, String commandString, boolean equals) {
        super(globalName, id, commandString, equals);
    }
    public void addOption(String name, String value) {
        options.add(new TextPair(name, value));
    }
    public TextPair getOption(int i) {
        return options.get(i);
    }
    public ArrayList<TextPair> getOptions() {
        return options;
    }
    public String getSenderID() {
        return super.getId();
    }
}
