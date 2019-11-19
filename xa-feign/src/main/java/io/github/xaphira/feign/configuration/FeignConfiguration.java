package io.github.xaphira.feign.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.Retryer;

@Configuration
public class FeignConfiguration {
	
	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor() {
		return new BearerRequestInterceptor();
	}	
	
	
    @Bean
    public Retryer retryer() {
        return new CustomRetryer();
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
