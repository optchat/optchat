package models.request.security;


import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import play.data.Form;
import play.test.Helpers;
import utils.WithAppTest;

import java.util.HashMap;

import static org.fest.assertions.Assertions.assertThat;


public class LoginTest extends WithAppTest {
    @Test
    public void メールアドレスが未入力の場合はエラー() throws Exception {
        Form<Login> form = Form.form(Login.class).bind(new HashMap<String, String>() {
            {
                put("password", "aaa");
            }
        });
        assertThat(form.hasErrors()).isTrue();
        System.out.println(form.errors());
        assertThat(form.error("mail")).isNotNull();
    }
    @Test
    public void メールアドレス形式でない場合はエラー() throws Exception {
        Form<Login> form = Form.form(Login.class).bind(new HashMap<String, String>() {
            {
                put("mail", "mail");
                put("password", "aaa");
            }
        });
        assertThat(form.hasErrors()).isTrue();
        System.out.println(form.errors());
        assertThat(form.error("mail")).isNotNull();
    }
    @Test
    public void パスワードが未入力の場合はエラー() throws Exception {
        Form<Login> form = Form.form(Login.class).bind(new HashMap<String, String>() {
            {
                put("mail", "mail@domain.com");
            }
        });
        assertThat(form.hasErrors()).isTrue();
        System.out.println(form.errors());
        assertThat(form.error("password")).isNotNull();
    }
    @Test
    public void メールアドレスがDBに存在しない場合はエラー() throws Exception {
        Form<Login> form = Form.form(Login.class).bind(new HashMap<String, String>() {
            {
                put("mail", "aaamail@domain.com");
                put("password", "bbb");
            }
        });
        assertThat(form.hasGlobalErrors()).isTrue();
    }
    @Test
    public void パスワードが一致しない場合はエラー() throws Exception {
        models.entity.Login login = new models.entity.Login();
        login.mail = "aaa@domain.com";
        login.password = BCrypt.hashpw("aaa", BCrypt.gensalt());
        login.save();
        Form<Login> form = Form.form(Login.class).bind(new HashMap<String, String>() {
            {
                put("mail", "aaa@domain.com");
                put("password", "bbb");
            }
        });
        assertThat(form.hasGlobalErrors()).isTrue();
    }

}