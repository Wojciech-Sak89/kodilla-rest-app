package com.crud.tasks.controller;

import com.crud.tasks.trello.card.CreatedTrelloCardDto;
import com.crud.tasks.trello.dto.TrelloBoardDto;
import com.crud.tasks.trello.dto.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloFacade trelloFacade;
//    private TrelloService trelloService;
//    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "boards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//
//        trelloBoards.forEach(trelloBoardDto -> {
//
//            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
//
//            System.out.println("This board contains lists: ");
//
//            trelloBoardDto.getLists().forEach(trelloList ->
//                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//
//        });

//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//
//        trelloBoards.stream()
//                .filter(trelloBoardDto ->
//                        !trelloBoardDto.getId().isEmpty() && !trelloBoardDto.getName().isEmpty())
//                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
//                .forEach(trelloBoardDto ->
//                        System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));\
    }

    @RequestMapping(method = RequestMethod.POST, value = "cards")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }
}
