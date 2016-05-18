/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class KeyStoreTest {
  public static void main(String[] argv) throws Exception {
      
     String pub= "-----BEGIN CERTIFICATE-----\n" +
"MIIDZzCCAk+gAwIBAgIEHoy3TzANBgkqhkiG9w0BAQsFADBkMQswCQYDVQQGEwJTRzESMBAGA1UE\n" +
"CBMJU2luZ2Fwb3JlMRIwEAYDVQQHEwlTaW5nYXBvcmUxDDAKBgNVBAoTA0lEQTEMMAoGA1UECxMD\n" +
"U0lHMREwDwYDVQQDEwhNaWtlIFRhbjAeFw0xNjA1MTYwMjEzMzNaFw0xNjA4MTQwMjEzMzNaMGQx\n" +
"CzAJBgNVBAYTAlNHMRIwEAYDVQQIEwlTaW5nYXBvcmUxEjAQBgNVBAcTCVNpbmdhcG9yZTEMMAoG\n" +
"A1UEChMDSURBMQwwCgYDVQQLEwNTSUcxETAPBgNVBAMTCE1pa2UgVGFuMIIBIjANBgkqhkiG9w0B\n" +
"AQEFAAOCAQ8AMIIBCgKCAQEAqIG3lNbLuWLJEpAjXgik1XR93OT3bmMWQdrhumYH18cF5vIp5B47\n" +
"PcRc3npQ0Im9zYVHqu6o++EArNYB6xrtSyclGEquzFvFYpWI03eFwh57d321FiUz3KIupZuTCCIz\n" +
"xNv3KIxNXwRTFZn5vXs1sKl3lw8aLfElFBJqWeCHH+xde87Ol4qLdMpJjyducoylYlmFrfe785sV\n" +
"g1EB0COZwc5WttyYn7RPniSpApEMI9k4dT4gqKlbPUiMfJtxGbdIpOrzAgRPUjrZ4jjyNODj0lAA\n" +
"8Jw5hX3GHt4X/xciC7P/wmchQOuIQPkxSR1kg7PuWVpKi+jGfaPrFvlIhFYyPQIDAQABoyEwHzAd\n" +
"BgNVHQ4EFgQU3NzxTk3VWeYVPWlhdMwqb4dqYHEwDQYJKoZIhvcNAQELBQADggEBACGdgfshkNSm\n" +
"/pQRJrwYMbt7WyfNRqm+6jV/ILuUSiQX83fju4kExfCTFZpu4D4jCihURZ3ohvZ0u9B7pbWOQBOZ\n" +
"Sus5NYpyMjdaeoLRTloPy4vRjCfqvBwKfNe1fzU9HFbEIpLFcCU8kcCr4pZ/tEloY0QqM9CeqpJC\n" +
"fsglQ/QbMqE0fYDBapk+Q61lIYV6OF+6/osirDYw3v0RSN4fyXXSfUsjE6svP/0B0hy9Sv3XS/Jr\n" +
"FMerp2RHE36bx3YX8Z7R6b+XE4+KnciIzQ8z4HOFTsm3mYjnAdya1JIbtUt3xvJmTJrpEtLpyeME\n" +
"Xzni2KyiO3vtjTSAVNQJ7XmAtkY=\n"+
"-----END CERTIFICATE-----" ;
    //encrypt
  // get the public key
  byte[] publicKeyBytes = pub.getBytes();
  CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
  Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(publicKeyBytes));
  PublicKey publicKey = certificate.getPublicKey();

  //encrypt
  byte[] encrypted = encrypt(publicKey,"what the hell?".getBytes("UTF8"));
  System.out.println("encry: "+ new String(encrypted, "UTF8"));

  //get the private key
  PrivateKey priv = readPrivateKey("/home/dcnfc/clientkeystore");
  //decrypt
  byte[] decrypted = decrypt(priv,encrypted);
  System.out.println("decry: "+new String(decrypted,"UTF8"));
  
  }
  
  private static byte[] encrypt(PublicKey key, byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
{
    Cipher cipher = Cipher.getInstance("RSA");   
    cipher.init(Cipher.ENCRYPT_MODE, key);  
    return cipher.doFinal(plaintext);
}
  
  private static byte[] decrypt(PrivateKey key, byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
{
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, key);
    return cipher.doFinal(ciphertext);
}
  
private static PrivateKey readPrivateKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, Exception
{
    /*
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(readFileBytes(filename));
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePrivate(keySpec);     
    */
    PrivateKey priv = null;
    FileInputStream is = new FileInputStream(filename);
    KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
    keystore.load(is, "zaq1ZAQ!".toCharArray());

    String alias = "ida";

    Key key = keystore.getKey(alias, "zaq1ZAQ!".toCharArray());
    if (key instanceof PrivateKey) {
        priv = (PrivateKey)key;
    }
    return priv;
    
}

}

