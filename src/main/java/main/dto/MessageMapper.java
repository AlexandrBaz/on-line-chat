package main.dto;

import main.model.Message;
import java.time.format.DateTimeFormatter;

public class MessageMapper {

    public static DtoMessage map (Message message){
        DtoMessage dtoMessage = new DtoMessage();
        dtoMessage.setDateTime(message.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        dtoMessage.setUserName(message.getUser().getName());
        dtoMessage.setText(message.getMessage());
        return dtoMessage;
    }
}
