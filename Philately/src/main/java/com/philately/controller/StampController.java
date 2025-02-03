package com.philately.controller;

import com.philately.model.dto.StampCreateDTO;
import com.philately.model.dto.StampDTO;
import com.philately.service.StampService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stamps")
public class StampController {

    private final StampService stampService;

    public StampController(StampService stampService) {
        this.stampService = stampService;
    }

    @PostMapping("/add/{ownerId}")
    public ResponseEntity<StampDTO> addStamp(@Valid @RequestBody StampCreateDTO stampDTO, @PathVariable Long ownerId) {
        return ResponseEntity.ok(stampService.addStamp(stampDTO, ownerId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<StampDTO>> getAllStamps() {
        return ResponseEntity.ok(stampService.getAllStamps());
    }
}