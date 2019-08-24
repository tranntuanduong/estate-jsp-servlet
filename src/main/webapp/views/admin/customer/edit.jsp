<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var = "customerURL" value = "/admin-customer"/>
<c:url var="builddingAPI" value="/api-admin-building"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dach sách sản phẩm</title>
</head>
<body>

	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="admin-home">Trang chủ</a></li>
					<li>Dach sách sản phẩm</li>
					<li>Cập nhật khách hàng</li>
				</ul>
			</div>
			<div class="page-content">
			<div class="page-header">
				<h1>Thông tin khách hàng</h1>
			</div>
		</div>
		
		<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<form class="form-horizontal" role="form" id="formEdit">
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tên đầy đủ</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" name="name" value="${customer.name}"/>
									</div>
								</div>
							</div>
							
							
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Số điện thại</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="number" class="form-control input-sm"  name="phoneNumber" value="${customer.phoneNumber}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Email</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="email" value="${customer.email}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tên công ty</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="company" value="${customer.company}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Nhu cầu</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="need" value="${customer.need}"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Ghi chú</b></label>
								</div>
								<div class="col-sm-2">
									<div class="fg-line">
										<textarea rows="3" cols="88" name="node">${customer.node}</textarea>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label><b>Nhân viên phụ trách</b></label>
								</div>
								<div class="col-sm-4">
									
									<select class="form-control" name="userId">
											<option value=""   selected>--Chọn nhân viên--</option>
											<c:forEach var="item" items="${model.staffList}">
												<option value="${item.id}" ${(item.id==customer.userId)?'selected':''}>${item.fullName}</option>
											</c:forEach>
									</select>
								</div>
							</div>
							<input type="hidden" name="id" value="${customer.id}" id="customerId"/>
							
						</form>
						<div class="form-group">
							<div class = "col-sm-1 col-sm-offset-3">
								<button class = "btn btn-success" id="bntUpdateCustomer">Cập nhật khách hàng</button>
							</div>
						</div>		
						
					</div>
				</div>
			</div>
			
		<!-- CSKH -->
	

		<div class="page-content">
			<div class="page-header">
				<h1>Quá trình CSKH 		
					<button class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
							data-toggle="tooltip"
							title='Thêm quá trình'
							id = "btnCustomerCare">
						<span><i class="fa fa-plus-circle sbigger-110 purple"></i></span>
					</button>
				</h1>
				
			</div>
			<div class="row">
				<div class="col-xs-12">
					<table class="table table-bordered">
						<thead>
							 <tr>
							 	<td class="col-sm-1">id</td>
								<td class="col-sm-3">Ngày tạo</td>
								<td>Ghi chú</td>
							 </tr>
						</thead>
						<tbody>
							
							<c:forEach var="item" items="${customer.transactions}">
								
								<c:if test="${item.role == 'customercare'}">
									<tr>
										<th>${item.id}</th>
										<th>${item.createdDate}</th>
										<th>${item.customerCare}</th>
									</tr>
								</c:if>
								
							</c:forEach>
							
							<tr>
								<th></th>
								<th></th>
								<th>
									<form id="customerCare">
										<input type="text" style="width:100%" name="customerCare"/>
										<input type="hidden"  value="customercare" name="role"/>
									</form>	
								</th>
							</tr>
						</tbody>
								
					</table>
					
				</div>
			</div>
		</div>
		
		<!-- Dẫn đi xem -->
			
		<div class="page-content">
			<div class="page-header">
				<h1>Dẫn đi xem
					<button class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
							data-toggle="tooltip"
							title='Dẫn đi xem'
							id = "btnGuide">
						<span><i class="fa fa-plus-circle sbigger-110 purple"></i></span>
					</button>
				</h1>
				
			</div>
			<div class="row">
				<div class="col-xs-12">
					<table class="table table-bordered">
						<thead>
							 <tr>
							 	<td class="col-sm-1">id</td>
								<td class="col-sm-3">Ngày tạo</td>
								<td>Ghi chú</td>
							 </tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${customer.transactions}">
								<c:if test="${item.role == 'guide'}">
									<tr>
										<th>${item.id}</th>
										<th>${item.createdDate}</th>
										<th>${item.guide}</th>
									</tr>
								</c:if>
							</c:forEach>
							<tr>
								<th></th>
								<th></th>
								<th>
									<form id="guide">
										<input type="text" style="width:100%" name = "guide"/>
										<input type="hidden"  value="guide" name="role"/>
									</form>	
								</th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		
		
		</div>
	</div>
	<input type="hidden" name="id" value="customercare" id="role_customercare"/>
<script type="text/javascript">
$('#bntUpdateCustomer').click(function name() {
	
	var customerId = $('#customerId').val();
	var formData = $('#formEdit').serializeArray();
	var data = {};
	$.each(formData, function (index, v ) {		
		data[""+v.name+""] = v.value;		
	});
	data['customerId'] = customerId;
	updateCustomer(data, customerId);	
})

function updateCustomer(data, id) {
	$.ajax({
		url : 'http://localhost:8087/api/customer',
		data: JSON.stringify(data),
		type: 'PUT',	
		contentType: 'application/json',
		success: function(data) {
			window.location.href = "${customerURL}?action=EDIT&customerId="+id+"&role=staff&message=success";
		},		
		error: function() {
			window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=errorsystem";
		}
	});
}

//insert customer care
$('#btnCustomerCare').click(function name() {
	
	var customerId = $('#customerId').val();
	var formData = $('#customerCare').serializeArray();
	var data = {};
	$.each(formData, function (index, v ) {		
		data[""+v.name+""] = v.value;		
	});
	data['customerId'] = customerId;
	
	insertCustomerCare(data, customerId);	
})

function insertCustomerCare(data, id) {
	$.ajax({
		url : 'http://localhost:8087/api/customer/transaction',		
		data: JSON.stringify(data),
		type: 'POST',	
		contentType: 'application/json',
		success: function(data) {
			window.location.href = "${customerURL}?action=EDIT&customerId="+id+"&role=staff&message=success";
		},		
		error: function() {
			window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=errorsystem";
		}
	});
}

//insert guide
$('#btnGuide').click(function name() {
	
	var customerId = $('#customerId').val();
	var formData = $('#guide').serializeArray();
	var data = {};
	$.each(formData, function (index, v ) {		
		data[""+v.name+""] = v.value;		
	});
	data['customerId'] = customerId;
	
	insertCustomerCare(data, customerId);	
})

function insertCustomerCare(data, id) {
	$.ajax({
		url : 'http://localhost:8087/api/customer/transaction',		
		data: JSON.stringify(data),
		type: 'POST',	
		contentType: 'application/json',
		success: function(data) {
			window.location.href = "${customerURL}?action=EDIT&customerId="+id+"&role=staff&message=success";
		},		
		error: function() {
			window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=errorsystem";
		}
	});
}
</script>


</body>
</html>














