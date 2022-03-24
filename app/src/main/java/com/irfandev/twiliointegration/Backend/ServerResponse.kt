package com.irfandev.twiliointegration.Backend

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
/**
 *Irfan Elahi Dev
 */
class ServerResponse {
    @SerializedName("errNum")
    @Expose
    var errNum: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("code")
    @Expose
    var code: String? = null
}