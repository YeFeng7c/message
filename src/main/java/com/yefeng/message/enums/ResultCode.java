package com.yefeng.message.enums;

public enum ResultCode implements IFResult {
    DADA_EXCEPTION(3000, "数据异常"),
    USER_NAME_NULL(4001, "套你猴子的，把名字填上"),
    USER_MESSAGE_INFO(4002, "不填内容怎么留言"),
    USER_MESSAGE_SUCCESS(4000, "留言成功, easy peasy lemon squeeze"),
    MESSAGE_FIND_ALL(4003, "获取留言列表成功"),
    USER_MESSAGE_FAILED(4004, "不知道啥原因，留言失败"),
    REPLY_NAME(4100, "回复也要填名字"),
    REPLY_INFO(4101, "补全内容"),
    REPLY_SUCCESS(4102, "回复成功"),
    REPLY_FILED(4103, "莫名其妙就回复失败"),
    USER_NAME_NOT_NULL(4201, "姓名不能为空"),
    USER_EMAIL_NOT_NULL(4202, "邮箱不能为空"),
    USER_EMAIL_ERROR(4026, "邮箱格式不正确"),
    USER_EMAIL_EXIST(4207, "该邮箱已经注册"),
    USER_PASSWORD_NOT_NULL(4203, "密码不能为空"),
    USER_EMAIL_SEND_SUCCESS(4206, "邮件发送成功"),
    USER_REGIST_SUCCESS(4204, "注册成功"),
    USER_CODE_NULL(4028, "验证码不能为空"),
    USER__CODE_ERROR(4029, "验证码不正确"),
    USER_CODE_FAILED(4030, "验证码已过期"),
    USER_LOGIN_FILED(4205, "邮箱或密码错误"),
    USER_LOGIN_SUCCESS(4031, "登录成功"),
    USER_LOGOUT_SUCCESS(4300, "退出成功"),
    ARTICLES_FIND_SUCCESS(4041, "获取文章列表成功"),
    ARTICLES_FIND_INFO_SUCCESS(4042, "获取文章详情成功"),
    ARTICLES_NOT_EXIST(4043, "文章不存在"),
    ARTICLES_KINDS_SUCCESS(4050, "获取文章分类成功"),
    DOGTEXT_FIND_RANDOM_SUCCESS(4061, "随机获取日记成功"),
    DOGTEXT_TYPE_FIND_SUCCESS(4062, "获取所有类型成功"),
    LUNAR_GET_SUCCESS(4070, "获取日历成功"),
    LUNAR_YEAR_NOT_NULL(4071, "年份不能为空"),
    LUNAR_MONTH_NOT_NULL(4071, "月份不能为空"),
    LUNAR_DAY_NOT_NULL(4071, "出生日不能为空"),
    GET_PHONE_NUMBER_SUCCESS(4080, "获取手机号成功"),
    GET_LIUREN_SUCCESS(4090, "起卦成功"),
    GET_LIUREN_MONTH_ERROR(4091, "月份错误"),
    GET_LIUREN_DAY_ERROR(4092, "日期错误"),
    GET_LIUREN_RANDOM_ERROR(4093, "随机数错误"),
    GET_PINYIN_CONVERT_SUCCESS(5010, "转换成功"),
    GET_CONVERT_HTML_SUCCESS(5020, "转换成功")
    ;

    private final Integer code;
    private final String message;

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
