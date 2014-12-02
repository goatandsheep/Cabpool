<?php

/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['user_name']) && isset($_POST['first_name']) && isset($_POST['last_name'])
     && isset($_POST['emailaddress'])    && isset($_POST['userpassword'])  ) {

    // $pid = $_POST['pid'];
    // $name = $_POST['name'];
    // $price = $_POST['price'];
    // $description = $_POST['description'];


    $user_name = $_POST['user_name'];
    $first_name =  $_POST['first_name'];
    $last_name =  $_POST['last_name'];
    $emailaddress =  $_POST['emailaddress'];
    $userpassword =  $_POST['userpassword'];



    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql update row with matched pid
    $result = mysql_query("UPDATE user_info SET  first_name = '$first_name',

             last_name = '$last_name', userpassword = '$userpassword', emailaddress = '$emailaddress'
              WHERE user_name = '$user_name'");

    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "user_info successfully updated.";

        // echoing JSON response
        echo json_encode($response);
    } else {

    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>