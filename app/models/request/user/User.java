package models.request.user;

import models.utils.ValidationAware;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.i18n.Messages;

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

        user = models.entity.Login.find.where().eq("mail", mail)
                .findUnique();

        // パスワードチェック
        if (!user == null) {
            errors.add(new ValidationError("mail", "fewe"));
        }

        return errors.isEmpty() ? null : errors;
    }

}