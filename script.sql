USE [dentist]
GO
SET IDENTITY_INSERT [dbo].[Province] ON 

INSERT [dbo].[Province] ([id], [name]) VALUES (3, N'Đà Nẵng')
INSERT [dbo].[Province] ([id], [name]) VALUES (4, N'Đồng Nai')
INSERT [dbo].[Province] ([id], [name]) VALUES (2, N'Hà Nội')
INSERT [dbo].[Province] ([id], [name]) VALUES (5, N'Hải Phòng')
INSERT [dbo].[Province] ([id], [name]) VALUES (1, N'TP Hồ Chí Minh')
SET IDENTITY_INSERT [dbo].[Province] OFF
GO
SET IDENTITY_INSERT [dbo].[District] ON 

INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (1, N'Quận 1', 1)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (2, N'Quận 2', 1)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (3, N'Quận 3', 1)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (4, N'Quận 4', 1)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (5, N'Quận 5', 1)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (6, N'Ba Đình', 2)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (7, N'Cầu Giấy', 2)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (8, N'Đống Đa', 2)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (9, N'Hà Đông', 2)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (10, N'Hai Bà Trưng', 2)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (11, N'Cẩm Lê', 3)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (12, N'Hải Châu', 3)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (13, N'Liên Chiểu', 3)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (14, N'Biên Hòa', 4)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (15, N'Cẩm Mỹ', 4)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (16, N'Định Quán', 4)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (17, N'Đồ Sơn', 5)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (18, N'Dương Kinh', 5)
INSERT [dbo].[District] ([id], [name], [province_id]) VALUES (19, N'Hải An', 5)
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
INSERT [dbo].[Account] ([id], [date_of_birth], [email], [full_name], [gender], [password], [phone], [status], [district_id], [role_id]) VALUES (4, CAST(N'2001-06-15' AS Date), NULL, N'Phạm Xuân Kiên', 1, N'$2a$10$xW/E8xpf9qznG0yWVPpbLOl5Z5MSA8nWAcwOTkiqm59PaZyGJKryG', N'0111111111', 1, 6, 2)
INSERT [dbo].[Account] ([id], [date_of_birth], [email], [full_name], [gender], [password], [phone], [status], [district_id], [role_id]) VALUES (5, CAST(N'2001-07-16' AS Date), NULL, N'Nguyễn Trung Hiếu', 1, N'$2a$10$7b03cTGVyiZWFwiwopzyOOEwb/DHAMsHYS72pmgbOUILzoFyY4XnW', N'0222222222', 1, 8, 2)
INSERT [dbo].[Account] ([id], [date_of_birth], [email], [full_name], [gender], [password], [phone], [status], [district_id], [role_id]) VALUES (6, CAST(N'2001-08-17' AS Date), NULL, N'Nguyễn Phan Quỳnh Anh', 2, N'$2a$10$suiyy1MM29BZ/c9MwPK5gOUJkDPwVdoTqHrnDWJKASamiw2kQvZv6', N'0333333333', 1, 15, 2)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Branch] ON 

