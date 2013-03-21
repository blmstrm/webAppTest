<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="head.jsp"%>
<body>
	<div id='calendar'></div>
	<div id='eventDialog' class='dialog ui-helper-hidden'>
		<form>
			<div>
					<label>Title:</label>
					<input id='title' class="field" type="text"></input>
			</div>
			<div>
					<label>From:</label>
					<input id='from' class="field" type="text"></input>
			</div>
			<div>
					<label>To:</label>
					<input id='to' class="field" type="text"></input>
			</div>
			<label><input type="checkbox" id="allDay" />Allday?</label>
		</form>
	</div>
	
</body>
</html>