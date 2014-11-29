package models.request.chat;

import models.entity.User;
import org.junit.Test;
import play.data.Form;
import play.test.Helpers;

import java.util.HashMap;

import static org.fest.assertions.Assertions.assertThat;

public class SayRequestTest {

    @Test
    public void ユーザIDが設定されいないとエラー() throws Exception {
        Form<SayRequest> form = Form.form(SayRequest.class).bind(new HashMap<String, String>() {
            {
                put("message", "aaa");
            }
        });
        assertThat(form.hasErrors()).isTrue();
        System.out.println(form.errors());
        assertThat(form.error("userId")).isNotNull();
    }

    @Test
    public void メッセージが設定されていないとエラー() throws Exception {
        Form<SayRequest> form = Form.form(SayRequest.class).bind(new HashMap<String, String>() {
            {
                put("userId", "1");
            }
        });
        assertThat(form.hasErrors()).isTrue();
        assertThat(form.error("message")).isNotNull();
    }

    @Test
    public void ユーザIDがDBに存在しない数値だとエラー() throws Exception {
        Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase()), () -> {
            User.find.all().forEach(u -> u.delete());
            Form<SayRequest> form = Form.form(SayRequest.class).bind(new HashMap<String, String>() {
                {
                    put("userId", "1");
                    put("message", "test");
                }
            });
            assertThat(form.hasErrors()).isTrue();
            assertThat(form.error("userId")).isNotNull();
        });
    }

    @Test
    public void 正しい情報でユーザが存在する場合はエラー無し() throws Exception {
        Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase()), () -> {
            User.find.all().forEach(u -> u.delete());
            User user = new User();
            user.userId = 1L;
            user.name = "test";
            user.mail = "ee@exe.com";
            user.save();

            Form<SayRequest> form = Form.form(SayRequest.class).bind(new HashMap<String, String>() {
                {
                    put("userId", "1");
                    put("message", "test");
                }
            });
            assertThat(form.hasErrors()).isFalse();
        });
    }
}