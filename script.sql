USE
[dentist]
GO
SET IDENTITY_INSERT [dbo].[Province] ON 

INSERT [dbo].[Province] ([id], [name]) VALUES (1, N'Province 1')
INSERT [dbo].[Province] ([id], [name]) VALUES (2, N'Province 2')
SET IDENTITY_INSERT [dbo].[Province] OFF
GO
SET IDENTITY_INSERT [dbo].[District] ON 

INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (1, N'District 1', 2)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (2, N'District 2', 1)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (3, N'District 3', 2)
SET IDENTITY_INSERT [dbo].[District] OFF
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([id], [name]) VALUES (1, N'ROLE_ADMIN')
INSERT [dbo].[Role] ([id], [name]) VALUES (2, N'ROLE_USER')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [date_of_birth], [email], [full_name], [gender], [password], [phone], [status], [district_id], [role_id]) VALUES (1, CAST(N'2001-09-09' AS Date), N'hiddenwory@gmail.com', N'Bao NQ', 1, N'$2y$10$waeMWiuc3JnPGvGrekv5.O.e89aJ54LjkZqcax/whJvSkJCFjUNwi', N'012345678', 1, 1, 1)
INSERT [dbo].[Account] ([id], [date_of_birth], [email], [full_name], [gender], [password], [phone], [status], [district_id], [role_id]) VALUES (2, CAST(N'2001-09-27' AS Date), N'@Email.hiidd', N'hhh', 2, N'$2y$10$.2BTX3YT1FpOne62vDSVJuWkBh4DNXKegak2X5.bySo5EAd.6ccvW', N'0987654321', 1, 1, 1)
INSERT [dbo].[Account] ([id], [date_of_birth], [email], [full_name], [gender], [password], [phone], [status], [district_id], [role_id]) VALUES (3, CAST(N'2001-03-03' AS Date), N'hiddenwory@fpr.com', N'Bao', 1, N'$2a$12$Qo2rnP4JlbyszBuHYhmt5.gG.d7YBcYwcrbXehimfs4upXn5Be7be', N'0123456789', 1, 1, 2)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Branch] ON 

INSERT [dbo].[Branch] ([id], [close_time], [name], [open_time], [status], [url], [district_id]) VALUES (1, CAST(N'20:00:00' AS Time), N'Chi nhánh 1', CAST(N'07:00:00' AS Time), 1, N'15ZFiXDNPDvH2xSQjb5tZ4H0apDfxImrn', 2)
INSERT [dbo].[Branch] ([id], [close_time], [name], [open_time], [status], [url], [district_id]) VALUES (2, CAST(N'21:00:00' AS Time), N'Chi nhánh 2', CAST(N'07:30:00' AS Time), 1, N'1ohyMT9EB5LVIOJHYwX-baONpSfqkslnm', 1)
INSERT [dbo].[Branch] ([id], [close_time], [name], [open_time], [status], [url], [district_id]) VALUES (3, CAST(N'20:30:00' AS Time), N'Chi nhánh 3', CAST(N'06:00:00' AS Time), 1, N'19pJJvSIxX9FSdEoEk7HopjIyPXDGMggg', 3)
SET IDENTITY_INSERT [dbo].[Branch] OFF
GO
SET IDENTITY_INSERT [dbo].[Appointment] ON 

INSERT [dbo].[Appointment] ([id], [appointment_date], [guest_name], [phone], [status], [account_id], [branch_id], [appointment_time], [time_making]) VALUES (11, CAST(N'2022-05-30' AS Date), N'bao', N'0123456789', 0, NULL, 1, CAST(N'11:00:00' AS Time), CAST(N'2022-05-30T13:36:58.4280000' AS DateTime2))
INSERT [dbo].[Appointment] ([id], [appointment_date], [guest_name], [phone], [status], [account_id], [branch_id], [appointment_time], [time_making]) VALUES (12, CAST(N'2022-05-30' AS Date), N'Nguyễn Bảo', N'0123456789', 1, NULL, 1, CAST(N'11:00:00' AS Time), CAST(N'2022-05-30T19:09:08.9460000' AS DateTime2))
INSERT [dbo].[Appointment] ([id], [appointment_date], [guest_name], [phone], [status], [account_id], [branch_id], [appointment_time], [time_making]) VALUES (13, CAST(N'2022-05-30' AS Date), N'Nguyễn Bảo', N'0123456789', 0, NULL, 1, CAST(N'11:00:00' AS Time), CAST(N'2022-05-31T21:13:04.0540000' AS DateTime2))
SET IDENTITY_INSERT [dbo].[Appointment] OFF
GO
SET IDENTITY_INSERT [dbo].[Discount] ON 

INSERT [dbo].[Discount] ([id], [description], [end_date], [name], [percentage], [start_date], [status], [account_id]) VALUES (2, NULL, CAST(N'2022-06-10' AS Date), N'New member discount', 20, CAST(N'2022-05-28' AS Date), 1, 1)
INSERT [dbo].[Discount] ([id], [description], [end_date], [name], [percentage], [start_date], [status], [account_id]) VALUES (4, NULL, CAST(N'2022-05-28' AS Date), N'New service discount', 15, CAST(N'2022-05-20' AS Date), 1, 1)
INSERT [dbo].[Discount] ([id], [description], [end_date], [name], [percentage], [start_date], [status], [account_id]) VALUES (6, NULL, CAST(N'2022-06-15' AS Date), N'new member 2', 25, CAST(N'2022-05-28' AS Date), 1, 1)
SET IDENTITY_INSERT [dbo].[Discount] OFF
GO
SET IDENTITY_INSERT [dbo].[ServiceType] ON 

INSERT [dbo].[ServiceType] ([id], [description], [name]) VALUES (1, N'This is service type 1', N'Service type 1')
INSERT [dbo].[ServiceType] ([id], [description], [name]) VALUES (2, N'This is service type 2', N'Service type 2')
INSERT [dbo].[ServiceType] ([id], [description], [name]) VALUES (3, N'This is service type 3', N'Service type 3')
SET IDENTITY_INSERT [dbo].[ServiceType] OFF
GO
SET IDENTITY_INSERT [dbo].[Service] ON 

INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (4, N'Nhổ răng khôn', 15, 10, N'service 1', 1, N'1GfgBOgNFNAGK29PPu3lOmWWdi5F4v-4S', 3)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (5, N'Trồng răng cao cấp', 20, 15, N'service 2', 1, N'1eSbRQTRBvXeR-76lrVq0MJMKYfRaYpNG', 3)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (6, N'Trám răng cao cấp', 20, 10, N'service 3', 1, N'1m1rQXY5UJexauWauXyDCAfgWJwOY8PuC', 3)
SET IDENTITY_INSERT [dbo].[Service] OFF
GO
SET IDENTITY_INSERT [dbo].[Doctor] ON 

INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (3, N'111', N'nguyen a', N'1.jpg', 1)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (4, N'222', N'pham b', N'2.jpg', 2)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (5, N'333', N'bui', N'3.jpg', 1)
SET IDENTITY_INSERT [dbo].[Doctor] OFF
GO
INSERT [dbo].[Appointment_Detail] ([appointment_id], [service_id], [discount_id], [doctor_id]) VALUES (11, 4, 6, 3)
INSERT [dbo].[Appointment_Detail] ([appointment_id], [service_id], [discount_id], [doctor_id]) VALUES (11, 5, NULL, NULL)
INSERT [dbo].[Appointment_Detail] ([appointment_id], [service_id], [discount_id], [doctor_id]) VALUES (12, 4, 6, 3)
INSERT [dbo].[Appointment_Detail] ([appointment_id], [service_id], [discount_id], [doctor_id]) VALUES (12, 5, NULL, NULL)
INSERT [dbo].[Appointment_Detail] ([appointment_id], [service_id], [discount_id], [doctor_id]) VALUES (13, 4, 6, 3)
INSERT [dbo].[Appointment_Detail] ([appointment_id], [service_id], [discount_id], [doctor_id]) VALUES (13, 5, NULL, NULL)
GO
INSERT [dbo].[Discount_Service] ([discount_id], [service_id]) VALUES (2, 4)
INSERT [dbo].[Discount_Service] ([discount_id], [service_id]) VALUES (2, 6)
INSERT [dbo].[Discount_Service] ([discount_id], [service_id]) VALUES (4, 5)
INSERT [dbo].[Discount_Service] ([discount_id], [service_id]) VALUES (6, 4)
GO
