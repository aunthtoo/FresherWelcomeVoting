package com.aunthtoo.welcomevoting.stringdecryptandencrypt;

/**
 * Created by Aunt Htoo on 12/28/2016.
 */

public class EncryptAndDecrypt {

    public EncryptAndDecrypt() {

    }

    public static String encryptString(String str, String key) {

        String encryptStr ;
        char[] returnStr = new char[str.length()];


        if (key.length() >= str.length()) {

            for (int i = 0; i < str.length(); i++) {

                char character = (char) ((int) str.charAt(i) + (key.charAt(i) - 'A'));
                returnStr[i] = character;


            }
            encryptStr = String.valueOf(returnStr);
        } else {

            int con = 0;

            for (int i = 0; i < str.length(); i++) {
                if (con == key.length()) {

                    con = 0;
                }

                char character = (char) ((int) str.charAt(i) + (key.charAt(con) - 'A'));
                returnStr[i] = character;
                con++;

            }

            encryptStr = String.valueOf(returnStr);
        }


        return encryptStr;

    }

    public static String decryptString(String str,String key)
    {

        String encryptStr ;
        char[] returnStr = new char[str.length()];

        if (key.length() >= str.length()) {

            for (int i = 0; i < str.length(); i++) {

                char character = (char) ((int) str.charAt(i) - (key.charAt(i) - 'A'));
                returnStr[i] = character;


            }
            encryptStr = String.valueOf(returnStr);

        }
        else
        {

            int con = 0;

            for (int i = 0; i < str.length(); i++) {
                if (con == key.length()) {

                    con = 0;
                }

                char character = (char) ((int) str.charAt(i) - (key.charAt(con) - 'A'));
                returnStr[i] = character;
                con++;

            }

            encryptStr = String.valueOf(returnStr);
        }

        return encryptStr;

    }


}
