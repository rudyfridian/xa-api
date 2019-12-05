package io.github.xaphira.feign.configuration;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.Decoder;

@Configuration
public class FeignConfiguration {
	
	@Autowired
	private FeignSignatureInterceptor feignSignatureInterceptor;
	
	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor() {
		return new BearerRequestInterceptor(feignSignatureInterceptor);
	}

	@Primary
    @Bean
    public Decoder feignDecoder() {
        HttpMessageConverter<?> jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }
	
    @Bean
    public Retryer retryer() {
        return new CustomRetryer(30000, 3);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    public ObjectMapper customObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        //Customize as much as you want
        return objectMapper;
    }

	/*@Bean
    public Client client() throws NoSuchAlgorithmException, KeyManagementException {
    	TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}
		} };

		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			System.err.println("SSL Instance Fetching Failed, " + e1.getMessage());
		}
		try {
			sc.init(null, trustAllCerts, null);
		} catch (KeyManagementException e) {
			System.err.println("SSL Context Initializing Failed, " + e.getMessage());
		}

        Client trustSSLSockets = new Client.Default(sc.getSocketFactory(), new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
            	return true;
            }
        });
        return trustSSLSockets;
    }*/

}
