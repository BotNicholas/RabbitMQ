package org.botnicholas.projects.producer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class SubjectsRetreiverController {
  @Autowired
  private StreamBridge streamBridge;

  @GetMapping("/send")
  public ResponseEntity<String> sendMessage(@RequestParam String message) {
    streamBridge.send("output", "STREAM SAYS: " + message); //here output - is binding name from application.properties

    return ResponseEntity.ok(String.format("Message '%s' is sent", message));
  }
}