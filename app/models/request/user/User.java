package models.request.user;

import models.entity.Login;
import models.service.user.UserService;
import models.utils.ValidationAware;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * ユーザー登録リクエスト時のエンティティ
 * Created by purini-to on 2014/12/06.
 */
public class User implements ValidationAware {

    /**
     * ユーザーの名前
     */
    @Constraints.Required
    public String name;

    /**
     * メールアドレス
     */
    @Constraints.Required
    @Constraints.Email
    public String mail;

    /**
     * パスワード
     */
    @Constraints.Required
    public String password;

    @Override
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();

        Login loginUser = models.entity.Login.find.where().eq("mail", mail)
                .findUnique();

        // すでに存在するメールアドレスかチェック
        if (loginUser != null) {
            errors.add(new ValidationError("mail", "validation.error.mail.exists"));
        }

        return errors.isEmpty() ? null : errors;
    }
}