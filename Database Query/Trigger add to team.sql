USE [Formula1Database]
GO

/****** Object:  Trigger [dbo].[add_Team]    Script Date: 2/17/2020 10:19:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE trigger [dbo].[add_Team]
on [dbo].[Participates]
for insert
as
declare @Rid as int,
		@Did as int,
		@Tid as int,
		@year as int
set @Rid = (select RID from inserted)
set @Did = (select DID from inserted)
set @year = (select YEAR([Date]) from Race where RID = @Rid)
set @Tid = (select TID from Works_For where DID = @Did and Year = @year)
if ((select count(*) from Engage_IN where TID = @Tid and RID = @Rid) < 1 and @Tid is not null)
begin
	insert into Engage_IN
	values(@Rid, @Tid)
end
GO

ALTER TABLE [dbo].[Participates] ENABLE TRIGGER [add_Team]
GO

