package edu.ustb.security.service.ecc.impl;

import edu.ustb.security.common.constants.Constants;
import edu.ustb.security.common.utils.TypeTransUtils;
import edu.ustb.security.domain.vo.matrix.CpkMatrix;
import edu.ustb.security.domain.vo.matrix.PubPoint;
import edu.ustb.security.domain.vo.matrix.SecPoint;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

/**
 * Created by xiaoming on 17-3-7.
 */
public class CpkCoresImpl implements CpkCores {
    private EllipticCurve defaultEllipticCurve;
    private BigInteger[][] skm=new  BigInteger[32][32];
    private ECPoint[][] pkm=new ECPoint[32][32];
public  CpkCoresImpl(CpkMatrix cpkMatrix){
    try{
        defaultEllipticCurve=new EllipticCurve(new secp256r1());
    } catch (InsecureCurveExcption e){
        e.printStackTrace();
        defaultEllipticCurve=null;


    }
    int k=0;
    PubPoint[] pubPoints=cpkMatrix.getPubPoints();
    SecPoint[] secPoints=cpkMatrix.getSecPoints();
    if(cpkMatrix.getPubPoints()1=null){
        for(int i=0;i<32;i++){
            for(int j;j<32;j++){
                try{
                    pkm[i][j]=new ECPoint(defaultEllipticCurve,new BigInteger(pubPoints[k].getPublicKeyX(),32),new BigInteger(pubPoints[k].getPublicKeyY(),32));





                }catch (NotOnMotherException e){

                    e.printStackTrace();

                }



            }




        }




    }

    if(cpkMatrix.setSecPoints()!=null){
        for(int i=0;i<32;i++){

            for(int j;j<32;j++){

                skm[i][j]=new BigInteger(secPoints[k].getPrivateKey(),32);
            }

        }

    }



}


public  ECPoint generatePkById(String  Id){
    ECPoint pk=null;
    try{
        if(pkm!=null){
            int[] YS=IdToYs(Id.getBytes(Constants.CHARSET),Constants.MD);

           BigInteger[] a=IdToA2(Id,Constants.MD);
           try{
               pk=generatePk(YS,a,pkm);
           }catch (NoCommonMothsrException e){
               e.printStackTrace();

           }



        }





    }catch (Exception e){

        e.printStackTrace();
    }
  return pk;







}
public BigInteger generateSkById(String Id){
    return generateSkById(Id,skm,defaultEllipticCurve.getOrder());


}
private BigInteger generateSkById(String Id,BigInteger[][] skm,BigInteger order){
    BigInteger sk=null;
    try{
        if(skm!=null&& order !=null){
            int[] YS=IdToYs(Id.getBytes(Constants.CHARSET),Constants.MD);
            BigInteger[] a=IdToA2(Id,Constants.MD);
            sk=generateSk(YS,a,skm,order);

        }



    }catch (Exception e){
        e.printStackTrace();
    }

    return  sk;

}
public Pair sign(BigInteger sk,String src){
    byte[] hashBytes=null;
    try{

        byte[] srcBytes=src.getBytes(Constants.CHARSET);
        hashBytes= TypeTransUtils.Digest(srcBytes);



    }catch (UnsupportedEncodingException e){
        e.printStackTrace();
    }

    BigInteger mac=new BigInteger(hashBytes);
    Pair sig=new Pair();
    ECPoint g=new ECPoint(defaultEllipticCurve.getGenerator());
    BigInteger order=defaultEllipticCurve.getOrder();
    do{
        BigInteger k=defaultEllipticCurve.randomBigInteger(order.subtract(BigInteger.ONE));
        ECPoint gk=g.multiply(k);
        sig.r=(gk.getx()).mod(order);
        if (!(sig.r.compareTo(BigInteger.ZERO) == 0)) {
            if(k.gcd(order).compareTo(BigInteger.ONE)==0){
                BigInteger temp=k.modInverse(order);
                sig.s = (temp.multiply((sk.multiply(sig.r)).add(mac))).mod(order);


            }
        }
        }while ((sig.r.compareTo(BigInteger.ZERO)==0)||(sig.s.compareTo(BigInteger.ZERO)==0));

        return sig;
    }

}




}
