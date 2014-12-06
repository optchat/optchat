package models.request.user;

import models.entity.Login;
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

    /**
     * ユーザーエンティティ
     */
    public models.entity.User user;

    /**
     * ログインエンティティ
     */
    public models.entity.Login login;

    @Override
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();

        Login loginUser = models.entity.Login.find.where().eq("mail", mail)
                .findUnique();

        // すでに存在するメールアドレスかチェック
        if (loginUser != null) {
            errors.add(new ValidationError("mail", "fewe"));
        }

        this.user = createUserEntity(name, mail);
        this.login = createLoginEntity(mail, password);

        return errors.isEmpty() ? null : errors;
    }

    /**
     * ユーザーエンティティの作成
     *
     * @param name 名前
     * @param mail メールアドレス
     * @return ユーザーエンティティクラス
     */
    private models.entity.User createUserEntity(String name, String mail) {
        models.entity.User user = new models.entity.User();
        user.name = name;
        user.mail = mail;

        return user;
    }

    /**
     * ログインエンティティを作成
     * パスワードのハッシュ化はメソッド内で行う
     *
     * @param mail     メールアドレス
     * @param password パスワード
     * @return ログインエンティティ
     */
    private models.entity.Login createLoginEntity(String mail, String password) {
        models.entity.Login login = new models.entity.Login();
        login.mail = mail;
        login.password = BCrypt.hashpw(password, BCrypt.gensalt());

        return login;
    }
}