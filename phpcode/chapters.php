<?php

	
		
		require "connectdb.php";
		
		
		
	
		$book = $_POST["book"];
		//$book = "Analys2";
		
		
		if($connect){
			

			
			
							
				$sqlCheckBook= "Select `nrOfChapters` FROM `chapter` WHERE `bookID` = '$book'";
                $query = mysqli_query($connect,$sqlCheckBook);
                if ($query->num_rows > 0) {
                    // output data of each row
                    header("Content-Type: text/plain");
                    while($row = $query->fetch_assoc()) {          
                        echo $row["nrOfChapters"];
					
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