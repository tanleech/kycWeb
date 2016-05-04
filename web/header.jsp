<%-- 
    Document   : header
    Created on : Apr 14, 2016, 10:35:22 AM
    Author     : ftbs
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="sg.gov.ida.kyc.data.EmployeeDto" %>

     <header class="main-header">
        <div class="logo">
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg">             
            <a href="#menu-toggle" id="menu-toggle">
                 <img src="image/ida.png"/>
            </a> 
          </span>
            <div class="navbar-collapse collapse navbar-right">
                    <ul class="nav navbar-nav">
                        <li class="active">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <span class="hidden-xs">
                                        <b>
                                            <c:if test="${!empty sessionScope.login}">
                                                ${sessionScope.login.name}
                                            </c:if>
                                        </b>
                                    </span>
                                </a>
                                <ul class="dropdown-menu">

                                    <!-- Menu Footer-->
                                    <li class="user-footer">

                                        <div class="pull-right">
                                            <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                                        </div>
                                    </li>
                                </ul>

                        </li>
                    </ul>
                  </div>          
</header>
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>
    