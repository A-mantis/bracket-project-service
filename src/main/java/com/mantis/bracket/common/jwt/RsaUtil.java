package com.mantis.bracket.common.jwt;

import com.mantis.bracket.aspect.WebLogAspect;
import com.mantis.bracket.common.constant.StaticProperties;
import com.mantis.bracket.common.exception.BracketBusinessException;
import com.mantis.bracket.common.http.ExceptionResponse;
import com.mantis.bracket.common.utils.RequestUtil;
import com.mantis.bracket.session.BracketSession;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 10:41
 * @history: 1.2020/4/4 created by wei.wang
 */
public class RsaUtil {

    private static Logger logger = LoggerFactory.getLogger(RsaUtil.class);

    public static PublicKey findPublicKeyByUrl(String publicKeyUrl) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JwkSet> jwkSetEntity = restTemplate.getForEntity(publicKeyUrl, JwkSet.class);
        JwkKey key = jwkSetEntity.getBody().getKeys().get(0);
        BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(key.getN()));
        BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(key.getE()));
        try {
            return KeyFactory.getInstance(StaticProperties.RSA).generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new BracketBusinessException("获取公钥失败！");
        }
    }

    /**
     * 解析JwtToken内容
     *
     * @return
     */
    public static Map<String, Object> parseAccessToken(PublicKey publicKey, String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new BracketBusinessException(String.format("token异常 :%s", e.getMessage()));
        }
    }

    /**
     * 获取JWTToken
     *
     * @return
     */
    public static String getJwtToken(String authorizationHeader) {
        return Optional.ofNullable(RequestUtil.getRequestHeader())
                .map(o -> o.get(authorizationHeader))
                .orElseThrow(() -> new BracketBusinessException("获取JWTToken失败！"));
    }
}
