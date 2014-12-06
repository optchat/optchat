package models.service.user;

import models.entity.ChatMessage;
import models.request.chat.SayRequest;
import org.mindrot.jbcrypt.BCrypt;

/**
 * ユーザー情報の取得と登録を行うクラス
 * Created by purini-to on 2014/11/29.
 */
public class UserService {

    /**
     * インスタンスを取得
     *
     * @return インスタンス
     */
    public static UserService use() {
        return new UserService();
    }

    /**
     * コンストラクタ
     */
    private UserService() {
    }

    /**
     * ユーザーエンティティの作成
     *
     * @param name 名前
     * @param mail メールアドレス
     * @return ユーザーエンティティクラス
     */
    public models.entity.User createUserEntity(String name, String mail) {
        models.entity.User user = new models.entity.User();
        user.name = name;
        user.mail = mail;
        user.save();

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
    public models.entity.Login createLoginEntity(String mail, String password) {
        models.entity.Login login = new models.entity.Login();
        login.mail = mail;
        login.password = BCrypt.hashpw(password, BCrypt.gensalt());
        login.save();

        return login;
    }
}
