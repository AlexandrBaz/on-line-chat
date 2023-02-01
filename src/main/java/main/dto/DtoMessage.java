package main.dto;

public class DtoMessage {

    private String text;
    private String dateTime;
    private String userName;

    public DtoMessage(){}

    public DtoMessage(String text, String dateTime, String userName) {
        this.text = text;
        this.dateTime = dateTime;
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
