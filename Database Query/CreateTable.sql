USE [Formula1Database]
GO

/****** Object:  StoredProcedure [dbo].[Create_Table]    Script Date: 2/17/2020 1:53:19 PM ******/
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


