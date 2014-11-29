package models.entity;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
}