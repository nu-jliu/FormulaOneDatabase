USE [Formula1Database]
GO

/****** Object:  Trigger [dbo].[add_Stats]    Script Date: 2/17/2020 10:19:12 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE trigger [dbo].[add_Stats]
on [dbo].[Participates]
after insert
as
begin
	declare @did as int,
			@year as int,
			@Rank as int,
			@point_add as int
	set @did = (select DID from inserted)
	set @year = (select year(r.[Date]) from inserted i join Race r on i.RID = r.RID)
	set @Rank = (select Rank from inserted)
	exec @point_add = get_points @rank = @Rank
	if ((select count(*) from Stats_by_year where DID = @did and Year = @year) > 0)
	begin
		declare @old_point as int,
				@new_point as int
		set @old_point = (select Point from Stats_by_year where DID = @did and Year = @year)
		set @new_point = @old_point + @point_add
		update Stats_by_year 
		set Point = @new_point
		where DID = @did and Year = @year
	end
	else
	begin
		insert into Stats_by_year(DID, Year, Point)
		values(@did, @year, @point_add)
	end
end
GO

ALTER TABLE [dbo].[Participates] ENABLE TRIGGER [add_Stats]
GO

