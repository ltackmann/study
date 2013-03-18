<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>The Guest Book</title>
<s:head theme="ajax" />
</head>
<body>
<h3>The Guest Book</h3>
<p>To sign the Guest Book, <a
	href="<s:url action="GuestBook_input" namespace="/guestbook" />">click
here</a>.</p>
<table>
	<s:iterator value="messages">
		<tr>
			<td><s:property value="guest" /> wrote when he/she visited on <s:property
				value="when" /></td>
		</tr>
		<tr>
			<td><s:property value="message" /></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>