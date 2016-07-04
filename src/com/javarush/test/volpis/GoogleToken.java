package com.javarush.test.volpis;


import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.util.ArrayList;

public class GoogleToken
{
    private static final Base64 decoder = new Base64(true);

    public static String decodeAndParse(String token64String, String token64String1) throws IOException
    {
        //byte[] byteArray = Base64.decodeBase64(token64String.getBytes());
        //String decodedToken = new String(byteArray);

        String[] pieces = token64String.split("\\.");
        String[] pieces1 = token64String1.split("\\.");

        ArrayList<String> tokenPieces = new ArrayList<>();
        for (int i = 0; i < pieces.length; i++) {
            byte[] pieces0 = Base64.decodeBase64(pieces[i]);
            String decodedToken = new String(pieces0);
            tokenPieces.add(decodedToken);
        }
        ArrayList<String> tokenPieces1 = new ArrayList<>();
        for (int i = 0; i < pieces1.length; i++) {
            byte[] pieces0 = Base64.decodeBase64(pieces1[i]);
            String decodedToken = new String(pieces0);
            tokenPieces1.add(decodedToken);
        }

        byte[] pieces0 = Base64.decodeBase64(pieces[0]);
        String decodedToken0 = new String(pieces0);

        String jsonString = new String(decoder.decode(token64String), "UTF-8");

        return jsonString;
    }
    public static void main(String[] args) throws IOException
    {

        System.out.println(decodeAndParse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ",
                "eyJhbGciOiJSUzI1NiIsImtpZCI6IjU0MmMxYTkyZDRmNDRmN2NjMGU4NDJkYjkzZGY5MjAzYzQ4ZDU1MWMifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhdWQiOiI2NzU4NjM2MzI3OTktdWh0ajBnMWphbWFzaXEybjY4dmVnM3RmMmNwb2c5MWIuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTEwOTUwMzIwMTMxNjk4ODcwNjUiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXpwIjoiNjc1ODYzNjMyNzk5LXBidTFwYmJxZHVtb3ZhZGpyMWx2cGJhcDBiY2d0czFvLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiZW1haWwiOiJrb3N0eWFraHV0YUBnbWFpbC5jb20iLCJpYXQiOjE0NDgzODA2ODIsImV4cCI6MTQ0ODM4NDI4MiwicGljdHVyZSI6Imh0dHBzOi8vbGg1Lmdvb2dsZXVzZXJjb250ZW50LmNvbS8teTVPeUc2ZTNOQkEvQUFBQUFBQUFBQUkvQUFBQUFBQUFBRkUvbi1FM0s3YjYtRkUvczk2LWMvcGhvdG8uanBnIiwibG9jYWxlIjoicnUiLCJuYW1lIjoiS29zdHlhIEtodXRhIiwiZ2l2ZW5fbmFtZSI6Iktvc3R5YSIsImZhbWlseV9uYW1lIjoiS2h1dGEifQ.bf-u-RYZNHaeX_rb-TUBeIydZMZqWUwuhBe_7baHYYkge9qQ0EA5EyuVV1_-gdnaUltZs3w1WsTHSK-We3RsE23NprokE1tFxkKDp3wDa0-f9rWtIONvXVas5p6tsXuGMYdQcwbolpmB984HJv8BYEoIZj9jC7ag43pPRWcg9Bpgbnl7JjQl3BcmJLhMu0VcufBTiD5PPQiZ51wckrXl3IIhuGNIRxIbEhY5RBxkRDFLy41JcwJLNC1ow5YnjbKxrJGrlsKHLzSbX4Gna7R14U8DrucIZt65kHXyK1FfA1b1KeuSwCL6SM3wZkn_90oDXwSYjH_Dfpjg1DyuzKg0NQ"));
    }
}
