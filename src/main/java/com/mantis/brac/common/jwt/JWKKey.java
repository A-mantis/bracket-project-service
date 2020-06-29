package com.mantis.brac.common.jwt;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/10 15:01
 * @history: 1.2020/4/10 created by wei.wang
 */
public class JWKKey implements Serializable {
    private static final long serialVersionUID = 1L;

    String kty;
    String e;
    String x5t;
    String use;
    List<String> x5c;
    String kid;
    String alg;
    String n;

    public JWKKey() {
    }

    public JWKKey(String kty, String e, String x5t, String use, List<String> x5c, String kid, String alg, String n) {
        this.kty = kty;
        this.e = e;
        this.x5t = x5t;
        this.use = use;
        this.x5c = x5c;
        this.kid = kid;
        this.alg = alg;
        this.n = n;
    }

    public String getKty() {
        return kty;
    }

    public void setKty(String kty) {
        this.kty = kty;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getX5t() {
        return x5t;
    }

    public void setX5t(String x5t) {
        this.x5t = x5t;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public List<String> getX5c() {
        return x5c;
    }

    public void setX5c(List<String> x5c) {
        this.x5c = x5c;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }
}
