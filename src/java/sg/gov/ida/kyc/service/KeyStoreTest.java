/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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
     //Security.addProvider(new BouncyCastleProvider());

     String pub="-----BEGIN CERTIFICATE-----\n" +
"MIIDZzCCAk+gAwIBAgIEaHFN9DANBgkqhkiG9w0BAQsFADBkMQswCQYDVQQGEwJTRzESMBAGA1UE\n" +
"CBMJU2luZ2Fwb3JlMRIwEAYDVQQHEwlTaW5nYXBvcmUxDDAKBgNVBAoTA0lkYTEMMAoGA1UECxMD\n" +
"U3ZjMREwDwYDVQQDEwhNaWtlIFRhbjAeFw0xNjA1MTkwOTA1NTBaFw0xNjA4MTcwOTA1NTBaMGQx\n" +
"CzAJBgNVBAYTAlNHMRIwEAYDVQQIEwlTaW5nYXBvcmUxEjAQBgNVBAcTCVNpbmdhcG9yZTEMMAoG\n" +
"A1UEChMDSWRhMQwwCgYDVQQLEwNTdmMxETAPBgNVBAMTCE1pa2UgVGFuMIIBIjANBgkqhkiG9w0B\n" +
"AQEFAAOCAQ8AMIIBCgKCAQEAtpJdVHktcnRrvgTPtTBJfq0LdaZBavDj93uu5q9FngiCYfclHjRx\n" +
"s7iG/VYvSjUN86MYJuf/joKVbl5lfgbll4G7/aVSvU11YwAolKIUgEapSD6U9Xfsser0r+vtgvC6\n" +
"w5AdyQL2cn2MNOuCrNOuh3qnU8wkXMgKYKO/tR7vqxYpi2/Uwqd1LvhmebMDs/ZLv62DfxDm/pa9\n" +
"G2GOv0OoRY+nbWSLtxulBxV8dp0KnoE377IBHzs70eQ4oJ78Xgy4CjTQS0sm5mu74dLHZRpjXdSh\n" +
"U21AXG+Tfg0yoIT9LCcLGJWAneffvova+0vqgRtu6PPspfK1WCKuodXftY8wnQIDAQABoyEwHzAd\n" +
"BgNVHQ4EFgQU0/zbRzgT3cUlibXkrezjjH2R/IwwDQYJKoZIhvcNAQELBQADggEBACgBDSGA3DHv\n" +
"srdm/61ATmvQz3YNeO6WWJsV4+TaBmo/rKFoi1/4xzm4TQEIwhr3ChgcLzODoPnjl8ruhEB7VO/G\n" +
"S/A6TL13NOaUoFFa6MkqYAzWGwPQrsf/fFJ48jVi1Qul5T81OgmH7dHnQE2dMWeHpLmBHwtteZj7\n" +
"Crnjux8hTPLDqo7GaiX7idcFR6b5UuzHn1belQBHN2LaaEjB1oMHKphJwiorwPD/WYtiiTwpB00r\n" +
"d8H+HRt90Cd8CXRX6vyawYS5f1kZLj5N/Y8cQ57jyUI/qv3Shl1P3yiJ6QZrSR6OSAcEICAP41FA\n" +
"FO5KeU6bgzU5pVOq7+eRq6JQs3w=\n" +
"-----END CERTIFICATE-----"
;
     

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
  PrivateKey priv = readPrivateKey("/home/dcnfc/vault.jks");
  //decrypt
  byte[] decrypted = decrypt(priv,encrypted);
  System.out.println("decry: "+new String(decrypted,"UTF8"));
  
  }
  
  private static byte[] encrypt(PublicKey key, byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException,NoSuchProviderException
{
    Cipher cipher = Cipher.getInstance("RSA"); 
    cipher.init(Cipher.ENCRYPT_MODE, key);  
    return cipher.doFinal(plaintext);
}
  
  private static byte[] decrypt(PrivateKey key, byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException
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
    keystore.load(is, "passw0rd".toCharArray());

    String alias = "ida";

    Key key = keystore.getKey(alias, "passw0rd".toCharArray());
    if (key instanceof PrivateKey) {
        priv = (PrivateKey)key;
    }
    return priv;
    
}

}

