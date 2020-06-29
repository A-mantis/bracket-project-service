package com.mantis.brac.common.jwt;

import com.mantis.brac.common.constant.StaticProperties;
import com.mantis.brac.common.exception.BracBusinessException;
import com.mantis.brac.common.utils.DataUtil;
import com.mantis.brac.common.utils.RequestUtil;
import com.mantis.brac.session.BracSession;
import io.jsonwebtoken.Jwts;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 10:41
 * @history: 1.2020/4/4 created by wei.wang
 */
public class RSAUtil {

    public static PublicKey findPublicKeyByUrl(String publicKeyUrl) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JWKSet> jwkSetEntity = restTemplate.getForEntity(publicKeyUrl, JWKSet.class);
        JWKKey key = jwkSetEntity.getBody().getKeys().get(0);
        BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(key.getN()));
        BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(key.getE()));
        try {
            return KeyFactory.getInstance(StaticProperties.RSA).generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new BracBusinessException("获取公钥失败！");
        }
    }

    /**
     * 解析JwtToken内容
     *
     * @return
     */
    public static Map<String, Object> parseAccessToken(PublicKey publicKey, String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token).getBody();
    }

    /**
     * 获取JWTToken
     *
     * @return
     */
    public static String findJwtToken(String authorizationHeader) {
        return Optional.ofNullable(BracSession.getHeader())
                .map(o -> o.get(authorizationHeader))
                .orElseThrow(() -> new BracBusinessException("获取JWTToken失败！"));
    }
}
