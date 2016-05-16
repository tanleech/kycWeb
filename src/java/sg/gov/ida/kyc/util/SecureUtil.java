/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author root
 */
public class SecureUtil {
    
    private String keyStore;
    private String pubKey;
    private String alias;

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    

    public byte[] encrypt(byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, CertificateException{
        PublicKey key = getPublicKey();
        Cipher cipher = Cipher.getInstance("RSA");   
        cipher.init(Cipher.ENCRYPT_MODE, key);  
        return cipher.doFinal(plaintext);
    }
  
    public byte[] decrypt(byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, CertificateException, IOException, KeyStoreException,InvalidKeySpecException,UnrecoverableKeyException{
        PrivateKey key = getPrivateKey();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(ciphertext);
    }
    
    public String getKeyStore() {
        return keyStore;
    }

    public void setKeyStore(String keyStore) {
        this.keyStore = keyStore;
    }
    
    public PublicKey getPublicKey() throws CertificateException
    {
        byte[] publicKeyBytes = pubKey.getBytes();
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
        Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(publicKeyBytes));
        PublicKey publicKey = certificate.getPublicKey();
        
        return publicKey;
        
    }
    
    public PrivateKey getPrivateKey() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, InvalidKeySpecException, UnrecoverableKeyException
    {
        PrivateKey priv = null;
        FileInputStream is = new FileInputStream(keyStore);
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(is, "zaq1ZAQ!".toCharArray());

        //String alias = "ida";

        Key key = keystore.getKey(alias, "zaq1ZAQ!".toCharArray());
        if (key instanceof PrivateKey) {
            priv = (PrivateKey)key;
        }
        return priv;

    }
    
    
}
