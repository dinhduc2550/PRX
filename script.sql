USE [master]
GO
/****** Object:  Database [PRJ321_SE1429]    Script Date: 08/03/2022 9:29:45 CH ******/
CREATE DATABASE [PRJ321_SE1429]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PRJ321_SE1429', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\PRJ321_SE1429.mdf' , SIZE = 3328KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'PRJ321_SE1429_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\PRJ321_SE1429_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [PRJ321_SE1429] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PRJ321_SE1429].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PRJ321_SE1429] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET ARITHABORT OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [PRJ321_SE1429] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PRJ321_SE1429] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PRJ321_SE1429] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET  ENABLE_BROKER 
GO
ALTER DATABASE [PRJ321_SE1429] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PRJ321_SE1429] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PRJ321_SE1429] SET  MULTI_USER 
GO
ALTER DATABASE [PRJ321_SE1429] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PRJ321_SE1429] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PRJ321_SE1429] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PRJ321_SE1429] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [PRJ321_SE1429] SET DELAYED_DURABILITY = DISABLED 
GO
USE [PRJ321_SE1429]
GO
/****** Object:  Table [dbo].[HE141081_ducdv_Account]    Script Date: 08/03/2022 9:29:45 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HE141081_ducdv_Account](
	[AccountID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nvarchar](30) NOT NULL,
	[PassWord] [nvarchar](30) NOT NULL,
	[Role] [nvarchar](10) NULL,
	[Active] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[AccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HE141081_ducdv_HistoryBuy]    Script Date: 08/03/2022 9:29:45 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HE141081_ducdv_HistoryBuy](
	[AccountID] [int] NULL,
	[ProductID] [int] NULL,
	[Amount] [int] NOT NULL,
	[Price] [float] NOT NULL,
	[Total] [float] NOT NULL,
	[purchaseID] [int] IDENTITY(1,1) NOT NULL,
	[purchaseDate] [datetime] NULL DEFAULT (getdate()),
	[delivery_address] [nvarchar](200) NULL,
	[Recipient_name] [nvarchar](50) NULL,
	[Recipient_phone] [nvarchar](15) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HE141081_ducdv_News]    Script Date: 08/03/2022 9:29:45 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HE141081_ducdv_News](
	[NewsID] [int] IDENTITY(1,1) NOT NULL,
	[URL Image] [nvarchar](70) NOT NULL,
	[View] [int] NULL DEFAULT ((0)),
	[Title] [nvarchar](1000) NULL,
	[URL_txtFile] [nvarchar](200) NULL,
	[type] [bit] NULL,
	[userID] [int] NULL,
	[KeyWord] [nvarchar](max) NULL,
	[productID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[NewsID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HE141081_ducdv_Product]    Script Date: 08/03/2022 9:29:45 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HE141081_ducdv_Product](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[Product Name] [nvarchar](30) NOT NULL,
	[Amount] [int] NOT NULL,
	[Price] [float] NOT NULL,
	[Active] [bit] NULL,
	[URL Image] [nvarchar](100) NULL,
	[userID] [int] NULL,
	[Describe] [nvarchar](max) NULL,
	[View] [int] NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HE141081_ducdv_UserInfo]    Script Date: 08/03/2022 9:29:45 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HE141081_ducdv_UserInfo](
	[AccountID] [int] NOT NULL,
	[Name] [nvarchar](30) NOT NULL,
	[Address] [nvarchar](70) NOT NULL,
	[Phonenumber] [nvarchar](15) NOT NULL,
	[Gender] [nvarchar](10) NOT NULL,
	[DoB] [date] NULL
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[HE141081_ducdv_Account] ON 

INSERT [dbo].[HE141081_ducdv_Account] ([AccountID], [UserName], [PassWord], [Role], [Active]) VALUES (1, N'dinhduc2550', N'123', N'admin', 1)
INSERT [dbo].[HE141081_ducdv_Account] ([AccountID], [UserName], [PassWord], [Role], [Active]) VALUES (2, N'nguyenvan', N'2550', N'sale', 1)
INSERT [dbo].[HE141081_ducdv_Account] ([AccountID], [UserName], [PassWord], [Role], [Active]) VALUES (3, N'khanhpham1', N'123456', N'cus', 1)
INSERT [dbo].[HE141081_ducdv_Account] ([AccountID], [UserName], [PassWord], [Role], [Active]) VALUES (17, N' prj321', N'123', NULL, NULL)
INSERT [dbo].[HE141081_ducdv_Account] ([AccountID], [UserName], [PassWord], [Role], [Active]) VALUES (18, N'a', N'1', NULL, 1)
INSERT [dbo].[HE141081_ducdv_Account] ([AccountID], [UserName], [PassWord], [Role], [Active]) VALUES (19, N'', N'', NULL, NULL)
SET IDENTITY_INSERT [dbo].[HE141081_ducdv_Account] OFF
SET IDENTITY_INSERT [dbo].[HE141081_ducdv_HistoryBuy] ON 

INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (3, 2, 4, 500, 2000, 10, CAST(N'2021-03-13 09:44:26.577' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (2, 3, 2, 750, 1500, 11, CAST(N'2021-03-13 09:45:41.087' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 2, 800, 1600, 12, CAST(N'2021-03-13 10:00:17.243' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 1, 800, 800, 13, CAST(N'2021-03-21 22:33:06.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (2, 1, 1, 800, 800, 14, CAST(N'2021-03-21 22:34:07.470' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (2, 1, 1, 800, 800, 15, CAST(N'2021-03-21 22:39:00.337' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (2, 1, 1, 800, 800, 16, CAST(N'2021-03-21 22:39:26.307' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 2, 800, 1600, 18, CAST(N'2021-03-21 22:40:34.843' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 2, 3, 3000, 9000, 19, CAST(N'2021-03-21 22:40:34.890' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 6, 800, 4800, 21, CAST(N'2021-03-21 22:51:30.080' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 2, 1, 3000, 3000, 24, CAST(N'2021-03-21 22:55:57.257' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (2, 1, 1, 800, 2400, 26, CAST(N'2021-03-22 16:22:15.637' AS DateTime), N'hải phòng', N'Đức', N'0123456789')
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 1, 800, 800, 27, CAST(N'2021-03-22 16:23:27.137' AS DateTime), N'An DÆ°Æ¡ng - Háº£i PhÃ²ng', N'Äá»©c Äinh', N'0379010550')
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 2, 1, 3000, 3000, 28, CAST(N'2021-03-22 16:23:27.140' AS DateTime), N'An DÆ°Æ¡ng - Háº£i PhÃ²ng', N'Äá»©c Äinh', N'0379010550')
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 1, 800, 800, 29, CAST(N'2021-03-22 16:24:10.420' AS DateTime), N'Đoán Xem', N'Đức Đinh', N'0379010550')
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 2, 1, 3000, 3000, 30, CAST(N'2021-03-22 16:24:10.423' AS DateTime), N'Đoán Xem', N'Đức Đinh', N'0379010550')
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 1, 800, 800, 31, CAST(N'2021-03-22 16:24:26.867' AS DateTime), N'An Dương - Hải Phòng', N'Đức Đinh', N'0379010550')
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 2, 1, 3000, 3000, 32, CAST(N'2021-03-22 16:24:26.870' AS DateTime), N'An Dương - Hải Phòng', N'Đức Đinh', N'0379010550')
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 1, 800, 800, 33, CAST(N'2021-03-22 16:26:13.927' AS DateTime), N'Đoán Xem', N'Đức Đinh', N'0379010550')
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 2, 1, 3000, 3000, 34, CAST(N'2021-03-22 16:26:13.930' AS DateTime), N'Đoán Xem', N'Đức Đinh', N'0379010550')
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 2, 800, 1600, 17, CAST(N'2021-03-21 22:39:58.923' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 1, 800, 800, 20, CAST(N'2021-03-21 22:45:45.660' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 1, 800, 800, 25, CAST(N'2021-03-21 23:01:25.820' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 1, 1, 800, 800, 22, CAST(N'2021-03-21 22:54:42.737' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 2, 1, 3000, 3000, 23, CAST(N'2021-03-21 22:54:42.740' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 17, 2, 700, 1400, 35, CAST(N'2021-03-30 08:12:27.403' AS DateTime), N'Hà Nội', N'Đức Đinh', N'0379010550')
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 17, 3, 700, 2100, 36, CAST(N'2021-08-15 12:46:16.313' AS DateTime), N'Hải Phòng', N'Đức Đinh', N'0379010550')
INSERT [dbo].[HE141081_ducdv_HistoryBuy] ([AccountID], [ProductID], [Amount], [Price], [Total], [purchaseID], [purchaseDate], [delivery_address], [Recipient_name], [Recipient_phone]) VALUES (1, 15, 1, 819, 819, 37, CAST(N'2021-08-25 10:43:41.293' AS DateTime), N'Hải Phòng', N'Đức Đinh', N'0379010550')
SET IDENTITY_INSERT [dbo].[HE141081_ducdv_HistoryBuy] OFF
SET IDENTITY_INSERT [dbo].[HE141081_ducdv_News] ON 

INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (12, N'img\test\S21.jpg', 20, N'Realme naze release 2020', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_12.txt', 0, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (13, N'img\test\fortnite-logo-6399.jpg', 11, N'Apple CEO Tim Cook, senior execs to testify at Epic Fortnite trial', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_13.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (14, N'img\news\n_3.png', 9, N'[MWC Shanghai] OPPO giới thiệu loạt công nghệ: Air Charging, sạc nhanh, kết nối 5G và công nghệ thông minh', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_3.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (15, N'img\news\n_4.png', 0, N'Dòng Redmi Note 10 series sẽ có thêm phiên bản "Pro Max", sắp ra mắt vào 4/3 tới đây', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_4.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (16, N'img\news\n_5.png', 0, N'Apple có thể đang sản xuất vỏ pin với công nghệ từ tính thông minh cho iPhone', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_5.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (17, N'img\news\n_6.png', 1, N'Apple đã xây và tạo dựng nên thành công cho các cửa hàng Apple Store trên thế giới như thế nào?', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_6.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (18, N'img\news\n_7.png', 0, N'Bộ Công an đề xuất xử phạt từ 50 đến 80 triệu đồng khi tiết lộ dữ liệu cá nhân mà không có sự đồng ý của cá nhân đó', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_7.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (19, N'img\news\n_8.jpg', 10, N'Nvidia thông báo giảm sức mạnh đào tiền điện tử của card RTX 3060 mới, sẽ ra bộ xử lý chuyên dụng cho việc này', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_8.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (21, N'img\test\p_1.jpg', 54, N'Samsung Galaxy S21 Ultra 5G1', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_21.txt', 0, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (22, N'img\products\p_2.jpg', 16, N'Redmi K40 Ultra', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_22.txt', 0, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (23, N'img\products\p_3.jpg', 2, N'TV Samsung Neo QLED 2021', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\product\p_3.txt', 0, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (25, N'img\test\PS5_VR_controllers_1616146354346.webp', 0, N'Sony Unveils New VR Controllers With Adaptive Triggers for PlayStation 5', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_25.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (26, N'img\test\dell_alienware_m17_m15_r4_1616143955172.webp', 1, N'Dell Adds Cherry MX Mechanical Keyboard to Alienware m17 R4, Alienware m15 R4 Gaming Laptops Lineup', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_26.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (27, N'img\test\1616143904285.webp', 1, N'Samsung Galaxy A72, Galaxy A52 With Quad Rear Cameras, 90Hz Display Launched in India: Price, Specifications', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_27.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (28, N'img\test\trump_reuters_1610080649024.webp', 0, N'Donald Trump Said to Launch His Own Social Media Platform Following Facebook, Twitter Ban', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_28.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (29, N'img\test\elon_musk_cranky_reuters_1612959050419.webp', 0, N'Elon Musk Says Tesla Would Be Shut Down if Its Cars Were Used to Spy Following China Military Ban', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_29.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (30, N'img\test\oneplus_watch.png', 0, N'OnePlus Watch Pre-Orders Begin, Alleged Render Offers First Look at Design', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_30.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (31, N'img\test\Cyberpunk2077_corrupt_1608542148518.png', 0, N'Cyberpunk 2077 Developer Shares Patch 1.2 Details, No Release Date Announced Yet', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_31.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (32, N'img\test\oneplus_9_3_million_reservations_weibo_1616401320026.png', 1, N'OnePlus 9 Series Sees Over 3 Million Reservations in China Ahead of This Week''s Launch', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_32.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (33, N'img\test\iQoo_U3x_launch_1616402422297.png', 0, N'iQoo U3X 5G With Snapdragon 480 SoC, 5,000mAh Battery Launched: Price, Specifications', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_33.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (34, N'img\test\c.jpg', 2, N'OnePlus Nord N1 5G Design, Specifications Leaked; Tipped to Feature Same 6.49-Inch Display as Nord N10', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_34.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (35, N'img\test\n.png', 0, N'Huawei lawyers to focus on witness refusal to testify in U.S. extradition', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_35.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (36, N'img\test\redmi_note_10_series_sale_1616159232621.webp', 0, N'Redmi Note 10 Pro Next Sale on March 24, Redmi Note 10 Pro Max on March 25', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_36.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (37, N'img\test\a.png', 2, N'Deliveroo aims for $12 billion market cap in biggest London debut in a decade', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_37.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (38, N'img\test\táº£i xuá»‘ng (1).jfif', 7, N'Analysis: Electric shock - German auto stocks get a new lease of life', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\ntxt_24.txt', 1, 2, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (39, N'img\test\Screenshot_20201116-171145.jpg', 11, N'Test', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\ntxt_25.txt', 1, 1, NULL, NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (40, N'img\test\x3.jpg', 2, N'Test News', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\ntxt_26.txt', 1, 1, N'-1', NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (41, N'img\test\35769929_2735101_b7f0a96ff4deff286049f159bc25fac2_wm.jpg', 1, N'Artificial intelligence: Are we doing it all wrong?', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_41.txt', 1, 1, N'a', NULL)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (42, N'img\test\gettyimages-1291370118.webp', 0, N'Gen Z is getting screwed by remote work, Microsoft survey finds', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_42.txt', 0, 1, N'', 1)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (46, N'img\test\hp-chromebook-x360-14c.webp', 14, N'Why Chromebooks are now my go-to recommendation for most laptop buyers', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_46.txt', 0, 1, N'', 1)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (47, N'img\test\hTRxVGvaSyFc3ZQjxgcbcB-1200-80.jpg.webp', 15, N'Review Samsung S21 ULTRA', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_47.txt', 0, 1, N'', 1)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (48, N'img\test\doanxem.jpg', 0, N'Test', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\n_48.txt', 0, 1, N'', 9)
INSERT [dbo].[HE141081_ducdv_News] ([NewsID], [URL Image], [View], [Title], [URL_txtFile], [type], [userID], [KeyWord], [productID]) VALUES (49, N'img\test\doanxem.jpg', 1, N'h1', N'C:\Users\dinhd\OneDrive\Java Web\MyProject\web\txt\ntxt_32.txt', 1, 1, N'', 1)
SET IDENTITY_INSERT [dbo].[HE141081_ducdv_News] OFF
SET IDENTITY_INSERT [dbo].[HE141081_ducdv_Product] ON 

INSERT [dbo].[HE141081_ducdv_Product] ([ProductID], [Product Name], [Amount], [Price], [Active], [URL Image], [userID], [Describe], [View]) VALUES (1, N'Samsung Galaxy S21 Ultra 5G', 2, 800, 1, N'img\test\p_1.jpg', 1, N'The Galaxy S21 Ultra 5G, unveiled alongside Samsung''s Galaxy S21 and S21 Plus phones, proves that sometimes you have to do something twice to get it right. While just as bold as last year''s Galaxy S20 Ultra, the S21 Ultra is a refined second take on the Ultra concept. There''s still the 100x Space Zoom, but it''s easier to use. There is still the "big for the sake of being big" design, but it looks much more appealing. And there''s still a high price, but at $1,200, it costs $200 less than the S20 Ultra in the US. The whole Galaxy S21 lineup is available to purchase -- here''s how you can buy one.

If you want the absolute best specs and features, the S21 Ultra will undoubtedly seem appealing. The phone will also attract camera nerds, thanks to the improvements Samsung made to the cameras. And with the addition of S-Pen support -- it''s the first Galaxy S phone to use the stylus -- the S21 Ultra will likely catch the eye of Galaxy Note users looking for a different option.', 11)
INSERT [dbo].[HE141081_ducdv_Product] ([ProductID], [Product Name], [Amount], [Price], [Active], [URL Image], [userID], [Describe], [View]) VALUES (2, N'Redmi K40 Ultra', 70, 3000, 1, N'img\products\p_2.jpg', 1, N'Xiaomi Redmi K40 is officially announced on February 25, 2021.

The display comes in a 6.67-inch AMOLED screen with a resolution of 1080 x 2400 pixels. The display pixel density is 395 PPI and the screen is protected by Corning Gorilla Glass 5.

The device is powered by Qualcomm SM8250-AC Snapdragon 870 5G processor and Adreno 650 GPU. The rear camera consists of a triple-camera: 48 MP (wide) + 8 MP (ultrawide) + 5 MP (macro) while on the front there are 20 MP (wide) sensors.

The device is equipped with sensors such as a Fingerprint (side-mounted), accelerometer, gyro, proximity, compass, and color spectrum. The smartphone is fueled by a Non-removable Li-Po 4520 mAh battery.

Additionally, it features Fast charging 33W, 100% in 52 min (advertised) + Quick Charge 3+ and Power Delivery 3.0. It comes in three colors: Black, White, and Aurora. The smartphone runs on Android 11, MIUI 12 operating system.

The dimension of the device is 163.7 x 76.4 x 7.8 mm and it weighs 196 grams. It supports Dual SIM (Nano-SIM, dual stand-by). For the latest phones and tablets, check out giztop.com and get the best deals, coupons, offers, comparisons, reviews, and more!', 0)
INSERT [dbo].[HE141081_ducdv_Product] ([ProductID], [Product Name], [Amount], [Price], [Active], [URL Image], [userID], [Describe], [View]) VALUES (3, N'TV Samsung Neo QLED 2021', 0, 750, 0, N'img\products\p_3.jpg', 1, N'Đang test thử', 0)
INSERT [dbo].[HE141081_ducdv_Product] ([ProductID], [Product Name], [Amount], [Price], [Active], [URL Image], [userID], [Describe], [View]) VALUES (9, N'Realme Narzo', 10, 9999, 1, N'img\test\n_1.png', 1, N'Amazing triple camera setup on the rear front6GB RAM5000mAh battery with 30W Dart Charging supportSide based fingerprint sensorMediaTek Dimensity 800U MT6873V chipset
Beware of
Hybrid SIM Slot
Verdict
Realme Narzo 30 Pro can top the buyers'' list with its quality specs. The mid-range device assures an impressive gaming experience with its MediaTek Helio G95 Chipset and 6GB RAM. Those who are in search of quality cameras can definitely invest in the particular device, offering triple cameras on the back and a 16MP selfie shooter on the front. Moreover, the 5000mAh battery ensures long operative hours once 100% charged. 
Impressive Chipset teamed with 6GB RAM from Realme
Display and Camera
Realme Narzo 30 Pro displays a 6.5 inch IPS LCD screen with a resolution of 1080 x 2400 pixels teamed with an aspect ratio of 20:9. Moreover, it has a bezel-less punch-hole frame on the front loaded with a pixel density of 405ppi.', 1)
INSERT [dbo].[HE141081_ducdv_Product] ([ProductID], [Product Name], [Amount], [Price], [Active], [URL Image], [userID], [Describe], [View]) VALUES (10, N'Xiaomi Poco X3', 3, 300, 1, N'img\test\poco-x3-nfc-5.jpg', 1, N'Xiaomi Poco X3 is a powerful mid-range device offering attractive specs. Starting from a stunning rear and front camera arrangement, gigantic RAM accompanied by a powerful battery it has all. However, dual SIM users might not be comfortable with the hybrid SIM slot, considering the fact that the device has only 64GB internal storage.  

Get the best of RAM with a stunning camera setup
Display and Camera
Xiaomi Poco X3 has a 6.67-inch IPS LCD display, having a screen resolution of 1080 x 2400 pixels. The smartphone has a pixel density of 395ppi and a refresh rate of 120Hz. Further, its bezel-less punch-hole display has an aspect ratio of 20:9 and is protected by Corning Gorilla Glass v5.
The smartphone features an impressive quad-camera composition on its rear side with a 64MP f/1.89 Primary Lens, 13MP f/2.2 Ultra-Wide Angle Lens, a 2MP f/2.4 Macro lens and another 2MP f/2.4 Depth lens. The rear camera setup is equipped with features like Exposure Compensation, HDR Mode, Face detection, Touch to focus, Auto Flash, Digital Zoom and ISO control. There is a single 20MP f/2.2 Wide Angle Selfie Camera on the front. .  
Configuration and Battery
Xiaomi Poco X3 functions on Qualcomm Snapdragon 732G chipset and is powered by an Octa-core processor setup with 2.3GHz Kryo 470 Dual core and 1.8GHz Kryo 470 Hexa core. It is the 6GB RAM and Adreno 618 GPU, which ensures a smooth multitasking experience. 
The smartphone gets powered by a 6000mAh Li-Polymer battery equipped with 33W Fast Charging technology. 
 ', 15)
INSERT [dbo].[HE141081_ducdv_Product] ([ProductID], [Product Name], [Amount], [Price], [Active], [URL Image], [userID], [Describe], [View]) VALUES (11, N'Realme GT', 3, 500, 1, N'img\test\realme-gt-design-image-feat.jpeg', 1, N'Realme GT India launch could be close as the phone is listed on the Indian IMEI database with model number RMX2202, as spotted by tipster Mukul Sharma. The handset originally launched in China earlier this month with high-end specifications such as the Qualcomm Snapdragon 888 SoC, 120Hz AMOLED display, 64MP primary camera, and a 4,500mAh battery. To recall, Realme India CEO Madhav Sheth recently said that the company was evaluating the Realme GT launch in India. While thereâs no exact launch timeline, it looks like the Realme GT will indeed go official in the country soon.

Realme GT 5G specifications
The Realme GT 5G flaunts a 6.43-inch FHD+ Super AMOLED display with punch-hole cutout, 120Hz refresh rate, 1000nits peak brightness, and Corning Gorilla 5. The phone ships with the Qualcomm Snapdragon 888 5G SoC coupled with Adreno 660 GPU, up to 12GB LPDDR5 RAM, and 256GB UFS 3.1 storage. The device runs Android 11-based Realme UI 2.0 custom skin out of the box and packs a 4,500mAh battery with 65W fast-charging support. Connectivity features include 5G Dual-mode, 4G LTE, Wi-Fi 6, Bluetooth 5.2, GPS, 3.5mm headphone jack, and USB Type-C port for charging and data sync.

Realme GT 5G price in China is CNY 2,799 (approx Rs 31,400) for the 8GB+128GB model and CNY 3,299 (approx Rs 37,000) for the 12GB+256GB variant. It is available in Blue and Silver colours, and a Vegan Lether edition. ', 2)
INSERT [dbo].[HE141081_ducdv_Product] ([ProductID], [Product Name], [Amount], [Price], [Active], [URL Image], [userID], [Describe], [View]) VALUES (14, N'Xiaomi Mi 11 Lite 5G', 7, 400, 1, N'img\test\gsmarena_002.jpg', 1, N'Alongside its most premium phone yet Xiaomi also had a couple of exciting mid-rangers to announce today. The Mi 11 Lite 5G is officially the first smartphone with Qualcomm Snapdragon 780G chipset, while its 4G variant will pack a Snapdragon 732G chip.

The new Mi 11 Lite 5G is said to be Xiaomiâs most affordable 5G smartphone for the global market.

Otherwise the two Mi 11 Lites are essentially identical beyond the chipset. The screen in both cases is a 6.55â AMOLED of FullHD+ resolution with a single punch hole in the top left corner. You get a 90Hz refresh rate, 240Hz touch sampling rate and 10-bit color depth. While the screen is OLED, The camera island on the back looks like the flagship Mi 11, but the actual modules are rather different. The main one is a 64MP f/1.8 shooter with a 1/1.97â sensor size. There is also an 8MP f/2.2 ultrawide-angle snapper with a 1/4â sensor, while the third cam is a 5MP macro shooter with autofocus between 3 and 7 cm.

The punch hole on the front is for the 16MP selfie camera. it on the side, doubling as a power key.

The Snapdragon 780G inside the Mi 11 Lite 5G offers 7-series performance, but flagship-level connectivity. The CPU is clocked at 2.4GHz, while the GPU is Adreno 642, which is somewhere between SD855 and SD865 in terms of performance.

The Xiaomi Mi 11 Lite comes in Black, Pink, or Blue colors. It will also have three memory combinations - 6/64GB, 6/128GB, and 8/128GB. Prices will start from â¬299, with availability details to be revealed "soon".

The Mi 11 Lite 5G, is offered in Black, Yellow, or Green. The phone comes with either 6GB or 8GB RAM, while storage is 128GB for both versions. It will cost at least â¬369, and just like the LTE variant, details about market availability and launch dates will be announced soon.', 1)
INSERT [dbo].[HE141081_ducdv_Product] ([ProductID], [Product Name], [Amount], [Price], [Active], [URL Image], [userID], [Describe], [View]) VALUES (15, N'Meizu 18', 5, 819, 1, N'img\test\gsmarena_001.jpg', 1, N'The Meizu 18 has three versions. The most affordable $819 units come in Purple with 8GB RAM and 128GB storage. For just $50 extra, you can get double the internal storage.

The most expensive option of the vanilla Meizu 18 is $899 with 12GB RAM and 256GB of storage and is offered only in White.

The Meizu 18 Pro was introduced White, Blue, and Gray, but only the last one is sold on AliExpress. It costs $1,019 and comes with 8GB RAM and 256GB storage', 2)
INSERT [dbo].[HE141081_ducdv_Product] ([ProductID], [Product Name], [Amount], [Price], [Active], [URL Image], [userID], [Describe], [View]) VALUES (17, N'Xiaomi mi 11 ultra', 13, 700, 1, N'img\test\gsmarena_002 (1).jpg', 1, N'The sensor enjoys the benefits of Optical Image Stabilization (OIS) and introduces Dual Pixel Pro, which improves autofocus speed and accuracy. There''s also Smart ISO Pro, which can capture HDR photos in an instant.

The two other cameras on the Mi 11 Ultra back are no less impressive. The periscope lens offers 5x optical and 10x hybrid zoom. Even better, it is paired with a 48MP sensor (IMX586), which is fairly large in its own right 

For comparison, the Galaxy S21 Ultra has a 10 MP 1/3.24 sensor behind its periscope. Plus, the Xiaomi phone can record 8K through its periscope, which looks to be another first. Anyway, this camera also has OIS, though the lens is a bit dark at f/4.1.

Next up is the ultrawide camera, which has a lens and a 48MP 1/2.0nm sensor of its own (IMX586 again). The lens has autofocus and can shoot macro photos. Like the other two cameras on the back, this one can record 8K video. Also, all three have Night mode support.

The battery is the other headlining feature of the Mi 11 Ultra. It''sslightly larger than average for a super fast charging phone, 5,000 mAh, and sets a new benchmark with 67W wireless charging support. This matches the 67W wired charging, both can fill the battery to the top in just 36 minutes.

To pull it off, Xiaomi designed a 6x supercharge technology in-house, including a custom chip that enables wireless charging at 30V and a multi-level current control tech that speeds up charging by 10%. The phone also supports 10W reverse charging for good measure.

Okay, now we can talk about the ordinary things. And by ordinary we mean things like the 6.81 AMOLED display with 1440p+ resolution and Gorilla Glass Victus protection. It has a 120Hz refresh rate, but it is not adaptive (hence ordinary) and 480Hz touch sampling rate.

It''s an excellent screen with an A+ from DisplayMate, earned thanks to HDR10+ and Dolby Vision support. Furthermore, it is well calibrated to the DCI-P3 color space and has a high brightness mode for 900 nits of brightness (peak brightness is 1,700 nits).

Another highlight feature is the Snapdragon 888 chipset  we wouldn''t expect any less from a flagship. Xiaomi attached a new three-phase cooling system and added 12GB of LPDDR5 RAM plus 256GB of UFS 3.1 storage. The triple ISP of the chipset is used to full effect, allowing the Mi 11 Ultra to shoot with all three cameras simultaneously.

The phone has stereo speakers that were touched up by Harman Kardon. Also, it carries a Hi-Res Audio certification. Alas, there''s no 3.5mm headphone jack. There is Bluetooth 5.2 on board for wireless headphones, however. Additional connectivity includes dual SIM 5G, Wi-Fi 6E, NFC and an IR blaster.

', 4)
SET IDENTITY_INSERT [dbo].[HE141081_ducdv_Product] OFF
INSERT [dbo].[HE141081_ducdv_UserInfo] ([AccountID], [Name], [Address], [Phonenumber], [Gender], [DoB]) VALUES (1, N'Đức Đinh', N'Hải Phòng', N'0379010550', N'Male', CAST(N'2000-05-01' AS Date))
INSERT [dbo].[HE141081_ducdv_UserInfo] ([AccountID], [Name], [Address], [Phonenumber], [Gender], [DoB]) VALUES (2, N'Văn Mạnh', N'Hà Nội', N'0123456789', N'Male', CAST(N'2003-12-01' AS Date))
INSERT [dbo].[HE141081_ducdv_UserInfo] ([AccountID], [Name], [Address], [Phonenumber], [Gender], [DoB]) VALUES (3, N'Nam Hoà', N'Bắc Ninh', N'0585089385', N'Male', CAST(N'2000-12-06' AS Date))
ALTER TABLE [dbo].[HE141081_ducdv_HistoryBuy]  WITH CHECK ADD FOREIGN KEY([AccountID])
REFERENCES [dbo].[HE141081_ducdv_Account] ([AccountID])
GO
ALTER TABLE [dbo].[HE141081_ducdv_HistoryBuy]  WITH CHECK ADD FOREIGN KEY([ProductID])
REFERENCES [dbo].[HE141081_ducdv_Product] ([ProductID])
GO
ALTER TABLE [dbo].[HE141081_ducdv_News]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[HE141081_ducdv_Product] ([ProductID])
GO
ALTER TABLE [dbo].[HE141081_ducdv_News]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[HE141081_ducdv_Account] ([AccountID])
GO
ALTER TABLE [dbo].[HE141081_ducdv_Product]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[HE141081_ducdv_Account] ([AccountID])
GO
ALTER TABLE [dbo].[HE141081_ducdv_UserInfo]  WITH CHECK ADD FOREIGN KEY([AccountID])
REFERENCES [dbo].[HE141081_ducdv_Account] ([AccountID])
GO
USE [master]
GO
ALTER DATABASE [PRJ321_SE1429] SET  READ_WRITE 
GO
