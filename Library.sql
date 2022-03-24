USE master
GO
CREATE DATABASE Library
GO
USE Library
GO

--drop database Library

CREATE TABLE ACCOUNT(
	userID varchar(50),
	fullName nvarchar(100) not null,	
	password varchar(200) not null,
	userTypeID INT not null DEFAULT 1,
	grade INT,
	class varchar(10),
	email nvarchar(50),
	lastAccess DATETIME not null DEFAULT(GETDATE()),		
	PRIMARY KEY(userID)
)

CREATE TABLE USERTYPE(
	userTypeID INT,
	userTypeName nvarchar(20),
	PRIMARY KEY(userTypeID)
)

ALTER TABLE ACCOUNT
ADD FOREIGN KEY (userTypeID) REFERENCES USERTYPE(userTypeID)

INSERT INTO USERTYPE(userTypeID, userTypeName)
VALUES(0, N'Hiệu trưởng'),
(1, N'Nhân viên thư viện'),
(2, N'Giáo viên'),
(3,N'Học sinh')

INSERT INTO ACCOUNT(userID, fullName, password, userTypeID)
VALUES('c2qdien_ladung_gvddung', N'Lê Anh Dũng', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', 0)

CREATE TABLE BOOK(
	bookID INT IDENTITY(1000, 1),
	bookName nvarchar(200) not null,	
	categoryID varchar(5),
	subjectID int not null,	
	content ntext,
	bookImg nvarchar(1000),
	pdfLink nvarchar(1000),
	quantity INT not null,
	viewCounter INT not null default 1,
	CONSTRAINT bookCheck CHECK(quantity >= 0),
	PRIMARY KEY(bookID)
)

CREATE TABLE DEVICE(
	deviceID INT IDENTITY(1000, 1),
	deviceName nvarchar(200) not null,	
	subjectID int not null,	
	deviceImg nvarchar(1000),
	quantity INT not null,
	viewCounter INT not null default 1,
	CONSTRAINT deviceCheck CHECK(quantity >= 0),
	PRIMARY KEY(deviceID)
)

CREATE TABLE SUBJECT(
	subjectID int IDENTITY(100,1),
	subjectName nvarchar(200) NOT NULL,
	PRIMARY KEY(subjectID)
)

ALTER TABLE BOOK
ADD FOREIGN KEY (subjectID) REFERENCES SUBJECT(subjectID)  

ALTER TABLE DEVICE
ADD FOREIGN KEY (subjectID) REFERENCES SUBJECT(subjectID)  

INSERT INTO SUBJECT(subjectName)
VALUES(N'Toán Học'), (N'Vật Lý'), (N'Hóa Học'), (N'Ngữ Văn'), (N'Lịch Sử'), (N'Địa Lý'), (N'Sinh Học'), (N'Lịch Sử Địa Lý'), (N'Khoa Học Tự Nhiên'), (N'Tin Học'), (N'Âm Nhạc'), (N'Mỹ Thuật'), (N'Thể Dục'), (N'Tiếng Anh'), (N'Nghệ Thuật'), (N'Giáo Dục Thể Chất'), (N'Hoạt Động Trải Nghiệm Hướng Nghiệp'), (N'Tiếng Nhật'),(N'Giáo Dục Công Dân'), (N'Công Nghệ'), (N'Khác')

CREATE TABLE AUTHOR(
	authorID INT IDENTITY(1000, 1),
	authorName nvarchar(200) not null,
	PRIMARY KEY(authorID)
)

CREATE TABLE BOOKAUTHOR(
	authorID INT,
	bookID INT,
)

ALTER TABLE BOOKAUTHOR
ADD FOREIGN KEY (bookID) REFERENCES BOOK(bookID) ON DELETE CASCADE,
FOREIGN KEY (authorID) REFERENCES AUTHOR(authorID) ON DELETE CASCADE

CREATE TABLE CATEGORY(
	categoryID varchar(5),
	categoryName nvarchar(200) not null,
	PRIMARY KEY(categoryID)
)

ALTER TABLE BOOK
ADD FOREIGN KEY (categoryID) REFERENCES CATEGORY(categoryID) ON UPDATE CASCADE

CREATE TABLE TYPE(
	typeID int,
	typeName nvarchar(100),
	PRIMARY KEY (typeID)
)

INSERT INTO TYPE(typeID, typeName)
VALUES(0, N'Đang ở trong kho'), (1, N'Đã cho mượn'), (2, N'Đang chờ xử lý'), (3, N'Từ chối'), (4, N'Đang mượn'), (5, N'Đã trả'), (6, N'Bị làm mất')

CREATE TABLE BOOKS(
	bookCode int identity(1000,1),
	bookID int not null,
	typeID int not null default(0),
	PRIMARY KEY(bookCode)
)

CREATE TABLE DEVICES(
	deviceCode int identity(1000,1),
	deviceID int not null,
	typeID int not null default(0),
	PRIMARY KEY(deviceCode)
)

ALTER TABLE BOOKS
ADD FOREIGN KEY (bookID) REFERENCES BOOK(bookID) ON DELETE CASCADE,
FOREIGN KEY (typeID) REFERENCES TYPE(typeID)  
ALTER TABLE DEVICES
ADD FOREIGN KEY (deviceID) REFERENCES DEVICE(deviceID) ON DELETE CASCADE,
FOREIGN KEY (typeID) REFERENCES TYPE(typeID)  

CREATE TABLE BOOKORDER(
	bookOrderID INT IDENTITY(1,1),
	userID varchar(50) not null,
	bookID int not null,
	typeID int not null default(2),
	orderDate DATETIME not null DEFAULT(GETDATE()),
	primary key(bookOrderID)
)

ALTER TABLE BOOKORDER
ADD FOREIGN KEY (bookID) REFERENCES BOOK(bookID),
FOREIGN KEY (userID) REFERENCES ACCOUNT(userID),
FOREIGN KEY (typeID) REFERENCES TYPE(typeID)  

CREATE TABLE DEVICEORDER(
	deviceOrderID INT IDENTITY(1,1),
	userID varchar(50) not null,
	deviceID int not null,
	quantity int not null,
	typeID int not null default(2),
	orderDate DATETIME not null DEFAULT(GETDATE()),
	primary key(deviceOrderID)
)

ALTER TABLE DEVICEORDER
ADD FOREIGN KEY (deviceID) REFERENCES DEVICE(deviceID),
FOREIGN KEY (userID) REFERENCES ACCOUNT(userID),
FOREIGN KEY (typeID) REFERENCES TYPE(typeID)  

CREATE TABLE BOOKRECEIPT(
	bookReceiptID INT IDENTITY(1,1),
	userID varchar(50) not null,
	bookCode int not null,	
	typeID int not null default(4),
	borrowDate DATETIME not null DEFAULT(GETDATE()),
	payDate DATETIME,
	PRIMARY KEY (bookReceiptID)
)

ALTER TABLE BOOKRECEIPT
ADD FOREIGN KEY (bookCode) REFERENCES BOOKS(bookCode) ON DELETE CASCADE,
FOREIGN KEY (typeID) REFERENCES TYPE(typeID),
FOREIGN KEY (userID) REFERENCES ACCOUNT(userID)

CREATE TABLE DEVICERECEIPT(
	deviceReceiptID INT IDENTITY(1,1),
	userID varchar(50) not null,
	deviceID int not null,	
	quantity int not null,
	typeID int not null default(4),
	borrowDate DATETIME not null DEFAULT(GETDATE()),
	payDate DATETIME,
	PRIMARY KEY (deviceReceiptID)
)

CREATE TABLE DEVICERECEIPTDEVICES(
	deviceReceiptID int,
	deviceCode int
)

ALTER TABLE DEVICERECEIPTDEVICES
ADD FOREIGN KEY (deviceReceiptID) REFERENCES DEVICERECEIPT(deviceReceiptID) ON DELETE CASCADE,
FOREIGN KEY (deviceCode) REFERENCES DEVICES(deviceCode) ON DELETE CASCADE

ALTER TABLE DEVICERECEIPT
ADD FOREIGN KEY (deviceID) REFERENCES DEVICE(deviceID),
FOREIGN KEY (typeID) REFERENCES TYPE(typeID),
FOREIGN KEY (userID) REFERENCES ACCOUNT(userID)


CREATE TABLE MESSAGE(
	messageID int IDENTITY(1,1),
	typeID bit NOT NULL,
	userID varchar(50) NOT NULL,
	message ntext NOT NULL,
	time DATETIME NOT NULL DEFAULT(GETDATE()),
	PRIMARY KEY(messageID)
)

ALTER TABLE MESSAGE
ADD FOREIGN KEY(userID) REFERENCES ACCOUNT(userID)

CREATE TABLE IDEA(
	ideaID int identity(1, 1),
	userID varchar(50) not null,
	bookID int not null,
	content ntext not null,
	appear bit not null DEFAULT 0,
	time DATETIME NOT NULL DEFAULT(GETDATE()),
	primary key (ideaID)
)

ALTER TABLE IDEA
ADD FOREIGN KEY(bookID) REFERENCES BOOK(bookID) ON DELETE CASCADE,
FOREIGN KEY(userID) REFERENCES ACCOUNT(userID)

CREATE TABLE ACTIVE(
	activeID int identity(1, 1),	
	activeImg nvarchar(1000),
	name ntext not null,	
	appear bit not null DEFAULT 1,
	time DATETIME NOT NULL DEFAULT(GETDATE()),
	primary key (activeID)
)

CREATE TABLE [PLAN](
	planID int identity(1,1),
	title ntext not null,
	description ntext not null,
	planImg nvarchar(1000),
	appear bit not null DEFAULT 1,
	time DATETIME NOT NULL DEFAULT(GETDATE()),
	primary key (planID)
)

INSERT INTO AUTHOR(authorName)
VALUES (N'Phạm Thế Long'),
       (N'Nguyễn Chí Công'),
       (N'Hồ Sĩ Đàm'),
       (N'Nguyễn Minh Thuyết'),
       (N'Cao Cự Giác'),
       (N'Tạ Thị Thúy Anh'),
       (N'Nguyễn Quang Vinh'),
       (N'Vũ Đức Lưu'),
       (N'Vũ Dương Thụy'),
       (N'Đỗ Kim Hảo'),
	   (N'Đặng Quốc Khánh'),
	   (N'Mai Xuân Miên')

INSERT INTO CATEGORY(categoryID, categoryName)
VALUES ('SGK', N'Sách giáo khoa'),
       ('STK', N'Sách tham khảo'),
       ('SNV', N'Sách nghiệp vụ')

SET IDENTITY_INSERT BOOK OFF
INSERT INTO BOOK(bookName, categoryID, subjectID, content, bookImg, quantity)
VALUES (N'Bài tập Tin học Quyển 2', 'STK', 109, N'nội dung', N'https://storage.googleapis.com/cdn.nhanh.vn/store/3969/ps/20190617/image_130066.jpg', 5),
(N'Tin học Quyển 4', 'SGK', 109, N'nội dung', N'https://lh3.googleusercontent.com/proxy/ojV99Acc25n6Of-GUUj5Mq12jSHOpfIiRxDcEZcYTME-QgQ8jtTFcmg6KDLinYw79q5liDoGSLamsc4zWbcujBlApQMRd4pMtbB1khCJXkorERBEearF', 2),
(N'Tin học 6 - Kết nối tri thức với cuộc sống', 'SGK', 109, N'nội dung', N'https://images.thuvienpdf.com/gdTS9uEg4K.webp', 3),
(N'Tin học 6 - Sách GV - Kết nối tri thức với cuộc sống', 'SNV', 109, N'nội dung', N'https://images.thuvienpdf.com/vlxEkOl8Wm.webp', 2),
(N'Tin học 6 - Cánh diều', 'SGK', 109, N'nội dung', N'https://images.thuvienpdf.com/Oq8W4ECoTc.webp', 3),
(N'Ngữ văn 6 - Cánh diều', 'SGK', 103, N'nội dung', N'https://images.thuvienpdf.com/rrG1st5fPF.webp', 3),
(N'Khoa học tự nhiên 6 - Sách GV - Chân trời sáng tạo', 'SGK', 108, N'nội dung', N'https://blogtailieu.com/wp-content/uploads/2021/05/sgv-khoa-hoc-tu-nhien-6-1.jpg', 2),
(N'Hướng dẫn trả lời câu hỏi và bài tập Lịch sử 7', 'STK', 104, N'nội dung', N'https://images.thuvienpdf.com/rrG1st5fPF.webp', 3),
(N'Sinh học 9 - Sách GV', 'SNV', 106, N'nội dung', N'https://lh3.googleusercontent.com/proxy/C4YrIgJ8C6vffxHa6VuW5vD8yKJMNeo81jowp4rsMXw5PsQvGvRtn6U-k87qjHBzh6AOnR44T1BA5oZs9sUn7wfVY0bDkV8Db0nFLwdiwDte0Et5MQ', 3),
(N'Bài tập Sinh học 9', 'STK', 106, N'nội dung', N'https://lh3.googleusercontent.com/proxy/Mls_Ugy2FgCyfz-cNVvhwfZ_yA8VcCPJhGt4rRwMJPhfUPNqB4TAhRR_DY5Kf9ti9233A3DazQs-OtBxyZyGmijfjK1aeFZpk-dTT54lJuvaaw', 1),
(N'Toán nâng cao và các chuyên đề hình học 8', 'STK', 100, N'nội dung', N'https://bizweb.dktcdn.net/thumb/1024x1024/100/329/558/products/toan-nang-cao-va-cac-chuyen-de-hinh-hoc-8.jpg?v=1536303811110', 3),
(N'Bồi dưỡng Ngữ văn 9', 'STK', 103, N'nội dung', N'https://lh3.googleusercontent.com/proxy/5MOpaheeX4LvSu8Qs43UOe4uYkLW9N3ljKe98pdaQP_pHTTsMsUuLUpAYG2oqsRwIhxB_DIDW2lmU1hsrFs8txY-T3nFItIBIvKrUS5Lrz415feqZSC_ZMSSiF720SKd1xP78YreWtz3_V-VNGbTwQ', 1)

INSERT INTO BOOKAUTHOR(bookID, authorID)
VALUES(1000, 1000),
(1001, 1000),
(1002, 1001),
(1003, 1001),
(1004, 1002),
(1005, 1003),
(1006, 1004),
(1007, 1005),
(1008, 1006),
(1009, 1007),
(1010, 1008),
(1011, 1009),
(1011, 1010),
(1011, 1011)

INSERT INTO DEVICE(deviceName, subjectID, deviceImg, quantity)
VALUES 
(N'Thước đo độ', 100, N'https://bizweb.dktcdn.net/thumb/1024x1024/100/299/021/products/520058.jpg?v=1606127448113', 7),
(N'Thước eke', 100, N'https://kendotoy.com/wp-content/uploads/2018/09/thuoc-eke-45-do-do-choi-go.jpg', 8),
(N'Compa', 100, N'https://bizweb.dktcdn.net/thumb/1024x1024/100/299/021/products/230205.jpg?v=1613957289710', 6),
(N'Mô hình phân tử dạng đặc', 102, N'https://lh3.googleusercontent.com/proxy/-TxN3_SDfFM-PeBR4bDuia2adJg79Fo6tNrWCMpD8ixkGMqdNFxJBQcb2UL4jUy4K2AD8O8fFCLbhWZRtVtNuJm502Sya9xSDpUywLhX9l-O7cFZmkHpAnC3fVRokISeiUCAf-u1Dbs2Y3CvDh176tPjuM0AmwBvBmVq4yFMGhrA3DFEAtbMaBw', 4),
(N'Ống nghiệm', 102, N'https://nuoitomantoan.com/wp-content/uploads/2019/07/mua-%E1%BB%91ng-nghi%E1%BB%87m-th%E1%BB%A7y-tinh-%E1%BB%9F-%C4%91%C3%A2u-2-678x355.jpg', 20),
(N'Bản đồ tự nhiên châu Á', 105, N'https://d3.violet.vn/uploads/previews/document/0/686/341/BAN_DO_TU_NHIEN_CHAU_Ajpg.jpg', 3),
(N'Quả địa cầu', 105, N'https://tech12h.com/sites/default/files/styles/inbody400/public/6_36.png?itok=W4iUuvsh', 2),
(N'La bàn', 105, N'https://play-lh.googleusercontent.com/lLC4JX4lhwJzM5qg9HEpMxvE70i7K6R7gd3TZ2186Pr55XU1dh3oapAOkt8qhVW-3fpT', 4),
(N'Kính hiển vi', 106, N'https://maydochuyendung.com/img/uploads/cache_image/500x-XSP-06.jpg', 4),
(N'Tranh cấu trúc nhiễm sắc thể', 106, N'http://cocheditruyenvabiendi.weebly.com/uploads/2/4/0/5/24050693/1717622_orig.png', 4),
(N'Bản đồ chính trị sau chiến tranh thế giới thứ II', 104, N'https://cacnuoc.vn/wp-content/uploads/2016/12/ban-do-de-quoc-1936.png', 2),
(N'Tranh chiến dịch Biên Giới Thu Đông 1950', 104, N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSp7rOu5KTGK-UyUPcY6-oJVjG2VCfcJwW9SA&usqp=CAU', 2)

SELECT DO.deviceOrderID, DO.userID, A.fullName, D.deviceID, DO.quantity, DO.typeID, T.typeName, D.deviceName, DO.orderDate FROM DEVICEORDER DO INNER JOIN ACCOUNT A ON A.userID = DO.userID
INNER JOIN DEVICE D ON DO.deviceID = D.deviceID
INNER JOIN TYPE T ON DO.typeID = T.typeID
ORDER BY DO.deviceOrderID DESC

INSERT INTO DEVICEORDER(userID, deviceID, quantity)
VALUES(?, ?, ?)

select * from BOOK

update BOOKS
set typeID = 0

select * from BOOKORDER
select * from BOOKRECEIPT
select * from DEVICEORDER

UPDATE BOOKRECEIPT
SET typeID = 5, payDate = GETDATE()
WHERE bookReceiptID = 2

SELECT * FROM TYPE

select * from BOOKRECEIPT

select * from book
update BOOK
set quantity = 3
where bookID = 1008


SELECT DR.deviceReceiptID, DR.userID, A.fullName, D.deviceID, D.deviceName, DR.quantity, T.typeID, T.typeName, DR.borrowDate, DR.payDate FROM DEVICERECEIPT DR INNER JOIN ACCOUNT A ON DR.userID = A.userID
INNER JOIN DEVICE D ON D.deviceID = DR.deviceID
INNER JOIN TYPE T ON T.typeID = DR.typeID
WHERE DR.userID = 'ht'
ORDER BY DR.deviceReceiptID DESC

INSERT INTO DEVICERECEIPT(userID, deviceID, quantity)
VALUES(?, ?, ?)

INSERT INTO DEVICERECEIPTDEVICES(deviceReceiptID, deviceCode)
VALUES(?, ?)

SELECT DR.userID from DEVICERECEIPTDEVICES DD INNER JOIN DEVICERECEIPT DR ON DD.deviceReceiptID = DR.deviceReceiptID
WHERE DD.deviceCode = 1004

delete DEVICERECEIPT
select * from DEVICEs
update DEVICE
set quantity = 2 where deviceID = 1011

SELECT I.ideaID, I.userID, A.fullName, A.class, I.bookID, B.bookName, I.content, I.appear, I.time FROM IDEA I INNER JOIN ACCOUNT A ON A.userID = I.userID
INNER JOIN BOOK B ON I.bookID = B.bookID
ORDER BY I.ideaID DESC

delete IDEA

INSERT INTO IDEA(userID, bookID, content)
VALUES(?, ?, ?)

select * from BOOK

UPDATE IDEA
SET appear = ?
WHERE ideaID = ?

SELECT activeID, activeImg, name, description, appear, time FROM ACTIVE
ORDER BY activeID DESC

INSERT INTO ACTIVE(activeImg, name, description)
VALUES(?, ?, ?)

UPDATE ACTIVE
SET appear = ?, activeImg = ?, name = ?, description = ?
WHERE activeID = ?

delete ACTIVE

select * from IDEA

INSERT INTO BOOKAUTHOR(bookID, authorID)
VALUES(?, ?)


select * from SUBJECT

select * from CATEGORY

select * from BOOKAUTHOR

select * from author


delete DEVICE
where deviceID = 1010


SELECT * FROM DEVICERECEIPT

DELETE DEVICERECEIPT
WHERE deviceID = 1010

select * from DEVICE

select * from AUTHOR

delete AUTHOR
where authorID = 1040

select * from ACCOUNT
where userTypeID = 2

delete ACCOUNT
where userID = 'c2qdien_hthanh_gvddung'

UPDATE ACCOUNT
SET fullName = ?, email = ?, grade = ?, class = ?
WHERE userID = ?