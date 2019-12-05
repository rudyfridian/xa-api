package io.github.xaphira.feign.configuration;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.xaphira.common.security.SignatureEncrypt;
import io.github.xaphira.common.utils.DateUtil;

@Service
public class FeignSignatureInterceptor {
	
	@Value("${xa.signature.private-key}")
	private String privateKey;
	
	@Value("${xa.signature.public-key}")
	private String publicKey;
	
	public String getTimestamp() {
		return new Integer(new Double(Math.floor(DateUtil.DATE_TIME/1000)).intValue()).toString();
	}
	
	public String getPublicKey() {
		return this.publicKey;
	}
	
	public String getSignature(String path, String jwt) {
		String datenow = DateUtil.formatDate(new Date(DateUtil.DATE_TIME), DateUtil.DEFAULT_FORMAT_DATE);
		String message = this.getPublicKey() + ":" + 
				this.getTimestamp() + ":" +
				datenow  + ":" +
				path  + ":" +
				jwt;
		try {
			return SignatureEncrypt.getInstance().hash(this.privateKey, message);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
