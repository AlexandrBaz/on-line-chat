package main;

import main.dto.DtoMessage;
import main.dto.MessageMapper;
import main.model.Message;
import main.model.MessageRepository;
import main.model.User;
import main.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ChatController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    private MessageMapper messageMapper;

    @GetMapping("/init")
    public HashMap<String, Boolean> init(){
        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        Optional<User> userOpt = userRepository.findBySessionId(sessionId);

        response.put("result", userOpt.isPresent());
        return response;
    }
    @PostMapping("/auth")
    public HashMap<String, Boolean> auth(@Validated @RequestParam String name){
        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        if(name.isEmpty()) {
            response.put("result", false);
            return response;
        }
            User user = new User();
            user.setName(name);
            user.setSessionId(sessionId);
            response.put("result", true);
            userRepository.save(user);
        return response;
    }

    @PostMapping("/message")
    public Map<String, Boolean> sendMessage(@RequestParam String message){
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = userRepository.findBySessionId(sessionId).get();
        if (!message.isEmpty()) {
            Message messageDb = new Message();
            messageDb.setMessage(message);
            messageDb.setDateTime(LocalDateTime.now());
            messageDb.setUser(user);
            messageRepository.save(messageDb);
            return Map.of("result", true);
        } else {
            return Map.of("result", false);
        }
    }
    @GetMapping("/message")
    public List<DtoMessage> getMessageList(){
        return messageRepository
                .findAll(Sort.by(Sort.Direction.ASC, "dateTime"))
                .stream()
                .map(MessageMapper::map)
                .collect(Collectors.toList());

    }

    @GetMapping("/user")
    public HashMap<Integer, String> getUserList(){
        return null;
    }
}
