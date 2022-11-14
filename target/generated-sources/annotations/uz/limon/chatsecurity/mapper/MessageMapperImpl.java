package uz.limon.chatsecurity.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.limon.chatsecurity.dto.MessageDTO;
import uz.limon.chatsecurity.dto.custom.MessageCustomDTO;
import uz.limon.chatsecurity.model.Message;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-14T12:48:46+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDTO toDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setAuthor( message.getAuthorId() );
        messageDTO.setChat( message.getChatId() );
        if ( message.getCreatedAt() != null ) {
            messageDTO.setCreatedAt( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( message.getCreatedAt() ) );
        }
        messageDTO.setType( message.getType() );
        messageDTO.setContent( message.getContent() );
        messageDTO.setExt( message.getExt() );

        return messageDTO;
    }

    @Override
    public Message toEntity(MessageDTO messageDTO) {
        if ( messageDTO == null ) {
            return null;
        }

        Message message = new Message();

        message.setAuthorId( messageDTO.getAuthor() );
        message.setChatId( messageDTO.getChat() );
        try {
            if ( messageDTO.getCreatedAt() != null ) {
                message.setCreatedAt( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).parse( messageDTO.getCreatedAt() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        message.setType( messageDTO.getType() );
        message.setContent( messageDTO.getContent() );
        message.setExt( messageDTO.getExt() );

        return message;
    }

    @Override
    public MessageCustomDTO toCustomDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageCustomDTO messageCustomDTO = new MessageCustomDTO();

        if ( message.getCreatedAt() != null ) {
            messageCustomDTO.setCreatedAt( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( message.getCreatedAt() ) );
        }
        messageCustomDTO.setContent( message.getContent() );
        messageCustomDTO.setType( message.getType() );
        messageCustomDTO.setExt( message.getExt() );

        return messageCustomDTO;
    }
}
