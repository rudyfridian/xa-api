package io.github.xaphira.notification.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.xaphira.common.exceptions.BaseControllerException;
import io.github.xaphira.feign.dto.notification.MailNotificationDto;
import io.github.xaphira.notification.service.MailSenderService;
import io.github.xaphira.notification.service.TemplateMailService;

@RequestMapping("/api/notification")
@RestController
public class MailSenderCtrl extends BaseControllerException {

    @Autowired
    private MailSenderService service;

    @Autowired
    private TemplateMailService templateService;

    @RequestMapping(value = "/trx/post/mail/to/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendMail(@RequestBody MailNotificationDto mail) throws Exception {
    	if(mail.getFileTemplate() == null)        
    		service.sendMessageWithText(mail.getFrom(), mail.getTo(), mail.getSubject(), mail.getContent());
    	else
    		service.sendMessageWithText(mail.getFrom(), mail.getTo(), mail.getSubject(), templateService.getTemplate(mail.getBodyTemplate(), mail.getFileTemplate()));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("info", HttpStatus.OK.value());
		data.put("info_description", "Success");
		return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @RequestMapping(value = "/trx/post/mail/broadcast/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> broadcast(@RequestBody List<MailNotificationDto> mails) throws Exception {
    	mails.forEach(mail->{
        	if(mail.getFileTemplate() == null)        
        		service.sendMessageWithText(mail.getFrom(), mail.getTo(), mail.getSubject(), mail.getContent());
        	else
        		service.sendMessageWithText(mail.getFrom(), mail.getTo(), mail.getSubject(), templateService.getTemplate(mail.getBodyTemplate(), mail.getFileTemplate()));
    	});
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("info", HttpStatus.OK.value());
		data.put("info_description", "Success");
		return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
