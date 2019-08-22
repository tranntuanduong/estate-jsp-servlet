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
										<input type="text" class="form-control input-sm" name="#" value=""/>
									</div>
								</div>
							</div>
							
							
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Số điện thại</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="#" value=""/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Email</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="#" value=""/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tên công ty</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="#" value=""/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Nhu cầu</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<input type="text" class="form-control input-sm"  name="#" value=""/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Ghi chú</b></label>
								</div>
								<div class="col-sm-7">
									<div class="fg-line">
										<textarea rows="4" cols="90">
											
										</textarea>
									</div>
								</div>
							</div>
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
					<a  class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
							data-toggle="tooltip"
							title='Thêm quá trình'		
						href='<c:url value="/admin-building?action=EDIT"/>'>
						<span><i class="fa fa-plus-circle sbigger-110 purple"></i></span>
					</a>
				</h1>
				
			</div>
			<div class="row">
				<div class="col-xs-12">
					<table class="table table-bordered">
						<thead>
							 <tr>
							 	
								 <td class="col-sm-3">Ngày tạo</td>
								 <td>Ghi chú</td>
							 </tr>
						</thead>
						<tbody>
							<tr>
								<th>dsd</th>
								<th>dsd</th>
							</tr>
							<tr>
								<th>dsd</th>
								<th>dsd</th>
							</tr>
							<tr>
								<th>dsd</th>
								<th>dsd</th>
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
					<a  class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
							data-toggle="tooltip"
							title='Thêm quá trình'		
						href='<c:url value="/admin-building?action=EDIT"/>'>
						<span><i class="fa fa-plus-circle sbigger-110 purple"></i></span>
					</a>
				</h1>
				
			</div>
			<div class="row">
				<div class="col-xs-12">
					<table class="table table-bordered">
						<thead>
							 <tr>
							 	
								 <td class="col-sm-3">Ngày tạo</td>
								 <td>Ghi chú</td>
							 </tr>
						</thead>
						<tbody>
							<tr>
								<th>dsd</th>
								<th>dsd</th>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		
		
		</div>
	</div>
	


</body>
</html>














