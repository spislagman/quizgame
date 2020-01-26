package com.example.quizgame;

import java.util.EventListener;

public interface RecyclerViewListnerInterface extends EventListener {
    // 項目クリック
    public void click_item(int position);
}
