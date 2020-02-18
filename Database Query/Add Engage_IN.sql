USE [Formula1Database]
GO

/****** Object:  StoredProcedure [dbo].[AddEngage_IN]    Script Date: 2/17/2020 10:18:51 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[AddEngage_IN] (
	@rid int,
	@tid int
)
as
if ((select count(*) from Engage_IN where RID = @rid and TID = @tid) > 0)
begin
print N'Duplication Entry'
return 1
end
insert into Engage_IN
values (@rid, @tid)
GO

