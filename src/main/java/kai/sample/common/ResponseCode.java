package kai.sample.common;

public enum ResponseCode {

    FAIL(0, "失敗"),
    SUCCESS(1, "成功"),
    SYSTEM_ERROR(2, "系統錯誤"),

    ;

    private final int code;
    private final String defaultMsg;

    ResponseCode(int code, String defaultMsg) {
        this.code = code;
        this.defaultMsg = defaultMsg;
    }

    public int getCode() {
        return code;
    }

    public String getDefaultMsg() {
        return defaultMsg;
    }
}
