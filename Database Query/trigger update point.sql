USE [Formula1Database]
GO

/****** Object:  Trigger [dbo].[update_Point]    Script Date: 2/17/2020 10:19:42 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create trigger [dbo].[update_Point]
on [dbo].[Participates]
after update
as
begin
	declare @did as int,
			@rid as int,
			@oldpoint as int,
			@newpoint as int,
			@record as int,
			@oldrank as int,
			@newrank as int,
			@year as int
	set @did = (select DID from deleted)
	set @rid = (select RID from deleted)
	set @oldrank = (select Rank from deleted)
	set @newrank = (select Rank from inserted)
	set @year = (select YEAR(Date) from Race where RID = @rid)
	exec @oldpoint = get_points @rank = @oldrank
	exec @newpoint = get_points @rank = @newrank
	update Stats_by_year
	set Point = Point - @oldpoint + @newpoint
	where DID = @did and Year = @year
end
GO

ALTER TABLE [dbo].[Participates] ENABLE TRIGGER [update_Point]
GO

