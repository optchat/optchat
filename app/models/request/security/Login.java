package models.request.security;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * ログインエンティティ
 * Created by s1hit on 2014/11/29.
 */
@Entity
public class Login extends Model {

    /** ID */
    @Id
    public Long userId;

    /** メールアドレス */
    @NotNull
    public String mail;

    /** パスワード */
    public String password;
}