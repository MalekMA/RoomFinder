<?php

if(isset($_GET['key']) && $_GET['key'] = 'dsproj'){
	
	if(isset($_GET['time']) && isset($_GET['day'])){
	    $time = $_GET['time'];
	    $day = $_GET['day'];
	    echo $time . " " . $day . "<br>";
	    
	    $dsn = getenv('MYSQL_DSN');
	    $user = getenv('MYSQL_USER');
	    $password = getenv('MYSQL_PASSWORD');
	
		if(!isset($dsn, $user) || false === $password){
		        echo "Failed to connect to database";
		}
		
		$db = new PDO($dsn, $user, $password);
	
		$statement = $db->prepare("SELECT roomName 
								   FROM " . ucwords($day) . 
								" WHERE startTime <= " . $time . " AND endTime >= " . $time);
		$statement->execute();
		$notEmpty = $statement->fetchAll();
		
		echo "Not empty <br>";
		
		foreach($notEmpty as $data){
			echo $data["roomName"]."<br>";
		}
		
		$statement2 = $db->prepare("SELECT * FROM roomInfo");
		$statement2->execute();
		$allRooms = $statement2->fetchAll();
		
		foreach($notEmpty as $nE){
			$i = 0;
			foreach($allRooms as $all){
				if($nE["roomName"] == $all["roomName"]){
					$allRooms[$i]["roomName"] = "null";
					$i = $i + 1;
				}
				else {
					$i = $i + 1;
				}
			}
		}
		echo "Empty <br>";
		foreach($allRooms as $data){
			echo $data["roomName"]."<br>";
		}
	}
}