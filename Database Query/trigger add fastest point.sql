USE [Formula1Database]
GO

/****** Object:  Trigger [dbo].[add_point_to_fastest]    Script Date: 2/17/2020 10:20:13 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE trigger [dbo].[add_point_to_fastest]
on [dbo].[Race]
after insert
as
begin
	declare @did as int,
			@year as char(4)
	set @did = (select DID from inserted)
	set @year = (select YEAR([Date]) from inserted)
	if ((select count(*) from Stats_by_year where DID = @did and Year = @year) > 0)
	begin
		declare @old_point as int,
				@new_point as int
		set @old_point = (select Point from Stats_by_year where DID = @did and Year = @year)
		set @new_point = @old_point + 1
		update Stats_by_year 
		set Point = @new_point
		where DID = @did and Year = @year
	end
	else
	begin
		insert into Stats_by_year(DID, Year, Point)
		values(@did, @year, 1)
	end
end
GO

ALTER TABLE [dbo].[Race] ENABLE TRIGGER [add_point_to_fastest]
GO

