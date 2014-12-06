package models.request.security;

import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;
import play.i18n.Messages;

/**
 * ログインエンティティ
 * Created by s1hit on 2014/11/29.
 */
public class Login {


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

    /**
     * ログインエンティティ
     */
    public models.entity.Login user;

    public String validate() {
        //        List<ValidationError> errors = new ArrayList<>();

        user = models.entity.Login.find.where().eq("mail", mail)
                .findUnique();

        // パスワードチェック
        if (user == null || !BCrypt.checkpw(password, user.password)) {
//            errors.add(new ValidationError("password", "password unmatch."));
            return Messages.get("validation.error.password.unmatch");
        }

//        return errors.isEmpty() ? null : errors;
        return null;
    }

}