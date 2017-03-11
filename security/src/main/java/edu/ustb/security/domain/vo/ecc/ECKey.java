package edu.ustb.security.domain.vo.ecc;

import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

/**
 * Created by xiaoming on 17-3-5.
 */
public class ECKey implements Key {
    public boolean secret;
    private BigInteger sk;
    private BigInteger pk;
    public ECPoint  beta;
    protected EllipticCurve mother;

    public BigInteger getPk(){

        return  sk;

    }
  public ECPoint getPk(){
        return beta;




  }

    public  ECKey(EllipticCurve ec){
      mother ec;
      secret=true;
      BigInteger temp;
      boolean range;
      boolean  odd;
      do{
          sk=new BigInteger(ec.getp().bitLength()+17,new SecureRandom());

          if(mother.getOrder()!=null)
              sk=sk.mod(mother.getOrder());
          temp =sk.multiply(new BigInteger("32"));
          range=(temp.compareTo(mother.getOrder()))==1;
          odd=((sk.mod(new BigInteger("2"))).compareTo(BigInteger.ZERO))==1;





      }while ((!range)||(!odd));
      beta=(mother.getGenerator()).multiply(sk);
      beta.fastCache();





    }

    public ECKey(EllipticCurve ec,BigInteger skin){
        mother=ec;
        secret=true;
        sk=skin;
        if(mother.getOrder()!=null)
            sk=sk.mod(mother.getOrder());
        beta=(mother.getGenerator()).multiply(sk);
        beta.fastCache();




    }


    public  ECKey ManualECKey(EllipticCurve ec,BigInteger skin){

        mother=ec;
        secret =true;
        sk=new BigInteger(ec.getp().bitLength()+17,new SecureRandom());
        if(mother.getOrder()!=null)
            sk=sk.mod(mother.getOrder());
        beta=(mother.getGenerator()).multiply(sk);
        beta.fastCache();
        return null;


    }

    public String toString(){
        if(secret)return("secret key"+sk+""+beta+""+mother);
        else return("public key"+beta+""+mother);


    }
  public  boolean isPublic(){
        return(!secret);


  }

   public void writeKey(OutputStream out) throws IOException{
       DataOutputStream output=new DataOutputStream(out);
       mother.writeCurve(output);
       output.writeBoolean(secret);
       if(secret){
           byte[] skb=sk.toByteArray();
           output.writeInt(skb.length);
           output.write(skb);

       }
       byte[] betab=beta.compress();
       output.writeInt(betab.length);
       output.write(betab)

   }

   public Key readkey(InputStream in) throws IOException{
        DataInputStream input =new DataInputStream(in);
        ECKey k=new ECKey(new EllipticCurve(input));
        k.secret=input.readBoolean();
        if(k.secret){
            byte[] skb=new byte[input.readInt()];
            input.read(skb);
            k.sk=new BigInteger(skb);



        }
        byte[] betab=new byte[input.readInt()];
        input.read(betab);
        k.betab=new ECPoint(betab,k.mother);
        return k;


    }

   public Key getPublic(){
      Key temp=new ECKey( mother);
       ((ECKey)temp).beta=beta;
       ((ECKey) temp).sk=BigInteger.ZERO;
       ((ECKey)temp).secret=false;
       System.gc();;
       return  temp;


   }
   public  Key getManualPublic(){
       Key temp=new ECKey(mother);
       ((ECKey)temp).beta=beta;
       ((ECKey)temp).sk=BigInteger.ZERO;
       ((ECKey)temp).secret=false;
       System.gc();
       return temp;





   }

   public Key getPublicKeyByPoint(ECPoint pointpub){
       Key  temp=new ECKey(mother);
       ((ECKey)temp).beta=pointpub;
       ((ECKey)temp).sk=BigInteger.ZERO;
       ((ECKey)temp).secret=false;
       System.gc();
       return temp;





   }

}
