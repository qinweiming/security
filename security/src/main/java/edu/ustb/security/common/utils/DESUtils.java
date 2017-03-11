package edu.ustb.security.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

import static javax.crypto.SecretKeyFactory.*;
import static sun.security.x509.X509CertImpl.byte2hex;

/**
 * Created by xiaoming on 17-3-3.
 */
public class DESUtils  {

    /**
     * 加密，解密key
     */
private  static final String PASSWORD_CRYPT_KEY = "kEHrDooxWHCWtfeSxvDvgqZq";
    /**
     * 加密算法，可用DES，DESede,Blowfish.
     */

   private  final static  String ALGORITHM="DES";

    /**
     * 对数据进行des加密
     */

    public  final static  String  decrypt(String data) throws Exception{
        return new String(decrypt(hex2byte(data.getBytes()),
                PASSWORD_CRYPT_KEY.getBytes()));
    }
    public  final static  String  encrypt(String data) throws Exception {
        return byte2hex(encrypt(data.getBytes(),PASSWORD_CRYPT_KEY.getBytes()));



    }
    private static byte[] encrypt(byte[] data,byte[] key) {
        SecureRandom sr=new SecureRandom();
        DESKeySpec  dks=new DESKeySpec(key);

        SecretKeyFactory keyFactory=SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey=keyFactory.generateSecret(dks);
        Cipher cipher=Cipher.getInstance(ALGORITHM);

        cipher.init(Cipher.DECRYPT_MODE,securekey,sr);
        return cipher.doFinal(data);







    }

    private static  byte[] decrypt(byte[] data,byte[] key) throws Exception {
               SecureRandom  sr=new SecureRandom();

               DESKeySpec dks=new DESKeySpec(key);

               SecretKeyFactory    keyFactory=SecretKeyFactory.getInstance(ALGORITHM);

               SecretKey secretKey=keyFactory.generateSecret(dks);

               Cipher cipher=Cipher.getInstance(ALGORITHM);

               cipher.init(Cipher.DECRYPT_MODE,securekey,sr);

               return  cipher.doFinal(data);
    }


    public  static  byte[]   hex2byte(byte[]  b){


        if( (b.length %2)!=0)
           throw  new  IllegalArgumentException("长度不是偶数 ");
        byte[] b2=new byte[b.length/2];
        for(int n=0;n<b.length;n+=2){
            String  item=new String(b,n,2);
            b2[n/2]=(byte) Integer.parseInt(item,16);

        }
       return b2;
    }

    public static  String byte2hex(byte[] b){
        String hs="";
        String  stmp="";
        for(int n=0;n<b.length;n++){
            stmp=(Integer.toHexString(b[n]&0XFF));
            if(stmp.length()==1)
                hs=hs+"0"+stmp;
            else hs=hs+stmp;



        }
        return hs.toUpperCase();


    }

   public  static byte[] subBytes(byte[] src,int begin,int count){
        byte[] bs=new byte[count];
        for(int i=begin;i<begin+count;i++) bs[i-begin]=src[i];
        return bs;


   }

   public static  void main(String[] args){

       String src="Des src String";
       String  digest =TypeTransUtils.Digest(src,"SHA-256");
       System.out.println("Message digst \n" +digest);
       try{
           byte[] encrypt=subBytes(encrypt(digest.getBytes(),src.getBytes()),0,64);
           System.out.println(encrypt.length+"encrypt:\n"+byte2hex(encrypt));
           byte[] encrypt1=subBytes(encrypt(encrypt,src.getBytes()),0,64);

           System.out.println("-----------");
           System.out.println(encrypt1.length+"encryptl:\n"+byte2hex(encrypt1));


       } catch (Exception e){
           e.printStackTrace();
       }
   }

}
