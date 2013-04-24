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
					<label>ID:</label>
					<input id='title' class="field" type="text" size="15"></input>
			</div>
			<div>
					<label>Titel:</label>
					<input id='title' class="field" type="text" size="15"></input>
			</div>
			<div>
					<label>Start:</label>
					<input class="datepicker" type="text" id="from" size="15"></input>
			</div>
			<div>
					<label>Slut:</label>
					<input class="datepicker" type="text" id="to" size="15"></input>
			</div>
			<label><input type="checkbox" id="allDay" />Heldag</label>
		</form>
	</div>
	
</body>
</html>