package models.request.chat;

import models.entity.User;
import models.utils.ValidationAware;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * チャット発言時リクエストのエンティティ
 * Created by raizu on 2014/11/29.
 */
public class SayRequest implements ValidationAware {

    /** ユーザID */
    @Constraints.Required
    public Long userId;
    /** 発言 */
    @Constraints.Required
    public String message;

    /** ユーザIDに紐づくユーザ */
    public User user;

    @Override
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();

        // ユーザIDがDBに存在するかチェック
        user = User.find.byId(userId);
        if (user == null) {
            errors.add(new ValidationError("userId", "user not found"));
        }

        return errors.isEmpty() ? null : errors;
    }
}
