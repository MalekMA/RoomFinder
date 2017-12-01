<?php

if(isset($_GET['key']) && $_GET['key'] = 'dsproj'){
	
	if(isset($_GET['wifi']) && isset($_GET['sound']) && isset($_GET['seat']) && isset($_GET['overall']) && isset($_GET['total']) && isset($_GET['room'])){
	    $wifi = $_GET['wifi'];
	    $sound = $_GET['sound'];
	    $seat = $_GET['seat'];
	    $overall = $_GET['overall'];
	    $total = $_GET['total'];
	    $room = $_GET['room'];
	    
	    echo $review . "<br>";
	    $dsn = getenv('MYSQL_DSN');
	    $user = getenv('MYSQL_USER');
	    $password = getenv('MYSQL_PASSWORD');
	
		if(!isset($dsn, $user) || false === $password){
		        echo "Failed to connect to database";
		}
		
		$db = new PDO($dsn, $user, $password);
	
		$statement = $db->prepare("UPDATE roomInfo SET wifi = ?, sound = ?, seat = ?, overall = ?, totalRated = ? WHERE roomName LIKE ?");
		$statement->execute(array($wifi, $sound, $seat, $overall, $total, $room));

	}
}