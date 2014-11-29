package models.settings;

import com.avaje.ebean.Ebean;
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

        Map<String, List<Object>> iniData = (Map<String, List<Object>>) Yaml.load("inidata/sample-data.yml");
        List<Object> users = iniData.get("users");
        Ebean.save(users);

        List<User> all = User.find.all();
        all.stream().forEach(u -> Logger.info(u.toString()));
    }
}
