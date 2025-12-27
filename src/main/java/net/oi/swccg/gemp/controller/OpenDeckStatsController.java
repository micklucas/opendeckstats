package net.oi.swccg.gemp.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import net.oi.swccg.gemp.dto.DeckRankingsResponse;
import net.oi.swccg.gemp.dto.UploadResultsResponse;
import net.oi.swccg.gemp.entity.GameResult;
import net.oi.swccg.gemp.service.GameResultService;

@RestController
public class OpenDeckStatsController {
    
    public OpenDeckStatsController(GameResultService service) {
        this.service = service;
    }

    private GameResultService service;

    @PostMapping(path="/results")
    public ResponseEntity<Object> uploadResults(@Valid @RequestBody List<GameResult> gameResults) {
        UploadResultsResponse response = service.loadGameResults(gameResults);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path="/deckrankings")
    public ResponseEntity<Object> deckRankings() {
        DeckRankingsResponse response;
        
        response = service.returnDeckRankings();
        
        return ResponseEntity.ok(response);
    }

    @GetMapping(path="/deckrankings/report")
    public ResponseEntity<Object> deckRankingsReport() {
        String response = service.returnDeckRankingsReport();
        
        return ResponseEntity.ok(response);
    }
}
