package com.project.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.Project;
import com.project.services.ProjectsService;
import com.project.services.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProjectsController.class)
@AutoConfigureMockMvc
class ProjectsControllerTest {

    private static final String PROJECT_API_URL = "/api/project";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProjectsService projectsService;

    @MockBean
    private UsersService usersService;

    @Test
    void findByIdShouldReturnProject() throws Exception {
        long projectID = 1L;
        Project project = new Project("Project 1", "Description for project 1", LocalDate.now().plusDays(15));
        project.setID(projectID);

        Mockito.when(projectsService.findById(projectID)).thenReturn(Optional.of(project));

        ResultActions resultActions = mockMvc.perform(get(PROJECT_API_URL + "/{id}", project.getID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) projectID)));

        checkProjectJSONPath(resultActions, project);
    }

    @Test
    void findByIdShouldReturnNotFound() throws Exception {
        long projectID = 1L;
        Mockito.when(projectsService.findById(projectID)).thenReturn(Optional.empty());

        mockMvc.perform(get(PROJECT_API_URL + "/{id}", projectID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void findAllShouldReturnProjects() throws Exception {
        Project p1 = new Project("Project 1", "Description for project 1", LocalDate.now().plusDays(15));
        Project p2 = new Project("Project 2", "Description for project 2", LocalDate.now().plusDays(20));
        List<Project> projects = Arrays.asList(p1, p2);

        Mockito.when(projectsService.findAll()).thenReturn(projects);

        ResultActions resultActions = mockMvc.perform(get(PROJECT_API_URL + "/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(projects.size())));

        checkProjectsJSONPath(resultActions, projects);
    }

    @Test
    void createShouldReturnProject() throws Exception {
        long projectID = 1L;
        Project project = new Project("Project 1", "Description for project 1", LocalDate.now().plusDays(15));
        project.setID(projectID);

        Mockito.when(projectsService.create(any(Project.class))).thenReturn(project);

        ResultActions resultActions = mockMvc.perform(post(PROJECT_API_URL)
                .content(objectMapper.writeValueAsString(project))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        checkProjectJSONPath(resultActions, project);
    }

    @Test
    void updateShouldReturnProject() throws Exception {
        long projectID = 1L;
        Project project = new Project("Project 1", "Description for project 1", LocalDate.now().plusDays(15));
        project.setID(projectID);

        Mockito.when(projectsService.update(any(Project.class))).thenReturn(project);

        ResultActions resultActions = mockMvc.perform(put(PROJECT_API_URL)
                .content(objectMapper.writeValueAsString(project))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        checkProjectJSONPath(resultActions, project);
    }

    @Test
    void deleteByIdShouldReturnNoContent() throws Exception {
        long projectID = 1L;

        mockMvc.perform(delete(PROJECT_API_URL + "/{id}", projectID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    void checkProjectJSONPath(ResultActions resultActions, Project project) throws Exception {
        resultActions
                .andExpect(jsonPath("$.name", is(project.getName())))
                .andExpect(jsonPath("$.description", is(project.getDescription())))
                .andExpect(jsonPath("$.deliveryDate", is(project.getDeliveryDate().toString())));

    }

    void checkProjectsJSONPath(ResultActions resultActions, List<Project> projects) throws Exception {
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            String jsonIndexPath = "[" + i + "]";

            resultActions
                    .andExpect(jsonPath("$" + jsonIndexPath + ".name", is(project.getName())))
                    .andExpect(jsonPath("$" + jsonIndexPath + ".description", is(project.getDescription())))
                    .andExpect(jsonPath("$" + jsonIndexPath + ".deliveryDate", is(project.getDeliveryDate().toString())));
        }
    }
}
