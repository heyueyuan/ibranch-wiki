package cn.heyueyuan.ibranchwiki.response;

public class CommonResp<T> {

    private boolean success = true;

    private String message;

    private T content;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer("ResponseDto{");
        stringBuffer.append("success=").append(success);
        stringBuffer.append(", message='").append(message).append('\'');
        stringBuffer.append(", content=").append(content);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