INSERT [dbo].[Branch] ([id], [close_time], [name], [open_time], [status], [url], [district_id]) VALUES (1, CAST(N'20:00:00' AS Time), N'Chi nhánh 1', CAST(N'07:00:00' AS Time), 1, N'15ZFiXDNPDvH2xSQjb5tZ4H0apDfxImrn', 5)
INSERT [dbo].[Branch] ([id], [close_time], [name], [open_time], [status], [url], [district_id]) VALUES (2, CAST(N'21:00:00' AS Time), N'Chi nhánh 2', CAST(N'07:30:00' AS Time), 1, N'1ohyMT9EB5LVIOJHYwX-baONpSfqkslnm', 1)
INSERT [dbo].[Branch] ([id], [close_time], [name], [open_time], [status], [url], [district_id]) VALUES (3, CAST(N'20:30:00' AS Time), N'Chi nhánh 3', CAST(N'06:00:00' AS Time), 1, N'19pJJvSIxX9FSdEoEk7HopjIyPXDGMggg', 18)
INSERT [dbo].[Branch] ([id], [close_time], [name], [open_time], [status], [url], [district_id]) VALUES (5, CAST(N'20:30:00' AS Time), N'Chi nhánh 4', CAST(N'06:30:00' AS Time), 0, N'1vSOCxwjt_QjA7h_YgKAdJc1ykwVz9s-s', 10)
INSERT [dbo].[Branch] ([id], [close_time], [name], [open_time], [status], [url], [district_id]) VALUES (7, CAST(N'20:30:00' AS Time), N'Chi nhánh 5', CAST(N'06:30:00' AS Time), 1, N'16a1xS30oichYEm9vSfQ50m8PWpWs5MID', 6)
INSERT [dbo].[Branch] ([id], [close_time], [name], [open_time], [status], [url], [district_id]) VALUES (8, CAST(N'21:00:00' AS Time), N'Chi nhánh 6', CAST(N'06:30:00' AS Time), 0, N'1btVzGfkFLcgtVbaAqs5kschXeJ44zEy4', 3)
INSERT [dbo].[Branch] ([id], [close_time], [name], [open_time], [status], [url], [district_id]) VALUES (9, CAST(N'20:30:00' AS Time), N'Chi nhánh 7', CAST(N'06:30:00' AS Time), 1, N'195FUwxdIQCQbhOz9C8VnJ08QyZ5JEGIi', 15)
SET IDENTITY_INSERT [dbo].[Branch] OFF
GO
SET IDENTITY_INSERT [dbo].[Doctor] ON 

INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (3, N'Lê Xuân Đại', N'Lê Xuân Đại', N'1e5bvTPaL8Jm_g2ZdjngfZExs5bLx86Nl', 1)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (4, N'Nguyễn Hồng Minh', N'Nguyễn Hồng Minh', N'1uWd57FYYu4KuKD8Sr3fbVsX5LymXAc7p', 1)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (5, N'Nguyễn Lê Hữu', N'Nguyễn Lê Hữu', N'1jMLIkUpZX3j8numP5kY3Pft7dRAFCima', 1)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (8, N'Nguyễn Phan Quỳnh Anh', N'Nguyễn Phan Quỳnh Anh', N'1dWvZPX9Suy_BgGy-gsJqJnVPRuIeW_Wq', 2)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (9, N'Nguyễn Quốc Bảo', N'Nguyễn Quốc Bảo', N'1eCg43m-KpAypNH0euN5MwYzboZq2h3x1', 2)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (10, N'Nguyễn Tâm Đắc', N'Nguyễn Tâm Đắc', N'1V4E27eaSr4JQkELSQdbZGNWuzgpPnsD6', 2)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (13, N'Nguyễn Trung Hiếu', N'Nguyễn Trung Hiếu', N'1F_3MJ3KGCbRZoSxi8CwauR9LFgZ0ctCE', 3)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (15, N'Phạm Huỳnh Phương Kha', N'Phạm Huỳnh Phương Kha', N'1kqABvW9_pPfNRacydx6mGa9whGas33cu', 3)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (16, N'Phạm Việt Thuận Thiên', N'Phạm Việt Thuận Thiên', N'1Z_Z6CI4c_1ZUb6FXm-0cOualdWvhjaiE', 3)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (17, N'Đặng Phương Anh', N'Đặng Phương Anh', N'1lqmjyF-cxe-dt6whAqnigcJIdSYvDwEs', 5)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (19, N'Huỳnh Tấn Phát', N'Huỳnh Tấn Phát', N'1mZSYMxjX5jOSrq9fUDeXNdPhXjv19gS0', 5)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (20, N'Dương Phạm Minh Triết', N'Dương Phạm Minh Triết', N'1yFqfxNOOkIFWYRNGyk7wkpIOkDp2IlgY', 5)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (22, N'Huỳnh Trần Thế Trọng', N'Huỳnh Trần Thế Trọng', N'1Z_zcOjw-sg5bJMRLsgASGscaO_h9O5hy', 7)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (23, N'Lê Chí Thanh', N'Lê Chí Thanh', N'1XHA_hqsdMciNRKWA8-jXudfCjhSTk_mk', 7)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (24, N'Thái Quốc Toàn', N'Thái Quốc Toàn', N'1sJa5fuRWWDrzfHcsceDCMVhvG1mFfxFq', 7)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (25, N'Lưu Thanh Huy', N'Lưu Thanh Huy', N'1k5PVF7gD1YwxM6p1mv8yhxBzSX0_8t2Z', 8)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (26, N'Nguyễn Hữu Thanh', N'Nguyễn Hữu Thanh', N'1FxyfzrlpjL80bwLYG7TGicNMfvwdRywg', 8)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (27, N'Phạm Xuân Kiên', N'Phạm Xuân Kiên', N'1wi4h_Q5pVaFCjkXqvIJmg51E_B6Xb8Ns', 8)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (28, N'Mai Thanh Tỷ', N'Mai Thanh Tỷ', N'1rv6k-vOA9Sb6nfMzJAJqP84_mYiUSXsH', 9)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (29, N'Bùi Ngọc Huy', N'Bùi Ngọc Huy', N'1sRiS1nEVmrVQLA6oL1TgwjNUGt-BLLID', 9)
INSERT [dbo].[Doctor] ([id], [description], [name], [url], [branch_id]) VALUES (30, N'Nguyễn Nhật Huy', N'Nguyễn Nhật Huy', N'1esAizXjvfjxQt3-kaqSLSWVm91vXzmoH', 9)
SET IDENTITY_INSERT [dbo].[Doctor] OFF
GO

