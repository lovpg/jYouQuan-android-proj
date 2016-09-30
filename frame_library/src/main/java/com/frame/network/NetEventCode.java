package com.frame.network;

/**
 * 事件代号的集合
 * Created by LDM on 13-12-31. Email : nightkid-s@163.com
 */
public interface NetEventCode {

    /**请求数据失败*/
    int CODE_FAIL_REQUEST = -0x1001;
    /**解析json失败*/
    int CODE_FAIL_PARSE = -0x1002;
    /**没有该字段*/
    int CODE_NOT_EXIST_FIELD = -0x1003;
    /** 服务器成功处理结果*/
    int CODE_SUCCESS = 0;
    /**用户不存在*/
    int CODE_USER_NOT_EXIST = -1;
    /**密码错误*/
    int CODE_ERROR_PASSWORD = -2;
    /**缺少参数或者参数错误*/
    int CODE_ERROR_PARAM = -3;
    /**令牌/密钥错误*/
    int CODE_ERROR_TOKEN = -4;
    /**网络连接错误或者操作失败*/
    int CODE_FAIL_OPERATION = -5;
    /**账号已经登陆*/
    int CODE_ACCOUNT_LOGINED = 1003;
    /**账号会话状态失效*/
    int CODE_ACCOUNT_OFFLINE = 1000;

    /**该字段为空值的代替符号*/
    int CODE_NULL_CODE = -0x1009;

    int CODE_NULL_NETWORK = -400;
    
    /**非有效坐标*/
    double CODE_INVALID_LOCATION_X = 23.13836120631;
    double CODE_INVALID_LOCATION_Y = 113.35143102022;

    /**失败*/
    int CODE_FAIL = 0;
    /**没有该用户*/
    int CODE_NO_USER = 2;
    /**昵称重复*/
    int CODE_DUPLICATE_NICK_NAME = 3;
    /**重复绑定相同号码*/
    int CODE_DUPLICATE_BIND_PHONE = 3;
    /**数据有误*/
    int CODE_WRONG_DATA = 3;
    
    
    /**未绑定21cn账号**/
    int CODE_CN21_NOT_BINDING = 1005;
    /**已经绑定天翼账账号**/
    int CODE_CN21_BINDING = 1006;
    int RESULT_OK = 0;//天翼访问成功
    int RESULT_LOGN_NO = -17;//密码错误
    int RESULT_LOGN_NULL = -5;//密码为空
    int RESULT_DOES  = 51002;//账号不存在
    int RESULT_HAVE  = -65;//已经签到
    
    
    int UTYPE = 2;//家长，2教师
    
    String CODE_STRING_SUCCESS = "200";
    //密码错误
    String CODE_STRING_ERROR_PASSWORD = "WrongPasswordException";
}
