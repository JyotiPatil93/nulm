
package org.egov.nulm.repository;

import java.util.HashMap;
import java.util.Map;

import org.egov.nulm.common.CommonConstants;
import org.egov.nulm.config.NULMConfiguration;
import org.egov.nulm.model.NulmShgMemberRequest;
import org.egov.nulm.model.NulmSmidRequest;
import org.egov.nulm.model.SmidApplication;
import org.egov.nulm.model.SmidShgMemberApplication;
import org.egov.nulm.producer.Producer;
import org.egov.nulm.repository.builder.NULMQueryBuilder;
import org.egov.nulm.repository.rowmapper.SMIDRowMapper;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SmidShgMemberRepository {

	private JdbcTemplate jdbcTemplate;

	private Producer producer;

	private NULMConfiguration config;

	private SMIDRowMapper smidrowMapper;
	
	@Autowired
	public SmidShgMemberRepository(JdbcTemplate jdbcTemplate, Producer producer, NULMConfiguration config,
			SMIDRowMapper smidrowMapper
			) {
		this.jdbcTemplate = jdbcTemplate;
		this.producer = producer;
		this.config = config;
		this.smidrowMapper = smidrowMapper;
	}
	
	public void createMembers(SmidShgMemberApplication smidApplication) {
		NulmShgMemberRequest infoWrapper = NulmShgMemberRequest.builder().SmidShgMemberApplication(smidApplication).build();
	    producer.push(config.getSmidShgMemberSaveTopic(), infoWrapper);
	}
	
	public void updateMembers(SmidShgMemberApplication smidApplication) {
		NulmShgMemberRequest infoWrapper = NulmShgMemberRequest.builder().SmidShgMemberApplication(smidApplication).build();
	    producer.push(config.getSmidShgMemberUpdateTopic(), infoWrapper);	
	    }
	
	public void checkShgUuid(SmidShgMemberApplication smidapplication) {
		Map<String, String> errorMap = new HashMap<>();
		int i= 0;
		 i= jdbcTemplate.queryForObject(NULMQueryBuilder.SHG_UUID_EXIST_QUERY,
				new Object[] { smidapplication.getShgUuid(),smidapplication.getTenantId()},
				Integer.class);

		if(i==0)
		{
			errorMap.put(CommonConstants.INVALID_SHG_UUID,CommonConstants.INVALID_SHG_UUID_MESSAGE);
			throw new CustomException(errorMap);
		}
	}
}
