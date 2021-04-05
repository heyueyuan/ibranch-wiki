package cn.heyueyuan.ibranchwiki.exception;
public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("Username Name already existed"),
    LOGIN_USER_ERROR("username or password wrong"),
    VOTE_REPEAT("Voted"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