SET IDENTITY_INSERT [dbo].[Discount] ON 

INSERT [dbo].[Discount] ([id], [description], [end_date], [name], [percentage], [start_date], [status], [account_id]) VALUES (2, NULL, CAST(N'2022-06-10' AS Date), N'New member discount', 20, CAST(N'2022-05-28' AS Date), 1, 1)
INSERT [dbo].[Discount] ([id], [description], [end_date], [name], [percentage], [start_date], [status], [account_id]) VALUES (4, NULL, CAST(N'2022-05-28' AS Date), N'New service discount', 15, CAST(N'2022-05-20' AS Date), 1, 1)
INSERT [dbo].[Discount] ([id], [description], [end_date], [name], [percentage], [start_date], [status], [account_id]) VALUES (6, NULL, CAST(N'2022-06-15' AS Date), N'new member 2', 25, CAST(N'2022-05-28' AS Date), 1, 1)
SET IDENTITY_INSERT [dbo].[Discount] OFF
GO
SET IDENTITY_INSERT [dbo].[ServiceType] ON 

INSERT [dbo].[ServiceType] ([id], [description], [name]) VALUES (1, N'Các dịch vụ nhổ răng', N'Nhổ răng')
INSERT [dbo].[ServiceType] ([id], [description], [name]) VALUES (2, N'Các dịch vụ trám răng', N'Trám răng')
INSERT [dbo].[ServiceType] ([id], [description], [name]) VALUES (3, N'Các dịch vụ trồng răng, cấy ghép', N'Trồng răng')
INSERT [dbo].[ServiceType] ([id], [description], [name]) VALUES (4, N'Các dịch vụ chỉnh răng, niềng răng', N'Chỉnh răng')
INSERT [dbo].[ServiceType] ([id], [description], [name]) VALUES (5, N'Các dịch vụ phục hồi, chỉnh lại hàm', N'Phục hình hàm')
INSERT [dbo].[ServiceType] ([id], [description], [name]) VALUES (6, N'Các dịch vụ cạo vôi, tẩy trắng răng', N'Khác')
SET IDENTITY_INSERT [dbo].[ServiceType] OFF
GO
SET IDENTITY_INSERT [dbo].[Service] ON 

INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (4, N'Bước đầu tiên và cũng rất quan trọng trong quy trình nhổ răng khôn chính là tiến hành thăm khám, chụp X-quang răng để đánh giá sự phát triển của răng khôn bao gồm vị trí, kích thước, hướng mọc răng khôn. Khi đó, người bệnh cũng cần khai báo rõ tình trạng sức khỏe, làm các xét nghiệm cần thiết theo chỉ định của bác sĩ để có phương án nhổ răng khôn và xử lý phù hợp. Khi người bệnh đã đáp ứng đầy đủ các tiêu chí về mặt sức khỏe thì sẽ được chỉ định nhổ răng khôn. Tiếp đó, bác sĩ sẽ tiến hành vệ sinh, làm sạch khoang miệng bằng nước súc miệng chuyên dụng để đảm bảo yếu tố vô trùng trong quy trình nhổ răng khôn. Gây tê vùng cần nhổ răng để bệnh nhân không cảm thấy đau nhức trong quá trình điều trị, khi đó tinh thần của người bệnh cũng sẽ thoải mái và yên tâm hơn giúp quá trình nhổ răng diễn ra thuận lợi. Tùy vào độ khó của răng khôn mà bác sĩ sẽ thực hiện các thủ thuật tiểu phẫu khác nhau. Những trường hợp răng khôn mọc ngầm hoặc mọc chưa hết thì sẽ phải rạch nướu để bọc lộ thân răng rồi mới nhổ bỏ. Trường hợp phức tạp mọc ngầm trong xương thì sẽ phải cắt xương để tạo lối thoát cho răng, chia nhỏ phần thân răng và chân răng để dễ dàng loại bỏ chúng ra khỏi mô nướu. Sau khi đã nhổ bỏ răng khôn số 8 hoàn toàn thì bác sĩ sẽ tiến hành khâu vết thương, làm sạch khoang miệng và cầm máu cho bệnh nhân. Tiếp đó là kê đơn thuốc giảm đau, dặn dò kỹ lưỡng những lưu ý và cách chăm sóc răng miệng sau nhổ răng khôn để đảm bảo vết thương không bị nhiễm trùng.', 700000, 350000, N'Nhổ răng khôn', 1, N'1GfgBOgNFNAGK29PPu3lOmWWdi5F4v-4S', 1)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (5, N'Đây là bước đầu tiên của quy trình trồng răng sứ. Để có thể đưa ra lời tư vấn và lộ trình điều trị chính xác, nha sĩ sẽ phải kiểm tra tình trạng răng miệng hiện tại của người bệnh. Không chỉ kiểm tra bên ngoài, nha sĩ sẽ phải chụp phim để thấy được chiều dài chân răng, chân răng có nhiễm trùng không và mô nha chu. Cần chữa tủy trong trường hợp viêm nhiễm chân răng. Nha sĩ tư vấn cho bạn về loại sứ thích hợp để bạn trồng răng. Lựa chọn 1 trong 4 loại răng sứ đã nêu ở phần trên để tiến hành lấy dấu hàm răng và chế tác răng. Dựa trên dấu hàm răng, bác sĩ sẽ làm răng tạm trong lúc bạn chờ đợi răng sứ được hoàn thiện. Đây là bước quan trọng nhất trong quy trình trồng răng sứ. Bác sĩ cần tính toán chuẩn xác xem cần mài răng bên cạnh bao nhiêu, tránh tác động đến tủy. Nếu bạn đã phải chữa tủy trước khi mài, khi mài cùi sẽ có cảm giác ê buốt. Nhưng đừng lo lắng, cảm giác này chỉ xảy ra rất ít và sẽ hết dần. Răng sứ sẽ cần khoảng 2 ngày để chế tác. Trong khi đó, bạn cần được gắn răng tạm để không làm gián đoạn các sinh hoạt, ăn uống thông thường. Việc thử sườn nhằm đảm bảo răng mới hoàn toàn khít với răng thật. Nếu không, sau này bạn dễ mắc phải các bệnh như sâu răng, đen viền lợi. Sau đó, bác sĩ sẽ thử sứ thô trên răng thật để xem sự hài hòa về màu sắc, hình dáng răng với khuôn mặt bạn, đồng thời điều chỉnh khớp cắn. Bên cạnh đó cần kiểm tra thêm các tiếp xúc xung quanh răng và nướu. Đây là điều bắt buộc trong quy trình niềng răng sứ. Ở bước này, bác sĩ gắn răng sứ cho bạn và đánh bóng bề mặt răng. Đồng thời kiểm tra lại xem răng mới đã thực sự phù hợp với răng cũ và khuôn mặt của bạn hay chưa.', 2000000, 1200000, N'Trồng răng cao cấp', 1, N'1eSbRQTRBvXeR-76lrVq0MJMKYfRaYpNG', 3)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (6, N'Bác sĩ tiến hành thăm khám để xác định mức độ tổn thương của răng cần trám. Nếu cần có thể cho chụp phim X-Quang để xác định xem tủy răng có bị tổn thương hay không. Sau đó, bác sĩ sẽ lựa chọn phương pháp và vật liệu trám thích hợp (Composite resin có hoặc không có kết hợp với che tủy, trám lót bằng ciment). Bác sĩ tiến hành nạo sạch phần mô bị hư hại cũng như mài vát men làm tăng độ lưu giữ của miếng trám. Đây là cũng là một trong những bước quan trọng đối với quy trình cách trám răng, vì nếu không làm sạch được phần răng sâu thì các vi khuẩn sẽ hình thành khiến răng không được trị dứt điểm. Quy trình trám răng thẩm mỹ tuyệt đối không thể thiếu bước so màu răng, giúp bác sĩ lựa chọn chính xác màu của vật liệu trám. Sử dụng chỉ co nướu hoặc đặt khuôn trám trong những trường hợp bờ xoang sâu dưới nướu hoặc xoang sâu lớn. Thực hiện quy trinh tram rang qua các bước tiêu chuẩn: xói mòn acid (etching), tạo lớp dán (bonding) và trám composite resin quang trùng hợp (light polymerization). Sau khi hoàn tất quá trình trám răng ở trên, bác sĩ sẽ kiểm tra lại để chỉnh những điểm vướng, cộm để bệnh nhân ăn nhai dễ dàng và thoải mái hơn. Đánh bóng miếng trám và cho khách hàng xem miếng trám để đánh giá thẩm mỹ.', 600000, 300000, N'Trám răng cao cấp', 1, N'1m1rQXY5UJexauWauXyDCAfgWJwOY8PuC', 2)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (9, N'Hàm răng giả tháo lắp bán phần sẽ gồm có khung kim loại với nướu giả và răng giả được gắn bên trên. Khung kim loại còn có những móc nối (attachments) để hỗ trợ cố định hàm giả đúng vị trí. Tuy luôn được cố định nhưng hàm tháo lắp bán phần vẫn được thiết kế sao cho dễ dàng tháo rời để làm vệ sinh. Hàm răng tháo lắp là một trong các phương pháp phục hồi răng linh hoạt nhất. Bởi vì chúng có thể được tháo lắp một cách dễ dàng khi sử dụng và khi làm vệ sinh. Tuy nhiên, vẫn cần những bác sĩ có chuyên môn và kinh nghiệm để thực hiện lắp hàm giả một cách bài bản và đảm bảo đúng kỹ thuật. Từ đó, mới có thể chắc chắn tỷ lệ thành công khi lắp hàm giả luôn đạt mức cao nhất.', 3000000, 2000000, N'Tháo lắp toàn hàm', 1, N'12XxOlhUEh0zMs1h9_IxN0z3KEBfrKoG4', 5)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (10, N'Thủ thuật lấy vôi răng sẽ không hoàn toàn loại bỏ hết được những mảng bám và vôi răng trên bề mặt răng. Việc thực hiện bước đánh bóng hàm sẽ giúp loại bỏ những mảng bám và vụn vôi răng nằm trên bề mặt răng và kẽ răng. Từ đó, giúp việc loại cao răng được hiệu quả hơn. Sau khi lấy cao vôi bám và đánh bóng xong, bạn sẽ có cảm giác toàn hàm trắng và sáng hơn. Vì vậy, nhiều bạn lầm tưởng lấy cao vôi bám và đánh bóng sẽ tác tới men răng, làm trắng răng. Tuy vậy điều này không hoàn toàn sai. Mảng bám và vôi răng sẽ khiến răng bị xỉn và ố màu. Việc loại bỏ lớp cao răng và làm bóng sẽ phần nào khiến răng bạn trông trắng sáng hơn. Tuy nhiên, những thủ thuật này không hề ảnh hưởng tới men răng hay nướu. Vì vậy, việc làm bóng răng hoàn toàn không gây ảnh hưởng tới men răng.', 300000, 200000, N'Đánh bóng', 1, N'1pTLYRequjoRbZOONqiOUOg3-mkSIBZKT', 6)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (11, N'Cách tốt nhất để loại bỏ mảng bám, cao răng trên mặt răng là thực hiện lấy cao răng tại phòng khám nha khoa. Khi đó, nha sĩ sẽ dùng máy và các dụng cụ cầm tay chuyên dụng để lấy hết cao răng ở trên và dưới lợi cũng như loại bỏ hết mảng bám trên mặt răng. Tùy vào số lượng cao răng và tình trạng của lợi, nha sĩ sẽ quyết định lấy cao răng chỉ cần 1 lần hẹn hay nhiều hơn và có phải kết hợp dùng thuốc hay không. Lấy cao răng là một thủ thuật nha khoa đơn giản nhưng đem lại nhiều lợi ích. Mục tiêu của lấy cao răng là phải loại bỏ được hết cao răng và mảng bám ở mọi mặt của răng. Có như vậy mới đạt được mục đích điều trị và phòng ngừa.', 200000, 150000, N'Cạo vôi', 1, N'1KW4eXeQEc0DRq6Vbl98Feof3p1tuzDtT', 6)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (12, N'Trồng răng Implant toàn hàm với kỹ thuật All on 4 hay All on 6 hiện nay được đánh giá mang lại hiệu quả cao khi phục hồi tình trạng mất răng toàn hàm ở người trung niên. Người mất răng toàn hàm nếu không phục hồi sẽ gặp trở ngại rất nhiều về sức khỏe, giao tiếp lẫn sinh hoạt. Trước đây, người mất răng phải đeo hàm giả tháo lắp hoặc đặt nhiều trụ Implant để phục hình răng toàn hàm, chi phí cũng vì thế tốn kém hơn nhiều. Với sự phát triển vượt bậc về công nghệ, phương pháp trồng răng toàn hàm All-on-4 hoặc All-on-6 ra đời hoàn toàn có thể giúp bạn mang lại một hàm răng đẹp, chắc khỏe với chi phí tiết kiệm nhất.', 2500000, 1500000, N'Implant toàn hàm', 1, N'19Dmahhaq9GjmuTTUW_CXPWQ2_AcRg2sT', 5)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (13, N'Niềng răng đang là sự lựa chọn hàng đầu cho những ai đang gặp phải các vấn đề về sai lệch, hô, móm. Tuy vậy, đây cũng là một quá trình tốn kém thời gian, công sức và tiền bạc nên không phải ai cũng đủ điều kiện để thực hiện. Niềng răng hay còn được gọi là chỉnh nha, là một phương pháp kéo chỉnh răng về vị trí mong muốn theo lộ trình bằng việc sử dụng các khí cụ nha khoa chuyên dụng. Đây là một kỹ thuật khá phổ biến trong nhiều năm trở lại đây và được rất nhiều người tin tưởng sử dụng.', 1500000, 1000000, N'Niềng răng', 1, N'1irxuDJYhlapxGywNzVasJ3yNGpujJ-Nq', 4)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (14, N'Trồng răng toàn hàm bằng kỹ thuật Implant All-on-4 (All-on-Four) hoặc All-on-6 (All-on-Six) được xem là phương pháp trồng răng hiện đại, giúp phục hồi khả năng ăn nhai, thẩm mỹ hiệu quả cho người mất răng toàn hàm, thiếu xương ở vùng sau răng. All-on-4 hoặc All-on-6 thay thế toàn bộ răng đã mất bằng cách cấy ghép 4 hoặc 6 trụ Implant ở mỗi hàm. Trụ Implant được cấy ghép có vai trò như những chân răng thật giúp nâng đỡ hàm giả phục hình bằng Acrylic hoặc Composite hay răng sứ được gắn cố định trên thanh bar Titanium CAD/CAM.', 1500000, 1000000, N'Trồng răng', 1, N'1WwaZbsTYUWrxfASy0Jj0Cpcdg8F6l5Gv', 3)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (16, N'Tuỳ theo độ lớn của lỗ sâu, vị trí lỗ sâu, số lượng lỗ sâu và số lượng răng sâu cần trám, bác sĩ sẽ thông báo cụ thể cho bạn thời gian cần thiết để hoàn thành một miếng trám. Trung bình thời gian trám cho một lỗ sâu là 15-20 phút. Nếu các lỗ sâu gần nhau thì có thể trám nhiều răng, do tiết kiệm được thời gian chuẩn bị, thay đổi vị trí các dụng cụ bảo vệ (khuôn trám, đê cao su). Tuy nhiên, há miệng trong một thời gian dài (thường trên 30 phút) có thể ảnh huởng đến hệ thống thái dương hàm như đau khớp, mỏi cơ; vì vậy, các bác sĩ có xu hướng chia ra thành nhiều buổi hẹn để hạn chế áp lực liên tục lên khớp thái dương hàm trong thời gian dài.', 400000, 250000, N'Trám răng', 1, N'1mlm94VN0zuGF1MEmdgpZTWcgpErcRTNf', 2)
INSERT [dbo].[Service] ([id], [description], [max_price], [min_price], [name], [status], [url], [service_type_id]) VALUES (17, N'Kỹ thuật nhổ răng là kỹ thuật cơ bản trong lĩnh vực nha khoa. Việc sâu răng hàm và phải thực hiện nhổ răng sẽ khiến cho chức năng nhai, nghiền thức ăn bị ảnh hưởng rất nhiều. Vì thế, nhổ răng sâu hàm trên cần phải cân nhắc thật kỹ và không được tự ý xử lý khi chưa có chỉ định từ bác sĩ. Sau đây là những trường hợp cần phải nhổ răng hàm trên: Răng bị lung lay do bị chấn thương va chạm khiến không thể trở về vị trí như ban đầu được; Răng hàm trên bị sâu nặng ăn đến tủy khiến viêm nhiễm tủy, áp xe chân răng, tiêu xương, tủy chết; Răng khôn mọc ngược, bị lệch đâm vào lợi gây đau nhức.', 200000, 100000, N'Nhổ răng sâu', 1, N'1WGBwg8mKuf1PRM0ED_Ob5yGLSlcaZgZ2', 1)
SET IDENTITY_INSERT [dbo].[Service] OFF
GO

INSERT [dbo].[Discount_Service] ([discount_id], [service_id]) VALUES (2, 4)
INSERT [dbo].[Discount_Service] ([discount_id], [service_id]) VALUES (2, 6)
INSERT [dbo].[Discount_Service] ([discount_id], [service_id]) VALUES (4, 5)
INSERT [dbo].[Discount_Service] ([discount_id], [service_id]) VALUES (6, 4)
GO
