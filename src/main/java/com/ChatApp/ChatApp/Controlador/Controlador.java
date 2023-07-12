
package com.ChatApp.ChatApp.Controlador;

import com.ChatApp.ChatApp.Modelo.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class Controlador {
    @GetMapping("/")
    public String inicio(){
        return "PageHome";
    }
    @GetMapping("/chat")
    public String Chat(){
        return "index";
    }
    
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
//    @GetMapping("/login")
//    public String Login(){
//        return "login";
//    }
//    public String Logout(){
//        return "logout";
//    }
//    @GetMapping("/errores/403")
//    public String ErrorPErmisos(){
//       return "/errores/403"; 
//    }
}
