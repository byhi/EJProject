USE [ejdb]
GO
/****** Object:  Table [dbo].[product_entity]    Script Date: 2020.12.30. 16:46:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_entity](
	[productid] [bigint] IDENTITY(1,1) NOT NULL,
	[product_number] [bigint] NULL,
	[userid] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[productid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[user_entity]    Script Date: 2020.12.30. 16:46:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[user_entity](
	[userid] [bigint] IDENTITY(1,1) NOT NULL,
	[birth_date] [date] NULL,
	[sex] [bit] NOT NULL,
	[user_name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[userid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[product_entity]  WITH CHECK ADD  CONSTRAINT [FKn5careowq79he3xgt2c4i4fh1] FOREIGN KEY([userid])
REFERENCES [dbo].[user_entity] ([userid])
GO
ALTER TABLE [dbo].[product_entity] CHECK CONSTRAINT [FKn5careowq79he3xgt2c4i4fh1]
GO
