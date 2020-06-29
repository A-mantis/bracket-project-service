package com.mantis.brac.common.jwt;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/10 15:02
 * @history: 1.2020/4/10 created by wei.wang
 */
public class JWKSet implements Serializable {

    private static final long serialVersionUID = 1L;

    List<JWKKey> keys;

    public JWKSet() {
    }

    public JWKSet(List<JWKKey> keys) {
        this.keys = keys;
    }

    public List<JWKKey> getKeys() {
        return keys;
    }

    public void setKeys(List<JWKKey> keys) {
        this.keys = keys;
    }
}
