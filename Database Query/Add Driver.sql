USE [Formula1Database]
GO

/****** Object:  StoredProcedure [dbo].[AddDriver]    Script Date: 2/17/2020 10:18:33 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[AddDriver] (
	@name varchar(20) = null,
	@Dob date = null
)
as
if(@name='')
begin
Return 1
Print N' Please Entry Name'
end
if(@Dob='')
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
	insert into Driver
	values (@name, @dob)
end
GO

