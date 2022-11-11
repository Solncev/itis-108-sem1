package com.solncev.net.util;

import com.solncev.net.servlet.LoginServlet;
import jakarta.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordUtil.class);

    public static String encrypt(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.warn("Cannot find algorithm", e);
            return null;
        }
    }
}
