package edu.ustb.security.domain.vo.matrix;

/**
 * Created by xiaoming on 17-3-6.
 */
public class SecPoint {
    private  Integer  axisX;
    private  Integer   axisY;
    private   String     privateKey;

    public SecPoint() {
    }

    public SecPoint(Integer axisX, Integer axisY, String privateKey) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.privateKey = privateKey;
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

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
