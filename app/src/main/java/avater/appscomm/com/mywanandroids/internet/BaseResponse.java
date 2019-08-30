package avater.appscomm.com.mywanandroids.internet;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private static int SUCCESS_CODE = 0;//成功的code
    private static final long serialVersionUID = 5213230387175987834L;

    public int errorCode;
    public String errorMsg;
    public T data;

    public static int getSuccessCode() {
        return SUCCESS_CODE;
    }

    public static void setSuccessCode(int successCode) {
        SUCCESS_CODE = successCode;
    }


    public int getCode() {
        return errorCode;
    }

    public void setCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return errorMsg;
    }

    public void setMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return getCode() == SUCCESS_CODE;
    }
}
