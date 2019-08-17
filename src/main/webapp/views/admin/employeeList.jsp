<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
   	 $('body').on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
      });
 
	</script>
</head>
<body>

<input type="hidden" value="${buildingId}" id="buildingId" name="buildingId"/>
	<div class="modal-header">
        <h3 class="modal-title" id="exampleModalLabel">Giao tòa nhà cho nhân viên</h3>
       
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	         <span aria-hidden="true">&times;</span>
	        </button>
		     </div>
			     <div class="modal-body">
			        <table class="table table-condensed">
						<thead>
							<tr>
							   <th>Chọn nhân viênn</th>
							   <th>Tên nhân viên</th>	   			       
							</tr>
						</thead>		       				
		        		<c:forEach var="item" items="${users.listResult}" >
		        			<%boolean checked = false; %>				
							    <tbody>			    
							      <tr> 
							      	<td>
										<c:forEach var="building" items="${item.buildings}" >
													
													<c:if test="${building.id == buildingId}">
														<input type="checkbox" value="${item.id}" id="checkbox_${item.id}" checked>
														<%
														checked = true;
														%>
													</c:if>																	
										</c:forEach>		
										<%pageContext.setAttribute("checked", checked); %>
											<c:if test="${checked != true}">
												<input type="checkbox" value="${item.id}" id="checkbox_${item.id}">		
											</c:if>
																
												
									</td>
							      	<td>${item.fullName}</td>		 
							      </tr>
		    
							    </tbody>		   
		        		</c:forEach>
		        		
		        		
        			</table>
			     </div>
		     <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
        <button type="button" class="btn btn-primary" id=handOverBuilding>Lưu</button>
</div>
<script type="text/javascript">
$('#handOverBuilding').click(function name() {
	
	var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
		return $(this).val();			
	}).get();
	var data = {};
	data['ids'] = dataArray;	
	var buildingId = $('#buildingId').val();
	
	data['id'] = buildingId;
	handOverBuilding(data, buildingId);	
})
function handOverBuilding(data, id) {
$.ajax({
	//url: '${builddingAPI}',
	url : 'http://localhost:8087/api/building/handover',

	data: JSON.stringify(data),
	type: 'POST',	
	contentType: 'application/json',

	success: function(data) {
		window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=handOver_success";
	},		
	error: function() {
		window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=3&sortName=name&sortBy=ASC&message=errorsystem";
	}
});

}</script>
</body>
</html>