package com.neuq.info.common.aes;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Arrays;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;


/**
 * Created by lihang on 2017/4/13.
 */
public class AES {
    //算法名
    public static final String KEY_ALGORITHM = "AES";
    //加解密算法/模式/填充方式
    //可以任意选择，为了方便后面与iOS端的加密解密，采用与其相同的模式与填充方式
    //ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个参数iv
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    //生成密钥
    public static byte[] generateKey() throws Exception{
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }

    //生成iv
    public static AlgorithmParameters generateIV() throws Exception{
        //iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
        byte[] iv = new byte[16];
        Arrays.fill(iv, (byte) 0x00);
        AlgorithmParameters params = AlgorithmParameters.getInstance(KEY_ALGORITHM);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    //转化成JAVA的密钥格式
    public static Key convertToKey(byte[] keyBytes) throws Exception{
        SecretKey secretKey = new SecretKeySpec(keyBytes,KEY_ALGORITHM);
        return secretKey;
    }

    //加密
    public static byte[] encrypt(byte[] data,byte[] keyBytes,byte[] ivByte) throws Exception {
        //转化为密钥
        Key key = convertToKey(keyBytes);
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key,generateIV(ivByte));
        return cipher.doFinal(data);
    }

    //解密
    public static byte[] decrypt(byte[] encryptedData,byte[] keyBytes,byte[] ivByte ) throws Exception{
        Key key = convertToKey(keyBytes);
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key,generateIV(ivByte));
        return cipher.doFinal(encryptedData);
    }
    //生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception{
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }
    public static void main(String[] args) {
        //明文
        String plainTextString = "Hello,Bouncy Castle";
        System.out.println("明文 : "+plainTextString);
        byte[] key;
        try {
            //初始化密钥
            key = generateKey();
            //初始化iv
            AlgorithmParameters iv = generateIV();
            System.out.println("密钥 : ");
            //打印密钥
            for (int i = 0; i < key.length; i++) {
                System.out.printf("%x", key[i]);
            }
            System.out.println();

            //进行加密
            //byte[] encryptedData = encrypt(plainTextString.getBytes(), key,iv);
            //输出加密后的数据
//                System.out.println("加密后的数据 : ");
//                for (int i = 0; i < encryptedData.length; i++) {
//                    System.out.printf("%x", encryptedData[i]);
//                }
//                System.out.println();
            String encryptedData="txcwtZ9ZDDe9lMip32TeXiHDE4tnKgIY2bBovDyrOmuqM8f/bNG+Telg3F7b9PWHzSsDwsTxzYTdgqhMXhvMU2WwVFKxclugUB9h6sSUL481G4WRLW7eLCXXQdgAFY5LlANEzpoZf3USBUSOlKqMwNGI+iNBdm9qWhUcGINyCiWRguY8SuqcuPvNqlqfthiLjnjEhZj8zbypYJ3gA256cR277VHsoy1goekKews4aFDcWzUw3R0kVdDIXT30Jkh1hHBxx20mETU9Y7Y8DvW4SVfVf1/d4q1U3vhbkLDKkoNg3zU+RLej5nxUCLhaI3Ogwit1b4ZzTQNfRy03MzkEFGk586/qGl4avPMqkcBy9bfRDmdaNnRayE59b3oGxsAZJMbS/oEFYHuewrgA6pa7Zsyi84RluzJp9K+uhAOfbJEa5k2DAAYEfM/1flN5wg3h8c4RjLEKb58ryPw4q5AWe3n4kdmqqdGxPOZ1JIdVUDM=";
            String key1="Wnk1wE9ujBP6QyzO1tfa+w==";
            String iv1="0mTv1/Z517rm7FmI61TzBg==";

            byte[] data = decrypt(Base64.decodeBase64(encryptedData),Base64.decodeBase64(key1),Base64.decodeBase64(iv1));
            System.out.println("解密得到的数据 : " + new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }
