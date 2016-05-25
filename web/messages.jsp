<%-- 
    Document   : menu.jsp
    Created on : Apr 13, 2016, 1:48:42 PM
    Author     : ftbs
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="sg.gov.ida.kyc.data.dto.RequestDto" %>
<%@ page import="sg.gov.ida.kyc.data.dto.BankDto" %>
<%@ page import="sg.gov.ida.kyc.data.dto.CustomerDto" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

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
                <!-- table -->
                <table id="custTab" class="table">
                    <thead>
                        <tr>
                            <th>Identifier</th>
                            <th>Name</th>
                            <th>Ownership</th>
                            <th>Request From</th>
                            <th>Approve/Disapprove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.reqList}" var="entry">
                            <tr>
                                <td>${entry.customer.uid}</td>
                                <td>${entry.customer.name}</td>
                                <td>${entry.owner.name}</td>
                                <td>${entry.requester.name}</td>
                                <td><a href="customerEdit?action=A&uid=${entry.customer.uid}&reqby=${entry.requester.name}&owner=${entry.owner.name}&reqId=${entry.reqId}">Approve</a> / <a href="customerEdit?action=D">Disapprove</a></td>
                            </tr>                            
                        </c:forEach>
                        <!--
                        <tr>
                            <td><a href="customerView">S95XXXXXJ</a></td>
                            <td>Chin KunSong, Marvin</td>
                            <td>DBS Bank</td>
                            <td>NA</td>
                        </tr>
                        <tr>
                            <td><a href="customerView">S81XXXXXH</a></td>
                            <td>Da Renwu, Eric</td>
                            <td>Standard Chartered Bank</td>
                            <td>NO</td>
                        </tr>                        
                        <tr>
                            <td><a href="customerView">S05XXXXXH</a></td>
                            <td>Ip Man</td>
                            <td>HSBC Bank</td>
                            <td>NO</td>
                        </tr>                        
                        <tr>
                            <td><a href="customerView">S68XXXXXH</a></td>
                            <td>Jacky Cheung</td>
                            <td>HSBC Bank</td>
                            <td>NO</td>
                        </tr>                        
                        <tr>
                            <td><a href="customerView">S67XXXXXH</a></td>
                            <td>Koji Tamaki</td>
                            <td>Mitsubishi Bank</td>
                            <td>NO</td>
                        </tr>                        
                        -->
                    </tbody>
                        
                    
                </table> 
                
                <!-- end table -->
                
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script>
         $(document).ready(function () {
             $('#custTab').DataTable(
                     
                     {
                        "paging": false,
                        "ordering": true,
                        "info": true,
                        "searching" : true,
                        "autoWidth": true,
                        "pageLength": 15
                      }
                      );    
             
          }
          );
     </script>

</body>

</html>
