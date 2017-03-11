package edu.ustb.security.domain.vo.ecc.elliptic;

import edu.ustb.security.domain.vo.ecc.Pair;
import sun.nio.cs.ext.Big5_HKSCS;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.Random;

/**
 * Created by xiaoming on 17-3-9.
 */
public class EllipticCurve {
    private BigInteger a,b,p,order;
    private ECPoint generator;
    private BigInteger ppodbf;
    private int pointcmpsize;
    private String name;
    public static  final  BigInteger COEFA=new BigInteger("4");
    public  static  final  BigInteger COEFB=new BigInteger("27");
    public  static  final int PRIMESECURITY=500;


    public EllipticCurve(BigInteger a, BigInteger b,BigInteger p)   {

        this.a=a;
        this.b=b;
        this.p=p;
        if(!p.isProbablePrime(PRIMESECURITY)){





        }
if (isSingular()) throw new InsecureCurveException(InsecureCurveException.SINGULAR,this);
        byte[] pb=p.toByteArray();
        if (pb[0]==0) pointcmpsize=pb.length;
        else  pointcmpsize=pb.length+1;
        name="";





    }

public EllipticCurve(ECParameters ecp){
        this(ecp.a(),ecp.b(),ecp.p());
        order=ecp.order();
        name=ecp.toString();
        try{
            generator =new ECPoint(this,ecp.generatorX(),ecp.generatorY());
            generator.fastCache();




        } catch ( NotOnMotherException e){
            System.out.println();


        }



}

public void writeCurve(DataInputStream output) throws IOException{
    byte[] ab=a.toByteArray();
    output.writeInt(ab.length);
    output.write(ab);
    byte[] bb=b.toByteArray();
    output.writeInt(bb.length);
    output.write(bb);
    byte[] pb=p.toByteArray();
    output.writeInt(pb.length);
    output.write(pb);
    byte[] ob=order.toByteArray();
    output.writeInt(ob.length);
    output.write(ob);
    byte[] gb=generator.compress();
    output.writeint(gb.length);
    output.write(gb);
    byte[] ppb=getPPODBF().toByteArray();
    output.writeInt(ppb.length);
    output.write(ppb);
    output.writeInt(pointcmpsize);
    output.writeUTF(name);






}
public EllipticCurve(DataInputStream input){
    byte[] ab=new byte[input.readInt()];
    input.read(ab);
    a=new BigInteger(ab);
    byte[] bb=new byte[input.readInt()];
    intput.read(bb);
    b=new BigInteger(bb);
    byte[] pb=new byte[intput.readInt()];
    intput.read(pb);
    p=new BigInteger(pb);
    byte[]ob=new byte[intput.readInt()];
    intput.read(ob);
    order=new BigInteger(ob);
    byte[] gb=new byte[input.readInt()];
    input.read(gb);
    generator=new ECPoint(gb,this);
    byte[] ppb=new byte[input.readInt()];
    input.read(ppb);
    ppodbf=new BigInteger(ppb);
    pointcmpsize=input.readInt();
    name=input.readUTF();
    generator.fastCache();




}
public boolean isSingular(){
    BigInteger aa=a.pow(3);
    BigInteger bb=b.pow(2);
    BigInteger result=((aa.multiply(COEFA)).add(bb.multiply(COEFA))).mod(p);
    if(result.compareTo(BigInteger.ZERO)==0) return  true;
    else  return false;




}

public  BigInteger calculateOrder(){
    return null;



}
public ECPoint calculateGenerator(){
    return  null;



}

public boolean onCurve(ECPoint q){
    if(q.isZero()) return true;
    BigInteger y_square=(q.gety()).modPow(new BigInteger("2"),p);
    BigInteger x_cube=(q.getx()).modPow(new BigInteger("3"),p);
    BigInteger x=q.getx();
    BigInteger dum=((x_cube.add(a.multiply(x)))).add(b).mod(p);
    if (y_square.compareTo(dum)==0) return true;
    else  return false;






}


public  Pair sign2(Key sk,BigInteger mac){
    BigInteger k=BigInteger.ZERO;
    Pair sig=new Pair();
    ECPoint g=new ECPoint(generator);
    ECPoint gk=null;
    do{

        k=randomBigInteger(order.subtract(BigInteger.ONE));
        System.out.println();
        gk=g.multiply(k);
        sig.r=(gk.getx()).mod(order);
        if (!(sig.r.compareTo(BigInteger.ZERO)==0)){
            BigInteger temp=k.modInverse(order);
            sig.s=(temp.multiply((sk.getSk().multiply(sig.r)).add(mac))).mod(order);




        }



    }




}while((sig.r.compareTo(BigInteger.ZERO)==0)||(sig.s.compareTo(BigInteger.ZERO)==0));
return sig;}

public  boolean verify2(Key pk,BigInteger mac,Pair sig){

    ECPoint g=new ECPoint(generator);
    BigInteger r=sig.r;
    BigInteger s=sig.s;
    BigInteger w,u1,u2;
    if((r.compareTo(BigInteger.ONE)>=0)&&(r.compareTo(order.subtract(BigInteger.ONE<=0)&&(s.compareTo(BigInteger.ONE)>=0)&&())))
    try{
        ECPoint temp=g1.add();
        if (temp.get().mod(order).compareTo(r.mod(order))==0){
            return  true;





        } else {
            return false;




        }




    }

}

public  BigIntrger randomBigInteger(BigInteger n){
    Random rnd=new Random();
    int maxNumBitLength=n.bitLength();
    BigInteger aRandomBigInt;
    do {aRandomBigInt=new BigInteger( maxNumBitLength.rnd);






}



}
