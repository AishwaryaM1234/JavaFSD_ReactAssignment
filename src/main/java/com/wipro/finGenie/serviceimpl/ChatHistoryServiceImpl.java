package com.wipro.finGenie.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.finGenie.dto.ChatHistoryDTO;
import com.wipro.finGenie.entity.ChatHistory;
import com.wipro.finGenie.entity.User;
import com.wipro.finGenie.repository.ChatHistoryRepository;
import com.wipro.finGenie.repository.UserRepository;
import com.wipro.finGenie.service.ChatHistoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatHistoryServiceImpl implements ChatHistoryService {
 
    private final ChatHistoryRepository chatHistoryRepository;
    private final UserRepository userRepository;
 
    @Override
    public ChatHistoryDTO saveChat(ChatHistoryDTO dto) {
 
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
 
        ChatHistory chat = ChatHistory.builder()
                .userMessage(dto.getUserMessage())
                .botReply(dto.getBotReply())
                .user(user)
                .build();
 
        ChatHistory saved = chatHistoryRepository.save(chat);
 
        dto.setChatId(saved.getChatId());
 
        return dto;
    }
 
    @Override
    public List<ChatHistoryDTO> getAllChats() {
 
        return chatHistoryRepository.findAll()
                .stream()
                .map(chat -> {
                    ChatHistoryDTO dto = new ChatHistoryDTO();
                    dto.setChatId(chat.getChatId());
                    dto.setUserMessage(chat.getUserMessage());
                    dto.setBotReply(chat.getBotReply());
                    dto.setUserId(chat.getUser().getUserId());
                    return dto;
                })
                .toList();
    }
}