package cn.nrzt.cps.util;

import java.security.MessageDigest;
import java.util.UUID;
/**
 * 	密码、序列相关的方法
 * @author nrzt
 *
 */
public class CredentialUtil {

    /**
     * 进行MD5加密算法
     */
    public static String getMD5(String str) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            return null;
//         e.printStackTrace();
        }
        return sb.toString().toUpperCase();
    }
    /**
     * UUID 获取
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

}
