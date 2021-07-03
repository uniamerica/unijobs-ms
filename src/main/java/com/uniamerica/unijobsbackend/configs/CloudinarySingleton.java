package com.uniamerica.unijobsbackend.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;

public class CloudinarySingleton {
    private static Cloudinary cloudinary;

    public static void registerCloudinary(Cloudinary cloudinary) {
        CloudinarySingleton.cloudinary = cloudinary;
    }

    public static void deregisterCloudinary() {
        cloudinary = null;
    }

    private static class DefaultCloudinaryHolder {
        @Value("${cloudinary_api_key}")
        private static String cloudinaryApiKey;

        @Value("${cloudinary_api_secret}")
        private static String cloudinaryApiSecret;

        @Value("${cloudinary_cloud_name}")
        private static String cloudinaryCloudName;

        public static final Cloudinary INSTANCE = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "unijobs",
            "api_key", "372469738554692",
            "api_secret", "keFXO_iU4lOUD80aa2JoBO_C4O8")
        );
    }

    public static Cloudinary getCloudinary() {
        if (cloudinary == null) {
            return DefaultCloudinaryHolder.INSTANCE;
        }
        return cloudinary;
    }
}
