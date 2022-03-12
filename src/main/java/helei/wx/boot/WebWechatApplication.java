package helei.wx.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("helei.wx.oa")
@EnableScheduling
public class WebWechatApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebWechatApplication.class, args);
  }

}
