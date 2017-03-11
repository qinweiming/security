package edu.ustb.security.common.utils;

import edu.ustb.security.common.constants.Constants;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xiaoming on 17-3-3.
 */
public class TypeTransUtils {

    public static String Digest(String strSrc,String MD){
        MessageDigest  md=null;

        String  strDes=null;
        byte[] bt=strSrc.getBytes();
        try {
            md=MessageDigest.getInstance(MD);
            md.update(bt);
            strDes=bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return strDes;


    }

    public static byte[] Digest(byte[] srcBytes){
        byte[]   hashBytes=null;
       try{ hashBytes=Digest(srcBytes, Constants.MD);}
       catch ( NoSuchAlgorithmException e){

           e.printStackTrace();
       }
        return hashBytes;

    }
public  static byte[] Digest(byte[] srcByets,String MD) throws  NoSuchAlgorithmException{
        MessageDigest md=null;
        byte[] hashBytes=null;
        md=MessageDigest.getInstance(MD);
        md.update(srcByets);
        hashBytes=md.digest();
        return hashBytes;





}
public static byte[] Digest(InputStream inputStream) throws  Exception{
        return  Digest(inputStream,Constants.MD);



}

public static  byte[] Digest(InputStream  inputStream,String MD) throws Exception{

        byte[] buffer=new byte[1024];
        MessageDigest complete=MessageDigest.getInstance(MD);
        int numRead;
        do{
            numRead=inputStream.read(buffer);
            if(numRead>0){
                complete.update(buffer,0,numRead);
            } while(numRead !=-1);
            inputStream.close();
            return complete.digest();



        }




}

public static  byte[] intToBytes(int value){
        byte[] byte_src=new  byte[4];
        byte_src[3]=(byte) ((value & 0xFF000000)>>24);
        byte_src[2]=(byte)((value& 0x00FF0000)>>16);
        byte_src[1]=(byte)((value &0x0000FF00)>>8);
        byte_src[0]=(byte)((value & 0x000000FF));
        return byte_src;


}
public static int bytesToInt(byte[] ary,int offset){
    int value;
    value=(int)((ary[offset]&0xFF)|((ary[offset+1]<<8)&0xFF00)|((ary[offset+2]<<16)&0xFF0000)|((ary[offset+3]<<24)&0xFF000000));
    return  value;



}

public static String BytesToString(byte[] bytes){
    String values=null;
    try {
        values =new String(bytes,Constants.CHARSET);
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
return values;


}

public static String binary(byte[] bytes,int redix){
    return new BigInteger(1,bytes).toString(redix);

}

public static  String bytesHex(byte[] bts){
    String des="";
    String tmp=null;
    for(int i=0;i<bts.length:i++){
        tmp=(Integer.toHexString(bts[i]&0xFF));
        if(tmp.length()==1){

            des+="0";

        }
        des+=tmp;


    }
    return des;



}

public static String hexString2binaryString(String hexString){
    if(hexString==null||hexString.length()%2!=0)
        return null;
    String bString="",tmp;
    for(int i=0;i<hexString.length();i++){
        tmp="0000"+Integer.toBinaryString(Integer.parseInt(hexString.substring(i,i+1),16));
        bString+=tmp.substring(tmp.length()-4);


    }
    return bString;


}
}
