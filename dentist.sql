Create
database dentist
GO

Use dentist
GO

CREATE TABLE Province
(
    id   int,
    name nvarchar(30) not null,
    PRIMARY KEY (id)
);
INSERT INTO [dbo].[Province] ([name])
VALUES ('Province 1')
INSERT INTO [dbo].[Province] ([name])
VALUES ('Province 2')



CREATE TABLE District
(
    id          int,
    province_id int not null FOREIGN KEY REFERENCES Province(id),
    name        nvarchar(30) not null,
    PRIMARY KEY (id)
);
INSERT INTO [dbo].[District] ([name], [province_id])
VALUES ('District 1', 2)
INSERT INTO [dbo].[District] ([name], [province_id])
VALUES ('District 2', 1)
INSERT INTO [dbo].[District] ([name], [province_id])
VALUES ('District 3', 2)



CREATE TABLE Role
(
    id   int,
    name nvarchar(30) not null,
    PRIMARY KEY (id)
);
INSERT INTO [dbo].[Role] ([name])
VALUES ('Patient')
INSERT INTO [dbo].[Role] ([name])
VALUES ('Admin')



CREATE TABLE Account
(
    id            int,
    password      varchar(30),
    full_name     nvarchar(30) not null,
    date_of_birth date       not null,
    gender        int        not null,
    district_id   int        not null FOREIGN KEY REFERENCES District(id),
    phone         varchar(10),
    email         varchar(50),
    status        decimal(1) not null,
    role_id       int        not null FOREIGN KEY REFERENCES Role (id),
    PRIMARY KEY (id)
);

CREATE TABLE Notification
(
    id          int,
    description nvarchar( max) not null,
    status      decimal(1) not null,
    PRIMARY KEY (id)
);

CREATE TABLE Account_Notification
(
    account_id      int not null FOREIGN KEY REFERENCES Account(id),
    notification_id int not null FOREIGN KEY REFERENCES Notification(id),
    PRIMARY KEY (account_id, notification_id)
);

CREATE TABLE Branch
(
    id   int,
    name nvarchar(30) not null,
    url  varchar(MAX
) not null,
  district_id int not null FOREIGN KEY REFERENCES District(id),
  open_time TIME not null,
  close_time TIME not null,
  status decimal(1) not null,
  PRIMARY KEY (id)
);
INSERT INTO [dbo].[Branch] ([close_time], [name], [open_time], [status], [url], [district_id])
VALUES ('20:00:00', 'Branch 1', '7:00:00', 1, 'chinhanh1.png', 2)
INSERT INTO [dbo].[Branch] ([close_time], [name], [open_time], [status], [url], [district_id])
VALUES ('21:00:00', 'Branch 2', '7:30:00', 1, 'chinhanh2.png', 1)
INSERT INTO [dbo].[Branch] ([close_time], [name], [open_time], [status], [url], [district_id])
VALUES ('20:30:00', 'Branch 3', '6:00:00', 1, 'chinhanh3.png', 3)



CREATE TABLE Appointment
(
    id               int,
    account_id       int FOREIGN KEY REFERENCES Account(id),
    branch_id        int        not null FOREIGN KEY REFERENCES Branch(id),
    guest_name       nvarchar(30),
    phone            varchar(10),
    appointment_date date       not null,
    appointment_time time       not null,
    status           decimal(1) not null,
    PRIMARY KEY (id)
);

CREATE TABLE Doctor
(
    id   int,
    name nvarchar(30) not null,
    url  varchar(MAX
) not null,
  branch_id int not null FOREIGN KEY REFERENCES Branch(id),
  description nvarchar(MAX) not null,
  PRIMARY KEY (id)
);

CREATE TABLE Service_Type
(
    id          int,
    name        nvarchar(30) not null,
    description nvarchar( MAX),
    PRIMARY KEY (id)
);
INSERT INTO [dbo].[ServiceType] (description, name)
VALUES ('This is service type 1', 'Service type 1')
INSERT INTO [dbo].[ServiceType] (description, name)
VALUES ('This is service type 2', 'Service type 2')
INSERT INTO [dbo].[ServiceType] (description, name)
VALUES ('This is service type 3', 'Service type 3')



CREATE TABLE Service
(
    id              int,
    service_type_id int not null FOREIGN KEY REFERENCES Service_Type(id),
    name            nvarchar(50) not null,
    url             varchar(MAX
) not null,
  description nvarchar(MAX),
  status decimal(1) not null,
  min_price float not null,
  max_price float not null,
  PRIMARY KEY (id)
);
INSERT INTO [dbo].[Service] (service_type_id, name, url, description, status, min_price, max_price)
VALUES (3, 'service 1', 'service1.png', 'This is service 1', 1, 10, 15)
INSERT INTO [dbo].[Service] (service_type_id, name, url, description, status, min_price, max_price)
VALUES (3, 'service 2', 'service2.png', 'This is service 2', 1, 15, 20)
INSERT INTO [dbo].[Service] (service_type_id, name, url, description, status, min_price, max_price)
VALUES (3, 'service 3', 'service3.png', 'This is service 3', 1, 10, 20)



CREATE TABLE Discount
(
    id          int,
    account_id  int        not null FOREIGN KEY REFERENCES Account(id),
    name        nvarchar(30) not null,
    percentage  float      not null,
    description nvarchar( MAX),
    status      decimal(1) not null,
    end_date    date       not null,
    start_date  date       not null,
    PRIMARY KEY (id)
);

CREATE TABLE Appointment_Detail
(
    service_id  int not null FOREIGN KEY REFERENCES Service(id),
    booking_id  int not null FOREIGN KEY REFERENCES Appointment(id),
    doctor_id   int FOREIGN KEY REFERENCES Doctor(id),
    discount_id int FOREIGN KEY REFERENCES Discount(id),
    PRIMARY KEY (service_id, booking_id)
);

CREATE TABLE Discount_Service
(
    service_id  int not null FOREIGN KEY REFERENCES Service(id),
    discount_id int not null FOREIGN KEY REFERENCES Discount(id),
    PRIMARY KEY (service_id, discount_id)
);

CREATE TABLE Feedback
(
    id         int,
    service_id int        not null FOREIGN KEY REFERENCES Service(id),
    account_id int        not null FOREIGN KEY REFERENCES Account(id),
    time       date       not null,
    content    nvarchar( MAX) not null,
    status     decimal(1) not null,
    PRIMARY KEY (id)
);

SELECT TOP 1 d.*
FROM Discount d,
     Discount_Service ds
WHERE d.id = ds.discount_id
  AND ds.service_id = 4
  AND getdate() >= d.start_date
  AND getdate() <= d.end_date
  AND d.status = 1
  AND d.percentage >= ALL (
    SELECT d.percentage
    FROM Discount d,
         Discount_Service ds
    WHERE d.id = ds.discount_id
      AND ds.service_id = 4
      AND getdate() >= d.start_date
      AND getdate() <= d.end_date
      AND d.status = 1
)

SELECT Branch.*
FROM Branch
WHERE Branch.id = 1