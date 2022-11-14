package uz.limon.chatsecurity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "chat_users")
@Data
public class ChatUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "chat_id_seq")
    private Integer id;

    @Column(name = "chats_id")
    private Integer chatId;

    @Column(name = "users_id")
    private Integer userId;
}
