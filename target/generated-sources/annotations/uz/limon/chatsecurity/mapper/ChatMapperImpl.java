package uz.limon.chatsecurity.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.limon.chatsecurity.dto.ChatDTO;
import uz.limon.chatsecurity.dto.custom.ChatCustomDTO;
import uz.limon.chatsecurity.model.Chat;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-14T12:48:46+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
@Component
public class ChatMapperImpl implements ChatMapper {

    @Override
    public ChatDTO toDto(Chat chat) {
        if ( chat == null ) {
            return null;
        }

        ChatDTO chatDTO = new ChatDTO();

        if ( chat.getCreatedAt() != null ) {
            chatDTO.setCreatedAt( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( chat.getCreatedAt() ) );
        }
        chatDTO.setId( chat.getId() );
        chatDTO.setName( chat.getName() );

        return chatDTO;
    }

    @Override
    public Chat toEntity(ChatDTO chatDTO) {
        if ( chatDTO == null ) {
            return null;
        }

        Chat chat = new Chat();

        try {
            if ( chatDTO.getCreatedAt() != null ) {
                chat.setCreatedAt( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).parse( chatDTO.getCreatedAt() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        chat.setId( chatDTO.getId() );
        chat.setName( chatDTO.getName() );

        return chat;
    }

    @Override
    public ChatCustomDTO toCustomDto(Chat chat) {
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
}
