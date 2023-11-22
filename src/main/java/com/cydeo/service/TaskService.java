package com.cydeo.service;

import com.cydeo.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskDTO> listAllTasks();
    TaskDTO getByTaskId(Long id);
    void save(TaskDTO dto);
    void update(TaskDTO dto);
    void delete(Long id);

}
