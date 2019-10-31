package com.boe.tvpad.common;

public class HttpCommon {
   // 218.104.69.90:8080
    public static final String baseUrl = "http://112.126.123.94:8481";
    //public static final String baseUrl1 = "http://10.1.83.90:8080";
    //登录
    public static final String getLoginData = baseUrl + "/login/login";
    //修改密码
    public static final String getChangePwdData = baseUrl + "/user/changePassword";
    //获取侧滑里面的展厅列表
    public static final String getSlidHollList = baseUrl + "/smartShowRoom/list";
    //退出登录
    public static final String exitLogin = baseUrl + "/login/logout";
    //获取设备管理列表
    public static final String getDeviceData = baseUrl + "/partition/listForPad";
    //获取灯光列表
    public static final String getLightData = baseUrl + "/lighting/getLightList";
    //获取分区的数据
    public static final String getPartData = baseUrl + "/exhibits/listForPad";
    //获取侧滑右边节目列表
    public static final String getProgramList = baseUrl + "/exhibits/getProgramListForExhibits";
    //删除节目
    public static final String deleteProgram = baseUrl + "/exhibits/relieveForPad";
    //通知后台开启或关闭灯光或设备
    public static final String noticeOpenOrCloseDeviceOrLight = baseUrl + "/switch/updateSwitch";
    //是否需要重新登录
    public static final String needOrNotLogin = baseUrl+ "login/loginState";
    //侧滑控制播放节目
    public static final String playProgram = baseUrl + "/exhibits/setExhibitsProgram";
    //控制按钮
    public static final String controller = baseUrl + "/exhibits/setExhibitsState";
    //去下载的同步按钮
    public static final String  todownload= baseUrl + "/exhibits/downloadForAndroid";
}
