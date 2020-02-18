USE [Formula1Database]
GO

/****** Object:  Trigger [dbo].[check_fast_Driver]    Script Date: 2/17/2020 10:20:35 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE trigger [dbo].[check_fast_Driver]
on [dbo].[Race]
after insert
as
declare @Did as int,
		@Rid as int
set @Did = (select DID from inserted)
set @Rid = (select RID from inserted)
if ((select count(*) from Participates where DID = @Did and RID = @Rid) < 1)
begin
	insert into Participates(RID, DID)
	values(@Rid, @Did)
end
GO

ALTER TABLE [dbo].[Race] ENABLE TRIGGER [check_fast_Driver]
GO

