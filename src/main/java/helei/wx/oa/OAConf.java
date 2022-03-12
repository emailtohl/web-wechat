package helei.wx.oa;

import helei.wx.oa.infra.OAInfraConf;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OAInfraConf.class})
@ComponentScan("helei.wx.oa")
public class OAConf {
}
