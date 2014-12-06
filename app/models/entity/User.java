package models.entity;

import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * ユーザエンティティ
 * Created by raizu on 2014/11/29.
 */
@Entity
public class User extends Model {

    /** ID */
    @Id
    public Long userId;

    /** 名前 */
    @NotNull
    public String name;

    /** メールアドレス */
    @NotNull
    public String mail;

    /** チャットメッセージ */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<ChatMessage> chatMessages = new ArrayList<>();

    /** ファインダー */
    public static Finder<Long, User> find = new Finder<>(Long.class, User.class);

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", chatMessages=" + chatMessages +
                '}';
    }
}