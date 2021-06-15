package kai.sample.controller.dto;

import kai.sample.common.ResponseCode;
import org.springframework.util.StringUtils;

public class BasicReponse {

    private int code;
    private String message;

    public BasicReponse() {
        this(null, null);
    }

    public BasicReponse(ResponseCode rsCode, String message) {
        if (rsCode == null) {
            rsCode = ResponseCode.SUCCESS;
        }

        this.code = rsCode.getCode();
        if (StringUtils.isEmpty(message)) {
            this.message = rsCode.name();
        } else {
            this.message = message;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
