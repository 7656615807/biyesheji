package com.sample;


public class User {

  private String userid;
  private long rid;
  private String password;
  private String name;
  private long level;
  private String phone;
  private String email;
  private String photo;
  private long flag;
  private java.sql.Timestamp lastlogin;
  private long lockflag;


  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }


  public long getRid() {
    return rid;
  }

  public void setRid(long rid) {
    this.rid = rid;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getLevel() {
    return level;
  }

  public void setLevel(long level) {
    this.level = level;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }


  public long getFlag() {
    return flag;
  }

  public void setFlag(long flag) {
    this.flag = flag;
  }


  public java.sql.Timestamp getLastlogin() {
    return lastlogin;
  }

  public void setLastlogin(java.sql.Timestamp lastlogin) {
    this.lastlogin = lastlogin;
  }


  public long getLockflag() {
    return lockflag;
  }

  public void setLockflag(long lockflag) {
    this.lockflag = lockflag;
  }

}
