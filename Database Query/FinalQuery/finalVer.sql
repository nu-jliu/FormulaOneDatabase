USE [master]
GO
/****** Object:  Database [Formula1DatabaseFinalVer]    Script Date: 2/21/2020 2:59:05 PM ******/
CREATE DATABASE [Formula1DatabaseFinalVer]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Formula1Database', FILENAME = N'E:\Database\MSSQL12.MSSQLSERVER\MSSQL\DATA\Formula1DatabaseFinalVer.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 10%)
 LOG ON 
( NAME = N'Formula1Database_log', FILENAME = N'E:\Database\MSSQL12.MSSQLSERVER\MSSQL\DATA\Formula1DatabaseFinalVer_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Formula1DatabaseFinalVer].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET ARITHABORT OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET RECOVERY FULL 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET  MULTI_USER 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET DELAYED_DURABILITY = DISABLED 
GO
USE [Formula1DatabaseFinalVer]
GO
/****** Object:  User [zhaoy10]    Script Date: 2/21/2020 2:59:05 PM ******/
CREATE USER [zhaoy10] FOR LOGIN [zhaoy10] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [niey]    Script Date: 2/21/2020 2:59:05 PM ******/
CREATE USER [niey] FOR LOGIN [niey] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [zhaoy10]
GO
ALTER ROLE [db_owner] ADD MEMBER [niey]
GO
ALTER ROLE [db_accessadmin] ADD MEMBER [niey]
GO
ALTER ROLE [db_securityadmin] ADD MEMBER [niey]
GO
ALTER ROLE [db_backupoperator] ADD MEMBER [niey]
GO
/****** Object:  UserDefinedFunction [dbo].[calculate_age]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE function [dbo].[calculate_age](@dob date)
returns int
as
begin
declare @age as int,
		@year as int,
		@month as int,
		@day as int
	set @year = YEAR(@dob)
	set @age = YEAR(GETDATE()) - @year
	set @month = MONTH(@dob)
	set @day = DAY(@dob)
	if (@month > MONTH(GETDATE()))
	begin
		set @age = @age - 1
	end
	else if @day > DAY(GETDATE())
	begin
		set @age = @age - 1
	end
	return @age
end
GO
/****** Object:  Table [dbo].[Driver]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Driver](
	[DID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](20) NOT NULL,
	[DOB] [date] NOT NULL,
	[Number] [int] NULL,
 CONSTRAINT [PK__Driver__C0365630DD935A65] PRIMARY KEY CLUSTERED 
(
	[DID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [CK_Number] UNIQUE NONCLUSTERED 
(
	[Number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_Driver] UNIQUE NONCLUSTERED 
(
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[History]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[History](
	[UID] [int] NOT NULL,
	[RID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UID] ASC,
	[RID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Participates]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Participates](
	[RID] [int] NOT NULL,
	[DID] [int] NOT NULL,
	[Rank] [int] NULL,
 CONSTRAINT [PK__Particip__D6FC24518251ABF3] PRIMARY KEY CLUSTERED 
(
	[RID] ASC,
	[DID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Race]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Race](
	[RID] [int] IDENTITY(1,1) NOT NULL,
	[Weather] [varchar](20) NOT NULL,
	[Date] [date] NOT NULL,
	[Race_Name] [varchar](40) NOT NULL,
	[Lap_Time] [time](3) NULL,
	[DID] [int] NULL,
 CONSTRAINT [PK__Race__CAFF41322882B7B9] PRIMARY KEY CLUSTERED 
(
	[RID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_Race_1] UNIQUE NONCLUSTERED 
(
	[Race_Name] ASC,
	[Date] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Star_Driver]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Star_Driver](
	[UID] [int] NOT NULL,
	[DID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UID] ASC,
	[DID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Star_Team]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Star_Team](
	[UID] [int] NOT NULL,
	[TID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UID] ASC,
	[TID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Stats_by_year]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Stats_by_year](
	[Year] [int] NOT NULL,
	[DID] [int] NOT NULL,
	[Point] [int] NULL,
 CONSTRAINT [PK__Stats_by__C8BE053625CD36D3] PRIMARY KEY CLUSTERED 
(
	[Year] ASC,
	[DID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Team]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Team](
	[TID] [int] IDENTITY(1,1) NOT NULL,
	[Team_name] [varchar](20) NOT NULL,
	[Engine_manf] [varchar](20) NOT NULL,
	[Model_Num] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[TID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_Team] UNIQUE NONCLUSTERED 
(
	[Team_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Team_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[UID] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](20) NOT NULL,
	[EMail] [varchar](50) NOT NULL,
	[PasswordHash] [varchar](50) NOT NULL,
	[PasswordSalt] [varchar](50) NOT NULL,
	[Accessbility] [int] NOT NULL,
 CONSTRAINT [PK__User__C5B19602301FF114] PRIMARY KEY CLUSTERED 
(
	[UID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UQ__User__F3DBC572EEC657AF] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Works_For]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Works_For](
	[DID] [int] NOT NULL,
	[TID] [int] NULL,
	[Year] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[DID] ASC,
	[Year] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[All_Driver_View]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



CREATE VIEW [dbo].[All_Driver_View]
AS
SELECT     [Number] as 'No.', Name, dbo.calculate_age(DOB) AS Age, t.Team_name as 'Team', wf.Year
FROM        dbo.Driver d
join dbo.Works_For wf on d.DID = wf.DID
join dbo.Team t on t.TID = wf.TID
GO
/****** Object:  View [dbo].[all_race_info]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



CREATE view [dbo].[all_race_info]
as
select r.Race_Name as [Race Name], r.Date, r.Weather, r.Lap_Time as [Fastest Lap Time], d.Name as [Fastest Driver], d2.Name as [Champion]
from Race r left join Participates p on r.RID = p.RID and p.Rank = 1
			join Driver d on r.DID = d.DID
			left join Driver d2 on d2.DID = p.DID

GO
/****** Object:  View [dbo].[All_Team]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE view [dbo].[All_Team] 
as 
select t.Team_name, t.Engine_manf, t.Model_Num
from Team t

GO
/****** Object:  View [dbo].[driver_race_info]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[driver_race_info]
as
select d.Name, dbo.calculate_age(d.DOB) as Age, r.Race_Name, r.Date, p.Rank
from Driver d join Participates p on d.DID = p.DID
			  join Race r on r.RID = p.RID
GO
/****** Object:  View [dbo].[Driver_Stats_View]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[Driver_Stats_View]
AS
SELECT s.Year, d .Number AS [No.], d .Name, s.Point, RANK() OVER (partition BY s.Year
ORDER BY s.Point DESC) AS Rank, T .Team_name
FROM     Stats_by_year s JOIN
                  Driver d ON s.DID = d .DID LEFT JOIN
                  Works_For W ON s.Year = w.Year AND s.DID = w.DID LEFT JOIN
                  Team T ON w.TID = T .TID
GO
/****** Object:  View [dbo].[patricipate_info]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE view [dbo].[patricipate_info]
as
select d.Name, d.DID, p.Rank, r.Race_Name, r.RID, YEAR(r.Date) as year
from Driver d join Participates p on d.DID = p.DID
			  join Race r on r.RID = p.RID
GO
/****** Object:  View [dbo].[show_DriverOnTeam]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[show_DriverOnTeam]
as
select d.Name, dbo.calculate_age(d.DOB) as Age, t.Team_name, t.Model_Num, t.Engine_manf
from Driver d join Works_For w on d.DID = w.DID
		      join Team t on w.TID = t.TID
GO
/****** Object:  View [dbo].[Team_Driver]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[Team_Driver]
as
select t.Team_name, d1.Name as 'Driver 1', d2.Name as 'Driver 2'
from Team t join Works_For w1 on t.TID = w1.TID
			join driver d1 on w1.DID = d1.DID
			join Works_For w2 on t.TID = w2.TID
			join Driver d2 on w2.DID = d2.DID and d2.DID > d1.DID
GO
/****** Object:  View [dbo].[team_stats_view]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE view [dbo].[team_stats_view]
as
select *, RANK() over (partition by a.Year order by a.[Total Point] desc) as rank
from (select t.Team_name, sum(s.Point) as [Total Point], s.Year
from Stats_by_year s join Works_For w on s.DID = w.DID and s.Year = w.Year
				     join Team t on w.TID = t.TID
group by w.TID, s.Year, t.Team_name) as a
GO
/****** Object:  View [dbo].[User_Stared_Team]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[User_Stared_Team]
as 
select Team_name, Team.Engine_manf, Team.Model_Num
from Star_Team 
join Team
on Star_Team.TID = Team.TID
GO
/****** Object:  View [dbo].[User_Stored_Drivers]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[User_Stored_Drivers]
AS
SELECT        dbo.Driver.*
FROM            dbo.[User] INNER JOIN
                         dbo.Star_Drive ON dbo.[User].UID = dbo.Star_Drive.UID INNER JOIN
                         dbo.Driver ON dbo.Star_Drive.DID = dbo.Driver.DID
GO
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [DF_User_Accessbility]  DEFAULT ((1)) FOR [Accessbility]
GO
ALTER TABLE [dbo].[History]  WITH NOCHECK ADD  CONSTRAINT [FK__History__RID__42E1EEFE] FOREIGN KEY([RID])
REFERENCES [dbo].[Race] ([RID])
GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK__History__RID__42E1EEFE]
GO
ALTER TABLE [dbo].[History]  WITH NOCHECK ADD  CONSTRAINT [FK__History__UID__41EDCAC5] FOREIGN KEY([UID])
REFERENCES [dbo].[User] ([UID])
GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK__History__UID__41EDCAC5]
GO
ALTER TABLE [dbo].[Participates]  WITH CHECK ADD  CONSTRAINT [FK__Participate__DID__318258D2] FOREIGN KEY([DID])
REFERENCES [dbo].[Driver] ([DID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Participates] CHECK CONSTRAINT [FK__Participate__DID__318258D2]
GO
ALTER TABLE [dbo].[Participates]  WITH CHECK ADD  CONSTRAINT [FK__Participate__RID__308E3499] FOREIGN KEY([RID])
REFERENCES [dbo].[Race] ([RID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Participates] CHECK CONSTRAINT [FK__Participate__RID__308E3499]
GO
ALTER TABLE [dbo].[Race]  WITH NOCHECK ADD  CONSTRAINT [FK__Race__DID__37703C52] FOREIGN KEY([DID])
REFERENCES [dbo].[Driver] ([DID])
GO
ALTER TABLE [dbo].[Race] CHECK CONSTRAINT [FK__Race__DID__37703C52]
GO
ALTER TABLE [dbo].[Star_Driver]  WITH NOCHECK ADD  CONSTRAINT [FK__Star_Drive__DID__498EEC8D] FOREIGN KEY([DID])
REFERENCES [dbo].[Driver] ([DID])
GO
ALTER TABLE [dbo].[Star_Driver] CHECK CONSTRAINT [FK__Star_Drive__DID__498EEC8D]
GO
ALTER TABLE [dbo].[Star_Driver]  WITH NOCHECK ADD  CONSTRAINT [FK__Star_Drive__UID__489AC854] FOREIGN KEY([UID])
REFERENCES [dbo].[User] ([UID])
GO
ALTER TABLE [dbo].[Star_Driver] CHECK CONSTRAINT [FK__Star_Drive__UID__489AC854]
GO
ALTER TABLE [dbo].[Star_Team]  WITH NOCHECK ADD FOREIGN KEY([TID])
REFERENCES [dbo].[Team] ([TID])
GO
ALTER TABLE [dbo].[Star_Team]  WITH NOCHECK ADD FOREIGN KEY([TID])
REFERENCES [dbo].[Team] ([TID])
GO
ALTER TABLE [dbo].[Star_Team]  WITH NOCHECK ADD  CONSTRAINT [FK__Star_Team__UID__4C6B5938] FOREIGN KEY([UID])
REFERENCES [dbo].[User] ([UID])
GO
ALTER TABLE [dbo].[Star_Team] CHECK CONSTRAINT [FK__Star_Team__UID__4C6B5938]
GO
ALTER TABLE [dbo].[Stats_by_year]  WITH NOCHECK ADD  CONSTRAINT [FK__Stats_by_ye__DID__45BE5BA9] FOREIGN KEY([DID])
REFERENCES [dbo].[Driver] ([DID])
GO
ALTER TABLE [dbo].[Stats_by_year] CHECK CONSTRAINT [FK__Stats_by_ye__DID__45BE5BA9]
GO
ALTER TABLE [dbo].[Works_For]  WITH CHECK ADD  CONSTRAINT [FK__Works_For__DID__3DE82FB7] FOREIGN KEY([DID])
REFERENCES [dbo].[Driver] ([DID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Works_For] CHECK CONSTRAINT [FK__Works_For__DID__3DE82FB7]
GO
ALTER TABLE [dbo].[Works_For]  WITH CHECK ADD FOREIGN KEY([TID])
REFERENCES [dbo].[Team] ([TID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Driver]  WITH CHECK ADD  CONSTRAINT [CK_Driver_DOB] CHECK  (([DOB]<getdate()))
GO
ALTER TABLE [dbo].[Driver] CHECK CONSTRAINT [CK_Driver_DOB]
GO
ALTER TABLE [dbo].[Participates]  WITH CHECK ADD  CONSTRAINT [CK_RaceRank] CHECK  (([rank]>(0) OR [Rank]<(25)))
GO
ALTER TABLE [dbo].[Participates] CHECK CONSTRAINT [CK_RaceRank]
GO
ALTER TABLE [dbo].[Race]  WITH CHECK ADD  CONSTRAINT [CK_Lap_Time] CHECK  ((datepart(minute,[Lap_Time])<(3)))
GO
ALTER TABLE [dbo].[Race] CHECK CONSTRAINT [CK_Lap_Time]
GO
ALTER TABLE [dbo].[Race]  WITH CHECK ADD  CONSTRAINT [CK_Valid_Name] CHECK  ((len([Race_Name])>=(10)))
GO
ALTER TABLE [dbo].[Race] CHECK CONSTRAINT [CK_Valid_Name]
GO
ALTER TABLE [dbo].[Stats_by_year]  WITH CHECK ADD  CONSTRAINT [CK_Year] CHECK  (([Year]>=(1950)))
GO
ALTER TABLE [dbo].[Stats_by_year] CHECK CONSTRAINT [CK_Year]
GO
ALTER TABLE [dbo].[Team]  WITH CHECK ADD  CONSTRAINT [CK_Engine] CHECK  (([Engine_manf]<>NULL))
GO
ALTER TABLE [dbo].[Team] CHECK CONSTRAINT [CK_Engine]
GO
ALTER TABLE [dbo].[Team]  WITH CHECK ADD  CONSTRAINT [CK_Modnum] CHECK  (([Model_Num]<>NULL))
GO
ALTER TABLE [dbo].[Team] CHECK CONSTRAINT [CK_Modnum]
GO
ALTER TABLE [dbo].[Team]  WITH CHECK ADD  CONSTRAINT [CK_Team] CHECK  (([Team_name]<>NULL))
GO
ALTER TABLE [dbo].[Team] CHECK CONSTRAINT [CK_Team]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [CK_UID] CHECK  (([User].[UID]<>NULL))
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [CK_UID]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [CK_User_PassFormat] CHECK  (([Email] like '%@%'))
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [CK_User_PassFormat]
GO
/****** Object:  StoredProcedure [dbo].[AddDriver]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[AddDriver] (
	@name varchar(20) = null,
	@Dob date,
	@Number int
)
as
if(@name='')
begin
Return 1
Print N' Please Entry Name'
end
if(@Dob is null)
begin
Return 3
Print N' Please Entry Date of Birth'
end
begin
	declare @age as int
	exec @age = dbo.get_age @dob = @Dob
	if @age < 18 or @age > 50
	begin
		print N'The age range is invalid'
		return 2
	end
	insert into Driver ([Name], DOB, Number)
	values (@name, @dob, @Number)
end
GO
/****** Object:  StoredProcedure [dbo].[AddHistory]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[AddHistory] (
	@race varchar(40) = null,
	@Uid int =null,
	@year int = null
)
as

if(@race='')
begin
Print N' Race Name cannot be empty'
Return 1
end

if (@year is null)
begin
print N'Invalid year input'
return 2
end

if((select count (*) from Race where Race_Name=@race and YEAR(Date) = @year) <1)
begin
Print N'No matching race found'
Return 3
end

if(@Uid is null)
begin
print N'Illegal user detected'
return 4
end


if((select count (*) from [User] where UID = @Uid) <1)
begin
Print N'User not exist'
Return 5
end



begin
declare
@RID int = null
set @RID =( select RID from Race where Race_Name=@race and YEAR(Date) = @year)

if ((select count(*) from History where UID = @Uid and RID = @RID) > 0)
begin
print N'Invalid Entry'
return 6
end

	insert into History
	values (@Uid, @RID)
end
GO
/****** Object:  StoredProcedure [dbo].[AddParticipates]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[AddParticipates] (
	@name varchar(20),
	@year int,
	@racename varchar(40),
	@rank int
)
as
begin
declare @rid as int,
		@did as int
if (@name in (select [Name] from Driver) 
	and @year in (select year([Date]) from Race)
	and @racename in (select Race_Name from Race))
begin
	set @rid = (select RID from Race where YEAR(Date) = @year and Race_Name = @racename)
	set @did = (select DID from Driver where [Name] = @name)
	if ((select count(*) from Participates where DID = @did and RID = @rid) > 0 
	or (select COUNT(*) from Participates where RID = @rid and Rank = @rank) > 0)
	begin
		print N'Entry already exists'
		return 10
	end
	else 
		begin
		insert into Participates(RID, DID, Rank)
		values (@rid, @did, @rank)
	end
end
else
begin
	print N'At least one element does not exist'
	return 15
end
end
GO
/****** Object:  StoredProcedure [dbo].[AddRace]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[AddRace] (
	@weather varchar(20),
	@date date = null,
	@racename varchar(40) = null,
	@laptime time(7) = null,
	@Dname varchar(20)
)
as
if(@Dname in (select [Name] from Driver) 
	and (select COUNT(*) from Race where Date = @date) < 1 
	and (select COUNT(*) from Race where Race_Name = @racename and YEAR(Date) = YEAR(@date)) < 1)
begin
	declare @did as int
	set @did = (select DID from Driver where Name = @Dname)
	insert into Race(Weather, [Date], Race_Name, Lap_Time, DID)
	values (@weather, @date, @racename, @laptime, @did)
end
else
begin
	print N'Invalid input'
	return 1
end
GO
/****** Object:  StoredProcedure [dbo].[AddStar_Driver]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[AddStar_Driver] (
	@uid int,
	@did int
)
as
if (@uid in (select UID from [User]) and @did in (select DID from Driver))
begin
	if((select count(*) from Star_Driver where UID = @uid and DID = @did) > 0)
	begin
		print N'The driver is already on your favorite list'
		return 2
	end
	insert into Star_Driver
	values (@uid, @did)
end
else
begin
	print N'User or Driver not exists'
	return 1
end
GO
/****** Object:  StoredProcedure [dbo].[AddStar_Team]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[AddStar_Team] (
	@uid int,
	@tid int
)
as
if (@uid in (select UID from [User]) and @tid in (select TID from Team))
begin
	if ((select count(*) from Star_Team where UID = @uid and TID = @tid) > 0)
	begin
		print N'The team is already in you favorite list'
		return 2
	end
	insert into Star_Team
	values (@uid, @tid)
end
else
begin
	print N'User or Team not exists'
	return 1
end
GO
/****** Object:  StoredProcedure [dbo].[AddStats]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[AddStats] (
	@year int,
	@did int,
	@point int = null,
	@rank int = null
)
as
if (@year > YEAR(GETDATE()))
begin
print N'Year too large'
return 1
end
else if (@did in (select DID from Driver))
begin
insert into Stats_by_year
values (@year, @did, @point, @rank)
end
else
begin 
print N'Driver not exists'
return 2
end
GO
/****** Object:  StoredProcedure [dbo].[AddTeam]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[AddTeam] (
	@name varchar(20),
	@manf varchar(20),
	@num varchar(20)
)
as
if (@name is null or @name = '') 
begin
print N'Team name cannot be null or empty'
return 1
end 
if(@manf is null or @manf = '') 
begin
print N'Please Entry Manufacture'
return 3
end
if(@num is null or @num = '') 
begin
print N'Please Entry Model Number'
return 4
end
if ((select count(*) from Team where Team_name = @name) = 1)
begin
print N'Team name already existed'
return 2
end
insert into Team(Team_name, Engine_manf, Model_Num)
values (@name, @manf, @num)
GO
/****** Object:  StoredProcedure [dbo].[AddWorksFor]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[AddWorksFor] (
	@Dname varchar(50) = null,
	@Tname varchar(50) = null,
	@Year int=null
)
as
declare @Tid as int,
		@Did as int
set @Tid = (select TID from Team where Team_name = @Tname)
set @Did = (select DID from Driver where Name = @Dname)
if (@Dname is null or @Tname is null or @Year is null)
begin
print N'Invalid input'
return 5
end
if(@Dname = '' or (select count(*) from Driver where [Name] = @Dname) < 1)
begin
Print N' Please Entry valid Driver Name'
Return 1

end
if(@Tname = '' or ((select count (*) from Team where Team_name = @Tname)< 1))
begin
Print N' Please Entry valid Team Name'
Return 2

end

if(@Year = '' or @Year < 1950 or @Year > YEAR(GETDATE()) or @Year is null)
begin
Print N' Please Entry valid  Year'
Return 3

end

if((select count (*) from Works_For where DID = @Did and @Year = [YEAR] and TID = @Tid) > 0)
begin
	print N' record already exist'
	return 4
	
end
	insert into Works_For([Year],DID,TID)
	values ( @Year, @Did,@Tid)
GO
/****** Object:  StoredProcedure [dbo].[change_Password]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[change_Password] (
	@Username_0 varchar(20),
	@EMail_1 varchar(50),
	@PasswordSalt_2 varchar(50),
	@PasswordHash_3 varchar(50)
	)
as
if(@Username_0 = null)
	begin
		print 'error: Invalid Username Detected'
		return 10
	end
if not exists(select * from [User] where username = @Username_0)
	begin
		print 'error: Invalid Username Detected'
		return 10
	end
if(@EMail_1 = null)
	begin
		print 'error: Invalid E-Mail Detected'
		return 10
	end
if(@PasswordHash_3 = null)
	begin 
		print 'error: Invalid Password Detected'
		return 10
	end
if((select EMail from [User] where username = @Username_0) <> @EMail_1)
	begin 
		print 'error: The E-Mail is incorrect'
		return 10
	end
update  [User]
set PasswordHash = @PasswordHash_3, PasswordSalt = @PasswordSalt_2
where username = @Username_0 and EMail = @EMail_1
print 'Changed Successfully'
return 0
GO
/****** Object:  StoredProcedure [dbo].[Create_All_Driver_View]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[Create_All_Driver_View]
as
exec ('
create view [All_Driver_View]
as
select DID, Age, [Name], DOB
from Driver')
GO
/****** Object:  StoredProcedure [dbo].[Create_Driver_Stats_View]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[Create_Driver_Stats_View] 
as
exec ('
create view [Driver_Stats_View]
as
select driver.Name, Stats_by_year.Year, Stats_by_year.Rank, Stats_by_year.Point
from Stats_by_year join Driver on Stats_by_year.DID = Driver.DID')
GO
/****** Object:  StoredProcedure [dbo].[Create_Table]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[Create_Table] 
as
create table [User] (
	UID int identity(1, 1),
	primary key(UID),
	username varchar(20) not null unique,
	Password text,
	EMail varchar(50) not null
)
create table Driver (
	DID int identity(1, 1),
	primary key(DID),
	Name varchar(20) not null unique,
	DOB Date
)
create table Team (
	TID int identity(1, 1),
	primary key(TID),
	Team_name varchar(20) not null unique,
	Engine_manf varchar(20) not null,
	Model_Num varchar(10)
)
create table Race (
	RID int identity(1, 1),
	primary key(RID),
	Weather varchar(20) not null,
	[Date] date not null unique,
	[Race_Name] varchar(20) not null,
	[Lap_Time] time,
	DID int references Driver(DID) on update cascade on delete set null,
)
create table [Engage_IN] (
	RID int references Race(RID) on update cascade on delete cascade, 
	TID int references Team(TID) on update cascade on delete cascade,
	primary key(RID, TID)
)
create table Participates (
	RID int references Race(RID) on update cascade on delete cascade,
	DID int references Driver(DID) on update cascade on delete cascade,
	Rank int,
	primary key(RID, DID)
)
create table History (
	UID int references [User](UID) on update cascade on delete cascade,
	RID int references Race(RID) on update cascade on delete cascade,
	primary key(UID, RID)
)
create table [Stats_by_year] (
	Year int,
	DID int references Driver(DID) on update cascade on delete cascade,
	primary key(Year, DID),
	Point int,
	Rank int
)
create table Star_Drive (
	UID int references [User](UID) on update cascade on delete cascade,
	DID int references Driver(DID) on update cascade on delete cascade, 
	primary key (UID, DID)
)
create table Star_Team (
	UID int references [User](UID) on update cascade on delete cascade,
	TID int references Team(TID) on update cascade on delete cascade,
	primary key (UID, TID)
)
create table Works_For (
	DID int references Driver(DID) on update cascade on delete cascade,
	TID int references Team(TID) on update cascade on delete cascade, 
	primary key (DID, TID)
)
GO
/****** Object:  StoredProcedure [dbo].[Create_User_Stared_Drivers_View]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[Create_User_Stared_Drivers_View]
as exec ('
create view [User_Stored_Driver_View]
as
select Driver.DID, Driver.Age, Driver.Name, Driver.DOB
from [User] join Star_Drive on [User].UID = Star_Drive.UID
			join Driver on Driver.Age = Star_Drive.DID')
GO
/****** Object:  StoredProcedure [dbo].[Create_User_Stared_Team]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[Create_User_Stared_Team]
as
exec ('
create view [User_Stared_Team]
select Team.Team_name, Team.Engine_manf, team.Model_Num
from [User] join Star_Team on [User].UID = Star_Team.UID
			join Team on Star_Team.TID = Team.TID')
GO
/****** Object:  StoredProcedure [dbo].[delete_Star_Driver]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[delete_Star_Driver]
(
	@UID_0 int,
	@DName_1 varchar(20)
)
as 
if((select DID from Driver where [Name] = @DName_1) = null)
	begin 
		print'Driver Not Found'
		return -1
	end
		
declare @DID_2 int
set @DID_2 = (select DID from Driver where [Name] = @DName_1)

if((select sd.DID from Star_Driver sd
	where sd.UID = @UID_0) = null)
	begin 
		print N'The Driver is not Starred'
		return 2
	end

delete [Star_Driver] where DID = @DID_2 and UID = @UID_0
GO
/****** Object:  StoredProcedure [dbo].[delete_Star_Team]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[delete_Star_Team]
(
	@UID_0 int,
	@TName_1 varchar(20)
)
as 
if((select TID from Team where [Team_name] = @TName_1) = null)
	begin 
		raiserror('Team Not Found', 15, 1)
		return -1
	end
		
declare @TID_2 int
set @TID_2 = (select TID from Team where Team_name = @TName_1)

if((select t.TID from Star_Team st
	join Team t on st.TID = t.TID
	where st.TID = @UID_0) = null)
	begin 
		raiserror('The Team is not Starred', 15, 1)
		return 2
	end

delete Star_Team where (TID = @TID_2 and UID = @UID_0)
GO
/****** Object:  StoredProcedure [dbo].[disable_all_trigger]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[disable_all_trigger]
as
disable trigger all on dbo.Participates;
disable trigger all on dbo.Race;
GO
/****** Object:  StoredProcedure [dbo].[enable_all_trigger]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[enable_all_trigger]
as
enable trigger all on dbo.Participates;
enable trigger all on dbo.Race;
GO
/****** Object:  StoredProcedure [dbo].[get_Accessbility]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

 CREATE procedure [dbo].[get_Accessbility] (
	@UID_0 int
 )
 as
declare @Return_Val int
set @Return_Val = (select Accessbility from [User] where [UID] = @UID_0)
return @Return_Val
GO
/****** Object:  StoredProcedure [dbo].[get_age]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[get_age] (
	@dob date
)
as
begin
	declare @age as int,
			@year as int,
			@month as int,
			@day as int
	set @year = YEAR(@dob)
	set @age = YEAR(GETDATE()) - @year
	set @month = MONTH(@dob)
	set @day = DAY(@dob)
	if @month > MONTH(GETDATE())
	begin
		set @age = @age - 1
	end
	else if @day > DAY(GETDATE())
	begin
		set @age = @age - 1
	end
	return @age
end
GO
/****** Object:  StoredProcedure [dbo].[get_all_Drivers]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[get_all_Drivers] (
@year int
)
as 
	select * from All_Driver_View adv
	where adv.year = @year
	order by adv.[No.] asc
GO
/****** Object:  StoredProcedure [dbo].[get_all_Races]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[get_all_Races]
as 
	select *
	from dbo.all_race_info as i
	order by i.Date asc
GO
/****** Object:  StoredProcedure [dbo].[get_all_stats]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[get_all_stats]
as
begin
select *
from Driver_Stats_View v
order by v.Rank asc, v.[No.] asc
end
GO
/****** Object:  StoredProcedure [dbo].[get_all_Teams]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[get_all_Teams]
as 
	select * from All_Team
GO
/****** Object:  StoredProcedure [dbo].[get_all_WorksFor]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[get_all_WorksFor]
as 
	select d.[Name], t.Team_name, w.Year from [Works_For] w join Team t on w.TID = t.TID
												    join Driver d on w.DID = d.DID
GO
/****** Object:  StoredProcedure [dbo].[get_DriverID]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[get_DriverID] (
	@name varchar(20)
)
as
begin
	declare @id as int
	set @id = (select top 1 DID from Driver where [Name] = @name)
	if (@id is null)
	begin
		print N'Invalid driver name'
		return -1
	end
	else 
	begin
		return @id
	end
end
GO
/****** Object:  StoredProcedure [dbo].[get_Likes]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[get_Likes] (
	@uid int
)
as
select u.username, d.Name
from [User] u join Star_Drive s on u.UID = s.UID
			join Driver d on s.DID = d.DID
where u.UID = @uid
GO
/****** Object:  StoredProcedure [dbo].[get_not_watched_race]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[get_not_watched_race] (
	@uid as int,
	@year as int
)
as
select ari.[Race Name], ari.Date
from dbo.all_race_info ari
where YEAR(Date) = @year and [Race Name] not in (select Race_Name from History h join Race r on h.RID = r.RID where h.UID = @uid)
GO
/****** Object:  StoredProcedure [dbo].[get_points]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[get_points]
(
	@rank int = null
)
as
begin
	if (@rank > 24 or @rank < 0)
	begin
		raiserror('Invalid input of rank', 14, 1)
		return 10
	end
	if (@rank is null)
		return 0
	if(@rank = 1)
		return 25
	else if(@rank = 2)
		return 18
	else if(@rank = 3)
		return 15
	else if(@rank = 4)
		return 12
	else if(@rank = 5)
		return 10
	else if(@rank = 6)
		return 8
	else if(@rank = 7)
		return 6
	else if(@rank = 8)
		return 4
	else if(@rank = 9)
		return 2
	else if(@rank = 10)
		return 1
	else 
		return 0 
end
GO
/****** Object:  StoredProcedure [dbo].[get_race_years]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[get_race_years]
as
select distinct year(i.Date)
from dbo.all_race_info i
GO
/****** Object:  StoredProcedure [dbo].[get_Starred_Driver]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[get_Starred_Driver]
(@UID_0 int)
as 
select dr.Name, dr.Number as 'No.', t.Team_name as 'Team', r.Race_Name, r.Date, p.[Rank] from Star_Driver sd
join Works_for wf on sd.DID = wf.DID
join Driver dr on sd.DID = dr.DID
join Team t on t.TID = wf.TID
join Participates p on p.DID = dr.DID
join Race r on r.RID = p.RID
where sd.[UID] = @UID_0
GO
/****** Object:  StoredProcedure [dbo].[get_Starred_Team]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[get_Starred_Team]
(@UID_0 int)
as 
select t.Team_name as 'Team', tsv.[Total Point], tsv.Year, tsv.rank from Star_Team st
join Team t on t.TID = st.TID
join team_stats_view tsv on t.Team_name = tsv.Team_name
where st.UID = @UID_0
GO
/****** Object:  StoredProcedure [dbo].[get_Stats]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[get_Stats] (
@Year int
)
as
select distinct * from Driver_Stats_View ads where ads.[Year] = @Year
order by ads.Rank asc
GO
/****** Object:  StoredProcedure [dbo].[get_Stats_Years]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[get_Stats_Years]
as
select distinct [Year]
from Stats_by_year
GO
/****** Object:  StoredProcedure [dbo].[get_Team_Stats]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[get_Team_Stats] (
@Year int
)
as
select ads.Team_name, ads.[Total Point], ads.Rank from team_stats_view ads where ads.[Year] = @Year
order by ads.Rank asc
GO
/****** Object:  StoredProcedure [dbo].[get_TeamID]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[get_TeamID] (
	@name varchar(20)
)
as
begin
	declare @id as int
	set @id = (select top 1 TID from Team where Team_name = @name)
	if (@id is null)
	begin
		print N'Invalid team name'
		return -1
	end
	else 
	begin
		return @id
	end
end
GO
/****** Object:  StoredProcedure [dbo].[get_UID]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[get_UID] (
	@Username_0 varchar(20)
	)
as
declare @Return_Val int
set @Return_Val = (select [UID] from [User] where username = @Username_0)
return @Return_Val
GO
/****** Object:  StoredProcedure [dbo].[get_User_History]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[get_User_History]
(@UID_0 int)
as 
select r.Weather, r.Date,r.Race_Name,r.Lap_Time/*, t.Team_name*/ from History H
--join Works_for wf on sd.DID = wf.DID
join Race r on H.RID = r.RID
--join Team t on t.TID = wf.TID
where h.UID = @UID_0
GO
/****** Object:  StoredProcedure [dbo].[getDrivers]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[getDrivers]
as
select [Name]
from Driver
GO
/****** Object:  StoredProcedure [dbo].[getDriversInfo]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[getDriversInfo]
(@DName varchar(20)= null)
as
if @DName =null 
begin 
print 'Enter Driver Name'
return 10
end
else
begin
select Age, [Name], DOB
from Driver 
where @DName= [Name]
end
GO
/****** Object:  StoredProcedure [dbo].[getRaceInfo]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[getRaceInfo] 
(
	@race_time datetime = null,
	@race_name varchar(20) = null
)
as
if (@race_name is null and @race_time is null)
begin
print N'Invalid input'
return 10
end
if (@race_time is null)
begin
select Weather, [Time], Race_Name, Lap_Time, d.[Name]
from Race r join Driver d on r.DID = d.DID
where r.Race_Name = @race_name
end
else if (@race_name is null)
begin 
select Weather, [Time], Race_Name, Lap_Time, d.[Name] 
from Race r join Driver d on r.DID = d.DID
where r.[Time] = @race_time
end
else
begin
select Weather, [Time], Race_Name, Lap_Time, d.[Name]
from Race r join Driver d on r.DID = d.DID
where Race_Name = @race_name and [Time] = @race_time
end
GO
/****** Object:  StoredProcedure [dbo].[getTeamInfo]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create proc [dbo].[getTeamInfo]
(@TName varchar(20)= null)
as
if @TName =null 
begin 
print 'Enter Team Name'
return 10
end
else
begin
select Team_name, Engine_manf, Model_Num
from Team 
where @TName= Team_name
end
GO
/****** Object:  StoredProcedure [dbo].[Pupulate_Driver]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[Pupulate_Driver] (
	@id as int,
	@name as varchar(20),
	@dob as date,
	@num as int

)
as
set identity_insert Driver on
insert into Driver(DID, [Name], DOB, Number)
values(@id, @name, @dob, @num)
set identity_insert Driver off
GO
/****** Object:  StoredProcedure [dbo].[Recover_History]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[Recover_History] (
	@uid as int,
	@rid as int
)
as
alter table History nocheck constraint all
insert into History(UID, RID)
values(@uid, @rid)
alter table History check constraint all
GO
/****** Object:  StoredProcedure [dbo].[Recover_Participates]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[Recover_Participates] (
	@rid as int,
	@did as int,
	@rank as int
)
as
alter table Participates nocheck constraint all;
disable trigger all on Participates;
insert into Participates(RID, DID, Rank)
values(@rid, @did, @rank)
alter table Participates check constraint all;
enable trigger all on Participates;
GO
/****** Object:  StoredProcedure [dbo].[Recover_Race]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[Recover_Race] (
	@rid as int,
	@weather as varchar(20),
	@date as date,
	@name as varchar(40),
	@time as time,
	@did as int
)
as
set identity_insert Race on;
disable trigger all on Race;
alter table Race nocheck constraint all
insert into Race(RID, Weather, Date, Race_Name, Lap_Time, DID)
values(@rid, @weather, @date, @name, @time, @did)
alter table Participates check constraint all;
enable trigger all on Race;
set identity_insert Race off
GO
/****** Object:  StoredProcedure [dbo].[Recover_Star_Driver]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[Recover_Star_Driver] (
	@uid as int,
	@did as int
)
as
alter table Star_Driver nocheck constraint all
insert into Star_Driver(UID, DID)
values(@uid, @did)
alter table Star_Driver check constraint all
GO
/****** Object:  StoredProcedure [dbo].[Recover_Star_Tean]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[Recover_Star_Tean] (
	@uid as int,
	@tid as int
)
as
alter table Star_Team nocheck constraint all
insert into Star_Team(UID, TID)
values(@uid, @tid)
alter table Star_Team check constraint all
GO
/****** Object:  StoredProcedure [dbo].[Recover_Stats_by_Year]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[Recover_Stats_by_Year] (
	@year as int,
	@did as int,
	@point as int
)
as
alter table Stats_by_Year nocheck constraint all
insert into Stats_by_year(Year, DID, Point)
values(@year, @did, @point)
alter table Stats_by_Year check constraint all
GO
/****** Object:  StoredProcedure [dbo].[Recover_Team]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[Recover_Team] (
	@tid as int,
	@name as varchar(20),
	@engine as varchar(20),
	@model as varchar(20)
)
as
set identity_insert Team on
alter table Team nocheck constraint all
insert into Team(TID, Team_name, Engine_manf, Model_Num)
values(@tid, @name, @engine, @model)
alter table Team check constraint all
set identity_insert Team off
GO
/****** Object:  StoredProcedure [dbo].[Recover_User]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[Recover_User] (
	@uid as int,
	@user as varchar(20),
	@email as varchar(50),
	@hash as varchar(50),
	@salt as varchar(50),
	@access as int
)
as
set identity_insert [User] on
alter table [User] nocheck constraint all
insert into [User](UID, username, EMail, PasswordHash, PasswordSalt, Accessbility)
values(@uid, @user, @email, @hash, @salt, @access)
alter table [User] check constraint all
set identity_insert [User] off
GO
/****** Object:  StoredProcedure [dbo].[Recover_Works_For]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[Recover_Works_For] (
	@did as int,
	@tid as int,
	@year as int
)
as
alter table Works_For nocheck constraint all;
insert into Works_For(DID, TID, Year)
values(@did, @tid, @year);
alter table Works_For check constraint all;
GO
/****** Object:  StoredProcedure [dbo].[Register_New_User]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[Register_New_User] (
	@Username_0 varchar(20),
	@EMail_1 varchar(50),
	@PasswordSalt_2 varchar(50),
	@PasswordHash_3 varchar(50)
	)
as
if(@Username_0 = null)
	begin
		print 'error: Invalid Username Detected'
		return 10
	end
if (@Username_0 in (select username from [User]) or len(@Username_0) > 50)
begin
	print N'duplicate username input'
	return 10
end
if(@EMail_1 = null or len(@EMail_1) > 50 or not (@EMail_1 like '%@%'))
	begin
		print 'error: Invalid E-Mail Detected'
		return 10
	end
if(@PasswordHash_3 is null)
	begin 
		print 'error: Invalid Password Detected'
		return 10
	end
if((select EMail from [User] where EMail = @EMail_1) is not null)
	begin 
		print 'error: The E-Mail has been registered'
		return 10
	end

insert into [User]
	([Username], [PasswordSalt], [PasswordHash], [EMail])
	values (@Username_0, @PasswordSalt_2, @PasswordHash_3, @EMail_1)
	print 'Registered Successfully'
	return 0
GO
/****** Object:  StoredProcedure [dbo].[SelectRaceName]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[SelectRaceName]
as
select distinct Race_Name
from Race
GO
/****** Object:  StoredProcedure [dbo].[SelectTeamNames]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[SelectTeamNames]
as
select distinct Team_name
from Team
GO
/****** Object:  StoredProcedure [dbo].[Team_Driver_View]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[Team_Driver_View] 
as
exec ('
create view [Team_Driver]
as
select t.Team_name, d1.Name as ''Driver 1'', d2.Name as ''Driver 2''
from Team t join Works_For w1 on t.TID = w1.TID
			join driver d1 on w1.DID = d1.DID
			join Works_For w2 on t.TID = w2.TID
			join Driver d2 on w2.DID = d2.DID and d2.DID > d1.DID ')
GO
/****** Object:  StoredProcedure [dbo].[UpdateDriver]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[UpdateDriver] (
	@name varchar(20) = null,
	@dob date = null
)
as
if((select DID from Driver where [Name]=@name)is not null)
begin
if(@name=null or @name='')
begin 
	print N'Please entry Name'
	return 3
end
if(@dob= '')
begin
print N'Please entry Date of Birth'
	return 2
end
	declare @age as int,
			@year as int,
			@month as int,
			@day as int
	set @year = YEAR(@dob)
	set @age = YEAR(GETDATE()) - @year
	set @month = MONTH(@dob)
	set @day = DAY(@dob)
	if @month > MONTH(GETDATE())
	begin
		set @age = @age - 1
	end
	else if @day > DAY(GETDATE())
	begin
		set @age = @age - 1
	end
	if @age < 18 or @age > 50
	begin
		print N'The age range is invalid'
		return 4
	end
else
begin
Update Driver
set DOB=  @dob
where [Name]= @name
end
end
else
begin 
return 1
print N' Please Entry valid Name'
end
GO
/****** Object:  StoredProcedure [dbo].[UpdateParticipates]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[UpdateParticipates] (
	@name varchar(20),
	@year int,
	@racename varchar(40),
	@rank int
)
as
begin
if ((select count(*) from Race where Race_Name = @racename and YEAR(Date) = @year) > 0
	and (select COUNT(*) from Driver where Name = @name) > 0 and @rank is not null)
begin
	declare @rid as int,
			@did as int
	set @did = (select DID from Driver where Name = @name)
	set @rid = (select RID from Race where YEAR(Date) = @year and Race_Name = @racename)
	if ((select COUNT(*) from Participates where DID = @did and RID = @rid) > 0)
	begin
		update Participates
		set Rank = @rank
		where DID = @did and RID = @rid
	end
	else 
	begin
		print N'Invalid input'
		return 10
	end
end
else
begin
	print N'Invalid input'
	return 10
end
end
GO
/****** Object:  StoredProcedure [dbo].[UpdateRace]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[UpdateRace] (
	@weather varchar(20),
	@date date,
	@racename varchar(40),
	@laptime time,
	@drivername varchar(20)
)
as
begin
	if (@date is null)
	begin
		print N'Date cannot be null'
		return 10
	end
	else
	begin
		declare @rid as int
		set @rid = (select RID from Race where Date = @date)
		if (@weather is not null)
		begin
			update Race
			set Weather = @weather
			where RID = @rid
		end
		if (@racename is not null)
		begin
			update Race
			set Race_Name = @racename
			where RID = @rid
		end
		if (@laptime is not null)
		begin
			update Race 
			set Lap_Time = @laptime
			where RID = @rid
		end
		if (@drivername is not null and @drivername in (select Name from Driver))
		begin
			update Race
			set DID = (select DID from Driver where Name = @drivername)
			where RID = @rid
		end
	end
end
GO
/****** Object:  StoredProcedure [dbo].[UpdateTeam]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[UpdateTeam] (
	@name varchar(20),
	@manf varchar(20)=null,
	@num varchar(20)=null
)
as
if (@name is null or @name = '') 
begin
print N'Team name cannot be null or empty'
return 1
end

if ( (select Team_name from Team where Team_name = @name) is not null )
begin

if((@manf is null or @manf = '') and (@num is not null or @num!= ''))
begin
Update Team
set Model_Num = @num
where Team_name= @name
end

if((@manf is not null or @manf !='') and (@num is null or @num= '' ))
begin
Update Team
set Engine_manf = @manf
where Team_name= @name
end

if((@manf is null or @manf = '') and (@num is  null or @num= ''))
begin
Print N'No entry detected'
return 3
end
if ((@manf is not null or @manf != '') and (@num is not null or @num!= ''))
begin 
Update Team
set Engine_manf = @manf, Model_Num = @num
where Team_name= @name
end

end

else
begin
print N' Not valid Team Name'
return 4
end
GO
/****** Object:  StoredProcedure [dbo].[UpdateWorksFor]    Script Date: 2/21/2020 2:59:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[UpdateWorksFor] (
	@Dname varchar(50),
	@Tname varchar(50),
	@Year int
)
as

declare @Tid as int,
		@Did as int
set @Tid = (select TID from Team where Team_name = @Tname)
set @Did = (select DID from Driver where Name = @Dname)

if (@Dname is null or @Tname is null or @Year is null)
begin
print N'Invalid input'
return 1
end

if(@Dname=''or (select count([Name])from Driver where @Dname=[Name])=0)
begin
Return 2
Print N' Please Entry valid Driver Name'
end
if(@Tname='' or ((select count(*) from Team where Team_name = @Tname)=0))
begin
Return 3
Print N' Please Entry valid Team Name'
end

if(@Year='' or @Year<1950 or(@Year>YEAR(GETDATE())))
begin
Return 4
Print N' Please Entry valid  Year'
end

if((Select count(*) from Works_For  where DID = @Did and @Year=[YEAR]and @Tid=TID)>0)
begin
	
	return 5
	print N' same record already exist'
end
if((Select count(*) from Works_For  where DID = @Did and @Year=[YEAR])<1)
begin
	
	return 6
	print N' No record found'
end
	Update Works_For
	set DID = @Did, Year = @Year, TID = @Tid
	where DID = @Did and Year = @Year
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Driver"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 208
            End
            DisplayFlags = 280
            TopColumn = 1
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1176
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1356
         SortOrder = 1416
         GroupBy = 1350
         Filter = 1356
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'All_Driver_View'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'All_Driver_View'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'Driver_Stats_View'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'Driver_Stats_View'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "User"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 208
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Star_Drive"
            Begin Extent = 
               Top = 6
               Left = 246
               Bottom = 102
               Right = 416
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Driver"
            Begin Extent = 
               Top = 6
               Left = 454
               Bottom = 234
               Right = 724
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'User_Stored_Drivers'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'User_Stored_Drivers'
GO
USE [master]
GO
ALTER DATABASE [Formula1DatabaseFinalVer] SET  READ_WRITE 
GO
