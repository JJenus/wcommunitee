package com.predictz.predictz.util;

import org.apache.tomcat.util.buf.HexUtils;

import java.util.Base64;

public class Base64Converter {
    private static String convertBase64ToHex(String base64){
        byte[] decoded = Base64.getDecoder().decode(base64);

        return HexUtils.toHexString(decoded).toUpperCase();
    }

    public static String convertHexToBase64(String str){
        byte[] bytes = HexUtils.fromHexString(str);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static void main(String[] args) {
        //  String base64input = "IdRdAqgAAAAAWwAAAAA=";
        String base64input = "DH4BAAAEAAAAAA==";

        System.out.println("Base64 String: "+base64input);
        String hexString = convertBase64ToHex(base64input);
        System.out.println("To hex: "+hexString);

        //Test convert to Base64
        String base64Output = convertHexToBase64(hexString);
        System.out.println("To base64: "+base64Output);

        //  if ("21D45D02A8000000005B00000000".length() == hexString.length()){
        //       System.out.println(hexString);
        //  }else{
        //     System.out.println("wrong -" + hexString);
        // }
    }
}
