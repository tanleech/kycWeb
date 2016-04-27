<%-- 
    Document   : header
    Created on : Apr 14, 2016, 10:35:22 AM
    Author     : ftbs
--%>

<div class="container-fluid">
    <h1><a href="#menu-toggle"  id="menu-toggle"><img src="image/ida.png"/></a></h1>
</div>
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>
    