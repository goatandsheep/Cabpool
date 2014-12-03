<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// include db connect class
require_once dirname(__FILE__) . '/db_connect.php';

// check for required fields

    // $name = $_POST['name'];
    // $price = $_POST['price'];
    // $description = $_POST['description'];
    $required = array('user_name','userpassword','first_name','last_name','emailaddress');
    
    $error = false;

    foreach($required as $field){
        if(empty($_POST[$field])){
            $error = true;
        }
    }

    if($error){
        // required field is missing
        $response["success"] = 0;
        $response["message"] = "Required field(s) is missing";

        // echoing JSON response
        echo json_encode($response);

    }else{
        $user_name = $_POST['user_name'];
        $userpassword = $_POST['userpassword'];
        $first_name = $_POST['first_name'];
        $last_name = $_POST['last_name'];
        $emailaddress = $_POST['emailaddress'];

        // connecting to db
        $db = new DB_CONNECT();


        $check = mysql_query("SELECT * FROM user_info WHERE user_name = '$user_name'");

        if(mysql_fetch_row($check)==0){

        // mysql inserting a new row
        // $result = mysql_query("INSERT INTO products(name, price, description) VALUES('$name', '$price', '$description')");

        $result = mysql_query("INSERT INTO user_info(user_name, userpassword, first_name, last_name, emailaddress) VALUES('$user_name', '$userpassword', '$first_name', '$last_name', '$emailaddress')");


        // check if row inserted or not
        if ($result) {
            // successfully inserted into database
            $response["success"] = 1;
            $response["message"] = "user info successfully created.";

            // echoing JSON response
            echo json_encode($response);
        } else {
            // failed to insert row
            $response["success"] = 0;
            $response["message"] = "Oops! An error occurred.";

            // echoing JSON response
            echo json_encode($response);
        }
    }
    else{
        $response["success"] = -1;
        $response["message"] = "Username taken.";

            // echoing JSON response
            echo json_encode($response);
    }
}
?>