<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
							chủ</a></li>
					<li><a href="#">Sản phẩm</a></li>
					<li>Thêm sản phẩm</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tên Sản phẩm</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Hình ảnh cho sản phẩm</b></label>						
								</div>
								<div class="col-sm-3">
									<div class="fg-line">
										<!-- <button class="btn btn-info">
											Thêm hình ảnh
											<i class="glyphicon glyphicon-picture"></i>
											
										</button> -->
										<label class="block clearfix">
											<input type="file" name="file" class="btn btn-info">
										</label>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Người quản lí sản phẩm</b></label>
								</div>
								<div class="col-sm-3">
									<div class="fg-line">
										<select class="form-control" id="sel1">
											<option value="" disabled selected>-Chọn người quản lí-</option>
											<option value="#">Nhân viên A</option>
											<option value="#">Nhân viên B</option>
											<option value="#">Nhân viên C</option>
											<option value="#">Nhân viên D</option>
										</select>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Quận</b></label>
								</div>
								<div class="col-sm-3">
									<div class="fg-line">
										<select class="form-control" id="sel1">
											<option value="" disabled selected>-Chọn quận-</option>
											<option value="#">Quận 1</option>
											<option value="#">Quận 2</option>
											<option value="#">Quận 3</option>
											<option value="#">Quận 4</option>
										</select>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Phường</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Đường</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Kết cấu</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Số tầng hầm</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="number" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Diện tích sàn</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="number" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Hướng</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Hạng</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Diện tích thuê</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="number" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Mô tả diện tích</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<textarea class="form-control" id="form-field-8" placeholder="Nhập phần mô tả" 
										style="margin: 0px 15.6563px 0px 0px; height: 85px;  width: 832px;"></textarea>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Giá thuê</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="number" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Mô tả giá</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Phí ô tô</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Phí mô tô</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Phí ngoài giờ</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tiền điện</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Đặt cọc</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Thanh toán</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Thời hạn thuê</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Thời gian trang trí</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Tên quản lí</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>SĐT quản lí</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Phí môi giới</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<input type="text" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<label><b>Loại sản phẩm</b></label>
								</div>
								<div class="col-sm-9">
									<div class="fg-line">
										<label class="checkbox-inline"><input type="checkbox" value="">Option 1</label>
										<label class="checkbox-inline"><input type="checkbox" value="">Option 2</label>
										<label class="checkbox-inline"><input type="checkbox" value="">Option 3</label>
									</div>
								</div>
							</div>		
							<div class="form-group">
								<div class = "col-sm-1 col-sm-offset-3">
									<button class = "btn btn-success" type = "submit">Thêm sản phẩm</button>
								</div>
							</div>				
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.main-content -->
</body>
</html>