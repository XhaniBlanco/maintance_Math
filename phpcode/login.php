<?php

	
		
		require "connectdb.php";
		
		
	
		
		$username = $_POST["username"];
		$password = $_POST["password"];
		
		
		
		
		
		if($connect){
			

			
				$sqlCheckUsername = "Select * FROM `user` WHERE `username` = '$username'";
				$usernameQuery = mysqli_query($connect,$sqlCheckUsername);
				
				
				if(mysqli_num_rows($usernameQuery) > 0){
					$sqlLogin = "Select * FROM `user` WHERE `username` LIKE '$username' AND `password` LIKE '$password'";
					$sqlLoginQuery = mysqli_query($connect,$sqlLogin);
					if(mysqli_num_rows($sqlLoginQuery) > 0){
						echo "Login Success";
					}
					else{
						echo "Wrong password";
					}
				}
				else{
					echo "This username is not registered";
					
				}
		
			
		}
		else{
			echo "Connection Error";
		}
		
	
?>