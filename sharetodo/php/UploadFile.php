<?php
    session_start();
?>
<html>
    <head>
        <meta http-equiv="REFRESH" content="0;url=../Profil.php"/>
        <?php
            echo "REDIRECTING ...\n";
            
            // define a constant for the maximum upload size
            define ('MAX_FILE_SIZE', 1024 * 10000000); //batas maksimum 50kB
            
              define('UPLOAD_DIR', 'C:/xampp/htdocs/progin/tubes/server/');
              $file = $_SESSION['username'].".png"; //apakah file dengan ekstensi lain bisa di-convert ke tipe ini?
              
              // create an array of permitted MIME types
              $permitted = array('image/gif', 'image/jpeg', 'image/pjpeg', 'image/jpg',
            'image/png');
              
              // upload if file is OK
              if (in_array($_FILES['image']['type'], $permitted)
                  && $_FILES['image']['size'] > 0 
                  && $_FILES['image']['size'] <= MAX_FILE_SIZE) {
                switch($_FILES['image']['error']) {
                  case 0:
                        echo "Upload: " . $_FILES["image"]["name"] . "<br>";
                        echo "Type: " . $_FILES["image"]["type"] . "<br>";
                        echo "Size: " . ($_FILES["image"]["size"] / 1024) . " kB<br>";
                        echo "Temp file: " . $_FILES["image"]["tmp_name"] . "<br>";
                        
                      // move the file to the upload folder and rename it
                      $success = move_uploaded_file($_FILES['image']['tmp_name'], UPLOAD_DIR . $file);
                    if ($success) {
                      $result = "$file uploaded successfully.";
                    } else {
                      $result = "Error uploading $file. Please try again.";
                    }
                    break;
                  case 3:
                  case 6:
                  case 7:
                  case 8:
                    $result = "Error uploading $file. Please try again.";
                    break;
                  case 4:
                    $result = "You didn't select a file to be uploaded.";
                }
              } else {
                $result = "$file is either too big or not an image.";
              }
              
            //create connection
            $con = mysqli_connect("127.0.0.1","root","root","distributedAgenda");
        
            //check the connection
            if (mysqli_connect_errno($con)) {
                echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
            }else {
                $curUser = $_SESSION['username'];
                $result = mysqli_query($con,"UPDATE user SET avatar='$file' WHERE username='$curUser'");
            }
        ?>
    </head>
    <body>
    </body>
</html>