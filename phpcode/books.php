<?php

	
		
		require "connectdb.php";
		
		
		
	
		$course = $_POST["course"];
		//$course = "Analys";
		
		
		if($connect){
			

			
			
							
				$sqlCheckBook= "Select `bookID` FROM `book` WHERE `courseID` = '$course'";
                $query = mysqli_query($connect,$sqlCheckBook);
                if ($query->num_rows > 0) {
                    // output data of each row
                    header("Content-Type: text/plain");
                    while($row = $query->fetch_assoc()) {
                        echo $row["bookID"].",";
					}
				}
					
			
				
				else {
				echo "0 results";
				}
				
			
		}
		else{
			echo "Connection Error";
		}
		
	
?>