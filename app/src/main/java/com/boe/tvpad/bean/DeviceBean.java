package com.boe.tvpad.bean;

import java.io.Serializable;
import java.util.List;

public class DeviceBean {

    /**
     * data : [{"room_id":54,"red":"0","createTime":"2019-09-22T05:52:55.000+0000","name":"4","count":1,"countOn":0,"updateTime":"2019-09-22T05:52:55.000+0000","company":"10141854","id":98,"sort":98}]
     * titleInfo : {"roomId":"54","roomName":"新建展厅3"}
     * state : s
     * message :
     */

    private TitleInfoBean titleInfo;
    private String state;
    private String message;
    private List<DataBean> data;

    public TitleInfoBean getTitleInfo() {
        return titleInfo;
    }

    public void setTitleInfo(TitleInfoBean titleInfo) {
        this.titleInfo = titleInfo;
    }

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

    public static class TitleInfoBean {
        /**
         * roomId : 54
         * roomName : 新建展厅3
         */

        private String roomId;
        private String roomName;

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }
    }

    public static class DataBean implements Serializable {
        /**
         * room_id : 54
         * red : 0
         * createTime : 2019-09-22T05:52:55.000+0000
         * name : 4
         * count : 1
         * countOn : 0
         * updateTime : 2019-09-22T05:52:55.000+0000
         * company : 10141854
         * id : 98
         * sort : 98
         */

        private int room_id;
        private String red;
        private String createTime;
        private String name;
        private int count;
        private int countOn;
        private String updateTime;
        private String company;
        private int id;
        private int sort;

        public int getRoom_id() {
            return room_id;
        }

        public void setRoom_id(int room_id) {
            this.room_id = room_id;
        }

        public String getRed() {
            return red;
        }

        public void setRed(String red) {
            this.red = red;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCountOn() {
            return countOn;
        }

        public void setCountOn(int countOn) {
            this.countOn = countOn;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
