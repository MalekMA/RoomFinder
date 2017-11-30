<?php

if(isset($_GET['key']) && $_GET['key'] = 'dsproj'){
	
	if(isset($_GET['time']) && isset($_GET['day'])){
	    $time = $_GET['time'];
	    $day = $_GET['day'];
	    
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
		
		$output = array();
		
		foreach($allRooms as $data){
			if($data["roomName"] == "null"){
				
			}
			else{
				echo $data["roomName"].","
						.$data["lat"].","
						.$data["lon"].","
						.$data["wifi"].","
						.$data["sound"].","
						.$data["seat"].","
						.$data["overall"].","
						.$data["totalRated"]."<br>";
			}
		}
	}
}