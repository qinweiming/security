package edu.ustb.security.domain.vo.matrix;

/**
 * Created by xiaoming on 17-3-6.
 */
public class CpkMatrix {
    private int matrixField;
    private  int ecType;
    private  PubPoint[] pubPoints;
    private   SecPoint[]  secPoints;

    public CpkMatrix() {
    }

    public CpkMatrix(int ecType, PubPoint[] pubPoints, SecPoint[] secPoints) {
        this.ecType = ecType;
        this.pubPoints = pubPoints;
        this.secPoints = secPoints;
    }

    public int getMatrixField() {
        return matrixField;
    }

    public void setMatrixField(int matrixField) {
        this.matrixField = matrixField;
    }

    public int getEcType() {
        return ecType;
    }

    public void setEcType(int ecType) {
        this.ecType = ecType;
    }

    public PubPoint[] getPubPoints() {
        return pubPoints;
    }

    public void setPubPoints(PubPoint[] pubPoints) {
        this.pubPoints = pubPoints;
    }

    public SecPoint[] getSecPoints() {
        return secPoints;
    }

    public void setSecPoints(SecPoint[] secPoints) {
        this.secPoints = secPoints;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public String toJson(){
        return JSON.toJSONString(this);


    }

    public  String toSecJson(){
        SimplePropertyPreFilter simplePropertyPreFilter=new SimplePropertyPreFilter();
        simplePropertyPreFilter.getExcludes().add("pubpoints");
        return JSON.toJSONString(this,simplePropertyPreFilter);






    }
    public static CpkMatrix fromJson(String cpkMatrixsJson) {
        return JSON.parseObject(cpkMatrixsJson, CpkMatrix.class);
    }

}
