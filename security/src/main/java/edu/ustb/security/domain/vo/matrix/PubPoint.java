package edu.ustb.security.domain.vo.matrix;

/**
 * Created by xiaoming on 17-3-6.
 */
public class PubPoint {

    private Integer axisX;
    private  Integer  axisY;//矩阵的y
    private  String     publicKeyX;
    private   String  publicKeyY;

    public PubPoint() {
    }

    public PubPoint(Integer axisX, Integer axisY, String publicKeyX, String publicKeyY) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.publicKeyX = publicKeyX;
        this.publicKeyY = publicKeyY;
    }

    public Integer getAxisX() {
        return axisX;
    }

    public void setAxisX(Integer axisX) {
        this.axisX = axisX;
    }

    public Integer getAxisY() {
        return axisY;
    }

    public void setAxisY(Integer axisY) {
        this.axisY = axisY;
    }

    public String getPublicKeyX() {
        return publicKeyX;
    }

    public void setPublicKeyX(String publicKeyX) {
        this.publicKeyX = publicKeyX;
    }

    public String getPublicKeyY() {
        return publicKeyY;
    }

    public void setPublicKeyY(String publicKeyY) {
        this.publicKeyY = publicKeyY;
    }
}
