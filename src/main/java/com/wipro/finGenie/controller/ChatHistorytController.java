package com.wipro.finGenie.controller;
 
import java.util.List;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.wipro.finGenie.dto.ChatHistoryDTO;
import com.wipro.finGenie.service.ChatHistoryService;
 
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
 
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatHistorytController {
 
    private final ChatHistoryService chatHistoryService;
 
    @PostMapping
    public ResponseEntity<ChatHistoryDTO> saveChat(
            @Valid @RequestBody ChatHistoryDTO dto) {
 
        return ResponseEntity.ok(
                chatHistoryService.saveChat(dto));
    }
 
    @GetMapping
    public ResponseEntity<List<ChatHistoryDTO>> getAllChats() {
 
        return ResponseEntity.ok(
                chatHistoryService.getAllChats());
    }
}