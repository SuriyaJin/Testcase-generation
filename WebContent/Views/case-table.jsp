<%@page import="com.ebox.WebTest.TestCase"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.panel-table .panel-body {
	padding: 0;
}

.panel-table .panel-body .table-bordered {
	border-style: none;
	margin: 0;
}

.panel-table .panel-body .table-bordered>thead>tr>th:first-of-type {
	text-align: center;
	width: 100px;
}

.panel-table .panel-body .table-bordered>thead>tr>th:last-of-type,
	.panel-table .panel-body .table-bordered>tbody>tr>td:last-of-type {
	border-right: 0px;
}

.panel-table .panel-body .table-bordered>thead>tr>th:first-of-type,
	.panel-table .panel-body .table-bordered>tbody>tr>td:first-of-type {
	border-left: 0px;
}

.panel-table .panel-body .table-bordered>tbody>tr:first-of-type>td {
	border-bottom: 0px;
}

.panel-table .panel-body .table-bordered>thead>tr:first-of-type>th {
	border-top: 0px;
}

.panel-table .panel-footer .pagination {
	margin: 0;
}

.panel-table .panel-footer .col {
	line-height: 34px;
	height: 34px;
}

.panel-table .panel-heading .col h3 {
	line-height: 30px;
	height: 30px;
}

.panel-table .panel-body .table-bordered>tbody>tr>td {
	line-height: 34px;
}
</style>
</head>
<body>

	<%
            List<TestCase> testCaseList = (List<TestCase>) request.getAttribute("testCaseList");
        %>
	<%if(testCaseList.size()==0) {%>
	<h2>Atleast add one case</h2>
	<%}else{%>
	<div class="container">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">

				<div class="panel panel-default panel-table">
					<div class="panel-heading">
						<div class="row">
							<div class="col col-xs-6">
								<h3 class="panel-title">Testcase Table</h3>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-striped table-bordered table-list">
							<thead>
								<tr>
									<th><em class="fa fa-cog"></em></th>
									<th class="hidden-xs">Id</th>
									<th class="hidden-xs">Selector</th>
									<th>Selector Value</th>
									<th>Action</th>
									<th>Text</th>
								</tr>
							</thead>
							<tbody>
								<%                                        
                                        for (TestCase t : testCaseList) {
                                    %>
								<tr>
									<td align="center"><a id = "<%=t.getId() %>" onclick="edit(this)" class="btn btn-default"><em
											class="fa fa-pencil"></em></a> <a onclick="deleteCase()" class="btn btn-danger"><em
											class="fa fa-trash"></em></a></td>
									<td><%=t.getId()%></td>
									<td><%=t.getSelector()%></td>
									<td><%=t.getSelectorValue()%></td>
									<td><%=t.getAction()%></td>
									<td>
										<%if(t.getIsText())
                                        			out.print(t.getText());
                                        	  else
                                        	  		out.print("NIL");%>
									</td>
								</tr>
								<%}%>
							</tbody>
						</table>
					</div>
					<!--                        <div class="panel-footer">
                            <div class="row">
                                <div class="col col-xs-4">Page 1 of 5
                                </div>
                                <div class="col col-xs-8">
                                    <ul class="pagination hidden-xs pull-right">
                                        <li><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                    </ul>
                                    <ul class="pagination visible-xs pull-right">
                                        <li><a href="#">«</a></li>
                                        <li><a href="#">»</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>-->
				</div>

			</div>
		</div>
	</div>
	<button class="btn btn-success" style="align: right;">Finish</button>
	<%} %>
</body>
</html>
