package edu.ustb.security.domain.vo.ecc;

import java.math.BigInteger;

/**
 * Created by xiaoming on 17-3-4.
 */
public class Pair {
    public BigInteger r;
    public  BigInteger s;

    public Pair(){}
    public Pair(BigInteger r,BigInteger s){
        this.r=r;
        this.s=s;
    }


}
