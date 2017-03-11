package edu.ustb.security.domain.vo.ecc;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.spec.EllipticCurve;

/**
 * Created by xiaoming on 17-3-5.
 */
public class ECPoint implements Serializable {
    public final static BigInteger TWO=new BigInteger("2");
    public  final static  BigInteger THREE=new BigInteger("3");
    private EllipticCurve mother;
    private  BigInteger x,y;
    private  boolean iszero;
    private ECPoint[] fastcache=null;
    private ECPoint[]  cache=null;
     public void fastCache(){
         try {
             if (fastcache == null) {
                 fastcache = new ECPoint[256];
                 fastcache[0] = new ECPoint(mother);
                 for (int i = 1; i < fastcache.length; i++) {
                     fastcache[i] = fastcache[i - 1].add(this);

                 }


             }

         }catch(NoCommonMotherException e){
             System.out.println("ECPoint.fastcache:THIS CANNOT HAPPEN!!!");



         }


     }


     public ECPoint(EllipticCurve mother,BigInteger x,BigInteger y) throws  NotOnMotherException{
         this.mother=mother;
         this.x=x;
         this.y=y;

         iszero=false;





     }

    public  ECPoint(ECPoint q){
         this.x=q.x;
         this.y=q.y;
         this.mother=q.mother;



    }
    public  ECPoint(byte[] bytes,EllipticCurve mother){
        this.mother=mother;
        if(bytes[0]==2){
            iszero=true;
            return;




        }
        boolean ymt=false;
        if(bytes[0]!=0)ymt=true;
        bytes[0]=0;
        x=new BigInteger(bytes);
        if(mother.getPPODBF()==null)System.out.println("Fuck fig!!");

        y=x.multiply(x).add(mother.getA()).multiply(x).add(mother.getb()).modPow(mother.getPODBF(),mother.getp());
        if(ymt!=y.testBit(0)){
            y=mother.getp().subtract(y);




        }
        iszero=false;






    }



    public ECPoint(EllipticCurve e){
        x=y=BigInteger.ZERO;
        mother=e;
        iszero=true;




    }

    public byte[] compress(){
        byte[] cmp=new   byte[mother.getPCS()];
        if(iszero){
            cmp[0]=2;


        }

        byte[] xb=x.toByteArray();
        System.arraycopy(xb,0,cmp,mother.getPCS()-xb.length,xb.length);

        if(y.testBit(0))cmp[0]=1;
        return cmp;








    }

    public ECPoint add(ECPoint q) throws  NoCommonMotherException{
        if(!hasCommonMother(q)) throw new NoCommonMotherException();
        if(this.iszero) return q;
        else  if(q.iszero()) return this;
        BigInteger y1=y;
        BigInteger  y2=q.gety();
        BigInteger  x1=x;
        BigInteger  x2=q.getx();
        BigInteger  alpha;
        if (x2.compareTo(x1)==0){
            if(!(y2.compareTo(y1)==0))return  new ECPoint(mother);
            else {

                alpha=((x1.modPow(TWO,mother.getp())).multiply(THREE)).add(mother.geta());
                alpha=(alpha.multiply((TWO.multiply(y1)).modInverse(mother.getp()))).mod(mother.getp());




            } else {
                alpha=((y2.subtract(y1)).multiply((x2.subtract(x1)).modInverse(mother.getp()))).mod(mother.getp());


            }

            BigInteger x3,y3;
            x3=(((alpha.modPow(TWO,mother.getp())).subtract(x2)).subtract(x1)).mod(mother.getp());
            y3=((alpha.multiply(x1.subtract(x3))).subtract(y1)).mod(mother.getp());
            try{
                return  new ECPoint(mother,x3,y3);

            }catch (NotOnMotherException e){

                System.out.println("Error in add!!Result not on mother");
                return null;


            }


        }





    }

    public  ECPoint subtract(ECPoint q) throws  NoCommonMotherException{
              if(!hasCommonMother(q)) throw new NoCommonMotherException();
              if(this.iszero) return q;
              else  if(q.isZero()) return this;

              BigInteger y1=y;
              BigInteger  y2=new BigInteger("0").subtract(q.gety());
              BigInteger x1=x;
              BigInteger x2=q.getx();
              BigInteger  alpha;

              if(x2.compareTo(x1)==0){
                  if(!(y2.compareTo(y1)==0)) return  new ECPoint(mother);
                  else {
                      alpha = ((x1.modPow(TWO, mother.getp())).multiply(THREE)).add(mother.geta());
                      alpha = (alpha.multiply((TWO.multiply(y1)).modInverse(mother.getp()))).mod(mother.getp());


                  }



              }else {
                  alpha = ((y2.subtract(y1)).multiply((x2.subtract(x1)).modInverse(mother.getp()))).mod(mother.getp());


              }

        BigInteger  x3,y3;
              x3=(((alpha.modPow(TWO,mother.getp())).subtract(x2)).subtract(x1)).mod(mother.getp());
        y3 = ((alpha.multiply(x1.subtract(x3))).subtract(y1)).mod(mother.getp());

        try {
            return new ECPoint(mother, x3, y3);
        } catch (NotOnMotherException e) {
            System.out.println("Error in add!!! Result not on mother!");
            return null;
        }

    }

    public ECPoint multiply(BigInteger coef){
        try{

            ECPoint result =new ECPoint(mother);
            byte[] coefb=coef.toByteArray();
            if(fastcache!=null){
                for(int i=0;i<coefb.length;i++){
                    result=result.times256().add(fastcache[coefb[i]&255]);


                }

                return  result;


            }
            if(cache==null){
                cache=new ECPoint[16];
                cache[0]=new ECPoint(mother);
                for(int i=1; i<cache.length;i++){
                    cache[i]=cache[i-1].add(this);




                }


            }

            for(int i=0; i<coefb.length;i++){
                result=result.times16().add(cache[(coefb[i]>>4)&15]).times16().add(cache[coefb[i]&15]);



            }return result;


        } catch (NoCommonMotherException e){
            System.out.println("Error in pow");
            return null;
        }






    }


    private ECPoint  times16(){
        try{
            ECPoint result=this;
            for(int i=0;i<4;i++){
                result =result.add(result);




            }
            return result;




        }catch (Exception e){
            System.out.println("Err");
            return  null;



        }



    }

    private  ECPoint times256(){
        try{
            ECPoint result=this;
            for(int i=0;i<8;i++){
                result=result.add(result);



            }
            return  result;


        }catch (Exception e) {
            System.out.println("ECPoint.times256: THIS CANNOT HAPPEN!!!");
            return null;
        }




    }
    public BigInteger getX(){
        return x;



    }

    public BigInteger gety(){

        return  y;



    }

    public EllipticCurve getMother(){

        return mother;


    }

    public String toString(){
        return "(" + x.toString() + ", " + y.toString() + ")";


    }

    public  boolean  hasCommonMother(ECPoint){
        if (this.mother.geta().equals(p.getMother().geta()) && this.mother.getb().equals(p.getMother().getb()) && this.mother.getp().equals(p.getMother().getp())) {
            return true;
        } else {
            System.out.println("the different mother");
            return false;
        }



    }
    public boolean isZero() {
        return iszero;
    }


}
