package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.dto.TrelloBoardDto;
import com.crud.tasks.trello.dto.TrelloCardDto;
import com.crud.tasks.trello.dto.TrelloListDto;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //Given
        List<TrelloListDto> trelloDtoLists = new ArrayList<>();
        trelloDtoLists.add(new TrelloListDto("testListID_1", "test list name_1", true));
        trelloDtoLists.add(new TrelloListDto("testListID_2", "test list name_2", true));
        trelloDtoLists.add(new TrelloListDto("testListID_3", "test list name_3", true));

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("testBoardID_1", "test board name_1", trelloDtoLists));
        trelloBoardDtos.add(new TrelloBoardDto("testBoardID_2", "test board name_2", trelloDtoLists));
        trelloBoardDtos.add(new TrelloBoardDto("testBoardID_3", "test board name_3", trelloDtoLists));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(trelloBoards.size(), trelloBoardDtos.size());

        assertEquals(trelloBoards.get(0).getId(), trelloBoardDtos.get(0).getId());
        assertEquals(trelloBoards.get(1).getName(), trelloBoardDtos.get(1).getName());

        assertEquals(trelloBoards.get(2).getLists().get(1).isClosed(), trelloBoardDtos.get(2).getLists().get(1).isClosed());

    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("testListID_1", "test list name_1", true));
        trelloLists.add(new TrelloList("testListID_2", "test list name_2", false));
        trelloLists.add(new TrelloList("testListID_3", "test list name_3", true));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("testBoardID_1", "test board name_1", trelloLists));
        trelloBoards.add(new TrelloBoard("testBoardID_2", "test board name_2", trelloLists));
        trelloBoards.add(new TrelloBoard("testBoardID_3", "test board name_3", trelloLists));

        //When
        List<TrelloBoardDto> trelloDtoBoards = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(trelloDtoBoards.size(), trelloBoards.size());

        assertEquals(trelloDtoBoards.get(0).getId(), trelloBoards.get(0).getId());
        assertEquals(trelloDtoBoards.get(1).getName(), trelloBoards.get(1).getName());

        assertEquals(trelloDtoBoards.get(2).getLists().get(1).isClosed(), trelloBoards.get(2).getLists().get(1).isClosed());
    }

    @Test
    public void mapToListTest() {
        //Given
        List<TrelloListDto> trelloDtoLists = new ArrayList<>();
        trelloDtoLists.add(new TrelloListDto("testListID_1", "test list name_1", true));
        trelloDtoLists.add(new TrelloListDto("testListID_2", "test list name_2", true));
        trelloDtoLists.add(new TrelloListDto("testListID_3", "test list name_3", true));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloDtoLists);

        //Then
        assertEquals(trelloLists.size(), trelloDtoLists.size());

        assertEquals(trelloLists.get(0).getId(), trelloDtoLists.get(0).getId());
        assertEquals(trelloLists.get(1).getName(), trelloDtoLists.get(1).getName());
        assertEquals(trelloLists.get(2).isClosed(), trelloDtoLists.get(2).isClosed());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("testListID_1", "test list name_1", true));
        trelloLists.add(new TrelloList("testListID_2", "test list name_2", true));
        trelloLists.add(new TrelloList("testListID_3", "test list name_3", true));

        //When
        List<TrelloListDto> trelloDtoLists = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(trelloLists.size(), trelloLists.size());

        assertEquals(trelloDtoLists.get(0).getId(), trelloLists.get(0).getId());
        assertEquals(trelloDtoLists.get(1).getName(), trelloLists.get(1).getName());
        assertEquals(trelloDtoLists.get(2).isClosed(), trelloLists.get(2).isClosed());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test desc",
                "test pos", "test listId_1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
        assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test name", "test desc",
                "test pos", "test listId_1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCardDto.getName(), trelloCardDto.getName());
        assertEquals(trelloCardDto.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCardDto.getPos(), trelloCardDto.getPos());
        assertEquals(trelloCardDto.getListId(), trelloCardDto.getListId());
    }


}
