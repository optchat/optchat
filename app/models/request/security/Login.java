package models.request.security;

import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;
import play.i18n.Messages;

/**
 * ログインエンティティ
 * Created by s1hit on 2014/11/29.
 */
public class Login {


    /** メールアドレス */
    @Constraints.Required
    @Constraints.Email
    public String mail;

    /** パスワード */
    @Constraints.Required
    public String password;

    /** ログインエンティティ */
    public models.entity.Login user;
//
//    /**
//     * バリデーション
//     * @return null:OK
//     */
//    public String validate() {
//        if (!authenticate(mail, password)) {
//            return Messages.get("controllers.security.invalid.user");
//        }
//        return null;
//    }

    /**
     * 認証処理
     * @param mail ユーザID
     * @param password パスワード
     * @return true:認証OK
     */
    private Boolean authenticate(String mail, String password) {
        user = models.entity.Login.find.where().eq("mail", mail)
                .findUnique();
        return (user != null && BCrypt.checkpw(password, user.password));
    }
}