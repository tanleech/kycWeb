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
"MIIDZzCCAk+gAwIBAgIETYVXnTANBgkqhkiG9w0BAQsFADBkMQswCQYDVQQGEwJTRzESMBAGA1UE\n" +
"CBMJU2luZ2Fwb3JlMRIwEAYDVQQHEwlTaW5nYXBvcmUxDDAKBgNVBAoTA0lEQTEMMAoGA1UECxMD\n" +
"U1ZDMREwDwYDVQQDEwhNaWtlIFRhbjAeFw0xNjA1MjQwNTM0NDRaFw0xNjA4MjIwNTM0NDRaMGQx\n" +
"CzAJBgNVBAYTAlNHMRIwEAYDVQQIEwlTaW5nYXBvcmUxEjAQBgNVBAcTCVNpbmdhcG9yZTEMMAoG\n" +
"A1UEChMDSURBMQwwCgYDVQQLEwNTVkMxETAPBgNVBAMTCE1pa2UgVGFuMIIBIjANBgkqhkiG9w0B\n" +
"AQEFAAOCAQ8AMIIBCgKCAQEAp0xz1Qlwi6UYOLcp2+3W+hDenDxLCFtN8YcSnIzcW6aOBrPqAZNa\n" +
"PNMaQN3hXQOx14Q8Cb38ItDqYVVF1uOavB580rvVVcYyz/xpJID6AKgL0mTP7mcTPv+rspGBZx4E\n" +
"mjf0FlCuPhdGkgV+e2wkl+NjAhuaN74Htu1jpjovfPVkKEoDLW37dqSnxJ4yiU9HwylImtexAxqk\n" +
"VR+9aGl4VmS0rmEpRyb6AJu4AkWqgGVw1SXghZe/1e6sEII8UBYJO1MgGZBa7+O99fHXOk1JcYVZ\n" +
"A0qaPL3CVrPzeX56Q74Ce40UptWN7+EeI/tlQYuSftu32I5/gvDZ33QhknIzpwIDAQABoyEwHzAd\n" +
"BgNVHQ4EFgQU04+cauxc9VOA27smkWuW2lK/PqMwDQYJKoZIhvcNAQELBQADggEBABeZAknBCwrX\n" +
"6VPITAkcAJxzAxC+By1uTkQE41chGq349A6iIaFiqoFE8q/PxFHwRDGNs33YBRxiDRH6S7SnSftR\n" +
"E586TjRXnkxRdaCuJpW2/IskCrztHfPLXHc2bD3krloT8rfa5EkQk+VMAo3OMmWf77+xV8ZWzExx\n" +
"sHilEMT8RGKioEIlaa5i4oo4WJxdCbF0TspopEaq2qjqwh0YRJ6qpZz9k9ZCn8Jyhgtli/4et8DH\n" +
"xHW/upKYtp90eqn3Fz4AHXAOeCU+bkxhjYSwqH9nZaIrqrZ7X2gPnsD+bsqW/Bc7tNf1A1/lfGY2\n" +
"lDkcEwHLAdbhMYHpzV+SeEAxAjQ=\n" +
"-----END CERTIFICATE-----"
;
     

  //encrypt
  // get the public key
  byte[] publicKeyBytes = pub.getBytes();
  CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
  Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(publicKeyBytes));
  PublicKey publicKey = certificate.getPublicKey();
  //encrypt
  byte[] encrypted = encrypt(publicKey,"what the hell ?".getBytes("UTF8"));
  System.out.println("encry: !!!!!!!"+ new String(encrypted, "UTF8"));

  //get the private key
  PrivateKey priv = readPrivateKey("c:/Users/ftbs/vault.jks");
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
    keystore.load(is, "changeit".toCharArray());

    String alias = "ida";

    Key key = keystore.getKey(alias, "changeit".toCharArray());
    if (key instanceof PrivateKey) {
        priv = (PrivateKey)key;
    }
    return priv;
    
}

}

