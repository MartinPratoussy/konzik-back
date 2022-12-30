package com.konzik.Chat.controller;

import com.konzik.Chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {
    @MessageMapping("/publicChat.register")
    @SendTo("/topic/public")
    public ChatMessage registerPublic(@Payload ChatMessage chatMessage,
                                SimpMessageHeaderAccessor headerAccessor) {
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/publicChat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessagePublic(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.register")
    @SendTo("/topic/{topicId}")
    public ChatMessage registerEvent(@Payload ChatMessage chatMessage,
                                     SimpMessageHeaderAccessor headerAccessor,
                                     @DestinationVariable String topicId) {
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/{topicId}")
    public ChatMessage sendMessageEvent(@Payload ChatMessage chatMessage,
                                        @DestinationVariable String topicId) {
        return chatMessage;
    }

}
