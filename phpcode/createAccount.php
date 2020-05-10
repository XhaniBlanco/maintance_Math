<?php

	
		
		require "connectdb.php";
		
		
	
		
		$username = $_POST["username"];
		$firstname = $_POST["firstname"];
		$lastname = $_POST["lastname"];
		$email = $_POST["email"];
		$education = "indstriell ekonomi";
		$password = $_POST["password"];
		
		
		
		
		$isValidEmail = filter_var($email,FILTER_VALIDATE_EMAIL);

		if($isValidEmail === false){
			echo "This Email is not valid";
		}
		else{
			$sqlCheckUsername = "Select * FROM `user` WHERE `username` = '$username'";
			$usernameQuery = mysqli_query($connect,$sqlCheckUsername);
			
			$sqlCheckEmail = "Select * FROM `user` WHERE `email` = '$email'";
			$emailQuery = mysqli_query($connect,$sqlCheckEmail);
			
			if(mysqli_num_rows($usernameQuery) > 0){
				echo "Username already exist, try another one";
			}
			else if(mysqli_num_rows($emailQuery ) > 0){
				echo "Email already exist, try another one";
			}
		
			else{
				$sql_createAccount = "INSERT INTO user (username,firstname,lastname,email,education,password) VALUES('$username','$firstname','$lastname','$email','$education','$password')";
				
				if(mysqli_query($connect,$sql_createAccount)){
				echo "Successfully created an account";
				}
				else{
				echo "Failed to create account";
				}
			}
		
		}
		
	
?>