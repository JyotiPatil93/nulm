package org.egov.nulm.config;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.egov.tracer.config.TracerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Import({ TracerConfiguration.class })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class NULMConfiguration {

	@Value("${app.timezone}")
	private String timeZone;

	@PostConstruct
	public void initialize() {
		TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
	}

	@Bean
	@Autowired
	public MappingJackson2HttpMessageConverter jacksonConverter(ObjectMapper objectMapper) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper);
		return converter;
	}
	
	// Idgen Config
		@Value("${egov.idgen.host}")
		private String idGenHost;

		@Value("${egov.idgen.path}")
		private String idGenPath;
		
		@Value("${egov.idgen.nulm.sep.idname}")
		private String sepapplicationNumberIdgenName;

		@Value("${egov.idgen.nulm.sep.idformat}")
		private String sepapplicationNumberIdgenFormat;
		
		@Value("${egov.idgen.nulm.smid.idname}")
		private String smidapplicationNumberIdgenName;

		@Value("${egov.idgen.nulm.smid.idformat}")
		private String smidapplicationNumberIdgenFormat;

		@Value("${egov.idgen.nulm.smid.shg.idname}")
		private String SmidShgIdgenName;

		@Value("${egov.idgen.nulm.smid.shg.idformat}")
		private String SmidShgIdgenFormat;
		
		
	//SEP Config topics
		@Value("${persister.save.sepapplication.topic}")
		private String SEPApplicationSaveTopic;
		
		@Value("${persister.update.sepapplication.topic}")
		private String SEPApplicationUpdateTopic;
		
		@Value("${persister.update.sepapplication.status.topic}")
		private String SEPApplicationUpdateStatusTopic;
		
	//SEP Status
		@Value("${sep.application.approved.status}")
		private String approved;
		
		@Value("${sep.application.rejected.status}")
		private String rejected;
		
	//SEP Search parameter config
		@Value("${egov.user.role.citizen}")
		private String roleCitizenUser;
		
		@Value("${egov.user.role.ngo}")
		private String roleNgoUser;

    //SMID kafka topic
		
		@Value("${persister.save.smidapplication.topic}")
		private String SMIDApplicationSaveTopic;
		
		@Value("${persister.update.smidapplication.topic}")
		private String SMIDApplicationUpdateTopic;
		
		@Value("${persister.update.smidapplication.status.topic}")
		private String SMIDApplicationUpdateStatusTopic;
		
		@Value("${persister.save.smidshg.topic}")
		private String SMIDSHGSaveTopic;
		
		@Value("${persister.update.smidshg.topic}")
		private String SMIDSHGUpdateTopic;
		
		@Value("${persister.updatestatus.smidshg.topic}")
		private String SMIDSHGUpdateStatusTopic;
		
		
		@Value("${persister.save.smidshg.member.topic}")
		private String SmidShgMemberSaveTopic;
		
		@Value("${persister.update.smidshg.member.topic}")
		private String SmidShgMemberUpdateTopic;
		
		
		
	}
