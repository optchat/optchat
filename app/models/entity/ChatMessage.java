package models.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * チャット発言エンティティ
 * Created by raizu on 2014/11/29.
 */
@Entity
public class ChatMessage {

    /** ID */
    public Long messageId;

    /** メッセージ */
    public String message;

    /** ユーザID */
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

}
