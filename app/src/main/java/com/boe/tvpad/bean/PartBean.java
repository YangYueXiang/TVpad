package com.boe.tvpad.bean;

import java.util.List;

public class PartBean {

    /**
     * data : [{"play":"aaa.jpg","red":"1","filepath":"aaa.jpg","downloadState":null,"playType":"视频","name":"room1播放器","id":74,"state":"stateOn","store":"内存不足","type":"播放器"},{"name":"1","id":73,"state":"stateOut","type":"开关"}]
     * state : s
     * message :
     */

    private String state;
    private String message;
    private List<DataBean> data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * play : aaa.jpg
         * red : 1
         * filepath : aaa.jpg
         * downloadState : null
         * playType : 视频
         * name : room1播放器
         * id : 74
         * state : stateOn
         * store : 内存不足
         * type : 播放器
         */

        private String play;
        private String red;
        private String filepath;
        private String downloadState;
        private String playType;
        private String name;
        private int id;
        private String state;
        private String store;
        private String type;
        private String programId;

        public String getProgramId() {
            return programId;
        }

        public void setProgramId(String programId) {
            this.programId = programId;
        }

        public String getPlay() {
            return play;
        }

        public void setPlay(String play) {
            this.play = play;
        }

        public String getRed() {
            return red;
        }

        public void setRed(String red) {
            this.red = red;
        }

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }

        public String getDownloadState() {
            return downloadState;
        }

        public void setDownloadState(String downloadState) {
            this.downloadState = downloadState;
        }

        public String getPlayType() {
            return playType;
        }

        public void setPlayType(String playType) {
            this.playType = playType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
