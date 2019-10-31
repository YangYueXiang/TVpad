package com.boe.tvpad.bean;

import java.util.List;

public class LightBean {

    /**
     * data : [{"id":1,"createTime":"2019-08-06T02:19:20.000+0000","updateTime":"2019-08-06T02:19:20.000+0000","number":"L201906260001","name":"灯光1","description":null,"state":null,"sort":1,"room":{"id":3,"createTime":"2019-08-06T02:28:46.539+0000","updateTime":"2019-08-06T02:28:46.539+0000","name":"第二实验室","sort":2,"filename":"c://test","filepath":"test.png","userList":null,"partitionList":null,"lightingList":null}},{"id":2,"createTime":"2019-08-06T02:28:42.000+0000","updateTime":"2019-08-06T02:28:42.000+0000","number":"L201906260002","name":"灯光2","description":null,"state":null,"sort":2,"room":{"id":4,"createTime":"2019-08-06T02:28:46.540+0000","updateTime":"2019-08-06T02:28:46.540+0000","name":"HQ展厅","sort":3,"filename":"c://test","filepath":"test.png","userList":null,"partitionList":null,"lightingList":null}}]
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
         * id : 1
         * createTime : 2019-08-06T02:19:20.000+0000
         * updateTime : 2019-08-06T02:19:20.000+0000
         * number : L201906260001
         * name : 灯光1
         * description : null
         * state : null
         * sort : 1
         * room : {"id":3,"createTime":"2019-08-06T02:28:46.539+0000","updateTime":"2019-08-06T02:28:46.539+0000","name":"第二实验室","sort":2,"filename":"c://test","filepath":"test.png","userList":null,"partitionList":null,"lightingList":null}
         */

        private int id;
        private String createTime;
        private String updateTime;
        private String number;
        private String name;
        private String description;
        private String state;
        private int sort;
        private RoomBean room;

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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public RoomBean getRoom() {
            return room;
        }

        public void setRoom(RoomBean room) {
            this.room = room;
        }

        public static class RoomBean {
            /**
             * id : 3
             * createTime : 2019-08-06T02:28:46.539+0000
             * updateTime : 2019-08-06T02:28:46.539+0000
             * name : 第二实验室
             * sort : 2
             * filename : c://test
             * filepath : test.png
             * userList : null
             * partitionList : null
             * lightingList : null
             */

            private int id;
            private String createTime;
            private String updateTime;
            private String name;
            private int sort;
            private String filename;
            private String filepath;
            private String userList;
            private String partitionList;
            private String lightingList;

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

            public String getUserList() {
                return userList;
            }

            public void setUserList(String userList) {
                this.userList = userList;
            }

            public String getPartitionList() {
                return partitionList;
            }

            public void setPartitionList(String partitionList) {
                this.partitionList = partitionList;
            }

            public String getLightingList() {
                return lightingList;
            }

            public void setLightingList(String lightingList) {
                this.lightingList = lightingList;
            }
        }
    }
}
