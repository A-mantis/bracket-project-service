package com.mantis.bracket.common.jwt;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/10 15:02
 * @history: 1.2020/4/10 created by wei.wang
 */
public class JwkSet implements Serializable {

    private static final long serialVersionUID = 1L;

    List<JwkKey> keys;

    public JwkSet() {
    }

    public JwkSet(List<JwkKey> keys) {
        this.keys = keys;
    }

    public List<JwkKey> getKeys() {
        return keys;
    }

    public void setKeys(List<JwkKey> keys) {
        this.keys = keys;
    }
}
