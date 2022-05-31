USE [dentist]
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

INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (4, N'Bước đầu tiên và cũng rất quan trọng trong quy trình nhổ răng khôn chính là tiến hành thăm khám, chụp X-quang răng để đánh giá sự phát triển của răng khôn bao gồm vị trí, kích thước, hướng mọc răng khôn. Khi đó, người bệnh cũng cần khai báo rõ tình trạng sức khỏe, làm các xét nghiệm cần thiết theo chỉ định của bác sĩ để có phương án nhổ răng khôn và xử lý phù hợp. Khi người bệnh đã đáp ứng đầy đủ các tiêu chí về mặt sức khỏe thì sẽ được chỉ định nhổ răng khôn. Tiếp đó, bác sĩ sẽ tiến hành vệ sinh, làm sạch khoang miệng bằng nước súc miệng chuyên dụng để đảm bảo yếu tố vô trùng trong quy trình nhổ răng khôn. Gây tê vùng cần nhổ răng để bệnh nhân không cảm thấy đau nhức trong quá trình điều trị, khi đó tinh thần của người bệnh cũng sẽ thoải mái và yên tâm hơn giúp quá trình nhổ răng diễn ra thuận lợi. Tùy vào độ khó của răng khôn mà bác sĩ sẽ thực hiện các thủ thuật tiểu phẫu khác nhau. Những trường hợp răng khôn mọc ngầm hoặc mọc chưa hết thì sẽ phải rạch nướu để bọc lộ thân răng rồi mới nhổ bỏ. Trường hợp phức tạp mọc ngầm trong xương thì sẽ phải cắt xương để tạo lối thoát cho răng, chia nhỏ phần thân răng và chân răng để dễ dàng loại bỏ chúng ra khỏi mô nướu. Sau khi đã nhổ bỏ răng khôn số 8 hoàn toàn thì bác sĩ sẽ tiến hành khâu vết thương, làm sạch khoang miệng và cầm máu cho bệnh nhân. Tiếp đó là kê đơn thuốc giảm đau, dặn dò kỹ lưỡng những lưu ý và cách chăm sóc răng miệng sau nhổ răng khôn để đảm bảo vết thương không bị nhiễm trùng.', 15, 10, N'Nhổ răng khôn', 1, N'1GfgBOgNFNAGK29PPu3lOmWWdi5F4v-4S', 3)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (5, N'Đây là bước đầu tiên của quy trình trồng răng sứ. Để có thể đưa ra lời tư vấn và lộ trình điều trị chính xác, nha sĩ sẽ phải kiểm tra tình trạng răng miệng hiện tại của người bệnh. Không chỉ kiểm tra bên ngoài, nha sĩ sẽ phải chụp phim để thấy được chiều dài chân răng, chân răng có nhiễm trùng không và mô nha chu. Cần chữa tủy trong trường hợp viêm nhiễm chân răng. Nha sĩ tư vấn cho bạn về loại sứ thích hợp để bạn trồng răng. Lựa chọn 1 trong 4 loại răng sứ đã nêu ở phần trên để tiến hành lấy dấu hàm răng và chế tác răng. Dựa trên dấu hàm răng, bác sĩ sẽ làm răng tạm trong lúc bạn chờ đợi răng sứ được hoàn thiện. Đây là bước quan trọng nhất trong quy trình trồng răng sứ. Bác sĩ cần tính toán chuẩn xác xem cần mài răng bên cạnh bao nhiêu, tránh tác động đến tủy. Nếu bạn đã phải chữa tủy trước khi mài, khi mài cùi sẽ có cảm giác ê buốt. Nhưng đừng lo lắng, cảm giác này chỉ xảy ra rất ít và sẽ hết dần. Răng sứ sẽ cần khoảng 2 ngày để chế tác. Trong khi đó, bạn cần được gắn răng tạm để không làm gián đoạn các sinh hoạt, ăn uống thông thường. Việc thử sườn nhằm đảm bảo răng mới hoàn toàn khít với răng thật. Nếu không, sau này bạn dễ mắc phải các bệnh như sâu răng, đen viền lợi. Sau đó, bác sĩ sẽ thử sứ thô trên răng thật để xem sự hài hòa về màu sắc, hình dáng răng với khuôn mặt bạn, đồng thời điều chỉnh khớp cắn. Bên cạnh đó cần kiểm tra thêm các tiếp xúc xung quanh răng và nướu. Đây là điều bắt buộc trong quy trình niềng răng sứ. Ở bước này, bác sĩ gắn răng sứ cho bạn và đánh bóng bề mặt răng. Đồng thời kiểm tra lại xem răng mới đã thực sự phù hợp với răng cũ và khuôn mặt của bạn hay chưa.', 20, 15, N'Trồng răng cao cấp', 1, N'1eSbRQTRBvXeR-76lrVq0MJMKYfRaYpNG', 3)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (6, N'Bác sĩ tiến hành thăm khám để xác định mức độ tổn thương của răng cần trám. Nếu cần có thể cho chụp phim X-Quang để xác định xem tủy răng có bị tổn thương hay không. Sau đó, bác sĩ sẽ lựa chọn phương pháp và vật liệu trám thích hợp (Composite resin có hoặc không có kết hợp với che tủy, trám lót bằng ciment). Bác sĩ tiến hành nạo sạch phần mô bị hư hại cũng như mài vát men làm tăng độ lưu giữ của miếng trám. Đây là cũng là một trong những bước quan trọng đối với quy trình cách trám răng, vì nếu không làm sạch được phần răng sâu thì các vi khuẩn sẽ hình thành khiến răng không được trị dứt điểm. Quy trình trám răng thẩm mỹ tuyệt đối không thể thiếu bước so màu răng, giúp bác sĩ lựa chọn chính xác màu của vật liệu trám. Sử dụng chỉ co nướu hoặc đặt khuôn trám trong những trường hợp bờ xoang sâu dưới nướu hoặc xoang sâu lớn. Thực hiện quy trinh tram rang qua các bước tiêu chuẩn: xói mòn acid (etching), tạo lớp dán (bonding) và trám composite resin quang trùng hợp (light polymerization). Sau khi hoàn tất quá trình trám răng ở trên, bác sĩ sẽ kiểm tra lại để chỉnh những điểm vướng, cộm để bệnh nhân ăn nhai dễ dàng và thoải mái hơn. Đánh bóng miếng trám và cho khách hàng xem miếng trám để đánh giá thẩm mỹ.', 20, 10, N'Trám răng cao cấp', 1, N'1m1rQXY5UJexauWauXyDCAfgWJwOY8PuC', 3)
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
