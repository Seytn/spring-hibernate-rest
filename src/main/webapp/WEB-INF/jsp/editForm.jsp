
	<h2>Edit database</h2>

	<form action="${pageContext.request.contextPath}/update" method="post">
		<div class="form-inline">
			<input class="form-control" name="id" placeholder="ID">
		</div>
		<div class="form-inline">
			<input class="form-control" name="active" placeholder="Active">
		</div>
		<div class="form-inline">
			<input class="form-control" name="amount" placeholder="Amount">
		</div>
		<div class="form-inline">
			<input class="form-control" name="amount_period"
				placeholder="Amount period">
		</div>
		<div class="form-inline">
			<input class="form-control" name="amount_type"
				placeholder="Amount type">
		</div>
		<div class="form-inline">
			<input class="form-control" name="authorization_percent"
				placeholder="Authorization Percent">
		</div>
		<div class="form-inline">
			<input class="form-control" name="from_date" placeholder="From date">
		</div>
		<div class="form-inline">
			<input class="form-control" name="order_number"
				placeholder="Order number">
		</div>
		<div class="form-inline">
			<input class="form-control" name="request" placeholder="Request">
		</div>
		<div class="form-inline">
			<input class="form-control" name="to_date" placeholder="To date">
		</div>
				<div class="form-inline">
			<input class="form-control" name="system_id" placeholder="System ID">
		</div>

		<div class="checkbox">
			<label> <input type="checkbox"> Delete?
			</label>
		</div>
		<button type="submit" class="btn btn-danger">Submit</button>
	</form>

