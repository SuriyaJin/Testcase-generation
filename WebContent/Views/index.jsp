<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.ebox.WebTest.TestCase"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>
<style>
input, textarea {
	width: 20%;
	border: 0;
	padding: 10px 5px;
	background: white no-repeat;
	background-image: linear-gradient(to bottom, #1abc9c, #1abc9c),
		linear-gradient(to bottom, silver, silver);
	background-size: 0 2px, 100% 1px;
	background-position: 50% 100%, 50% 100%;
	transition: background-size 0.3s cubic-bezier(0.64, 0.09, 0.08, 1);
}

input:focus, textarea:focus {
	background-size: 100% 2px, 100% 1px;
	outline: none;
}
.center {
    margin-top:50px;   
}

.modal-header {
	padding-bottom: 5px;
}

.modal-footer {
    	padding: 0;
	}
    
.modal-footer .btn-group button {
	height:40px;
	border-top-left-radius : 0;
	border-top-right-radius : 0;
	border: none;
	border-right: 1px solid #ddd;
}
	
.modal-footer .btn-group:last-child > button {
	border-right: 0;
}
</style>
</head>
<body>
<div class="modal fade" id="squarespaceModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
			<h3 class="modal-title" id="lineModalLabel">My Modal</h3>
		</div>
		<div class="modal-body">
			<form>
              <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
              </div>
              <button type="submit" class="btn btn-outline btn-success">Update</button>
            </form>

		</div>
		<div class="modal-footer">
		</div>
	</div>
  </div>
</div>	
	<div style="margin-top: 50px">
		<center>
			<div class="container">
				<div class="row"></div>
				<div class="row-fluid">
					<select name="By-options" id="By-options" class="selectpicker"
						data-show-subtext="true" data-live-search="true">
						<option data-subtext="  Perform with element id" value="Id">Id</option>
						<option data-subtext="  Perform with element name" value="Name">Name</option>
						<option data-subtext="  Perform with css property"
							value="Css Selector">Css Selector</option>
						<option data-subtext="  Perform with element path" value="X-Path">X-Path</option>
						<option data-subtext="  Perform with tag name" value="Tag">Tag</option>
						<option data-subtext="Perform with class name" value="Class Name">Class Name</option>
					</select> <input placeholder="Selector value" id="selectorValues"
						type="text"> <select onchange="visible(this)"
						style="width: 10px" name="operation" class="selectpicker"
						data-show-subtext="true" data-live-search="true" id="operation">
						<option data-subtext="  Click Element" value="Click">Click</option>
						<option data-subtext="  Check Element present" value="CheckTrue">Check Element Present</option>
						<option data-subtext="  Check Element Absent" value="CheckFalse">Check Element Absent</option>
						<option data-subtext="  To type the text" value="Send Keys">Send Keys</option>
						<option data-subtext="  Check the select options"
							value="Select Options">Select Options</option>
						<option data-subtext="  Check the content equality"
							value="Verify Text">Verify Text</option>
						<option data-subtext="  Check the content contains"
							value="Contains Text">Contains Text</option>
					</select> <input id="text" style="visibility: hidden" placeholder="Text"
						type="text">
					<button onclick="add()" class="btn btn-info">Add</button>
					<button id="show" onclick="show()" class="btn btn-warning">Show
						Added</button>
				</div>
				<div id="Added cases" style="margin-top: 50px;"></div>
			</div>
		</center>
	</div>
</body>
<script>
var modal = document.getElementById('squarespaceModal');

var span = document.getElementsByClassName("close")[0];

span.onclick = function() {
 modal.style.display = "none";
}

window.onclick = function(event) {
 if (event.target == modal) {
     modal.style.display = "none";
     
 }
}

		function edit(element)
		{
				var modal = document.getElementById('squarespaceModal');
				var id = element.id;
				var http = new XMLHttpRequest();
				http.open("GET","edit.do?id="+id,true);
				http.onreadystatechange = function() {
	            if(http.readyState == 4 && http.status == 200) {
	            	//document.getElementById('editTestCase').innerHTML = http.responseText;
					modal.style.display = "block";
	        	}
	        }
	        	http.send(null);	
		}

        function visible(element) {
            var name = element.value;
            if(name ==="Verify Text" || name === "Send Keys" || name === "Select Options" || name === "Contains Text")  {
                document.getElementById("text").style.visibility = "visible";
                document.getElementById("text").placeholder = name;
            }
            else{
                document.getElementById("text").style.visibility = "hidden";
            }
        }
        function add() {
        	var http = new XMLHttpRequest();
        	var url = "add.do";
        	var selector = document.getElementById("By-options").value;
        	var selectorValue = document.getElementById("selectorValues").value;
        	var action = document.getElementById("operation").value;
        	var params = "selector="+selector+"&selectorValue="+selectorValue+"&action="+action;
        	if(document.getElementById("text").style.visibility === "visible"){
        		params += "&text="+document.getElementById("text").value;
        		url = "addWithText.do";
        	}
        	http.open("POST", url, true);
        	http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            http.onreadystatechange = function() {
            if(http.readyState == 4 && http.status == 200) {
        	        document.getElementById("Added cases").innerHTML = http.responseText;
        	    }
        	}
        	http.send(params); 
        }
        
        function show() {
        	var http = new XMLHttpRequest();
        	http.open("GET", "show.do", true);
            http.onreadystatechange = function() {
            if(http.readyState == 4 && http.status == 200) {
        	        document.getElementById("Added cases").innerHTML = http.responseText;
        	    }
        	}
        	http.send(null);
        }
        function deleteCase(){
        	alert("In delete");
        }
    </script>
</html>
