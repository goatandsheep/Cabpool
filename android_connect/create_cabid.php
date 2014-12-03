<?php

/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['user_name']) &&isset($_POST['cabid'])  ) {

    // $pid = $_POST['pid'];
    // $name = $_POST['name'];
    // $price = $_POST['price'];
    // $description = $_POST['description'];


    $user_name = $_POST['user_name'];
    $cabid = $_POST['cabid'];



    // include db connect class
    require_once dirname(__FILE__) . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql update row with matched pid
    $result = mysql_query("UPDATE user_info SET  cabid = '$cabid'  WHERE user_name = '$user_name'");

    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "cabid successfully updated.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        $response["message"] = "cabid fcking failed.";
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>