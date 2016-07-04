package com.javarush.test.volpis;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;
import facebook4j.Facebook;
import facebook4j.Reading;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthSupport;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SocialNetwork
{

    static final String FB_APP_ID = "230959493943430";
    static final String FB_APP_SECRET = "f4711c225d44bbd6bf6ef9490c4189af";
    static final String TW_APP_ID = "wCeAHC9OQU0zYINy4Tz2mTJ9r";
    static final String TW_APP_SECRET = "AqogO48TyMNAG76bEujdoK4RgNIjLeP63fANHISZHw9QZEFpC5";

    public static void twitterAuth() throws IOException, TwitterException
    {
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(TW_APP_ID, TW_APP_SECRET);
        RequestToken requestToken = twitter.getOAuthRequestToken();
        System.out.println(twitter.users());
        System.out.println(requestToken.getToken());
        System.out.println(requestToken.getAuthorizationURL());
        twitter4j.auth.AccessToken accessToken = null;
        //accessToken = twitter.getOAuthAccessToken(requestToken.getToken());
        //System.out.println(accessToken);
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        *//*while (null == accessToken)
        {
            System.out.println("Open the following URL and grant access to your account:");
            System.out.println(requestToken.getAuthorizationURL());
            System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
            String pin = br.readLine();

        }*//*
        System.out.println(accessToken);*/
    }

    public static void facebookAuth() throws FacebookException
    {

        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(FB_APP_ID, FB_APP_SECRET);

        Configuration configuration = createConfiguration();
        AccessToken accessToken;
        OAuthSupport oAuthSupport = new OAuthAuthorization(configuration);
        accessToken = oAuthSupport.getOAuthAppAccessToken();
        String token = accessToken.getToken();
//        System.out.println(token);
//        FacebookClient facebookClient = new DefaultFacebookClient("EAADSDnKYEIYBAPSgrDyuORUzKXMSHBvDhFZBuZCEera88R3IzbrcNsTOwfS4XgVYMOzf8QGcERnKPavv4V4ZAyESDTeWY4sZB7yKP24R31fWHBn6hzH47gcyDiLxFz6cTuc3oZABJsdVZB41lvOggebH5wUnsUIK6Ee9Kppwc0UQZDZD");
        FacebookClient facebookClient = new DefaultFacebookClient("EAADSDnKYEIYBAPSgrDyuORUzKXMSHBvDhFZBuZCEera88R3IzbrcNsTOwfS4XgVYMOzf8QGcERnKPavv4V4ZAyESDTeWY4sZB7yKP24R31fWHBn6hzH47gcyDiLxFz6cTuc3oZABJsdVZB41lvOggebH5wUnsUIK6Ee9Kppwc0UQZDZD");
        User me = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "email,first_name,last_name,gender"));
        String email = me.getName();
        System.out.println(email);
//        String result= facebookClient.fetchObject("email", String.class);

//        String userId = facebook.getId();
        Reading reading = new Reading().fields("email");
        //facebook4j.User user = facebook.getUser(userId, reading);
        //System.out.println(user.getEmail());

    }

    public static void main(String[] args) throws FacebookException, IOException, TwitterException
    {
        facebookAuth();
        //twitterAuth();
    }

    public static Configuration createConfiguration()
    {
        ConfigurationBuilder confBuilder = new ConfigurationBuilder();

        confBuilder.setDebugEnabled(true);
        confBuilder.setOAuthAppId(FB_APP_ID);
        confBuilder.setOAuthAppSecret(FB_APP_SECRET);
        confBuilder.setUseSSL(true);
        confBuilder.setJSONStoreEnabled(true);


        Configuration configuration = confBuilder.build();
        return configuration;
    }
}
