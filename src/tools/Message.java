package tools;




public class Message {
    //TODO - add other data members and methods
    private String name;
    private String id;
    private String content;
    private boolean isPrivate;
    /**
     * Constructor of the Message class. Given.
     */
    public Message(String name, String id, String content, boolean isPrivate) {
        this.name = name;
        this.id = id;
        this.content = content;
        this.isPrivate = isPrivate;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public boolean getIsPrivate() {
        return isPrivate;
    }
}
