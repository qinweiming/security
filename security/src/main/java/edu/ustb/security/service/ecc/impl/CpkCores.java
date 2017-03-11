package edu.ustb.security.service.ecc.impl;

import edu.ustb.security.domain.vo.ecc.Pair;

import java.io.OutputStream;
import java.math.BigInteger;
import java.security.spec.ECPoint;

/**
 * Created by xiaoming on 17-3-7.
 */
public interface CpkCores {
    ECPoint  generatePkByTd(String Id);

    BigInteger  generateSkById(String Id);
    abstract Pair sign(BigInteger  sk, String src);
    boolean  verify(ECPoint  pk,String src,Pair  sign);
    void  generateQRcode(OutputStream outputStream,String src,Pair pair);





}
