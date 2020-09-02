package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TaskControllerGetTasksTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private DbService service;

    @Mock
    private TaskMapper taskMapper;

    @Test
    public void shouldGetNoTasks() {
        //Given
        when(service.getAllTasks()).thenReturn(new ArrayList<>());
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(new ArrayList<>());

        //When
        List<TaskDto> taskDtos = taskController.getTasks();

        //Then
        assertNotNull(taskDtos);
        assertEquals(0, taskDtos.size());
    }

    @Test
    public void shouldGetAllTasks() {
        //Given
        Task task1 = new Task(1111L, "test_title", "test_content");
        Task task2 = new Task(1111L, "test_title", "test_content");
        Task task3 = new Task(1111L, "test_title", "test_content");

        TaskDto taskDto1 = new TaskDto(1111L, "test_title", "test_content");
        TaskDto taskDto2 = new TaskDto(1111L, "test_title", "test_content");
        TaskDto taskDto3 = new TaskDto(1111L, "test_title", "test_content");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(taskDto1);
        taskDtos.add(taskDto2);
        taskDtos.add(taskDto3);

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtos);

        //When
        List<TaskDto> taskDtosController = taskController.getTasks();

        //Then
        assertNotNull(taskDtosController);
        assertEquals(3, taskDtosController.size());

        taskDtosController.forEach(taskDto -> {
            assertEquals(1111L, taskDto.getId());
            assertEquals("test_title", taskDto.getTitle());
            assertEquals("test_content", taskDto.getContent());
        });
    }

}
