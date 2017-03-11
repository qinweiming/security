package edu.ustb.security.service.ecc.impl;

import edu.ustb.security.domain.vo.ecc.ECKey;
import edu.ustb.security.domain.vo.ecc.Key;
import edu.ustb.security.domain.vo.matrix.CpkMatrix;
import edu.ustb.security.domain.vo.matrix.PubPoint;
import edu.ustb.security.domain.vo.matrix.SecPoint;
import javafx.scene.layout.BackgroundImage;

import java.math.BigInteger;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

/**
 * Created by xiaoming on 17-3-7.
 */
public class CpkMatrixsFactory {


    public static CpkMatrix  generateCpkMatrix(){
        EllipticCurve  ellipticCurve=null;
        try{
            ellipticCurve=new EllipticCurve(new secp256r1());

        }catch (InsecureCurveException e){
            e.printStackTrace();



        }
        return  generateCpkMatrix(ellipticCurve);









    }
    public  static CpkMatrix  generateCpkMatrix(EllipticCurve ellipticCurve){
        if(ellipticCurve!=null){
            CpkMatrix cpkMatrix=new CpkMatrix();
            PubPoint[] pubPoints=new PubPoint[1024];
            SecPoint[]  secPoints=new SecPoint[1024];
            int k=0;
            for(int i=0;i<32;i++){
                for(int j=0;j<32;j++){
                    Key key=new ECKey(ellipticCurve);
                    BigInteger  sk=key.getSk();
                    ECPoint pk=key.getPK();
                    pubPoints[k]=new PubPoint(i,j,pk.getx().toString(32),pk.gety().toString(32));
                    secPoints[k]new SecPoint(i,j,sk.toString(32));
                    k++;

                }



            }
            cpkMatrix.setPubPoints(pubPoints);
            cpkMatrix.setSecPoints(secPoints);
            return  cpkMatrix;



        }

        return  null;


    }


}
