/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.controller.util.criptografia;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author goliveira
 */
public class GeraCriptografiaDeString {
    private final Cipher cryptographer;
    private final String privateKey = "Desenv_Web2017.2";

    public GeraCriptografiaDeString() throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException {
        this.cryptographer = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(this.privateKey.getBytes("UTF-8"),"AES");
        this.cryptographer.init(Cipher.ENCRYPT_MODE,key);
    }
    
    
    private String encryptString(String stringToBeEncrypted) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedStringInBytes = this.cryptographer.doFinal(stringToBeEncrypted.getBytes("UTF-8"));
        String encryptedString = Base64.encode(encryptedStringInBytes);
        return encryptedString;
    }

    public String tryToEncryptString(String stringToBeEncrypted) {
        String encryptedString = null;
        try {
            encryptedString = encryptString(stringToBeEncrypted);
        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(GeraCriptografiaDeString.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (encryptedString == null)
                encryptedString = "";
        }
        return encryptedString;
    }
    
}
