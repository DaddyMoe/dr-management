package com.recklassrekkids.drm.console.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 *
 * @author moses@doraventures.com
 */
@Service("helloService")
public class HelloMessageService {

  @Value("${name:unknown}")
  private String name;

  public String getMessage() {
    return getMessage(name);
  }

  public String getMessage(String name) {
    return "Hello " + name;
  }

}
