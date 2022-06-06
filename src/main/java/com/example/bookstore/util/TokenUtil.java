package com.example.bookstore.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;
/**
 * this annotation will be auto detected using path scanning
 */
@Component
/**
 * create class name tokenutil.
 */
public class TokenUtil {
    private static final String TOKEN_SECRET = "Akshata";

    /**
     *This method create a token with the id parameter as a claim. It then returns the same token
     * it will enables users to verify their detaile to web & then it generate unique token.
     * @param id
     * @return token string
     */
    public  String createToken(int id)   {
        try {
            // We create a token using the HMAC256 algorithm and store the id as claim.
            //to set algorithm
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            String token = JWT.create()
                    .withClaim("user_id", id)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            //log Token Signing Failed
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
    // This method decodes the passed token and returns the id claim. If the verification fails it will throw an exception
    public int decodeToken(String token)
    {
        int userid;
        //for verification algorithm
        Verification verification = null;
        try {
            // We specify the algorithm for the verifier here
            verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException  e) {
            e.printStackTrace();
        }
        //and then build the verifier
        JWTVerifier jwtverifier=verification.build();
        //We verify and decode the token using the verifier. If the token is incorrect it will throw an exception.
        //to decode token
        DecodedJWT decodedjwt=jwtverifier.verify(token);
        // We extract the claim from the decoded token .
        Claim claim=decodedjwt.getClaim("user_id");
        //and the convert the claim to int type.
        userid=claim.asInt();
        // We then return this id.
        return userid;

    }
}
