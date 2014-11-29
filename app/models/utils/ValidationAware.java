package models.utils;

import play.data.validation.ValidationError;

import java.util.List;

/**
 * バリデーション可能なエンティティを示すインターフェース
 * Created by raizu on 2014/11/29.
 */
public interface ValidationAware {

    /**
     * バリデーション
     * @return バリデーションのエラー
     */
    public List<ValidationError> validate();
}
