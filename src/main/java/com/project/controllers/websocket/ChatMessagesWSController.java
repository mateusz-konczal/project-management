package com.project.controllers.websocket;

import com.project.model.ChatMessage;
import com.project.services.ChatMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
public class ChatMessagesWSController {

    private final ChatMessagesService chatMessagesService;

    @Autowired
    public ChatMessagesWSController(ChatMessagesService chatMessagesService) {
        this.chatMessagesService = chatMessagesService;
    }

    @MessageMapping("/createMessage/{project_id}")
    @SendTo("/message/{project_id}")
    public ChatMessage create(@DestinationVariable String project_id, ChatMessage chatMessage) {
        log.info("New chat message: " + chatMessage);
        return chatMessagesService.create(chatMessage);
    }

}
