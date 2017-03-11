package edu.ustb.security.domain.vo.ecc;

import java.math.BigInteger;

/**
 * Created by xiaoming on 17-3-4.
 */
public interface ECParameters {
    public BigInteger a();

    public BigInteger b();
     public BigInteger p();
     public BigInteger generatorX();
     public BigInteger generatorY();
     public  BigInteger order();
     public String toString();



}
