package com.project.controllers.api;

import com.project.model.ChatMessage;
import com.project.services.ChatMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat-messages")
@CrossOrigin
public class ChatMessagesController {

    private final ChatMessagesService chatMessagesService;

    @Autowired
    public ChatMessagesController(ChatMessagesService chatMessagesService) {
        this.chatMessagesService = chatMessagesService;
    }

    @GetMapping()
    public ResponseEntity<List<ChatMessage>> findAllChatMessages() {
        List<ChatMessage> allChatMessages = chatMessagesService.findAll();
        return new ResponseEntity<>(allChatMessages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatMessage> findById(@PathVariable("id") long id) {
        Optional<ChatMessage> chatMessage = chatMessagesService.findById(id);

        if (chatMessage.isPresent()) {
            return new ResponseEntity<>(chatMessage.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allByProject/{projectID}")
    public ResponseEntity<List<ChatMessage>> findAllByProjectID(@PathVariable("projectID") long projectID) {
        List<ChatMessage> allByProjectID = chatMessagesService.findAllByProjectID(projectID);
        return new ResponseEntity<>(allByProjectID, HttpStatus.OK);
    }

    @GetMapping("/allByUser/{userID}")
    public ResponseEntity<List<ChatMessage>> findAllByUserID(@PathVariable("userID") long userID) {
        List<ChatMessage> allByUserID = chatMessagesService.findAllByUserID(userID);
        return new ResponseEntity<>(allByUserID, HttpStatus.OK);
    }

}
