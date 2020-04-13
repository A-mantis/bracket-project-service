package com.mantis.brac.common.utils.jwt;

import com.mantis.brac.common.exception.BracBusinessException;
import com.mantis.brac.common.profile.UserProfile;
import com.mantis.brac.common.utils.DataUtil;
import com.mantis.brac.config.StaticProperties;
import com.mantis.brac.session.BracSession;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 10:41
 * @history: 1.2020/4/4 created by wei.wang
 */
public class RSAUtil {


    private static String RSA = "RSA";

    public static PublicKey findPublicKeyByUrl() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<JWKSet> jwkSetEntity = restTemplate.getForEntity("https://adfs.smec-cn.com/adfs/discovery/keys", JWKSet.class);
            JWKKey key = jwkSetEntity.getBody().getKeys().get(0);
            BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(key.getN()));
            BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(key.getE()));
            return KeyFactory.getInstance(RSA).generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new BracBusinessException("获取公钥失败！");
        }
    }


    /**
     * 初始化UserProfile，只记录用户id
     *
     * @return
     */
    public static void jwtTokenInitUser() {
        try {
            Map<String, Object> subject = findJwtTokenProperty();
            BracSession.getUserProfile().setUid((String) subject.get("upn"));
        } catch (Exception e) {
            throw new BracBusinessException("根据JWTToken获取当前用户失败！");
        }
    }

    /**
     * 初始化UserProfile，并记录全部属性
     *
     * @return
     */
    public static void jwtTokenInitUserProperty() {
        try {
            Map<String, Object> subject = findJwtTokenProperty();
            BracSession.getUserProfile().setUid((String) subject.get("upn")).getAttributes().put("jwtUserProfile", subject);
        } catch (Exception e) {
            throw new BracBusinessException("获取JWT属性失败！" + e.getMessage());
        }
    }

    /**
     * 解析JwtToken内容
     *
     * @return
     */
    private static Map<String, Object> findJwtTokenProperty() {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(findPublicKeyByUrl())
                    .build()
                    .parseClaimsJws(findJwtToken()).getBody();
        } catch (Exception e) {
            throw new BracBusinessException("解析JwtToken内容失败！");
        }
    }

    /**
     * 获取JWTToken
     *
     * @return
     */
    private static String findJwtToken() {
        try {
            HttpServletRequest httpServletRequest = DataUtil.getHttpServletRequest();
            if (httpServletRequest.getHeader(StaticProperties.HEADER_AUTHORIZATION) == null) {
                throw new BracBusinessException("获取JWTToken失败！");
            } else {
                return httpServletRequest.getHeader(StaticProperties.HEADER_AUTHORIZATION).
                        replaceFirst(StaticProperties.REQUEST_HEADER_BEARER, "");
            }
        } catch (Exception e) {
            throw new BracBusinessException("获取JWTToken失败！");
        }
    }

    /**
     * 验证JWTToken
     *
     * @param jwt
     * @return
     */
    public static boolean checkJwtToken(String jwt) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(findPublicKeyByUrl())
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (ExpiredJwtException | SignatureException e) {
            e.printStackTrace();
            return false;
        }
    }
}
