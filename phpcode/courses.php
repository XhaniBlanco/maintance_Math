<?php

	
		
		require "connectdb.php";
		
		
		
	
		$subject = $_POST["subject"];
		//$subject = "Math";
		
		
		
		
		if($connect){
			

			
				
					
					$sqlCheckCourse= "Select `courseID`, `courseArea` FROM `course` WHERE `courseArea` = '$subject'";
                $courseQuery = mysqli_query($connect,$sqlCheckCourse);
                if ($courseQuery->num_rows > 0) {
                    // output data of each row
                    header("Content-Type: text/plain");
                    while($row = $courseQuery->fetch_assoc()) {
                        echo  $row["courseID"].",";
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