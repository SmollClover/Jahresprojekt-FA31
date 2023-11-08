package de.hhbk.managers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.EcJwkGenerator;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.EllipticCurves;
import org.jose4j.lang.JoseException;

public class AuthorizationManager {
    private EllipticCurveJsonWebKey senderJwk = null;
    private EllipticCurveJsonWebKey receiverJwk = null;

    public AuthorizationManager() throws JoseException {
        this.generateKeyPair();
    }

    private void generateKeyPair() throws JoseException {
        this.senderJwk = EcJwkGenerator.generateJwk(EllipticCurves.P521);
        this.receiverJwk = EcJwkGenerator.generateJwk(EllipticCurves.P521);

        this.senderJwk.setKeyId("tenzur");
        this.receiverJwk.setKeyId("tenzur");
    }

    public String generateJWT(long userID) throws JoseException {
        // Create the Claims, which will be the content of the JWT
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("Tenzur"); // who creates the token and signs it
        claims.setAudience("User"); // to whom the token is intended to be sent
        claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
        claims.setGeneratedJwtId(); // a unique identifier for the token
        claims.setIssuedAtToNow(); // when the token was issued/created (now)
        claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
        claims.setSubject("Authorization"); // the subject/principal is whom the token is about
        claims.setClaim("userID", userID); // additional claims/attributes about the subject can be added

        // A JWT is a JWS and/or a JWE with JSON claims as the payload.
        // In this example it is a JWS so we create a JsonWebSignature object.
        JsonWebEncryption jwe = new JsonWebEncryption();

        // The payload of the JWS is JSON content of the JWT Claims
        jwe.setPayload(claims.toJson());

        // The JWT is signed using the private key
        jwe.setKey(this.receiverJwk.getPublicKey());

        // Set the Key ID (kid) header because it's just the polite thing to do.
        // We only have one key in this example but a using a Key ID helps
        // facilitate a smooth key rollover process
        jwe.setKeyIdHeaderValue(this.receiverJwk.getKeyId());

        // Set the signature algorithm on the JWT/JWS that will integrity protect the
        // claims
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.ECDH_ES_A256KW);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512);

        // Sign the JWS and produce the compact serialization or the complete JWT/JWS
        // representation, which is a string consisting of three dot ('.') separated
        // base64url-encoded parts in the form Header.Payload.Signature
        // If you wanted to encrypt it, you can simply set this jwt as the payload
        // of a JsonWebEncryption object and set the cty (Content Type) header to "jwt".
        String jwt = jwe.getCompactSerialization();

        System.out.println(jwt);
        return jwt;
    }

    public boolean validateToken(String token) throws MalformedClaimException {
        // Use JwtConsumerBuilder to construct an appropriate JwtConsumer, which will
        // be used to validate and process the JWT.
        // The specific validation requirements for a JWT are context dependent,
        // however,
        // it is typically advisable to require a (reasonable) expiration time, a
        // trusted issuer, and
        // an audience that identifies your system as the intended recipient.
        // If the JWT is encrypted too, you need only provide a decryption key or
        // decryption key resolver to the builder.
        JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime() // the JWT must have an expiration time
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for
                // clock skew
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer("Tenzur") // whom the JWT needs to have been issued by
                .setExpectedAudience("User") // to whom the JWT is intended for
                .setVerificationKey(this.senderJwk.getKey()) // verify the signature with the public key
                .setDecryptionKey(this.receiverJwk.getPrivateKey()).setVerificationKey(this.senderJwk.getPublicKey()).setJweAlgorithmConstraints(ConstraintType.PERMIT, KeyManagementAlgorithmIdentifiers.ECDH_ES_A256KW).setJweContentEncryptionAlgorithmConstraints(ConstraintType.PERMIT, ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512) // which is only RS256 here
                .build(); // create the JwtConsumer instance

        try {
            // Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
            System.out.println("JWT validation succeeded! " + jwtClaims);
            return true;
        } catch (InvalidJwtException e) {
            // InvalidJwtException will be thrown, if the JWT failed processing or
            // validation in anyway.
            // Hopefully with meaningful explanations(s) about what went wrong.
            System.out.println("Invalid JWT! " + e);

            // Programmatic access to (some) specific reasons for JWT invalidity is also
            // possible
            // should you want different error handling behavior for certain conditions.

            // Whether or not the JWT has expired being one common reason for invalidity
            if (e.hasExpired()) {
                System.out.println("JWT expired at " + e.getJwtContext().getJwtClaims().getExpirationTime());
            }

            // Or maybe the audience was invalid
            if (e.hasErrorCode(ErrorCodes.AUDIENCE_INVALID)) {
                System.out.println("JWT had wrong audience: " + e.getJwtContext().getJwtClaims().getAudience());
            }
            return false;
        }
    }

    public String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(15, password.toCharArray());
    }

    public BCrypt.Result verifyPassword(String password, String hashedPassword) {
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
    }
}
