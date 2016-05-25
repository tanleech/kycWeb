<%-- 
    Document   : Login
    Created on : Apr 13, 2016, 10:41:11 AM
    Author     : ftbs
--%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

 <div class="container" style="margin-top:40px">
    <div class="row">
     <div class="col-sm-6 col-md-4 col-md-offset-4">
	<div class="panel panel-default">
	    <div class="panel-heading">
		<strong> Sign in to continue</strong>
	    </div>
	    <div class="panel-body">
		<form role="form" action="login" method="POST">
	    	    <fieldset>
			<div class="row">
			    <div class="center-block">
                                <img class="profile-img col-sm-6 col-md-offset-1" src="image/kyc.jpg" alt=""/>
			    </div>
			</div>
                        <br/>
			<div class="row">
			    <div class="col-sm-12 col-md-10  col-md-offset-1 ">
				<div class="form-group">
			            <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-user"></i>
				        </span> 
					<input class="form-control" placeholder="Username" name="loginname" type="text" autofocus>
				    </div>
				</div>
				<div class="form-group">
				    <div class="input-group">
					<span class="input-group-addon">
				  	    <i class="glyphicon glyphicon-lock"></i>
					</span>
					<input class="form-control" placeholder="Password" name="password" type="password" value="">
				    </div>
				</div>
				<div class="form-group">
				    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign in">
				</div>
			    </div>
			</div>
		    </fieldset>
		</form>
	    </div>
            <!--
	    <div class="panel-footer ">
		Don't have an account! <a href="#" onClick=""> Sign Up Here </a>
	    </div>
            -->
        </div>
    </div>
    </div>
</div>

</body>
</html>
