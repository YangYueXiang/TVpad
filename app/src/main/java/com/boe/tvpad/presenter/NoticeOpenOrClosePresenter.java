package com.boe.tvpad.presenter;

public interface NoticeOpenOrClosePresenter {
    void obtainOpenOrCloseState(String roomId,String partitionId,String  id ,String state,String type, String padToken);
}
