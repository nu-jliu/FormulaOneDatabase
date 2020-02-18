USE [Formula1Database]
GO

/****** Object:  Trigger [dbo].[update_fastest_point]    Script Date: 2/17/2020 10:22:07 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create trigger [dbo].[update_fastest_point]
on [dbo].[Race]
after update
as
begin
	declare @olddriver as int,
			@newdriver as int,
			@rid as int,
			@year as int
	set @olddriver = (select DID from deleted)
	set @newdriver = (select DID from inserted)
	set @rid = (select RID from deleted)
	set @year = (select YEAR(Date) from Race where RID = @rid)
	if (@olddriver <> @newdriver)
	begin
		update Stats_by_year
		set Point = Point - 1
		where DID = @olddriver and Year = @year
		update Stats_by_year
		set Point = Point + 1
		where DID = @newdriver and Year = @year
	end
end
GO

ALTER TABLE [dbo].[Race] ENABLE TRIGGER [update_fastest_point]
GO

