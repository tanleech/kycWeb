<%-- 
    Document   : customer
    Created on : Apr 14, 2016, 3:05:20 PM
    Author     : ftbs
--%>

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
                           <input type="text" class="form-control"/>   
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-1">Name:</label>
                        <div class="col-sm-3">
                           <input type="text" class="form-control"/>   
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-1">Address:</label>
                        <div class="col-sm-3">
                           <input type="text" class="form-control"/>   
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-1">Phone:</label>
                        <div class="col-sm-3">
                           <input type="text" class="form-control"/>   
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-sm-1">Risk Rating:</label>
                        <div class="col-sm-3">
                         <select class="form-control" name="rating">
                             <option value="R">Red</option>
                             <option value="A">Amber</option>
                             <option value="G">Green</option>
                         </select>                        
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

    </div>    </body>
</html>
