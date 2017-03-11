package edu.ustb.security.domain.vo.ecc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.spec.ECPoint;

/**
 * Created by xiaoming on 17-3-4.
 */
public interface Key {
    public Key readKey(InputStream in) throws IOException;
    public void writeKey(OutputStream out) throws IOException;
    public Key getPublic();
    public boolean isPublic();
    public BigInteger getSk();
    public ECPoint  getPK();
    public Key getPublicKeyByPoint(ECPoint pointpub);



}
