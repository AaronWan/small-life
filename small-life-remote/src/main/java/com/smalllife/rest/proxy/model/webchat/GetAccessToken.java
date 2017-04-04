package com.smalllife.rest.proxy.model.webchat;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by Aaron on 03/04/2017.
 */
public interface GetAccessToken {
    @Data
    class Result extends BaseResult{
        @SerializedName("access_token")
        private String accessToken;
        @SerializedName("expires_in")
        private String expiresIn;
    }

}
