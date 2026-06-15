package com.wipro.finGenie.service;
 
import java.util.List;
import com.wipro.finGenie.dto.ChatHistoryDTO;
 
public interface ChatHistoryService {
 
    ChatHistoryDTO saveChat(
            ChatHistoryDTO dto);
 
    List<ChatHistoryDTO> getAllChats();
}
