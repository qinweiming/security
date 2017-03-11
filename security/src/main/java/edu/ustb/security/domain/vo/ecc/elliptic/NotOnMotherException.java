package edu.ustb.security.domain.vo.ecc.elliptic;

import java.security.spec.ECPoint;

/**
 * Created by xiaoming on 17-3-11.
 */
public class NotOnMotherException  extends  Exception{
    private ECPoint sender;
    public  NotOnMotherException(ECPoint sender){
        this.sender=sender;




    }

    public  String getErrorString(){
        return "";





    }

    public  ECPoint getSource(){
        return sender;


    }

}
