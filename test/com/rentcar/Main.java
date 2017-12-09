package com.rentcar;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

/**
 * Created by andrei on 07.12.17.
 */
public class Main {
@Test
public void date() throws ParseException {
    String date = "2017-05-01TTT 02:00".replaceAll("([T]+)", " ");
    System.out.println(date);
//    System.out.println(Instant.parse(date));
//    System.out.println(SimpleDateFormat.getInstance().parse(date));;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
    Date date2 = format.parse(date);
    System.out.println(date2);
}

    @Test
    public void testEncryption() throws NoSuchAlgorithmException {
        System.out.println(cryptPassword("1234567"));;
        System.out.println(cryptPassword("09090909"));;
    }

    private String cryptPassword(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return HexBin.encode(sha256.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
