package models.request.security;

import models.utils.ValidationAware;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.i18n.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * ログインエンティティ
 * Created by s1hit on 2014/11/29.
 */
public class Login implements ValidationAware {


    /** メールアドレス */
    @Constraints.Required
    @Constraints.Email
    public String mail;

    /** パスワード */
    @Constraints.Required
    public String password;

    /** ログインエンティティ */
    public models.entity.Login user;

    @Override
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();

        user = models.entity.Login.find.where().eq("mail", mail)
                .findUnique();

        // パスワードチェック
        if (user == null || !BCrypt.checkpw(password, user.password)) {
            errors.add(new ValidationError("password", "password unmatch."));
        }

        return errors.isEmpty() ? null : errors;
    }

}