<?php

if(isset($_GET['key']) && $_GET['key'] = 'dsproj'){
	
	if(isset($_GET['review']) && isset($_GET['room'])){
	    $review = $_GET['review'];
	    $room = $_GET['room'];
	    
	    echo $review . "<br>";
	    $dsn = getenv('MYSQL_DSN');
	    $user = getenv('MYSQL_USER');
	    $password = getenv('MYSQL_PASSWORD');
	
		if(!isset($dsn, $user) || false === $password){
		        echo "Failed to connect to database";
		}
		
		$db = new PDO($dsn, $user, $password);
	
		$statement = $db->prepare("INSERT INTO roomReviews (roomName, review) values (?, ?)");
		$statement->execute(array($room, $review));

	}
}