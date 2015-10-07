/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.youtubeinteg;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sunil
 */
public class Authentication {

   final static HttpTransport TRANSPORT = new NetHttpTransport();
   final static JsonFactory JSON_FACTORY = new JacksonFactory();
    
   com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient h;
   public static void main(String []g) throws Exception
   {
      List<String>scops = new <String>ArrayList();
      scops.add("https://www.googleapis.com/auth/youtube");
   
      GoogleCredential credential = null;
      try
      {
         credential = new GoogleCredential.Builder()
        .setTransport(TRANSPORT)
        .setJsonFactory(JSON_FACTORY)
        .setServiceAccountId(Constants.SERVICE_ACCOUNT_ID)
        .setServiceAccountScopes(scops)
        .setServiceAccountPrivateKeyFromP12File(new File("/home/sunil/NetBeansProjects/GoogleAuthTest/src/googleauthtest/key.p12"))
        //.setClientSecrets("74279030240-db333th3oph219blk19kkh540m0gu4f5.apps.googleusercontent.com","")
        //.setClientSecrets(GoogleClientSecrets.load(JSON_FACTORY,new InputStreamReader(java.io.Reader.class.getResourceAsStream("client_secrets.json"))))
        .build();

        credential.refreshToken();
        System.out.println(credential.getAccessToken());
        System.out.println(credential.getTokenServerEncodedUrl());
        System.out.println(credential.getExpiresInSeconds());
        System.out.println(credential.getServiceAccountPrivateKey().toString());
        //credential.getServiceAccountPrivateKey();

        credential = new GoogleCredential().setAccessToken(credential.getAccessToken());
        YouTube youtube = new YouTube.Builder(TRANSPORT, JSON_FACTORY, credential).setApplicationName("").build();
//YouTube youtube = new YouTube.Builder();
      }
      catch(IOException e)
      {
          System.out.println(e.toString());
      }
      /*
      YouTube youtube =  new YouTube.Builder(TRANSPORT, JSON_FACTORY,credential).setApplicationName("gleaming-scene-655").build();
      
      Activities act = youtube.activities();
      Activities.List list = act.list("s");
       */
   }
}
