<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<?php
require_once('config.php');
?>

<?php
// Inialize session
session_start();

// Check, if username session is NOT set then this page will jump to login page
if (!isset($_COOKIE['UserLogin'])) {
    header('Location: index.php');
}
?>

<?php
if (connectDB()) {
    $queryProfile = "SELECT* FROM user WHERE Username='" . $_SESSION['username'] . "'";
    $result = mysql_query($queryProfile);
    $data = mysql_fetch_array($result);

    $uname = $data['Username'];
    $name = $data['Fullname'];
    $bday = $data['DateOfBirth'];
    $ava = $data['Avatar'];
    $email = $data['Email'];
}
?>

<header>
    <a href="dashboard.php" title="Home"><img id="logo-small" src="img/Logo_Small2.png" alt="" /></a>
    <div id="dashboard"><a title="Go to Dashboard" href="dashboard.php">Dashboard</a></div>

    <div id="profile"><a title="Go to Profile" href="profile.php"><?php echo $uname ?></a></div>
    <div id="logout"><a title="Log out from here" href="logout.php">Log Out</a></div>
    <form id="search">
        <input type="text" name="Search" id="box">
        <select>
            <option> All </option>   
            <option> Category </option>
            <option> Task </option>
            <option> Username </option>
        </select>
        <input type="submit" value="Search">
    </form>
    <img id="smallava" src="<?php echo $ava ?>" />
</header>