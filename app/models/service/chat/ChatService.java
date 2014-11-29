package models.service.chat;

import models.entity.ChatMessage;
import models.request.chat.SayRequest;

/**
 * チャットの登録・取得などを行うサービス
 * Created by raizu on 2014/11/29.
 */
public class ChatService {

    /**
     * インスタンスを取得
     * @return インスタンス
     */
    public static ChatService use() {
        return new ChatService();
    }

    /** コンストラクタ */
    private ChatService() {
    }

    /**
     * 発言
     * @param request 発言リクエスト
     * @return インスタンス
     */
    public ChatService say(SayRequest request) {
        ChatMessage message = new ChatMessage();
        message.user = request.user;
        message.message = request.message;
        message.save();
        return this;
    }
}
