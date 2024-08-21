package com.sparta.commerce.user.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
  USER(Authority.USER),
  ADMIN(Authority.ADMIN);

  private final String authority;

  public String getAuthority() {
    return this.authority;
  }

  public static class Authority {
    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";
  }

}
