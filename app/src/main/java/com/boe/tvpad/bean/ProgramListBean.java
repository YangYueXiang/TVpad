package com.boe.tvpad.bean;

import java.util.List;

public class ProgramListBean {

    /**
     * data : [{"playInterval":null,"tempFilename":"http://10.251.159.9:8080/image/program/20190925090208T20/70.jpg","defaultPlay":"0","type":"图片","duration":null,"number":"C201909250001","filename":"http://10.251.159.9:8080/image/program/20190925090208T20/70.jpg","size":"500*395px","name":"70(1)","downloadType":"0","company":"alily","id":350,"flow":"35.00","coverName":null,"thumbnailName":"http://10.251.159.9:8080/image/program/20190925090208T20/thum_70.jpg"}]
     * titleInfo : {"partitionName":"66","partitionId":99,"exhibitsId":79,"exhibitsName":"55","roomId":55,"roomName":"alily-展厅1"}
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
         * partitionName : 66
         * partitionId : 99
         * exhibitsId : 79
         * exhibitsName : 55
         * roomId : 55
         * roomName : alily-展厅1
         */

        private String partitionName;
        private int partitionId;
        private int exhibitsId;
        private String exhibitsName;
        private int roomId;
        private String roomName;

        public String getPartitionName() {
            return partitionName;
        }

        public void setPartitionName(String partitionName) {
            this.partitionName = partitionName;
        }

        public int getPartitionId() {
            return partitionId;
        }

        public void setPartitionId(int partitionId) {
            this.partitionId = partitionId;
        }

        public int getExhibitsId() {
            return exhibitsId;
        }

        public void setExhibitsId(int exhibitsId) {
            this.exhibitsId = exhibitsId;
        }

        public String getExhibitsName() {
            return exhibitsName;
        }

        public void setExhibitsName(String exhibitsName) {
            this.exhibitsName = exhibitsName;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }
    }

    public static class DataBean {
        /**
         * playInterval : null
         * tempFilename : http://10.251.159.9:8080/image/program/20190925090208T20/70.jpg
         * defaultPlay : 0
         * type : 图片
         * duration : null
         * number : C201909250001
         * filename : http://10.251.159.9:8080/image/program/20190925090208T20/70.jpg
         * size : 500*395px
         * name : 70(1)
         * downloadType : 0
         * company : alily
         * id : 350
         * flow : 35.00
         * coverName : null
         * thumbnailName : http://10.251.159.9:8080/image/program/20190925090208T20/thum_70.jpg
         */

        private Object playInterval;
        private String tempFilename;
        private String defaultPlay;
        private String type;
        private String duration;
        private String number;
        private String filename;
        private String size;
        private String name;
        private String downloadType;
        private String company;
        private int id;
        private String flow;
        private String coverName;
        private String thumbnailName;
        private String photoNum;
        private String photoFlow;

        public String getPhotoNum() {
            return photoNum;
        }

        public void setPhotoNum(String photoNum) {
            this.photoNum = photoNum;
        }

        public String getPhotoFlow() {
            return photoFlow;
        }

        public void setPhotoFlow(String photoFlow) {
            this.photoFlow = photoFlow;
        }

        public Object getPlayInterval() {
            return playInterval;
        }

        public void setPlayInterval(Object playInterval) {
            this.playInterval = playInterval;
        }

        public String getTempFilename() {
            return tempFilename;
        }

        public void setTempFilename(String tempFilename) {
            this.tempFilename = tempFilename;
        }

        public String getDefaultPlay() {
            return defaultPlay;
        }

        public void setDefaultPlay(String defaultPlay) {
            this.defaultPlay = defaultPlay;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDownloadType() {
            return downloadType;
        }

        public void setDownloadType(String downloadType) {
            this.downloadType = downloadType;
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

        public String getFlow() {
            return flow;
        }

        public void setFlow(String flow) {
            this.flow = flow;
        }

        public String getCoverName() {
            return coverName;
        }

        public void setCoverName(String coverName) {
            this.coverName = coverName;
        }

        public String getThumbnailName() {
            return thumbnailName;
        }

        public void setThumbnailName(String thumbnailName) {
            this.thumbnailName = thumbnailName;
        }
    }
}
