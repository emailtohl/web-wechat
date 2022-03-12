package helei.wx.oa.infra;

import org.apache.http.HttpHost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 公众号的底层配置
 *
 * @author HeLei
 */
@Configuration
public class OAInfraConf {
  private final Logger logger = LoggerFactory.getLogger(OAInfraConf.class);
  @Autowired
  Environment env;

  @Bean
  public CloseableHttpClient acceptsUntrustedCertsHttpClient()
      throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    HttpClientBuilder builder = HttpClientBuilder.create();

    String proxyHost = env.getProperty("proxyHost");
    String proxyPort = env.getProperty("proxyPort");
    if (proxyHost != null && proxyHost.length() > 0 && proxyPort != null && proxyPort.length() > 0) {
      builder.setProxy(new HttpHost(proxyHost, Integer.parseInt(proxyPort)));
    }

    KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
    // setup a Trust Strategy that allows all certificates.
    SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(trustStore, new TrustStrategy() {
      public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        logger.debug("以下请求被信任且接受： \n" + "X509Certificate : " + Arrays.deepToString(arg0) + "  " + arg1);
        return true;
      }
    }).build();
    builder.setSSLContext(sslContext);
    // don't check Hostnames, either.
    // -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if
    // you don't want to weaken
    HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
    // here's the special part:
    // -- need to create an SSL Socket Factory, to use our weakened "trust
    // strategy";
    // -- and create a Registry, to register it.
    SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
        .register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory)
        .build();
    // now, we create connection-manager using our Registry.
    // -- allows multi-threaded use
    PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    connMgr.setMaxTotal(200);
    connMgr.setDefaultMaxPerRoute(100);
    builder.setConnectionManager(connMgr);
    // finally, build the HttpClient;
    // -- done!
    return builder.build();
  }

  @Bean
  public HttpsClient httpsClient() throws KeyStoreException, NoSuchAlgorithmException, IOException, KeyManagementException {
    return new HttpsClient(acceptsUntrustedCertsHttpClient());
  }

  @Bean
  public RestTemplate httpsForWechatRestTemplate()
      throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, IOException {
    CloseableHttpClient httpClient = acceptsUntrustedCertsHttpClient();
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
        httpClient);
    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
    reInitMessageConverter(restTemplate);
    return restTemplate;
  }

  /**
   * RestTemplate默认使用的StringHttpMessageConverter采用ISO-8859-1编码，所以对中文支持不友好，这里需要替换为UTF-8
   * 注意： List<HttpMessageConverter<?>>中的各个元素顺序不能变，例如StringHttpMessageConverter在第二个位置，只能原地替换
   */
  private void reInitMessageConverter(RestTemplate restTemplate) {
    List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
    HttpMessageConverter<?> converterTarget = null;
    for (HttpMessageConverter<?> item : converterList) {
      if (item.getClass() == StringHttpMessageConverter.class) {
        converterTarget = item;
        break;
      }
    }
    if (converterTarget != null) {
      Collections.replaceAll(converterList, converterTarget, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }
  }

  @Bean
  public TokenService tokenService() throws KeyStoreException, NoSuchAlgorithmException, IOException, KeyManagementException {
    String appID = env.getProperty("appID");
    String appsecret = env.getProperty("appsecret");
    RestTemplate restTemplate = httpsForWechatRestTemplate();
    return new TokenService(restTemplate, appID, appsecret);
  }

  @Bean
  public OauthService oauthService() throws KeyStoreException, NoSuchAlgorithmException, IOException, KeyManagementException {
    String appID = env.getProperty("appID");
    String appsecret = env.getProperty("appsecret");
    HttpsClient httpsClient = httpsClient();
    TokenService tokenService = tokenService();
    return new OauthService(httpsClient, tokenService, appID, appsecret);
  }

  @Bean
  public SignService signService() {
    return new SignService(env.getProperty("Token"));
  }
}