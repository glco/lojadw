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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import sun.security.util.Password;

/**
 *
 * @author goliveira
 */
public class HttpServletRequestComSenhaCriptografada extends HttpServletRequestWrapper{
    
    
    private GeraCriptografiaDeString encriptador;
    
    public HttpServletRequestComSenhaCriptografada(HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException  {
        super(request);
        encriptador = new GeraCriptografiaDeString();
    }
    
    /**
     *
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name){
        if(name.compareToIgnoreCase("senha")==0)
            return  this.encriptador.tryToEncryptString(super.getParameter(name));
        else
            return super.getParameter(name);
}
    
}
