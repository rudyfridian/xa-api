--
--	IF Not Use Schema 
--

CREATE TABLE mst_city (
	"id" int NOT NULL,
	code varchar(100),
	"name" varchar(200),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	province_id int NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE mst_country (
	country_uuid varchar(36) NOT NULL,
	country_code varchar(3) NOT NULL,
	country_name varchar(150) NOT NULL,
	capital varchar(150) NOT NULL,
	phone_prefix varchar(10),
	"flag" varchar(100),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (country_uuid)
);
CREATE TABLE mst_currency (
	currency_uuid varchar(36) NOT NULL,
	currency_code varchar(3) NOT NULL,
	currency_name varchar(150) NOT NULL,
	currency_sign varchar(10),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (currency_uuid)
);
CREATE TABLE mst_district (
	"id" int NOT NULL,
	code varchar(100),
	"name" varchar(200),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	city_id int NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE file_metadata ( 
	file_metadata_uuid varchar(36) NOT NULL,
	file_checksum varchar(36) NOT NULL,
	file_full_name text NOT NULL,
	file_short_name text,
	file_extension varchar(100),
	file_full_path text,
	file_location text,
	file_size int,
	file_date timestamp,
	file_type varchar(255),
	file_download_count int,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (file_metadata_uuid)
);
CREATE TABLE mst_language (
	language_uuid varchar(36) NOT NULL,
	language_code varchar(10) NOT NULL,
	language_identifier varchar(100) NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (language_uuid)
);
CREATE TABLE mst_province (
	"id" int NOT NULL,
	code varchar(100),
	"name" varchar(200),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	country_uuid varchar(36) NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE mst_r_country_currency (
	country_uuid varchar(36) NOT NULL,
	currency_uuid varchar(36) NOT NULL
);
CREATE TABLE mst_subdistrict (
	"id" int NOT NULL,
	code varchar(100),
	"name" varchar(200),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	district_id int NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE mst_zipcode (
	"id" int NOT NULL,
	zipcode varchar(100),
	subdistrict_id int,
	district_id int,
	city_id int,
	province_id int,
	country_uuid varchar(36),
	PRIMARY KEY ("id")
);
CREATE TABLE oauth_access_token (
	token_id varchar(255),
	"token" bytea,
	authentication_id varchar(255) NOT NULL,
	user_name varchar(255),
	client_id varchar(255),
	authentication bytea,
	refresh_token varchar(255),
	PRIMARY KEY (authentication_id)
);
CREATE TABLE oauth_approvals (
	userId varchar(255),
	clientId varchar(255),
	"scope" varchar(255),
	status varchar(10),
	expiresAt timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lastModifiedAt timestamp
);
CREATE TABLE oauth_client_details (
	client_id varchar(255) NOT NULL,
	resource_ids varchar(255),
	client_secret varchar(255),
	"scope" varchar(255),
	authorized_grant_types varchar(255),
	web_server_redirect_uri varchar(255),
	authorities varchar(255),
	access_token_validity int,
	refresh_token_validity int,
	additional_information varchar(4096),
	autoapprove varchar(255),
	PRIMARY KEY (client_id)
);
CREATE TABLE oauth_client_token (
	token_id varchar(255),
	"token" bytea,
	authentication_id varchar(255) NOT NULL,
	user_name varchar(255),
	client_id varchar(255),
	PRIMARY KEY (authentication_id)
);
CREATE TABLE oauth_code (
	code varchar(255),
	authentication bytea
);
CREATE TABLE oauth_refresh_token (
	token_id varchar(255),
	"token" bytea,
	authentication bytea
);
CREATE TABLE sec_corporate (
	corporate_uuid varchar(36) NOT NULL,
	corporate_id varchar(50) NOT NULL,
	corporate_name varchar(255) NOT NULL,
	corporate_non_expired boolean DEFAULT true NOT NULL,
	email varchar(150),
	address text,
	telp_number varchar(20),
	fax_number varchar(20),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (corporate_uuid)
);
CREATE TABLE sec_function (
	function_uuid varchar(36) NOT NULL,
	menu_uuid varchar(36) NOT NULL,
	role_uuid varchar(36) NOT NULL,
	"access" varchar(30) DEFAULT 'INQ,GET,ADD,PUT,DEL,APV' NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (function_uuid)
);
CREATE TABLE sec_menu (
	menu_uuid varchar(36) NOT NULL,
	title varchar(100) NOT NULL,
	url text,
	"level" int NOT NULL,
	"ordering" int NOT NULL,
	ordering_str varchar(100),
	icon varchar(100),
	is_leaf boolean DEFAULT false NOT NULL,
	is_home boolean DEFAULT false NOT NULL,
	is_group boolean DEFAULT false NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	parent_uuid varchar(36),
	PRIMARY KEY (menu_uuid)
);
CREATE TABLE sec_menu_i18n (
	menu_i18n_uuid varchar(36) NOT NULL,
	menu_uuid varchar(36) NOT NULL,
	locale_code varchar(10) NOT NULL,
	title varchar(100) NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (menu_i18n_uuid)
);

CREATE TABLE sec_r_user_corporate (
	user_uuid varchar(36) NOT NULL,
	corporate_uuid varchar(36) NOT NULL
);
CREATE TABLE sec_r_user_role (
	user_uuid varchar(36) NOT NULL,
	role_uuid varchar(36) NOT NULL
);
CREATE TABLE sec_role (
	role_uuid varchar(36) NOT NULL,
	role_name varchar(50) NOT NULL,
	description text,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (role_uuid)
);
CREATE TABLE sec_user (
	user_uuid varchar(36) NOT NULL,
	username varchar(25) NOT NULL,
	"password" text NOT NULL,
	account_enabled boolean DEFAULT true NOT NULL,
	account_non_expired boolean DEFAULT true NOT NULL,
	account_non_locker boolean DEFAULT true NOT NULL,
	credentials_non_expired boolean DEFAULT true NOT NULL,
	fullname varchar(200) NOT NULL,
	email varchar(150) NOT NULL,
	address text,
	city varchar(200),
	province varchar(200),
	district_code varchar(100),
	phone_number varchar(20),
	mobile_number varchar(20),
	image varchar(250),
	description text,
	verification_code varchar(100),
	raw text,
	locale varchar(10) DEFAULT 'en-US' NOT NULL,
	authority_default varchar(100),
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (user_uuid)
);
CREATE TABLE mst_locale (
	locale_uuid varchar(36) NOT NULL,
	locale_code varchar(10) NOT NULL,
	locale_identifier varchar(100) NOT NULL,
	locale_icon varchar(100),
	locale_default boolean DEFAULT false NOT NULL,
	locale_enabled boolean DEFAULT true NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (locale_uuid)
);
CREATE TABLE mst_parameter (
	parameter_uuid varchar(36) NOT NULL,
	parameter_code varchar(50) NOT NULL,
	parameter_value text NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	parameter_group_uuid varchar(36) NOT NULL,
	PRIMARY KEY (parameter_uuid)
);
CREATE TABLE mst_parameter_group (
	parameter_group_uuid varchar(36) NOT NULL,
	parameter_group_code varchar(50) NOT NULL,
	parameter_group_name varchar(100) NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (parameter_group_uuid)
);
CREATE TABLE mst_parameter_i18n (
	parameter_i18n_uuid varchar(36) NOT NULL,
	parameter_uuid varchar(36) NOT NULL,
	locale_uuid varchar(36) NOT NULL,
	parameter_value text NOT NULL,
	"version" int DEFAULT 0 NOT NULL,
	is_active boolean DEFAULT true NOT NULL,
	created_date timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(25),
	modified_date timestamp,
	modified_by varchar(25),
	PRIMARY KEY (parameter_i18n_uuid)
);
CREATE TABLE wfl_delegation (
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
CREATE TABLE wfl_document_status (
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
CREATE TABLE wfl_document_status_i18n (
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
CREATE TABLE wfl_document_transition (
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
CREATE TABLE wfl_document_type (
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
CREATE TABLE wfl_tasklist (
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
CREATE TABLE wfl_tasklist_detail (
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
ALTER TABLE sec_user ADD CONSTRAINT username UNIQUE (username);
ALTER TABLE sec_user ADD CONSTRAINT email UNIQUE (email);
ALTER TABLE sec_corporate ADD CONSTRAINT corporate_id UNIQUE (corporate_id);


ALTER TABLE mst_city
	ADD FOREIGN KEY (province_id) 
	REFERENCES mst_province ("id");

ALTER TABLE mst_district
	ADD FOREIGN KEY (city_id) 
	REFERENCES mst_city ("id");

ALTER TABLE mst_province
	ADD FOREIGN KEY (country_uuid) 
	REFERENCES mst_country (country_uuid);

ALTER TABLE mst_r_country_currency
	ADD FOREIGN KEY (country_uuid) 
	REFERENCES mst_country (country_uuid);

ALTER TABLE mst_r_country_currency
	ADD FOREIGN KEY (currency_uuid) 
	REFERENCES mst_currency (currency_uuid);

ALTER TABLE mst_subdistrict
	ADD FOREIGN KEY (district_id) 
	REFERENCES mst_district ("id");

ALTER TABLE mst_zipcode
	ADD FOREIGN KEY (city_id) 
	REFERENCES mst_city ("id");

ALTER TABLE mst_zipcode
	ADD FOREIGN KEY (country_uuid) 
	REFERENCES mst_country (country_uuid);

ALTER TABLE mst_zipcode
	ADD FOREIGN KEY (district_id) 
	REFERENCES mst_district ("id");

ALTER TABLE mst_zipcode
	ADD FOREIGN KEY (province_id) 
	REFERENCES mst_province ("id");

ALTER TABLE mst_zipcode
	ADD FOREIGN KEY (subdistrict_id) 
	REFERENCES mst_subdistrict ("id");

ALTER TABLE sec_function
	ADD FOREIGN KEY (role_uuid) 
	REFERENCES sec_role (role_uuid);

ALTER TABLE sec_function
	ADD FOREIGN KEY (menu_uuid) 
	REFERENCES sec_menu (menu_uuid);

ALTER TABLE sec_menu
	ADD FOREIGN KEY (parent_uuid) 
	REFERENCES sec_menu (menu_uuid);

ALTER TABLE sec_menu_i18n
	ADD FOREIGN KEY (menu_uuid) 
	REFERENCES sec_menu (menu_uuid);

ALTER TABLE sec_r_user_corporate
	ADD FOREIGN KEY (corporate_uuid) 
	REFERENCES sec_corporate (corporate_uuid);

ALTER TABLE sec_r_user_corporate
	ADD FOREIGN KEY (user_uuid) 
	REFERENCES sec_user (user_uuid);

ALTER TABLE sec_r_user_role
	ADD FOREIGN KEY (role_uuid) 
	REFERENCES sec_role (role_uuid);

ALTER TABLE sec_r_user_role
	ADD FOREIGN KEY (user_uuid) 
	REFERENCES sec_user (user_uuid);

ALTER TABLE mst_parameter
	ADD FOREIGN KEY (parameter_group_uuid) 
	REFERENCES mst_parameter_group (parameter_group_uuid);

ALTER TABLE mst_parameter_i18n
	ADD FOREIGN KEY (parameter_uuid) 
	REFERENCES mst_parameter (parameter_uuid);

ALTER TABLE mst_parameter_i18n
	ADD FOREIGN KEY (locale_uuid) 
	REFERENCES mst_locale (locale_uuid);

ALTER TABLE wfl_document_status_i18n
	ADD FOREIGN KEY (document_status_uuid) 
	REFERENCES wfl_document_status (document_status_uuid);

ALTER TABLE wfl_document_transition
	ADD FOREIGN KEY (doc_status_from) 
	REFERENCES wfl_document_status (document_status_uuid);

ALTER TABLE wfl_document_transition
	ADD FOREIGN KEY (document_type_uuid) 
	REFERENCES wfl_document_type (document_type_uuid);

ALTER TABLE wfl_document_transition
	ADD FOREIGN KEY (doc_status_to) 
	REFERENCES wfl_document_status (document_status_uuid);

ALTER TABLE wfl_tasklist
	ADD FOREIGN KEY (document_type_uuid) 
	REFERENCES wfl_document_type (document_type_uuid);

ALTER TABLE wfl_tasklist
	ADD FOREIGN KEY (document_transition) 
	REFERENCES wfl_document_transition (document_transition_uuid);

ALTER TABLE wfl_tasklist
	ADD FOREIGN KEY (document_status) 
	REFERENCES wfl_document_status (document_status_uuid);

ALTER TABLE wfl_tasklist
	ADD FOREIGN KEY (delegation) 
	REFERENCES wfl_delegation (delegation_uuid);

ALTER TABLE wfl_tasklist_detail
	ADD FOREIGN KEY (task_uuid) 
	REFERENCES wfl_tasklist (task_uuid);

ALTER TABLE wfl_tasklist_detail
	ADD FOREIGN KEY (document_transition_uuid) 
	REFERENCES wfl_document_transition (document_transition_uuid);

ALTER TABLE wfl_tasklist_detail
	ADD FOREIGN KEY (document_status_uuid) 
	REFERENCES wfl_document_status (document_status_uuid);

ALTER TABLE wfl_tasklist_detail
	ADD FOREIGN KEY (delegation_uuid) 
	REFERENCES wfl_delegation (delegation_uuid);
