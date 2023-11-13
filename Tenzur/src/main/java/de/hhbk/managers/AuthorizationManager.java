package de.hhbk.managers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import de.hhbk.entities.Benutzer;
import org.hibernate.Session;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.EcJwkGenerator;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.EllipticCurves;
import org.jose4j.lang.JoseException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.PrintWriter;

public class AuthorizationManager {
    /**
     * Encryption Keys
     */
    private EllipticCurveJsonWebKey senderJwk = null;
    private EllipticCurveJsonWebKey receiverJwk = null;

    public AuthorizationManager() throws JoseException {
        this.generateKeyPair();
    }

    /**
     * Creates an Encrpytion Key Pair for encrypting and decrypting a JWT
     * @throws JoseException
     */
    private void generateKeyPair() throws JoseException {
        this.senderJwk = EcJwkGenerator.generateJwk(EllipticCurves.P521);
        this.receiverJwk = EcJwkGenerator.generateJwk(EllipticCurves.P521);

        this.senderJwk.setKeyId("tenzur");
        this.receiverJwk.setKeyId("tenzur");
    }

    /**
     * 
     * @param userID the user ID to save into the JWT
     * @return The encrypted JWT String
     * @throws JoseException
     */
    @NotNull
    public String generateJWT(@NotNull long userID) throws JoseException {
        JsonWebSignature jws = getJsonWebSignature(userID);
        String innerJwt = jws.getCompactSerialization();

        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setKey(this.receiverJwk.getPublicKey());
        jwe.setKeyIdHeaderValue(this.receiverJwk.getKeyId());
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.ECDH_ES_A256KW);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512);
        jwe.setContentTypeHeaderValue("JWT");
        jwe.setPayload(innerJwt);

        String jwt = jwe.getCompactSerialization();
        return jwt;
    }

    /**
     * 
     * @param userID the user ID to save into the JWT
     * @return The unencrypted Json Web Signature
     */
    @NotNull
    private JsonWebSignature getJsonWebSignature(@NotNull long userID) {
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("Tenzur");
        claims.setAudience("User");
        claims.setExpirationTimeMinutesInTheFuture(10);
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();
        claims.setNotBeforeMinutesInThePast(2);
        claims.setSubject("Authorization");
        claims.setClaim("userID", userID);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(senderJwk.getPrivateKey());
        jws.setKeyIdHeaderValue(senderJwk.getKeyId());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.ECDSA_USING_P521_CURVE_AND_SHA512);
        return jws;
    }

    /**
     * 
     * @param token The encrypted JWT String
     * @return Either the decrypted token payload, here the user ID or null on failure
     */
    public String validateToken(@NotNull String token) {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setRequireExpirationTime()
                .setRequireSubject()
                .setExpectedIssuer("Tenzur")
                .setExpectedAudience("User")
                .setVerificationKey(this.senderJwk.getKey())
                .setDecryptionKey(this.receiverJwk.getPrivateKey())
                .setVerificationKey(this.senderJwk.getPublicKey())
                .setJwsAlgorithmConstraints(ConstraintType.PERMIT, AlgorithmIdentifiers.ECDSA_USING_P521_CURVE_AND_SHA512)
                .setJweAlgorithmConstraints(ConstraintType.PERMIT, KeyManagementAlgorithmIdentifiers.ECDH_ES_A256KW)
                .setJweContentEncryptionAlgorithmConstraints(ConstraintType.PERMIT, ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512)
                .build();

        try {
            JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
            return jwtClaims.getClaimValue("userID").toString();
        } catch (InvalidJwtException ignored) {
            return null;
        }
    }

    /**
     * 
     * @param password The unhashed password string
     * @return The hashed password string
     */
    @NotNull
    public String hashPassword(@NotNull String password) {
        return BCrypt.withDefaults().hashToString(15, password.toCharArray());
    }

    /**
     * 
     * @param password The unhashed password from the user input
     * @param hashedPassword The hashed password saved in the database for comparison
     * @return
     */
    @NotNull
    public BCrypt.Result verifyPassword(@NotNull String password, @NotNull String hashedPassword) {
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
    }
}
