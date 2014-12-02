<?php

/*
 * Following code will get single user_info details
 * A user_info is identified by user_info id (id)
 */

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_POST["user_name"]) && isset($_POST["userpassword"])) {
    $user_name = $_POST['user_name'];
    $userpassword = $_POST['userpassword'];

    // get a user_info from user_infos table
    $result = mysql_query("SELECT * FROM user_info WHERE user_name = '$user_name' AND userpassword = '$userpassword'");

    echo json_encode($result);

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $user_info = array();
            $user_info["id"] = $result["id"];
            $user_info["user_name"] = $result["user_name"];
            $user_info["userpassword"] = $result["userpassword"];
            $user_info["first_name"] = $result["first_name"];
            $user_info["last_name"] = $result["last_name"];
            $user_info["emailaddress"] = $result["emailaddress"];
            // success
            $response["success"] = 1;

            // user node
            $response["user_info"] = array();

            array_push($response["user_info"], $user_info);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no user_info found
            $response["success"] = 0;
            $response["message"] = "No user_info found 1";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no user_info found
        $response["success"] = 0;
        $response["message"] = "No user_info found 2";

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>