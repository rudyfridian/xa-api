CREATE TABLE workflow.wfl_delegation (
	delegation_uuid varchar(36) NOT NULL,
	user_from varchar(36),
	user_to varchar(36),
	delegate_reason text,
	start_date date,
	end_date date,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (delegation_uuid)
);
CREATE TABLE workflow.wfl_document_status (
	document_status_uuid varchar(36) NOT NULL,
	document_status_code varchar(50) NOT NULL,
	document_status_name varchar(200) NOT NULL,
	document_status_description text,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (document_status_uuid)
);
CREATE TABLE workflow.wfl_document_status_i18n (
	document_status_i18n_uuid varchar(36) NOT NULL,
	locale_code varchar(10) NOT NULL,
	document_status_uuid varchar(36) NOT NULL,
	document_status_name varchar(100) NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (document_status_i18n_uuid)
);
CREATE TABLE workflow.wfl_document_transition (
	document_transition_uuid varchar(36) NOT NULL,
	doc_status_from varchar(36),
	doc_status_to varchar(36),
	document_type_uuid varchar(36),
	role_from varchar(50),
	role_to varchar(50),
	"start" boolean DEFAULT false,
	"end" boolean DEFAULT false,
	raw text,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (document_transition_uuid)
);
CREATE TABLE workflow.wfl_document_type (
	document_type_uuid varchar(36) NOT NULL,
	document_type_code varchar(50) NOT NULL,
	document_type_name varchar(150) NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (document_type_uuid)
);
CREATE TABLE workflow.wfl_tasklist (
	task_uuid varchar(36) NOT NULL,
	document_type_uuid varchar(36),
	document_status varchar(36),
	document_transition varchar(36),
	document_no varchar(36),
	delegation varchar(36),
	"user" varchar(36),
	"role" varchar(36),
	start_date timestamp,
	end_date timestamp,
	sla_days int,
	sla_hours int,
	sla_minutes int,
	PRIMARY KEY (task_uuid)
);
CREATE TABLE workflow.wfl_tasklist_detail (
	task_detail_uuid varchar(36) NOT NULL,
	task_uuid varchar(36) NOT NULL,
	document_status_uuid varchar(36),
	document_transition_uuid varchar(36),
	delegation_uuid varchar(36),
	user_from varchar(36),
	role_from varchar(36),
	user_to varchar(36),
	role_to varchar(36),
	start_date timestamp,
	end_date timestamp,
	sla_days int,
	sla_hours int,
	sla_minutes int,
	PRIMARY KEY (task_detail_uuid)
);

ALTER TABLE workflow.wfl_document_status_i18n
	ADD FOREIGN KEY (document_status_uuid) 
	REFERENCES workflow.wfl_document_status (document_status_uuid);

ALTER TABLE workflow.wfl_document_transition
	ADD FOREIGN KEY (doc_status_from) 
	REFERENCES workflow.wfl_document_status (document_status_uuid);

ALTER TABLE workflow.wfl_document_transition
	ADD FOREIGN KEY (document_type_uuid) 
	REFERENCES workflow.wfl_document_type (document_type_uuid);

ALTER TABLE workflow.wfl_document_transition
	ADD FOREIGN KEY (doc_status_to) 
	REFERENCES workflow.wfl_document_status (document_status_uuid);

ALTER TABLE workflow.wfl_tasklist
	ADD FOREIGN KEY (document_type_uuid) 
	REFERENCES workflow.wfl_document_type (document_type_uuid);

ALTER TABLE workflow.wfl_tasklist
	ADD FOREIGN KEY (document_transition) 
	REFERENCES workflow.wfl_document_transition (document_transition_uuid);

ALTER TABLE workflow.wfl_tasklist
	ADD FOREIGN KEY (document_status) 
	REFERENCES workflow.wfl_document_status (document_status_uuid);

ALTER TABLE workflow.wfl_tasklist
	ADD FOREIGN KEY (delegation) 
	REFERENCES workflow.wfl_delegation (delegation_uuid);

ALTER TABLE workflow.wfl_tasklist_detail
	ADD FOREIGN KEY (task_uuid) 
	REFERENCES workflow.wfl_tasklist (task_uuid);

ALTER TABLE workflow.wfl_tasklist_detail
	ADD FOREIGN KEY (document_transition_uuid) 
	REFERENCES workflow.wfl_document_transition (document_transition_uuid);

ALTER TABLE workflow.wfl_tasklist_detail
	ADD FOREIGN KEY (document_status_uuid) 
	REFERENCES workflow.wfl_document_status (document_status_uuid);

ALTER TABLE workflow.wfl_tasklist_detail
	ADD FOREIGN KEY (delegation_uuid) 
	REFERENCES workflow.wfl_delegation (delegation_uuid);
