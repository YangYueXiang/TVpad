package com.boe.tvpad.bean;

import java.util.List;

public class SlidHollListBean {

    /**
     * data : [{"id":54,"createTime":"2019-09-19T03:00:58.000+0000","updateTime":"2019-09-19T03:00:58.000+0000","name":"新建展厅3","sort":54,"filename":"default.jpg","filepath":"http://10.251.159.9:8080/image/default.jpg","userList":null,"partitionList":null,"lightingList":null,"company":"10141854"},{"id":53,"createTime":"2019-09-19T01:16:20.000+0000","updateTime":"2019-09-19T01:16:20.000+0000","name":"新建展厅2","sort":53,"filename":"default.jpg","filepath":"http://10.251.159.9:8080/image/default.jpg","userList":null,"partitionList":null,"lightingList":null,"company":"10141854"}]
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
         * id : 54
         * createTime : 2019-09-19T03:00:58.000+0000
         * updateTime : 2019-09-19T03:00:58.000+0000
         * name : 新建展厅3
         * sort : 54
         * filename : default.jpg
         * filepath : http://10.251.159.9:8080/image/default.jpg
         * userList : null
         * partitionList : null
         * lightingList : null
         * company : 10141854
         */

        private int id;
        private String createTime;
        private String updateTime;
        private String name;
        private int sort;
        private String filename;
        private String filepath;
        private Object userList;
        private Object partitionList;
        private Object lightingList;
        private String company;
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }

        public Object getUserList() {
            return userList;
        }

        public void setUserList(Object userList) {
            this.userList = userList;
        }

        public Object getPartitionList() {
            return partitionList;
        }

        public void setPartitionList(Object partitionList) {
            this.partitionList = partitionList;
        }

        public Object getLightingList() {
            return lightingList;
        }

        public void setLightingList(Object lightingList) {
            this.lightingList = lightingList;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }
    }
}
