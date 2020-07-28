package org.egov.nulm.repository.builder;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SEPQueryBuilder {
	public static final String GET_SEP_APPLICATION_QUERY = "SELECT  NA.application_uuid,  NA.application_id,  NA.nulm_application_id,  NA.application_status, \n" + 
			"        NA.name,  NA.gender,  NA.age,  NA.dob,  NA.adhar_no,  NA.mother_name,  NA.father_or_husband_name, \n" + 
			"        NA.occupation,  NA.address,  NA.contact,  NA.since_how_long_in_chandigarh,  NA.qualification, \n" + 
			"        NA.category,  NA.is_urban_poor,  NA.is_minority,  NA.is_handicapped,  NA.is_loan_from_bankinginstitute, \n" + 
			"        NA.is_repayment_made,  NA.bpl_no,  NA.minority,  NA.type_of_business_to_be_started, \n" + 
			"        NA.previous_experience,  NA.place_of_work,  NA.bank_details,  NA.no_of_family_members, \n" + 
			"        NA.project_cost,  NA.loan_amount,  NA.recommended_amount,  NA.recommended_by, \n" + 
			"        NA.representative_name,  NA.representative_address,  NA.tenant_id,NA.is_active,NA.created_by ,NA.created_time,NA.last_modified_by, NA.last_modified_time,\n" + 
			"        array_to_json(array_agg(json_build_object('documentType',ND.document_type,'filestoreId',ND.filestore_id,'documnetUuid',ND.document_uuid,'isActive',ND.is_active,\n" + 
			"        'tenantId',ND.tenant_id,'applicationUuid',ND.application_uuid) ))as document \n" + 
			"  FROM public.nulm_sep_application_detail NA inner  join nulm_sep_application_document ND on NA.application_uuid=ND.application_uuid and NA.tenant_id=ND.tenant_id\n" + 
			"  where NA.application_id=(case when ?  <>'' then ?  else NA.application_id end) and NA.created_by=(case when ?  <>'' then ?  else NA.created_by end) AND NA.tenant_id=? AND NA.application_status=(case when ?  <>'' then ?  else NA.application_status end) \n" + 
			"  AND NA.is_active='true' AND ND.is_active='true' group by NA.application_uuid";
	
	 public static final String GET_SEP_DOCUMENT_QUERY="SELECT count(*) \n" + 
	 		"        FROM public.nulm_sep_application_document \n" + 
	 		"        WHERE application_uuid=? and tenant_id=? and filestore_id=? and document_type=? and is_active='true'; ";

}
