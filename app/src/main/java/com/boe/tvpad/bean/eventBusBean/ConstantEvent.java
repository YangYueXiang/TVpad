package com.boe.tvpad.bean.eventBusBean;

public class ConstantEvent {
    private int type;

    private String prigramId;
    private String programType;
    private String imgurl;//currentActivity需要的图片
    private String name;//currentActivity需要的名称
    private String lightposition;//点击灯光得知哪个条目
    private String lightId;
    private String lightstate;

    private String deviceId;
    private String deviceposition;
    private String devicestate;
    private int position;
    private int partposition;

    public int getPartposition() {
        return partposition;
    }

    public void setPartposition(int partposition) {
        this.partposition = partposition;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPrigramId() {
        return prigramId;
    }

    public void setPrigramId(String prigramId) {
        this.prigramId = prigramId;
    }

    public String getDeviceposition() {
        return deviceposition;
    }

    public void setDeviceposition(String deviceposition) {
        this.deviceposition = deviceposition;
    }

    public String getDevicestate() {
        return devicestate;
    }

    public void setDevicestate(String devicestate) {
        this.devicestate = devicestate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLightposition() {
        return lightposition;
    }

    public void setLightposition(String lightposition) {
        this.lightposition = lightposition;
    }

    public String getLightId() {
        return lightId;
    }

    public void setLightId(String lightId) {
        this.lightId = lightId;
    }

    public String getLightstate() {
        return lightstate;
    }

    public void setLightstate(String lightstate) {
        this.lightstate = lightstate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public ConstantEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    //分区灯光跳转
    public static final int SelectedLight = 1;
    //通知当前播放的节目更改封面
    public static final int ChangeCurrentPlayImg=2;
    //通知后台打开或关闭灯光
    public static final int NoticeToOpenOrClose=3;
    //通知activity通知后台打开或关闭设备
    public static final int OpenOrCloseDevice=4;
    public static final int OpenOrCloseSwitch=5;
    //获取设备的id,和名字跳转分区
    public static final int NoticePosition=6;
    //分区里面跳转的下标确定
    public static final int NoticePartPosition=7;
}
