package com.nemestniy.admin.picturestream.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExampleResponse {
    @SerializedName("urls")
    @Expose
    public Urls urls;
}
