package net.javaguides.galaxy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.galaxy.entities.Message;
import net.javaguides.galaxy.entities.User;
import net.javaguides.galaxy.repositories.MessageRepository;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    public List<Message> messageList(User user) {

        return messageRepository.
                findByUser(
                    user
                );
    }

    public void saveMessage(User user,
                            Message message){
        message.setUser(
            user
        );
        messageRepository.save(message);
        
    }
}