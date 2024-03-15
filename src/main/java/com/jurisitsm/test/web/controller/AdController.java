package com.jurisitsm.test.web.controller;

import com.jurisitsm.test.exception.UsedCarAdException;
import com.jurisitsm.test.service.AdService;
import com.jurisitsm.test.web.dto.request.AdRequest;
import com.jurisitsm.test.web.dto.response.AdResponse;
import com.jurisitsm.test.web.mapper.AdMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    private final AdService adService;
    private final AdMapper adMapper;

    @Autowired
    public AdController(AdService adService, AdMapper adMapper) {
        this.adService = adService;
        this.adMapper = adMapper;
    }

    @PostMapping
    public ResponseEntity<AdResponse> createAd(@Valid @RequestBody AdRequest ad) {
        return ResponseEntity.ok(adMapper.toAdResponse(adService.createAd(ad)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") String id) {
        adService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdResponse> getById(@PathVariable("id") String id) throws UsedCarAdException {
        return ResponseEntity.ok(adMapper.toAdResponse(adService.getById(id)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> search(
            @RequestParam(name = "brand", required = false) @Size(max = 50) String brand,
            @RequestParam(name = "type", required = false) @Size(max = 20) String type,
            @RequestParam(name = "price", required = false) Long price) {
        return ResponseEntity.ok(adService.searchAds(brand, type, price));
    }
}
