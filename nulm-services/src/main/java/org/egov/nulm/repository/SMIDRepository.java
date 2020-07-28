
package org.egov.nulm.repository;

import org.egov.nulm.config.NULMConfiguration;
import org.egov.nulm.model.NULMSMIDRequest;
import org.egov.nulm.model.SMIDApplication;
import org.egov.nulm.producer.Producer;
import org.egov.nulm.repository.rowmapper.SEPRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SMIDRepository {

	private JdbcTemplate jdbcTemplate;

	private Producer producer;

	private NULMConfiguration config;

	private SEPRowMapper seprowMapper;
	
	@Autowired
	public SMIDRepository(JdbcTemplate jdbcTemplate, Producer producer, NULMConfiguration config,
			SEPRowMapper seprowMapper
			) {
		this.jdbcTemplate = jdbcTemplate;
		this.producer = producer;
		this.config = config;
		this.seprowMapper = seprowMapper;
	}
	
	public void createSMIDApplication(SMIDApplication smidApplication) {
		NULMSMIDRequest infoWrapper = NULMSMIDRequest.builder().nulmSmidRequest(smidApplication).build();
	    producer.push(config.getSMIDApplicationSaveTopic(), infoWrapper);
	}
}
