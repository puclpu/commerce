package com.sparta.commerce.global.security.service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptService {

  private final AesBytesEncryptor aesBytesEncryptor;

  // 암호화
  public String encrypt(String personalData) {
    byte[] encrypt = aesBytesEncryptor.encrypt(personalData.getBytes(StandardCharsets.UTF_8));
    return byteArrayToString(encrypt);
  }

  // byte > String
  public String byteArrayToString(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte abyte : bytes) {
      sb.append(abyte);
      sb.append(" ");
    }
    return sb.toString();
  }

  // String > byte
  public byte[] stringToByteArray(String byteString) {
    String[] split = byteString.split("\\s");
    ByteBuffer buffer = ByteBuffer.allocate(split.length);
    for (String s : split) {
      buffer.put((byte) Integer.parseInt(s));
    }
    return buffer.array();
  }

}
