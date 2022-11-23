package com.branch.customer_integration.controller;

import com.branch.customer_integration.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UsersService usersService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getAllUserRepos_with_service_interaction() throws Exception {
        // given
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/user/getAllUserRepos/octocat")
                .content("octocat")
                .contentType(APPLICATION_JSON));

        // then
        verify(usersService).getAllUserInfo(argumentCaptor.capture());
        resultActions.andExpect(status().isOk());
    }

    @Test
    void getAllUserRepos_no_service_interaction_when_passing_a_white_space() throws Exception {
        // given

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/user/getAllUserRepos/{username}", " ")
                .contentType(APPLICATION_JSON));

        // then
        verifyNoInteractions(usersService);
        resultActions.andExpect(status().is4xxClientError());
    }
}
