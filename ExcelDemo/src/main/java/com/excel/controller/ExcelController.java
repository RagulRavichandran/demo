package com.excel.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.excel.service.ExcelService;

@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 2000) // every 2 seconds
    public void sendDataAutomatically() {
        List<String[]> excelData = excelService.getData();
        messagingTemplate.convertAndSend("/topic/excelData", excelData);
        System.out.println("excelData" + Arrays.deepToString(excelData.toArray()));
    }
}