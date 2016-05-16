<%-- 
    Document   : customer
    Created on : Apr 14, 2016, 3:05:20 PM
    Author     : ftbs
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="sg.gov.ida.kyc.data.dto.CustomerDto" %>
<%@ page import="sg.gov.ida.kyc.data.dto.BankDto" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="pre.jsp"%>
</head>
    <body>
    <div id="wrapper">
        <%@include file ="sidemenu.jsp" %>
        <%@include file ="header.jsp" %>
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <!-- form -->
                <form action="customerEdit" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1">Identifier:</label>
                        <div class="col-sm-3">
                            <input type="text" name="uid" class="form-control" value="${requestScope.cust.uid}"/>   
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-1">Name:</label>
                        <div class="col-sm-3">
                           <input type="text" name="name" class="form-control" value="${requestScope.cust.name}"/>   
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-1">Address:</label>
                        <div class="col-sm-3">
                           <input type="text" name="address" class="form-control" value="${requestScope.cust.address}"/>   
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-1">Phone:</label>
                        <div class="col-sm-3">
                            <input type="text" name="phone" class="form-control" value="${requestScope.cust.phone}"/>   
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-sm-1">Pep Rating:</label>
                        <div class="col-sm-3">
                            
                        <select class="form-control" id="pep" name="pep">
                            <c:choose>
                                <c:when test="${!empty requestScope.pepList}">
                                    <c:forEach var="entry" items="${requestScope.pepList}">
                                        <option value="${entry.status_cd}"
                                                ${requestScope.cust.pepStatus.status_cd == entry.status_cd ? 'selected' : ''}>
                                            ${entry.description}
                                        </option>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </select>    
                            
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-sm-1">Pep Reason:</label>
                        <div class="col-sm-3">
                           <input type="text" class="form-control" name="pepReason" value="${requestScope.cust.pepReason}"/>   
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="col-sm-1">
                            <button class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                    
                </form>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>   
    </body>
</html>
