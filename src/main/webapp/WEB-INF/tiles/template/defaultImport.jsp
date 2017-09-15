
<div class="container">
	<form action="${pageContext.request.contextPath}/importfile" method="post" encType="multipart/form-data">
		<div class="form-group">
			<input type="file" id="file" name="file">
			<button type="submit" class="btn btn-danger" >Submit</button>
			<label class="help-block">xls or xlsx file only</label>
		</div>

		
	</form>
</div>