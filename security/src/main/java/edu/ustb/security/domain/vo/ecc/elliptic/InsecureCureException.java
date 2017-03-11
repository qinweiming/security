package edu.ustb.security.domain.vo.ecc.elliptic;

import java.security.spec.*;

/**
 * Created by xiaoming on 17-3-11.
 */
public class InsecureCureException   extends Exception {
    public static final int NONPRIMEMODULUS = -1;
    public static final int SINGULAR = 0;
    public static final int SUPERSINGULAR = 1;
    public static final int ANOMALOUS = 2;
    public static final int TRACEONE = 3;
    private  int error;
    private EllipticCurve   sender;
    public InsecureCureException(int e, EllipticCurve sender){
        error=e;
        this.sender=sender;









    }

    public  InsecureCureException(int e, EllipticCurve sender){
        error =e;
        this.sender=sender;




    }

    public  int getError(){

        return  error;
    }
 public  String  getErrorString(){
        if(error==SINGULAR) return  "SINGULAR";
        else  if (error==NONPRIMEMODULUS) return "NO";
        else if (error==SUPERSINGULAR)return  "";




 }
    public EllipticCurve getSender(){
        return sender;


}
