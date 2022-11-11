package com.solncev.net.util;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map configMap = new HashMap();
            configMap.put("cloud_name", "test");
            configMap.put("api_key", "test");
            configMap.put("api_secret", "test");
            cloudinary = new Cloudinary(configMap);
        }

        return cloudinary;
    }
}
