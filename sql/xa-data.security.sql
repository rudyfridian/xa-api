--
-- Dumping data for table sec_role
--

INSERT INTO security.sec_role (role_uuid, role_name, description, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('06be80df-5c41-42a7-9050-b328312d2f3a', 'ROLE_ADMIN', 'Role Administrator', 0, true, '2018-12-02 20:52:28', NULL, NULL, NULL),
('1af2403b-a4f8-4492-94c1-5d6ab8b4a094', 'ROLE_RADIOGRAPHER', 'Role Doctor Radiographer', 0, true, '2018-12-03 11:57:36', NULL, NULL, NULL),
('d46b4b13-4159-4f5c-923d-5d8dfe3f48de', 'ROLE_PHYSICIAN', 'Role Doctor Clinic', 0, true, '2018-12-03 11:57:36', NULL, NULL, NULL),
('d4fd659f-7bd6-4b1c-9127-e2dcf04651b0', 'ROLE_NURSE', 'Role Nurse', 0, true, '2018-12-10 09:11:00', NULL, NULL, NULL),
('d68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8', 'ROLE_END', 'Role Patient', 0, true, '2018-12-03 11:57:36', NULL, NULL, NULL);

--
-- Dumping data for table sec_user
--

INSERT INTO security.sec_user (user_uuid, username, password, account_enabled, account_non_expired, account_non_locked, credentials_non_expired, fullname, email, address, city, province, district_code, phone_number, mobile_number, image, description, verification_code, raw, locale, theme, authority_default, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('181545af-9410-47ca-8de2-4fd29d9da5d9', 'doctor', '$2a$13$iyvREnHcryjZbnGwLkaJsug7cOBmk06QtbLN/L7k5ncJFPLildo1y', true, true, true, true, 'Doctor 01', 'doctor@cpu.co.id', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'en-US', 'default', 'ROLE_PHYSICIAN', 0, true, '2018-12-05 14:50:55', NULL, NULL, NULL),
('1ac29215-75e8-4e19-b4f1-e076da7ca1ab', 'admin', '$2a$13$nnc2wLwVUw1.swkX353QTOFYoSZgQZPUUvbAd0mgXV7DMu5KorKw.', true, true, true, true, 'Administrator', 'admin@cpu.co.id', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'en-US', 'default', 'ROLE_ADMIN', 0, true, '2018-12-02 20:50:29', NULL, NULL, NULL),
('38527ac6-edb6-4a4f-8e60-eede49c4c2a6', 'radiographer', '$2a$13$iyvREnHcryjZbnGwLkaJsug7cOBmk06QtbLN/L7k5ncJFPLildo1y', true, true, true, true, 'Radiographer', 'radiographer@cpu.co.id', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'en-US', 'default', 'ROLE_RADIOGRAPHER', 0, true, '2018-12-05 14:50:55', NULL, NULL, NULL),
('adfdd958-b9fe-4ebe-bf1e-1a19c0637071', 'patient', '$2a$13$iyvREnHcryjZbnGwLkaJsug7cOBmk06QtbLN/L7k5ncJFPLildo1y', true, true, true, true, 'Patient 01', 'patient01@cpu.co.id', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'en-US', 'default', 'ROLE_END', 0, true, '2018-12-03 13:05:57', NULL, NULL, NULL);

--
-- Dumping data for table oauth_client_details
-- xa-3rd = secretxa3rd01
-- xa-core = secretxa01
-- xa-mobile = secretxa02
-- xa-private = secretprivatexa01
--

INSERT INTO security.oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES
('xa-3rd', 'profile,master', '$2a$13$kh2pUDx33Pj00n63/S0HLOORCRw9/zhxEqdYjnsSRzJIwdRf9FRwe', 'read,write,check_token', 'authorization_code,refresh_token', 'http://xaphira.github.io:69/oauth/secure', 'ROLE_END', 3600, 1800, NULL, '0'),
('xa-core', 'profile,security,master,notification,general,file,report', '$2a$13$NrN1v1S4p0UsM8zDdvcUUeHqeUwmGbrobBaUwCO.eiIDVsIbIX5gy', 'read,write,trust,check_token', 'password,refresh_token', '', 'ROLE_ADMIN,ROLE_RADIOGRAPHER,ROLE_PHYSICIAN,ROLE_END', 7200, 3600, NULL, '1'),
('xa-mobile', 'profile,security,master', '$2a$13$jqHw2BuGwJsZXf.eF3XVlOgGxkquOGx4vRDL6ll8UUyr0wEj03YLO', 'read,write,check_token', 'password,refresh_token', '', 'ROLE_END', 7200, 3600, NULL, '1'),
('xa-private', 'profile,security,master,notification,general,file,report', '$2a$13$5wGOOgm9sN52flJGIdnn3urz9h4xubtDLJnaNmhmRTvqJUiuTJLny', 'read,write,trust,check_token', 'client_credentials', '', 'ROLE_PRIVATE', 30, 15, NULL, '1');

--
-- Dumping data for table sec_corporate
--

INSERT INTO security.sec_corporate(corporate_uuid, corporate_id, corporate_name, corporate_non_expired, email, address, telp_number) VALUES
	('0ae4b095-d957-4ff1-a34d-7a440cc8d8ca', 'CPU', 'PT. Cipta Pusaka Utama', true, 'admin@cpu.co.id', 'Fortune Belleza Blok D1/E15 Graha Raya, Kota Tangerang Banten', '+21 2221 9967'),
	('ae7efdc0-0302-4bc3-ab81-1f90b04d7393', 'DUMMY', 'Dummy Hospital', true, 'dummy_hospital@mailinator.com', 'Dummy', '+23456789');

--
-- Dumping data for table sec_menu
--

INSERT INTO security.sec_menu (menu_uuid, title, url, "level", ordering, ordering_str, icon, is_leaf, is_home, is_group, "version", is_active, created_date, created_by, modified_date, modified_by, parent_uuid) VALUES
('b9029fd3-44cd-479c-965d-a8da1bfb20eb', 'Dashboard', '/app/dashboard', 0, 0, '000', 'nb-home', false, true, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('77687148-0cda-4a4a-96f1-900cd986c326', 'Module PACS', '#', 0, 1, '001', NULL, false, false, true, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('5f4872f1-627e-4789-adf7-dc6db3884267', 'DICOM Viewer', '#', 0, 2, '002', 'nb-keypad', false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, NULL),
('079f7d96-8ef9-40d5-ab1a-0f6641e3e8cf', 'Archive', '/app/pacs/archive', 1, 0, '002.000', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, '5f4872f1-627e-4789-adf7-dc6db3884267'),
('0019a3e6-86ed-46d4-b6b2-e9525385c65f', 'Upload', '/app/pacs/upload', 1, 1, '002.001', NULL, false, false, false, 0, true, '2018-12-04 13:35:36', NULL, NULL, NULL, '5f4872f1-627e-4789-adf7-dc6db3884267');

--
-- Dumping data for table sec_menu_i18n
--

INSERT INTO security.sec_menu_i18n (menu_i18n_uuid, menu_uuid, locale_code, title, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('8edd8eaa-c665-4d81-ab41-49a61df686ba', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', 'id-ID', 'Beranda', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('e0498589-37f3-4bd2-8116-9f2713abe051', '77687148-0cda-4a4a-96f1-900cd986c326', 'id-ID', 'Modul PACS', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('f143dd3b-ec59-401f-9796-c7c9d253db04', '5f4872f1-627e-4789-adf7-dc6db3884267', 'id-ID', 'Tampilan DICOM', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('a817dc35-1d8d-4d60-b52a-d823bd5f6b5b', '079f7d96-8ef9-40d5-ab1a-0f6641e3e8cf', 'id-ID', 'Arsip', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL),
('77bf369b-fcf1-4eff-9241-d82e0dfc96a7', '0019a3e6-86ed-46d4-b6b2-e9525385c65f', 'id-ID', 'Unggah', 0, true, '2018-12-04 13:37:15', NULL, NULL, NULL);

--
-- Dumping data for table sec_function
--

INSERT INTO security.sec_function(function_uuid, menu_uuid, role_uuid, access, version, is_active, created_date, created_by, modified_date, modified_by) VALUES
('f313c770-ae05-4664-b514-a471ed2ec577', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,trust', 0, true, '2018-12-19 10:06:50.069434', 'admin', null, null),
('1f687a5e-e61c-4ce2-81fc-94ad9c1dbe8b', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', '1af2403b-a4f8-4492-94c1-5d6ab8b4a094', 'read,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('a7b6713a-ef5f-4a93-a111-a4769197ab83', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', 'd46b4b13-4159-4f5c-923d-5d8dfe3f48de', 'read,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('58a476f7-7986-4123-934e-9dae15e565eb', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', 'd4fd659f-7bd6-4b1c-9127-e2dcf04651b0', 'read,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('22c3a77b-8f2d-4f04-a925-825a1e223a9e', 'b9029fd3-44cd-479c-965d-a8da1bfb20eb', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8', 'read', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('e4870251-ee37-4685-a432-c5e863cbec61', '77687148-0cda-4a4a-96f1-900cd986c326', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('628447be-d7fc-4d0b-bf42-7aa8febaf791', '5f4872f1-627e-4789-adf7-dc6db3884267', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('1646f5de-f72a-4c1a-8250-6152fdc2b5d5', '079f7d96-8ef9-40d5-ab1a-0f6641e3e8cf', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null),
('d6da29e7-d474-4e3a-bd1b-d4c167262b38', '0019a3e6-86ed-46d4-b6b2-e9525385c65f', '06be80df-5c41-42a7-9050-b328312d2f3a', 'read,write,trust', 0, true, '2018-12-19 10:09:14.249008', 'admin', null, null);

--
-- Dumping data for table sec_r_user_corporate
--

INSERT INTO security.sec_r_user_corporate (user_uuid, corporate_uuid) VALUES
('1ac29215-75e8-4e19-b4f1-e076da7ca1ab', '0ae4b095-d957-4ff1-a34d-7a440cc8d8ca'),
('adfdd958-b9fe-4ebe-bf1e-1a19c0637071', 'ae7efdc0-0302-4bc3-ab81-1f90b04d7393'),
('181545af-9410-47ca-8de2-4fd29d9da5d9', 'ae7efdc0-0302-4bc3-ab81-1f90b04d7393'),
('38527ac6-edb6-4a4f-8e60-eede49c4c2a6', 'ae7efdc0-0302-4bc3-ab81-1f90b04d7393');

--
-- Dumping data for table sec_r_user_role
--

INSERT INTO security.sec_r_user_role (user_uuid, role_uuid) VALUES
('1ac29215-75e8-4e19-b4f1-e076da7ca1ab', '06be80df-5c41-42a7-9050-b328312d2f3a'),
('adfdd958-b9fe-4ebe-bf1e-1a19c0637071', 'd68a2ea7-f1cb-484c-a3d4-b669ef3ff3c8'),
('181545af-9410-47ca-8de2-4fd29d9da5d9', 'd46b4b13-4159-4f5c-923d-5d8dfe3f48de'),
('38527ac6-edb6-4a4f-8e60-eede49c4c2a6', '1af2403b-a4f8-4492-94c1-5d6ab8b4a094');
