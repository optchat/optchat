package models.settings;

import com.avaje.ebean.Ebean;
import models.entity.Login;
import models.entity.User;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Yaml;

import java.util.List;
import java.util.Map;

/**
 * 共通処理クラス
 * Created by raizu on 2014/11/29.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application application) {
        super.onStart(application);

        insertSampleUser();
    }

    /**
     * サンプル用のユーザを登録
     */
    public void insertSampleUser() {
        if (User.find.all().size() != 0) return;

        Map<String, List<Object>> iniData = (Map<String, List<Object>>) Yaml.load("inidata/sample-data.yml");
        List<Object> users = iniData.get("users");
        Ebean.save(users);

        List<Object> login = iniData.get("login_users");
        Ebean.save(login);

        List<User> all = User.find.all();
        all.stream().forEach(u -> Logger.info(u.toString()));
        List<Login> loginUsers = Login.find.all();
        loginUsers.stream().forEach(u -> Logger.info(u.toString()));
    }
}
