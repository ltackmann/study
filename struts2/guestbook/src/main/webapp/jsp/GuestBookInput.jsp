<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Sign the Guest Book</title>
<s:head theme="ajax" />
</head>
<body>
<h3>Sign the Guest Book</h3>
<s:form action="GuestBook" namespace="/guestbook">
	<s:textfield required="true" key="Your Name" name="guest" />
	<s:textarea required="true" rows="4" cols="36" key="Your Message"
		name="message" />
	<s:submit />
</s:form>
</body>
</html>