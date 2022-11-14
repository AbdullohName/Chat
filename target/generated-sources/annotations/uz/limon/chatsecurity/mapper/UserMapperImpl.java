package uz.limon.chatsecurity.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.limon.chatsecurity.dto.UserDTO;
import uz.limon.chatsecurity.dto.custom.ChatCustomDTO;
import uz.limon.chatsecurity.dto.custom.UserCustomDTO;
import uz.limon.chatsecurity.model.Chat;
import uz.limon.chatsecurity.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-14T12:48:46+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setFirstName( user.getFirstName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setPhoneNumber( user.getPhoneNumber() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setFirstName( userDTO.getFirstName() );
        user.setLastName( userDTO.getLastName() );
        user.setPhoneNumber( userDTO.getPhoneNumber() );
        user.setPassword( userDTO.getPassword() );
        user.setUsername( userDTO.getUsername() );

        return user;
    }

    @Override
    public UserCustomDTO toCustomDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserCustomDTO userCustomDTO = new UserCustomDTO();

        userCustomDTO.setId( user.getId() );
        userCustomDTO.setFirstName( user.getFirstName() );
        userCustomDTO.setLastName( user.getLastName() );
        userCustomDTO.setPhoneNumber( user.getPhoneNumber() );
        userCustomDTO.setUsername( user.getUsername() );
        userCustomDTO.setPassword( user.getPassword() );
        userCustomDTO.setChats( chatListToChatCustomDTOList( user.getChats() ) );

        return userCustomDTO;
    }

    protected ChatCustomDTO chatToChatCustomDTO(Chat chat) {
        if ( chat == null ) {
            return null;
        }

        ChatCustomDTO chatCustomDTO = new ChatCustomDTO();

        chatCustomDTO.setId( chat.getId() );
        chatCustomDTO.setName( chat.getName() );
        if ( chat.getCreatedAt() != null ) {
            chatCustomDTO.setCreatedAt( new SimpleDateFormat().format( chat.getCreatedAt() ) );
        }

        return chatCustomDTO;
    }

    protected List<ChatCustomDTO> chatListToChatCustomDTOList(List<Chat> list) {
        if ( list == null ) {
            return null;
        }

        List<ChatCustomDTO> list1 = new ArrayList<ChatCustomDTO>( list.size() );
        for ( Chat chat : list ) {
            list1.add( chatToChatCustomDTO( chat ) );
        }

        return list1;
    }
}
