package uz.limon.chatsecurity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uz.limon.chatsecurity.dto.ChatDTO;
import uz.limon.chatsecurity.dto.MessageDTO;
import uz.limon.chatsecurity.dto.ResponseDTO;
import uz.limon.chatsecurity.dto.UserDTO;



import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserAddIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private static String token;
    private final String username = "xadicha";
    private final String password = "Aa12345";

    @Order(0)
    @Test
    public void addUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String user = null;
        try {
            user = objectMapper.writeValueAsString(new UserDTO(
                    null,
                    "Xadicha",
                    "Qosimov",
                    "+998991112233",
                    username,
                    password));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/add")
                .contentType("application/json")
                .content(user);

        String responseBody = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ObjectReader reader = objectMapper.readerFor(new TypeReference<ResponseDTO<UserDTO>>() {});
        ResponseDTO<UserDTO> responseDTO = reader.readValue(responseBody);
        Assertions.assertNotNull(responseDTO.getData());
        Assertions.assertTrue(responseDTO.getSuccess());
    }
    @Order(1)
    @Test
    public void getToken() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(password);
        userDTO.setUsername(username);

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(userDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/token")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);

        String responseBody = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectReader reader = objectMapper.readerFor(new TypeReference<ResponseDTO<String>>() {});
        ResponseDTO<String> responseDTO = reader.readValue(responseBody);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertNotNull(responseDTO.getData());
        Assertions.assertTrue(responseDTO.getSuccess());

        token = responseDTO.getData();
    }

    @Order(4)
    @Test
    public void deleteUser() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/" + username)
                        .header("Authorization", "Bearer ".concat(token));
        String response =  mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status()
                        .is(200)).andReturn().getResponse().getContentAsString();
        ObjectReader objectReader = new ObjectMapper().readerFor(new TypeReference<ResponseDTO<Integer>>() {});
        ResponseDTO<Integer> responseDTO = objectReader.readValue(response);
        Assertions.assertNotNull(responseDTO.getData());
        Assertions.assertTrue(responseDTO.getSuccess());
        Assertions.assertEquals(responseDTO.getData(),1);
    }
//    @Order(2)
//    @Test
//    public void addChat() throws Exception {
//        ChatDTO chatDTO = new ChatDTO();
//        chatDTO.setName("Telegram");
//        chatDTO.setUserIds(new ArrayList<>(List.of(1,2)));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String content = objectMapper.writeValueAsString(chatDTO);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/chats/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content)
//                .header("Authorization","Bearer " + token);
//
//        String response = mockMvc.perform(requestBuilder)
//                .andExpect(status().is(200))
//                .andDo(print())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        ObjectReader objectReader = objectMapper.readerFor(new TypeReference<ResponseDTO<Integer>>() {
//        });
//        ResponseDTO<Integer> responseDTO = objectReader.readValue(response);
//        Assertions.assertNotNull(responseDTO.getData());
//        Assertions.assertTrue(responseDTO.getSuccess());
//    }
    @Order(3)
    @Test
    public void addMessage() throws Exception {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setAuthor(9);
        messageDTO.setType("TEXT");
        messageDTO.setChat(3);
        messageDTO.setContent("Hello");

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(messageDTO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .header("Authorization", "Bearer " + token);

        String response = mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ObjectReader objectReader = objectMapper.readerFor(new TypeReference<ResponseDTO<Integer>>() {
        });
        ResponseDTO<Integer> responseDTO = objectReader.readValue(response);
        Assertions.assertNotNull(responseDTO.getData());
        Assertions.assertTrue(responseDTO.getSuccess());
    }

}
