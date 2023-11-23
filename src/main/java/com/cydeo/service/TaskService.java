package com.cydeo.service;

import com.cydeo.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> listAllTasks();
    TaskDTO findById(Long id);
    void save(TaskDTO dto);
    void update(TaskDTO dto);
    void delete(Long id);

    int totalNoneCompletedTasks(String projectCode);
    int totalCompletedTasks(String projectCode);
    List<TaskDTO> listAllByProjectId(Long id);
    void complete(Long id);
}
