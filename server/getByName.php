<?php

if(isset($_GET['key']) && $_GET['key'] = 'dsproj'){
	
	if(isset($_GET['time']) && isset($_GET['day']) && isset($_GET['room'])){
	    $time = $_GET['time'];
	    $day = $_GET['day'];
	    $room = $_GET['room'];
	    
	    $dsn = getenv('MYSQL_DSN');
	    $user = getenv('MYSQL_USER');
	    $password = getenv('MYSQL_PASSWORD');
	
		if(!isset($dsn, $user) || false === $password){
		        echo "Failed to connect to database";
		}
		
		$db = new PDO($dsn, $user, $password);
	
		$statement = $db->prepare("SELECT * 
								   FROM " . ucwords($day) . 
								" WHERE endTime >= " . $time 
								. " AND roomName LIKE '" . $room . "%' ;");
		$statement->execute();
		$notEmpty = $statement->fetchAll();
		
		
		foreach($notEmpty as $data){
			echo $data["roomName"].","
						.$data["startTime"]."<br>";
		}
	}
}