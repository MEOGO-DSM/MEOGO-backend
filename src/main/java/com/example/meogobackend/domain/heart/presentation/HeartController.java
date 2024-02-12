package com.example.meogobackend.domain.heart.presentation;

import com.example.meogobackend.domain.heart.presentation.dto.HeartResponse;
import com.example.meogobackend.domain.heart.service.CreateHeartService;
import com.example.meogobackend.domain.heart.service.DeleteHeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequestMapping("/post")
@RequiredArgsConstructor
public class HeartController {
    private final CreateHeartService createHeartService;
    private final DeleteHeartService deleteHeartService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/heart/{id}")
    public HeartResponse heart(@PathVariable Long id) {
        return createHeartService.createHeart(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/heart/{id}")
    public HeartResponse deleteHeart(@PathVariable Long id) {
        return deleteHeartService.deleteHeart(id);
    }
}
