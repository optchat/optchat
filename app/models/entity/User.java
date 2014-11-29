package models.entity;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
}