//package ru.itis.models.scope;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Component;
//
//@Component
//@Scope(scopeName = "message", proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class MessageDataConfig {
//
//    private String text = "my metal friend";
//
//    public MessageData signIn() {
//        return new MessageData("Come here",text);
//    }
//
//    public MessageData signUp() {
//        return new MessageData("Join us", text);
//    }
//}
