package vnpt.cmms.controller.login.dto;

public enum LoginMessage {
    SUCCESS(0, "Success"), FAIL(1, "Invalid client code or secret");

    private Integer code;
    private String message;

    private LoginMessage(int code, String mess) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
