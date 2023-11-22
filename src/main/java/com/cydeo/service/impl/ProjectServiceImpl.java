package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final TaskService taskService;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, UserService userService, UserMapper userMapper, TaskRepository taskRepository, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userService = userService;
        this.userMapper = userMapper;
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO getByProjectCode(String code) {
        return projectMapper.convertToDto(projectRepository.findByProjectCode(code).get());
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO dto) {
        dto.setProjectStatus(Status.OPEN);
        //The fields that the user fill during creation of a project does not include Status.
        //So we are setting the status of the project dto object first as OPEN and then converting it to entity.
        Project project = projectMapper.convertToEntity(dto);
        projectRepository.save(project);
    }

    @Override
    public void update(ProjectDTO dto) {
        //Get Project entity object from the Database. For this, use "dto.getProjectCode()".
        //We are firstly getting this because thıs entity has id in the DB
        Optional<Project> project = projectRepository.findByProjectCode(dto.getProjectCode());
        //Convert the dto with updated data to entity
        Project convertedProject = projectMapper.convertToEntity(dto);
        //Sınce dto comes from (UI) it doesn't have an id.
        //Set the id, using the entity object we pulled from DB at the beginning.(Line 48,49,50)
        convertedProject.setId(project.get().getId());
        //Set the Status like id, because the UI (Form) does not have Status field as well
        convertedProject.setProjectStatus(project.get().getProjectStatus());
        //Save the converted object into DB with the updated data.
        projectRepository.save(convertedProject);
    }

    @Override
    public void delete(String code) {
        Optional<Project> project = projectRepository.findByProjectCode(code);
        project.get().setIsDeleted(true);
        projectRepository.save(project.get());
    }

    @Override
    public void complete(String projectCode) {
        Optional<Project> project = projectRepository.findByProjectCode(projectCode);
        project.get().setProjectStatus(Status.COMPLETE);
        projectRepository.save(project.get());
    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {

        UserDTO currentUserDto = userService.findByUserName("harold@manager.com");
        User user  = userMapper.convertToEntity(currentUserDto);

        List<Project> list = projectRepository.findAllByAssignedManager(user);


        return list.stream().map(project -> {
            ProjectDTO obj = projectMapper.convertToDto(project);
            obj.setUnfinishedTaskCounts(taskService.totalNoneCompletedTasks(project.getProjectCode()));
            obj.setCompleteTaskCounts(taskService.totalCompletedTasks(project.getProjectCode()));
            return obj;
        }).collect(Collectors.toList());
    }


}
