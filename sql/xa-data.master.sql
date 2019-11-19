--
-- Dumping data for table mst_locale
--

INSERT INTO master.mst_locale (locale_uuid, locale_code, locale_identifier, locale_icon, locale_default, locale_enabled, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('061e3d94-bfef-4e5f-bb24-735ba18e435f', 'id-ID', 'Indonesian (Indonesia)', 'id', false, true, 0, true, '2018-12-04 08:59:55', NULL, NULL, NULL),
('6a6ae290-7c33-46bd-a05a-f6f3964e4cb4', 'en-US', 'English (United States)', 'us', true, true, 0, true, '2018-12-04 08:59:55', NULL, NULL, NULL);

--
-- Dumping data for table mst_parameter_group
--

INSERT INTO master.mst_parameter_group (parameter_group_uuid, parameter_group_code, parameter_group_name, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('a34ce03b-35e5-40d7-9e95-47cb77cf623a', 'GENDER', 'Gender', 0, true, '2018-12-05 11:45:42', NULL, NULL, NULL),
('799bcdcb-b922-4fee-8266-6cd1e2142492', 'FILE_EXCLUSION', 'File Exclusion', 0, true, '2018-12-05 11:45:42', NULL, NULL, NULL);

--
-- Dumping data for table mst_parameter
--

INSERT INTO master.mst_parameter (parameter_uuid, parameter_code, parameter_value, "version", is_active, created_date, created_by, modified_date, modified_by, parameter_group_uuid) VALUES
('eb8c8621-c79c-4f1b-bd77-757d5ed106e5', 'GENDER.MALE', 'Male', 0, true, '2018-12-05 11:46:40', NULL, NULL, NULL, 'a34ce03b-35e5-40d7-9e95-47cb77cf623a'),
('f6dd4b90-6c06-4066-84a3-0599d1a06003', 'GENDER.FEMALE', 'Female', 0, true, '2018-12-05 11:46:40', NULL, NULL, NULL, 'a34ce03b-35e5-40d7-9e95-47cb77cf623a'),
('d78712b5-6c1e-498f-9981-9beb6d0997c5', 'EXE', 'exe', 0, true, '2018-12-05 11:46:40', NULL, NULL, NULL, '799bcdcb-b922-4fee-8266-6cd1e2142492'),
('112cf342-b9c9-4139-9a58-3cdfb5c68e07', 'BASH', 'bash', 0, true, '2018-12-05 11:46:40', NULL, NULL, NULL, '799bcdcb-b922-4fee-8266-6cd1e2142492'),
('56bee47f-7304-439d-9295-67d2b323a1ef', 'SH', 'sh', 0, true, '2018-12-05 11:46:40', NULL, NULL, NULL, '799bcdcb-b922-4fee-8266-6cd1e2142492'),
('453c7792-244e-41a7-b064-a2ab362fbbf1', 'BAT', 'bat', 0, true, '2018-12-05 11:46:40', NULL, NULL, NULL, '799bcdcb-b922-4fee-8266-6cd1e2142492');

--
-- Dumping data for table mst_parameter_i18n
--

INSERT INTO master.mst_parameter_i18n (parameter_i18n_uuid, parameter_uuid, locale_uuid, parameter_value, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('4e1cf97d-da2b-4502-a8fb-8b7c56685061', 'eb8c8621-c79c-4f1b-bd77-757d5ed106e5', '061e3d94-bfef-4e5f-bb24-735ba18e435f', 'Pria', 0, true, '2018-12-05 11:48:40', NULL, NULL, NULL),
('fc74760b-a4f3-48db-a3a8-3fe2e230d3bd', 'f6dd4b90-6c06-4066-84a3-0599d1a06003', '061e3d94-bfef-4e5f-bb24-735ba18e435f', 'Wanita', 0, true, '2018-12-05 11:48:40', NULL, NULL, NULL);
