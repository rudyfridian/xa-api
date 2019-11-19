--
-- Dumping data for table wfl_document_status
--

INSERT INTO workflow.wfl_document_status (document_status_uuid, document_status_code, document_status_name, document_status_description, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('306fcbdd-39fe-4df9-aeee-24b3c26c68c9', 'APPROVED', 'Approved', 'Document Approved', 0, true, '2018-12-06 15:29:16', NULL, NULL, NULL),
('86759a6e-e20c-4821-adef-82c4de25b5c0', 'PENDING', 'Pending', 'Document Pending', 0, true, '2018-12-06 15:29:16', NULL, NULL, NULL),
('b9dcf267-063a-465e-9923-b49324cdab11', 'DRAFT', 'Draft', 'Document Draft', 0, true, '2018-12-06 15:29:16', NULL, NULL, NULL),
('e005ceac-d420-45f5-a9e8-446df3745a90', 'REJECT', 'Reject', 'Document Reject', 0, true, '2018-12-06 15:29:16', NULL, NULL, NULL);

--
-- Dumping data for table wfl_document_status_i18n
--

INSERT INTO workflow.wfl_document_status_i18n (document_status_i18n_uuid, locale_code, document_status_uuid, document_status_name, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('0274570e-0c27-423a-a90f-957eb04752dd', 'id-ID', '86759a6e-e20c-4821-adef-82c4de25b5c0', 'Tunggu', 0, true, '2018-12-06 15:44:16', NULL, NULL, NULL),
('65cba896-0af0-4dd2-b858-7191ceee105e', 'id-ID', 'b9dcf267-063a-465e-9923-b49324cdab11', 'Draf', 0, true, '2018-12-06 15:44:16', NULL, NULL, NULL),
('6ee15aaa-98c2-4a24-94f8-ad7efb4b9b4a', 'id-ID', 'e005ceac-d420-45f5-a9e8-446df3745a90', 'Tolak', 0, true, '2018-12-06 15:44:16', NULL, NULL, NULL),
('97dc41f8-00b5-47fa-8dad-885f8bea30fe', 'id-ID', '306fcbdd-39fe-4df9-aeee-24b3c26c68c9', 'Setuju', 0, true, '2018-12-06 15:44:16', NULL, NULL, NULL);

--
-- Dumping data for table wfl_document_type
--

INSERT INTO workflow.wfl_document_type (document_type_uuid, document_type_code, document_type_name, "version", is_active, created_date, created_by, modified_date, modified_by) VALUES
('95961607-b321-4e39-b8f8-b77ca6dd368a', 'RADIOLOGY.ASSESSMENT', 'Radiology Assessment', 0, true, '2018-12-07 08:51:56', NULL, NULL, NULL);
