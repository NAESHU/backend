package com.naeshu.naeshubackend.opinion.controller;

import com.naeshu.naeshubackend.opinion.service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/opinion")
public class OpinionController {
    private final OpinionService opinionService;

//    @PostMapping("/{announceNumber}/register")
//    public ResponseEntity<Void> PostOpinion(@PathVariable Long announceNumber,
//                                            @Auth Long userId,
//                                            OpinionRequest opinionRequest){
//
//    }
}
