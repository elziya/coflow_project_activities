package com.technokratos.providers;

import com.technokratos.exceptions.CoflowAuthenticationHeaderException;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.AccountRoleEntity;
import com.technokratos.models.RoleEntity;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.technokratos.consts.CoflowConstants.ROLES;

@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {

    @Value("${jwt.expiration.access.millis}")
    private long expirationAccessInMillis;

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public String generateAccessToken(AccountEntity account) {
        return Jwts.builder()
                .setSubject(account.getId().toString())
                .claim("email", account.getEmail())
                .claim(ROLES,
                        account.getRoles().stream()
                                .map(AccountRoleEntity::getRole)
                                .map(RoleEntity::getRole)
                                .map(Objects::toString)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(expirationAccessInMillis)))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    @Override
    public String getEmailFromAccessToken(String token) {
        try {
            return (String) Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody().get("email");
        } catch (ExpiredJwtException e) {
            return (String) e.getClaims().get("email");
        } catch (SignatureException e){
            throw new CoflowAuthenticationHeaderException("User not found with token");
        }
    }

    @Override
    public Date getExpirationDateFromAccessToken(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(accessToken)
                    .getBody().getExpiration();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getExpiration();
        }
    }

    @Override
    public List<String> getRolesFromAccessToken(String token, boolean inFilter) {
        try {
            return (List<String>) Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody().get(ROLES);
        } catch (ExpiredJwtException e) {
            if (inFilter){
                throw new CoflowAuthenticationHeaderException("Access token expired");
            }
            return (List<String>) e.getClaims().get(ROLES);
        } catch (SignatureException e){
            throw new CoflowAuthenticationHeaderException("User not found with token");
        }
    }

    @Override
    public String getSubjectFromAccessToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        } catch (SignatureException e){
            throw new CoflowAuthenticationHeaderException("User not found with token");
        }
    }
}
